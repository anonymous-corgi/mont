package thread;

public class JoinDemo {
  
  public static class CustomThread1 extends Thread {  
    public CustomThread1() {  
      super("[CustomThread1] Thread");  
    };  
    public void run() {  
      String threadName = Thread.currentThread().getName();  
      System.out.println(threadName + " start.");  
      try {  
        for (int i = 0; i < 5; i++) {  
          System.out.println(threadName + " loop at " + i);  
          Thread.sleep(1000);  
        }  
        System.out.println(threadName + " end.");  
      } catch (Exception e) {  
        System.out.println("Exception from " + threadName + ".run");  
      }  
    }  
  }  
  
  public static class CustomThread2 extends Thread {  
    CustomThread1 t1;  
    public CustomThread2(CustomThread1 t1) {  
      super("[CustomThread2] Thread");  
      this.t1 = t1;  
    }  
    public void run() {  
      String threadName = Thread.currentThread().getName();  
      System.out.println(threadName + " start.");  
      try {  
        t1.join();  
        System.out.println(threadName + " end.");  
      } catch (Exception e) {  
        System.out.println("Exception from " + threadName + ".run");  
      }  
    }  
  }  

  public static void main(String[] args) {
    String mainThreadName = Thread.currentThread().getName();  
    System.out.println(mainThreadName + " start.");  
    CustomThread1 t1 = new CustomThread1();  
    CustomThread2 t2 = new CustomThread2(t1);  
    try {  
        t1.start();  
        Thread.sleep(2000);  
        t2.start();  
        t1.join();//�ڴ��a2�����̎עጵ�  
    } catch (Exception e) {  
        System.out.println("Exception from main");  
    }  
    System.out.println(mainThreadName + " end!"); 
  }

}