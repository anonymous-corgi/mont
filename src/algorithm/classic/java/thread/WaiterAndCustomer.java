package algorithm.classic.java.thread;

public class WaiterAndCustomer {

    public static class Waiter {

        private String dishes = null;

        public synchronized String getDishes() {
            System.out.println(Thread.currentThread().getName() + " has the waiter.");
            while (this.dishes == null) {
                try {
                    System.out.println(Thread.currentThread().getName() + " failed to get dishes and lets the waiter go.");
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            String d = this.dishes;
            System.out.println(Thread.currentThread().getName() + " takes dishes: " + this.dishes);
            this.dishes = null;
            notifyAll();
            System.out.println("The waiter is free to serve customers and chefs.");
            return d;
        }

        public synchronized void setDishes(String dishes) {
            System.out.println(Thread.currentThread().getName() + " has the waiter.");

            while (this.dishes != null) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is done, but the waiter has already taken a dish.");
                    System.out.println(Thread.currentThread().getName() + " lets the waiter go.");
                    wait();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

            this.dishes = dishes;
            System.out.println(Thread.currentThread().getName() + " gave waiter: " + this.dishes);
            notifyAll();
            System.out.println("The waiter is free to serve customers and chefs.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Waiter waiter = new Waiter();

        for (int i = 1; i <= 3; i++) {
            Thread customer = new Thread("Customer" + i) {
                public void run() {
                    waiter.getDishes();
                }
            };
            customer.start();
        }

        Thread.sleep(100);

        for (int i = 1; i <= 3; i++) {
            Thread chef = new Thread("Chef" + i) {
                public void run() {
                    String dishes = "[Kung Pao Chicken]";
                    waiter.setDishes(dishes);
                }
            };
            chef.start();
        }
    }
}
