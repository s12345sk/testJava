/**   
* @Title: PriorityBlockingQueueDemo.java 
* @Package bingfa 
* @Description:  优先级队列。一个生产者，一个消费者。通过设置的权限值，从queue中取出任务进行处理。
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-19 上午10:55:17 
* @version V1.0   
*/
package bingfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;

import static tool.Print.*;
public class PriorityBlockingQueueDemo {

	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PriorityBlockingQueue<Runnable> pbq = new PriorityBlockingQueue<Runnable>();
		ExecutorService es = Executors.newCachedThreadPool();
		pbq.put(new EndP(es));
		es.execute(new Producer(pbq));
		
		es.execute(new Customer(pbq));
	}

}
class Priority implements Runnable ,Comparable<Priority>{
	private static int count =0;
	private final int id = count++;
	private final int priValue;
	protected static List<Priority> list = new ArrayList<Priority>();
	public Priority(int priValue){
		this.priValue = priValue;
		list.add(this);
	}
	@Override
	public int compareTo(Priority o) {
		// TODO Auto-generated method stub
		return priValue>o.priValue?-1:(priValue<o.priValue?1:0);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		printnb(this+" ");
	}
	public String toString(){
		return "id-"+id+"-p-"+priValue;
	}
	public String summary(){
		return "【id-"+id+"-p-"+priValue+"】";
	}
	
}
class Producer implements Runnable{
	private PriorityBlockingQueue<Runnable> pbq;
	private Random rand = new Random(47);
	public Producer(PriorityBlockingQueue<Runnable> pbq){
		this.pbq = pbq;
	}
	public void run(){
		for(int i=0;i<10;i++)
			pbq.put(new Priority(rand.nextInt(10)));
		for(int i=0;i<10;i++)
			pbq.put(new Priority(rand.nextInt(100)));
		for(int i=0;i<10;i++)
			pbq.put(new Priority(rand.nextInt(250)));
		
	}
}
class Customer implements Runnable{
	private PriorityBlockingQueue<Runnable> pbq;
	
	public Customer(PriorityBlockingQueue<Runnable> pbq){
		this.pbq = pbq;
	}
	public void run(){
		try{
		    int count = 0;
			while(!Thread.interrupted()){			   
				pbq.take().run();
				if(++count%5==0)print();
				TimeUnit.MILLISECONDS.sleep(200);
			}
		}catch(InterruptedException e){
			
		}
	}
}
class EndP extends Priority{
	private ExecutorService es ;
	public EndP(ExecutorService es ){
		super(-1);
		this.es = es;
	}
	public void run(){
		int count = 0;
		for (Priority pr:list){
			printnb(pr.summary()+" ");
			if(++count%5==0)print();
		}
		
		es.shutdownNow();
	}
}