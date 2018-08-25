package thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {
  
  public static class TestThread extends Thread {
    
    public void run() {  
      System.out.println("Child" + this.getName() + " starts.");  
      try {  
        // 子线程休眠五秒  
        Thread.sleep(new Random().nextInt(13) * 100);  
      }  
      catch (InterruptedException e) {  
        e.printStackTrace();  
      }
      
      System.out.println("Child" + this.getName() + " ends.");
    }
  }

  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    
    // 创建一个同时允许两个线程并发执行的线程池
    ExecutorService executor = Executors.newFixedThreadPool(2);
    for(int i = 0; i < 5; i++) {
        Thread thread = new TestThread();
        executor.execute(thread);
    }
    executor.shutdown();
    
    try {
        // awaitTermination返回false即超时会继续循环，返回true即线程池中的线程执行完成主线程跳出循环往下执行，每隔10秒循环一次
        while (!executor.awaitTermination(10, TimeUnit.SECONDS));
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    
    long end = System.currentTimeMillis();
    System.out.println("MainTread spent：" + (end - start));
  }

}
