
-- 释放redis可重入锁脚本
local key = KEYS[1]
local threadId = ARGV[1]
local releaseTime = ARGV[2]


-- 判断锁是否是自己的，不是直接返回
if(redis.call('hexists', key, threadId) == 0) then
    return  nil;
end

-- 是自己的，锁上计数-1
local count = redis.call('hincrby', key, threadId, -1)
-- 还有其它人在使用，重置锁时间
if(count>0) then
    redis.call('expire', key, releaseTime)
    return nil;
else
    -- 已无人使用，直接释放
    redis.call('del', key)
    return nil
end
