package algorithm.classic.java.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceDemo {
  
  public static class TestThread extends Thread {
    
    public void run() {  
      System.out.println("Child" + this.getName() + " starts.");
      
      try {  
        Thread.sleep(new Random().nextInt(13) * 100);
      } catch (InterruptedException e) {
        e.printStackTrace();  
      }
      
      System.out.println("Child" + this.getName() + " ends.");
    }
  }
  
  public static void main(String[] args) {
    long start = System.currentTimeMillis();
    System.out.println("MainThread starts.");
    
    int threadNum = 5;
    ExecutorService executor = Executors.newFixedThreadPool(threadNum);
    
    for(int i = 0; i < threadNum; i++) {
      Thread thread = new TestThread();
      executor.execute(thread);
    }
    executor.shutdown();
    
    try {
      while (!executor.awaitTermination(10, TimeUnit.SECONDS));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    long end = System.currentTimeMillis();
    System.out.println("MainThread ends.");
    System.out.println("MainTread spent: " + (end - start));
  }
  
}
