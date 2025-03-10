package limiting;

public class TokenBucketRateLimiter extends MyRateLimiter {
    /**
     * 令牌桶的容量「限流器允许的最大突发流量」
     */
    private final long capacity;
    /**
     * 令牌发放速率
     */
    private final long generatedPerSeconds;
    /**
     * 最后一个令牌发放的时间
     */
    long lastTokenTime = System.currentTimeMillis();
    /**
     * 当前令牌数量
     */
    private long currentTokens;

    public TokenBucketRateLimiter(long generatedPerSeconds, int capacity) {
        this.generatedPerSeconds = generatedPerSeconds;
        this.capacity = capacity;
    }

    /**
     * 尝试获取令牌
     *
     * @return true表示获取到令牌，放行；否则为限流
     */
    @Override
    public synchronized boolean tryAcquire() {
        /*
          计算令牌当前数量
          请求时间在最后令牌是产生时间相差大于等于额1s（为啥时1s？因为生成令牌的最小时间单位时s），则
          1. 重新计算令牌桶中的令牌数
          2. 将最后一个令牌发放时间重置为当前时间
         */

        long now = lastTokenTime = System.currentTimeMillis();
        if(now - lastTokenTime >= 1000){
            long newPermits = (now - lastTokenTime) / 1000 * generatedPerSeconds;
            currentTokens = Math.min(newPermits + currentTokens, capacity);
        }

        if(currentTokens > 0){
            currentTokens--;
            return true;
        }
        return false;
    }
}