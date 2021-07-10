package hm.concurrent.interruptdemo;

/**
 * Interrupt案例1
 */
public class InterruptDemo01 {

    public static void main(String[] args) throws InterruptedException {

       Thread thread = new Thread(){

           @Override
           public void run() {
               while (!isInterrupted()){
                   System.out.println("线程1在执行---------------");
               }
           }
       };


       thread.start();

       Thread.sleep(1000);

       thread.interrupt();
    }
}
