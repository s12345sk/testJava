package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
	public static void main(String[] args){
		 CountDownLatch star = new CountDownLatch(1);
		 CountDownLatch end = new CountDownLatch(10);
		 ExecutorService es = Executors.newFixedThreadPool(5);
		 for(int i=0;i<10;i++)
			 es.execute(new Worker(i,star,end));
		 System.out.println("now can start !");
		 try {
			Thread.sleep(2000);
			star.countDown();
			System.out.println("waiting all over!");
			end.await();
			System.out.println("it is finally all over!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}

}
class Worker implements Runnable{
	private int id;
	private CountDownLatch start;
	private CountDownLatch end;
	public Worker(int id,CountDownLatch start,CountDownLatch end){
		this.id = id;
		this.start = start;
		this.end = end;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("worker "+id+" arrived!");
		try {
			start.await();
			System.out.println("worker "+id+" working!");
			Thread.sleep(8000);
			end.countDown();
			System.out.println("worker "+id+" end!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
