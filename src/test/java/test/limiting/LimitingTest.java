package test.limiting;


import junit.framework.TestCase;
import limiting.LeakyBucketRateLimiter;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LimitingTest extends TestCase {
    public void testLeakyBucketRateLimiter() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        ExecutorService singleThread = Executors.newSingleThreadExecutor();

        LeakyBucketRateLimiter leakyBucketRateLimiter = new LeakyBucketRateLimiter(2, 20);

        Queue<Integer> queue = new LinkedList<>();

        singleThread.execute(() -> {
            int count = 0;
            while(true){
                count++;
                boolean flag = leakyBucketRateLimiter.tryAcquire();
                if(flag){
                    queue.offer(count);
                    System.out.println(count + "--------流量被放行--------");
                }else{
                    System.out.println(count + "流量被限制");
                }
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if(!queue.isEmpty()) {
                System.out.println(queue.poll() + "被处理");
            }
        },0,500, TimeUnit.MILLISECONDS);


        // 保证主线程不会退出
        while (true) {
            Thread.sleep(10000);
        }
    }
}
