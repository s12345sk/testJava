/**   
* @Title: CountDownLatchTest1.java 
* @Package review 
* @Description:  all parts over then over
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-9 下午7:24:56 
* @version V1.0   
*/
package review;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest1 {
	
	public static void main(String[] args){
		ExecutorService es = Executors.newFixedThreadPool(3);
		CountDownLatch cdl = new CountDownLatch(10);
		for(int i=0;i<10;i++)
			es.execute(new Parts(i,cdl));
		es.shutdown();
		try {
			cdl.await();
			System.out.println("一切终结于此！");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

class Parts extends Thread{
	private int id;
	private CountDownLatch cdl;
	public Parts(int id,CountDownLatch cdl){
		this.id = id;
		this.cdl = cdl;
	}
	public void run(){
		doMyThings();
	}
	public void doMyThings(){
		System.out.println(id+" :已经开始任务！");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cdl.countDown();
		System.out.println(id+" :已经完成任务！");
	}
}
