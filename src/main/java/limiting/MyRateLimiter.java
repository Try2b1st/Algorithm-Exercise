package limiting;

public abstract class MyRateLimiter {
    public abstract boolean tryAcquire();
}
