package thread;

public class MyThreadPrinter implements Runnable {     
  
  private String name;     
  private String prev;     
  private String self;     
  
  private MyThreadPrinter(String name, String prev, String self) {
    this.name = name;
    this.prev = prev;
    this.self = self;
  }
  
  @Override
  public void run() {
    int count = 1;
    while (count < 2) {
      System.out.println("Thread " + this.name +  " Begins loop " + count + ".");
      synchronized (prev) {
        System.out.println("Thread " + this.name +  " Gets: " + prev + ".");
        synchronized (self) {
          System.out.println("Thread " + this.name +  " Gets: " + self + ".");

          self.notify();
          System.out.println("Thread " + this.name +  " is going to release and wait: " + self + ".");
        }

        try {
          System.out.println("Thread " + this.name +  " is going to release and wait: " + prev + ".");
          prev.wait();
//          prev.wait(10);
          System.out.println("Thread " + this.name +  " Re-gets: " + prev + ".");
        } catch (InterruptedException e) {     
          e.printStackTrace();
        }     
      }
      count++;
      System.out.println("Thread " + this.name +  " finishes loop " + count + ".");
    }  
  }
  
  public static void main(String[] args) throws Exception {
    String lockA = "LockA";
    String lockB = "LockB";
    String lockC = "LockC";
    MyThreadPrinter pa = new MyThreadPrinter("A", lockC, lockA);
    MyThreadPrinter pb = new MyThreadPrinter("B", lockA, lockB);
    MyThreadPrinter pc = new MyThreadPrinter("C", lockB, lockC);
    
    
    new Thread(pa).start();
    Thread.sleep(1);
    new Thread(pb).start();
    Thread.sleep(1);
    new Thread(pc).start();
    Thread.sleep(1);
  }
  
}    
