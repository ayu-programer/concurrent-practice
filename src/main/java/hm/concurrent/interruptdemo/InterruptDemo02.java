package hm.concurrent.interruptdemo;


/**
 * Interrupt 案例02
 */
public class InterruptDemo02 {
    public static void main(String[] args) throws InterruptedException {


        MyThread thread = new MyThread();

        thread.start();
        System.out.println("执行位置02---------");
        Thread.sleep(1000);
        thread.setShouldRun(false);

        // 这里的interrupt的作用其实就是打断下面MyThread类中run方法的30秒的休眠，
        // 当打断sleep方法的时候其实就会报错
        // 然后由于上面更改了标志位为false
        // 所以重新在执行while循环的时候，其实就会直接结束
        thread.interrupt();
    }

    private static class MyThread extends Thread {
        private Boolean shouldRun = true;

        @Override
        public void run() {

            while (shouldRun) {
                try {
                    System.out.println("线程01在执行-----------");
                    Thread.sleep(10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


        private void setShouldRun(Boolean shouldRun) {
            this.shouldRun = shouldRun;
        }
    }

}
