package Drafts;

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
        while (count < 4) {
        	System.out.println("Thread " + this.name +  " Begins.");
            synchronized (prev) {
            	System.out.println("Thread " + this.name +  " Gets: " + prev);
                synchronized (self) {     
                	System.out.println("Thread " + this.name +  " Gets: " + self);
                	System.out.println("Thread " + this.name + " Times: " + count);
                    count++; 
//                    self.notify();
//                    System.out.println("Thread " + this.name +  " Releases: " + self);
                }     
                try {
                    System.out.println("Thread " + this.name +  " is goint to release and wait: " + prev);
                    prev.wait(); 
//                    prev.wait(10);
                    System.out.println("Thread " + this.name +  " Releases: " + prev);
                } catch (InterruptedException e) {     
                    e.printStackTrace();     
                }     
            }     
            System.out.println("Thread " + this.name +  " is goint to finish a cycle.");
        }     
    }
    
    public static void main(String[] args) throws Exception {
    	String a = "LockA";
    	String b = "LockB";
    	String c = "LockC";  
        MyThreadPrinter pa = new MyThreadPrinter("A", c, a);     
        MyThreadPrinter pb = new MyThreadPrinter("B", a, b);     
        MyThreadPrinter pc = new MyThreadPrinter("C", b, c);     
             
             
        new Thread(pa).start();  
        Thread.sleep(1);  //确保按顺序A、B、C执行  
        new Thread(pb).start();  
        Thread.sleep(1);    
        new Thread(pc).start();     
        Thread.sleep(1);    
        }     
}    
