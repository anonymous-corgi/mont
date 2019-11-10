package algorithm.classic.java.thread;

import java.lang.ref.WeakReference;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class GoogleMultiThreadTaskAssign {

    private static class Computer {

        public long total(int fileId, int machineId) {
            Random r = new Random();

            try {
                Thread.sleep(r.nextInt(13) * 100);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return r.nextInt(3) != 1 ? r.nextInt(13) * 13 : -1;
        }
    }

    private static class TaskThread extends Thread {

        private interface Callback {
            void onSuccess(int fileId, long res);

            void onFail(int fileId, int machineId);
        }

        private static void generateThread(Computer computer,
                                           int fileId, int machineId,
                                           Callback callback) {
            Thread task = new TaskThread(computer, fileId, machineId, callback);
            task.start();
        }

        private final int fileId;
        private final int machineId;
        private WeakReference<Computer> computer;
        private Callback callback;

        private TaskThread(Computer computer, int fileId, int machineId, Callback callback) {
            this.computer = new WeakReference<>(computer);
            this.fileId = fileId;
            this.machineId = machineId;
            this.callback = callback;
        }

        public void run() {
            System.out.println("Task " + fileId + " has started");
            long res = -1;
            try {
                res = computer.get().total(fileId, machineId);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (res != -1) {
                if (callback != null) {
                    callback.onSuccess(fileId, res);
                }
            } else {
                if (callback != null) {
                    callback.onFail(fileId, machineId);
                }
            }
        }

    }

    private static long sum = 0;
    private static Computer api = new Computer();
    private static TaskThread.Callback callback;

    public static void main(String[] args) {
        System.out.println("The calculation Start.");
        int[] fileIds = {1, 2, 3, 4, 5};
        int[] machineIds = {1, 2, 3, 4, 5};
        CountDownLatch countDownLatch = new CountDownLatch(fileIds.length);

        callback = new TaskThread.Callback() {

            @Override
            synchronized public void onSuccess(int fileId, long res) {
                sum += res;
                countDownLatch.countDown();
                System.out.println("The result of " + fileId + " is: " + res);
            }

            @Override
            public void onFail(int fileId, int machineId) {
                System.out.println("The calculation of " + fileId + " failed.");
                TaskThread.generateThread(api, fileId, machineId, callback);
            }
        };

        for (int i = 0; i < fileIds.length; i++) {
            TaskThread.generateThread(api, fileIds[i], machineIds[i], callback);
        }

        try {
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("The calculation result: " + sum);
    }
}
