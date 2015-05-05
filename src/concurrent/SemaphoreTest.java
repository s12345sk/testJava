/**   
* @Title: Semaphore.java 
* @Package concurrent 
* @Description: TODO(用一句话描述该文件做什么) 
* @author:kang.sun1@renren-inc.com   
* @date 2014-9-23 下午7:37:54 
* @version V1.0   
*/ 
package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author renren
 *
 */
public class SemaphoreTest extends Thread{
	
	private Semaphore printer;
	private int id;
	public SemaphoreTest(int id,Semaphore sh){
		this.id = id;
		this.printer = sh;
		
	}
	public void run(){
		if(printer.availablePermits()>0){
			System.out.println("user "+id+" has printer,happy ");
		}else{
			System.out.println("user "+id+" no printer,sad ");
		}
		try {
			printer.acquire();
			System.out.println("user "+id+" get printer,begin ");
			Thread.sleep(1000);
			printer.release();
			System.out.println("user "+id+" done printer,end ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService es = Executors.newCachedThreadPool();
//		ExecutorService es = Executors.newFixedThreadPool(5);
		Semaphore printer = new Semaphore(4);
		for(int i=0;i<10;i++)
		es.execute(new SemaphoreTest(i,printer));
		es.submit(new SemaphoreTest(1,printer));
		es.shutdown();
		printer.acquireUninterruptibly(2);
		printer.release(4);

	}

}
