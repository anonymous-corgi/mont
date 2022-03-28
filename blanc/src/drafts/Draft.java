package drafts;

import java.util.concurrent.Executors;

public class Draft {
    interface name {
        abstract void main();
    }
    private static final sun.misc.Unsafe UNSAFE;
    private static final long stateOffset;
    private static final long runnerOffset;
    private static final long waitersOffset;
    static {
        try {
            UNSAFE = sun.misc.Unsafe.getUnsafe();
            Class<?> k = Draft.class;
            stateOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("state"));
            runnerOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("runner"));
            waitersOffset = UNSAFE.objectFieldOffset
                    (k.getDeclaredField("waiters"));
        } catch (Exception e) {
            throw new Error(e);
        }
        String nam = "";
        nam += "!";
        float a = 0x123;
    }
  
  public static void main(String[] args) {
//    Draft instance = new Draft();
      Executors.newSingleThreadExecutor();
    System.out.println();
  }
}