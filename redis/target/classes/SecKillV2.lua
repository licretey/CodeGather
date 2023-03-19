-- 秒杀校验脚本

-- 优惠券id
local voucherId = ARGV[1]
local userId = ARGV[2]
local orderId = ARGV[3]


-- 库存key
local stockKey = 'seckill:stock:' .. voucherId
-- 订单key
local orderKey = 'seckill:order:' .. voucherId

-- 业务逻辑
if( tonumber( redis.call('get', stockKey) ) <= 0) then
    -- 库存不足
    return 1
end

-- sismember 判断是否下单
if( redis.call('sismember', orderKey, userId) == 1) then
    -- 存在，说明是重复复下单
    return 2
end

-- 扣减库存，计数-1
redis.call('incrby', stockKey, -1)
-- 下单
redis.call('sadd', stockKey, userId)
-- 发送消息
redis.call('xadd', 'stream.orders','*', 'userId', userId, 'voucherId', voucherId, 'orderId', orderId)
return 0