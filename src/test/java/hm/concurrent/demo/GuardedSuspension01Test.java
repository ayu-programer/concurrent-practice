package hm.concurrent.demo;

import hm.concurrent.demo.messagedemo.ClientThread;
import hm.concurrent.demo.messagedemo.Request;
import hm.concurrent.demo.messagedemo.RequestQueue;
import hm.concurrent.demo.messagedemo.ServerThread;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class GuardedSuspension01Test {

    @Test
    public void  testGuardedSuspension() throws InterruptedException {

        final GuardedSuspension01 guardedSuspension01 = new GuardedSuspension01();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(() -> guardedSuspension01.get());

        Thread.sleep(2000);

        executorService.execute(() -> guardedSuspension01.put(20));

        executorService.shutdown();
        executorService.awaitTermination(30, TimeUnit.SECONDS);
    }


    @Test
    public void testMessageQueue(){
        RequestQueue requestQueue = new RequestQueue();
        Request request = new Request("Alice");
        Request request1 = new Request("tom");
        Request request2 = new Request("cat");
        requestQueue.putRequest(request);
        requestQueue.putRequest(request1);
        requestQueue.putRequest(request2);

        new ClientThread(requestQueue, "Alice", 3141592L).start();
        new ClientThread(requestQueue, "tom", 31415592L).start();
        new ClientThread(requestQueue, "cat", 31492L).start();
        new ServerThread(requestQueue, "boy", 6535897L).start();
    }
}
