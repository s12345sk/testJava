/**   
* @Title: BankDemo.java 
* @Package bingfa 
* @Description:  设计思路：通过优先级队列，进行选择。当人数较多时候，从空闲队列中拉出来，否则新增。当人数较少，可以
* 休息时候，则从优先级队列中找出服务最多的休息。每产生一个对象，都放进队列中运行。通过服务状态，来进行工作的进行和停止的控制。
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-23 下午1:16:40 
* @version V1.0   
*/
package bingfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static tool.Print.*;
public class BankDemo {
	
	
	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService es = Executors.newCachedThreadPool();
		CusLine cus = new CusLine(500);
		es.execute(new CusGenerator(cus));
		es.execute(new TellerM(es,cus,1500));
	}

}
class Customers {
	private final int time;
	private static int count = 0;
	private final int id = count++;
	public Customers(int time){
		this.time = time;
	}
	public String toString(){
		return "【cus:"+id+",time:"+time+"】";
	}
	public int getTime(){
		return time;
	}
}
class CusLine extends ArrayBlockingQueue<Customers>{
	public CusLine(int size){
		super(size);
	}
}
class CusGenerator implements Runnable{
	private CusLine line;
	private Random rand = new Random(47);
	public CusGenerator(CusLine line){
		this.line = line;
	}
	public void run(){
		
		try {
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(400));
				line.add(new Customers(rand.nextInt(1000)));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("cus not come in!");
		}
	}
}
class Teller implements Runnable,Comparable<Teller>{
	private CusLine line;
	private static int count = 0;
	private final int id = count++;
	private int sCount = 0;
	protected static List<Teller> list = new ArrayList<Teller>();
	private ReentrantLock lock = new ReentrantLock();
	private boolean serving = true;
	public Teller(CusLine line){
		this.line = line;
		list.add(this);
	}
	public void run(){
		try {
			while(!Thread.interrupted()){
				Customers cus = line.take();
				TimeUnit.MILLISECONDS.sleep(cus.getTime());
				sCount++;
				print(this+" deal "+cus);
				synchronized(this){
					while(!serving)
						lock.lock();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print(this+" interrupt");
		}
	}
	public String toString(){
		return "【tell:"+id+"，service:"+sCount+"】";
	}
	public int getScount(){
		return sCount;
	}
	public synchronized void doSth(){
		serving = false;
		print(this +" do sth else");
	}
	public synchronized void goWork(){
		if(serving)
			return;
		serving = true;
		print(this +" back to work");
		lock.unlock();
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Teller o) {
		// TODO Auto-generated method stub
		return sCount>o.sCount?-1:(sCount<o.sCount?1:0);
	}
}
class TellerM implements Runnable{
	private ExecutorService es;
	private CusLine line;
	private PriorityBlockingQueue<Teller> pb = new PriorityBlockingQueue<Teller>();
	private Queue<Teller> free = new LinkedBlockingDeque<Teller>();
	private int period;
	public TellerM(ExecutorService es,CusLine line,int period){
		this.es = es;
		this.line = line;
		this.period = period;
		Teller teller = new Teller(line);
		es.execute(teller);
		pb.add(teller);
	}
	public void adjustNumber(){
		//如果排队人数大于处理人员，加人
		if(line.size()/pb.size()>2){
			if(free.size()>0){
				Teller te = free.poll();
				te.goWork();
				pb.add(te);
				return;
			}
			Teller tell = new Teller(line);
			es.execute(tell);
			pb.put(tell);
			return;
		}
		//如果排队人数小于处理人员，做其他事
		if(pb.size()>1&&line.size()/pb.size()<2){
			freeOne();
		}
		if(line.size()==0)
			while(pb.size()>1)
				freeOne();
		
		
	}
	//free一个处理人员
	public void freeOne(){
		try {
			Teller tell = pb.take();
			tell.doSth();
			free.add(tell);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
		try {
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(period);
				adjustNumber();
				printnb("-----");
				for(Teller te:pb){
					printnb(te+" ");
				}
				print();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
