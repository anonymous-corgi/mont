package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
  
  public static class TestThread extends Thread {
    
    private CountDownLatch countDownLatch;  
    
    public TestThread(CountDownLatch countDownLatch) {  
      this.countDownLatch = countDownLatch;  
    }  
    
    public void run() {  
      System.out.println("Child" + this.getName() + " starts.");  
      try {  
        // 子线程休眠五秒  
        Thread.sleep(new Random().nextInt(13) * 100);  
      }  
      catch (InterruptedException e) {  
        e.printStackTrace();  
      } finally {
        //!!!!!!!!!!!!!!!!!!!!!!!! in finally!      
      // 倒数器减1
        countDownLatch.countDown();
      }
      
      System.out.println("Child" + this.getName() + " ends.");

    }
  }

  public static void main(String[] args) {
    System.out.println("MainThread starts at: " + System.currentTimeMillis());
    
    // 创建一个初始值为5的倒数计数器
    CountDownLatch countDownLatch = new CountDownLatch(5);
    for(int i = 0; i < 5; i++) {
      Thread thread = new TestThread(countDownLatch);
      thread.start();
    }
    
    try {
      // 阻塞当前线程，直到倒数计数器倒数到0
      countDownLatch.await();
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    System.out.println("MainThread ends at: " + System.currentTimeMillis());
  }

}
