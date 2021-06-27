package hm.concurrent.demo;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 等待通知机制  示例代码
 */
public class GuardedSuspension01 {

    Lock lock = new ReentrantLock();
    //创建一个队列
    private final Queue<Integer> soureList;

    public GuardedSuspension01() {
        this.soureList = new LinkedBlockingQueue<Integer>();
    }


    public synchronized   Integer get(){

        while (soureList.isEmpty()){
            try {

                wait(); //如果队列为null 就等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return soureList.peek();
    }

    public synchronized void put(Integer e){
        soureList.add(e);
        notifyAll();  //通知，继续执行
    }

}
