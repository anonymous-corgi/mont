package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
  
  public static class TaskThread extends Thread {
    
    private CountDownLatch countDownLatch;
    
    public TaskThread(CountDownLatch countDownLatch) {
      this.countDownLatch = countDownLatch;  
    }  
    
    public void run() {
      System.out.println("Child" + this.getName() + " starts.");
      
      try {
        Thread.sleep(new Random().nextInt(13) * 100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        //!!!!!Run this code in finally!!!!!
        countDownLatch.countDown();
      }
      
      System.out.println("Child" + this.getName() + " ends.");
    }
  }
  
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    System.out.println("MainThread starts.");
    
    int threadNum = 5;
    CountDownLatch countDownLatch = new CountDownLatch(threadNum);
    
    for(int i = 0; i < threadNum; i++) {
      Thread thread = new TaskThread(countDownLatch);
      thread.start();
    }
    
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    long end = System.currentTimeMillis();
    System.out.println("MainThread ends.");
    System.out.println("MainTread spent: " + (end - start));
  }
  
}
