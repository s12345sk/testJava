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

public class CountDownLatchTest2 {
	
	public static void main(String[] args) throws InterruptedException{
		ExecutorService es = Executors.newFixedThreadPool(3);
		CountDownLatch cdl = new CountDownLatch(10);
		CountDownLatch cdl2 = new CountDownLatch(1);
		for(int i=0;i<10;i++)
			es.execute(new Parts2(i,cdl,cdl2));
		System.out.println("当当当，运动员仪式开始！！");
//		Thread.sleep(1000);
		cdl2.countDown();
		System.out.println("当当当，运动员开始入场！");
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

class Parts2 extends Thread{
	private int id;
	private CountDownLatch cdl;
	private CountDownLatch cdl2;
	public Parts2(int id,CountDownLatch cdl,CountDownLatch cdl2){
		this.id = id;
		this.cdl = cdl;
		this.cdl2 = cdl2;
	}
	public void run(){
		doMyThings();
	}
	public void doMyThings(){
		try {
			cdl2.await();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
