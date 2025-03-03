package com.collection;

import com.collection.logwire2.AtomicLock;
import com.collection.logwire2.Constants;
import org.mockito.internal.util.StringUtil;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;


/**
 * 53 位划分规则为（从高位到低位）：<br/>
 * 前 32 位：秒级时间戳（Snowflake 是毫秒）。总共可以支持跨度 136 年的秒数。若以 UTC 时间 2021 年 1 月 1 日 0 点 0 分 0 秒作为起点的话，可以支持到 2157 年。<br/>
 * 中间 6 位：机器 Id。最多可以有 64 台机器，但实际要再少一些。具体生成规则见下文 机器 Id 自适应选择 。<br/>
 * 后 15 位：自增序列号。单台机器每秒可生成 32768 个序列号。<br/>
 * 生产环境：自适应选择机器 Id，范围从 0 到 47，最多 48 台机器。<br/>
 * 测试环境：自适应选择机器 Id，范围从 48 到 51，最多 4 台机器。<br/>
 * 开发环境：自适应选择机器 Id，范围从 52 到 63，最多 12 台机器。<br/>
 *
 * @author: wangkai
 * @create: 2021-04-01 10:08
 **/

public class SnowFlakeTest {


    public static final SnowFlakeTest UNAVAILABLE = new SnowFlakeTest("unavailable");

    static final long BASE_SECOND = initBaseSecond();
    static final long CLOCK_BACK_LIMIT = 10;
    static final long AUTO_SEQ_MASK = 0b111_1111_1111_1111;
    static final long MACHINE_ID_MASK = 0b11_1111 << 15;

    static long initBaseSecond() {
        int year = 2021;
        int month = 1;
        int dayOfMonth = 1;
        int zero = 0;
        LocalDateTime localDateTime = LocalDateTime.of(year, month, dayOfMonth, zero, zero);
        return OffsetDateTime.of(localDateTime, ZoneOffset.UTC).toEpochSecond();
    }

    private volatile long machineId = -1;

    private long lastSecond;
    private long lastAuto;
    private long originalMachineId = -1;
    private int referenceCount;
    private int snowflakeVersion = -1;
    private final String schema;
    private final String rootZkPath;
    private final AtomicLock lock = new AtomicLock();

    public SnowFlakeTest(String schema) {
        this.schema = schema;
        this.rootZkPath = StringUtil.join(Constants.ZK_SNOW_FLAKE_ROOT_PATH, "/", schema);
    }

    private static long buildId(long second, long machineId, long auto) {
        return (second << 21) | machineId | auto;
    }

    public long next() {
        long machineId = this.machineId;
        if (machineId == -1) {
            throw new RuntimeException("Snow flake not available");
        }
        lock.lock();
        try {
            long lastSecond = this.lastSecond;
            // todo 原来为SecondLock
            long newSecond = LocalDateTime.now().getSecond() - BASE_SECOND;
            long newId;
            if (newSecond == lastSecond) {
                newId = buildId(newSecond, machineId, lastAuto);
                if (this.lastAuto == AUTO_SEQ_MASK) {
                    this.lastSecond++;
                    this.lastAuto = 0;
                } else {
                    this.lastAuto++;
                }
            } else if (newSecond > lastSecond) {
                newId = buildId(newSecond, machineId, 0);
                this.lastSecond = newSecond;
                this.lastAuto = 1;
            } else {
                if (lastSecond - newSecond > CLOCK_BACK_LIMIT) {
                    throw new RuntimeException(StringUtil.join(
                            "Too many clock calls back: ", String.valueOf(lastSecond - newSecond)
                    ));
                }
                newId = buildId(lastSecond, machineId, lastAuto);
                if (this.lastAuto == AUTO_SEQ_MASK) {
                    this.lastSecond++;
                    this.lastAuto = 0;
                } else {
                    this.lastAuto++;
                }
            }
            if (machineId == originalMachineId) {
                return newId;
            } else {
                throw new RuntimeException("Snow flake not available");
            }
        } finally {
            lock.unlock();
        }
    }

    public void disable() {
        this.machineId = -1;
    }

    public int getOriginalMachineId() {
        return (int) (originalMachineId >> 15);
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void incrementReferenceCount(int inc) {
        referenceCount += inc;
    }

    public void updateMachineId(long machineId, int snowflakeVersion) {
        this.originalMachineId = this.machineId = machineId << 15;
        this.snowflakeVersion = snowflakeVersion;
    }

    public int getSnowflakeVersion() {
        return snowflakeVersion;
    }

    public String getSchema() {
        return schema;
    }

    public String getRootZkPath() {
        return rootZkPath;
    }

    public void setSnowflakeVersion(int snowflakeVersion) {
        this.snowflakeVersion = snowflakeVersion;
    }

    public static long getMilliseconds(long id) {
        return (getRelativeSeconds(id) + BASE_SECOND) * 1000;
    }

    public static long getRelativeSeconds(long id) {
        return id >>> 21;
    }

    public static long getMachineId(long id) {
        return (id & MACHINE_ID_MASK) >>> 15;
    }

    public static long getAutoSequence(long id) {
        return id & AUTO_SEQ_MASK;
    }

    public static long buildBaseId(long second) {
        return (second - BASE_SECOND) << 21;
    }

    @Override
    public String toString() {
        return "SnowFlake(" + schema + ", refCnt: "
                + referenceCount + ", originalMachineId: " + (originalMachineId >> 15) + ")";
    }


}
