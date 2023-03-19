
-- 获取redis可重入锁脚本
local key = KEYS[1]
local threadId = ARGV[1]
local releaseTime = ARGV[2]

-- 判断是否存在
if(redis.call('exists', key) == 0) then
    -- 不存在，获取锁
    redis.call('hset', key, threadId, '1');
    redis.call('expire', key, releaseTime);
    return  1;
end

-- 锁已存在，判断是否是自己
if(redis.call('hexists', key, threadId) == 1) then
    -- 是自己的锁，计数+1
    redis.call('hincrby', key, threadId, '1');
    redis.call('expire', key, releaseTime);
    return  1;
end

-- 此处说明存在的锁不是自己的，返回失败
return 0
