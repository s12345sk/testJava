/**   
* @Title: DelayQueueDemo.java 
* @Package bingfa 
* @Description:  delayqueue 按照预先制定的compareto方法，由小到大一次发生。
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-18 下午5:18:01 
* @version V1.0   
*/
package bingfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static tool.Print.*;
public class DelayQueueDemo {

	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		 // TODO Auto-generated method stub
		 int size = 10;
		 DelayQueue<DelayTask> dq = new  DelayQueue<DelayTask>();
		 ExecutorService es = Executors.newCachedThreadPool();
		 Random rand = new Random(47);
		 for(int i=0;i<10;i++){
			 dq.add(new DelayTask(rand.nextInt(3000)));
		 }
		 dq.add(new End(3000,es));
		 es.execute(new DelayTaskCosumer(dq));
	}
	

}
class DelayTask implements Runnable,Delayed{

	private static int count = 0;
	private final int id = ++count;
	private final int delta;
	private final long trigger;
	public static List<DelayTask> list = new ArrayList<DelayTask>();
	public DelayTask(int delta){
		this.delta = delta;
		trigger = System.nanoTime()+TimeUnit.NANOSECONDS.convert(delta, TimeUnit.MILLISECONDS);
		list.add(this);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		printnb(this+"  ");
	}
	
	public long getDelay(TimeUnit tu){
		return tu.convert(trigger-System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		DelayTask dt = (DelayTask)o;
		if(dt.trigger-this.trigger>0)return -1;
		if(dt.trigger-this.trigger<0)return 1;
		return 0;
	}
	public String toString(){
		return "delay--"+id+"--"+delta;
	}
	public String summary(){
		return "【delay--"+id+"--"+delta+"】";
	}

}
class End extends DelayTask{
	private ExecutorService es;
	public End(int delta,ExecutorService es){
		super(delta);
		this.es = es;
	}
	public void run(){
		print();
		for(DelayTask dl:list)
			printnb(dl.summary()+" ");
		print();
		print("hello,this is the dead!");
		es.shutdownNow();
	}
}
class DelayTaskCosumer implements Runnable{
	private DelayQueue<DelayTask> dq ;
	
	public DelayTaskCosumer(DelayQueue<DelayTask> dq){
		this.dq = dq;
		
	}
	public void run(){
		try{
		while(!Thread.interrupted()){
			dq.take().run();
			
		}
		}catch(InterruptedException e){
			print("hahahah InterruptedException!");
		}
	}
}