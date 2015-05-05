/**   
* @Title: BlockingQueueTest.java 
* @Package review 
* @Description: 阻塞队列，阻塞应该指的是在获取不到或者插不进去的时候，一直是空的。典型的生产者消费者模型。同步产生和消费
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-9 下午4:33:01 
* @version V1.0   
*/
package review;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlockingQueueTest {
	public static void main(String[] args){
		BlockingQueue<String> bq = new ArrayBlockingQueue<String>(300);
		ExecutorService es = Executors.newFixedThreadPool(4);
		for(int i=0;i<2;i++)
		es.submit(new Producer(i,bq));
		for(int m=2;m<4;m++)
		es.submit(new Consumer(m,bq));
		es.shutdown();
	}

}
class Producer extends Thread{
	private BlockingQueue<String> bq;
	private int id;
	public Producer(int id,BlockingQueue<String> bq){
		this.id = id;
		this.bq = bq;
	}
	public void run(){
		System.out.println(id+"  :begin put in");
		for(int i=0;i<20;i++){			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bq.add(id+"--"+i);
			System.out.println(id+" put "+i);
		}
	}
}
class Consumer extends Thread{
	private BlockingQueue<String> bq;
	private int id;
	public Consumer(int id,BlockingQueue<String> bq){
		this.id = id;
		this.bq = bq;
	}
	public void run(){
		int times = 0;
		System.out.println(id+"  :begin fuck");
		try {
			while(true){
			if(bq.isEmpty()){
				Thread.sleep(1000);
				times++;
				if(times>=3)break;
				continue;
			}
			
			times = 0;
			String s = bq.take();
			Thread.sleep(100);
			System.out.println(id+" has fucked "+s);
			}
			System.out.println("it is all over now!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
