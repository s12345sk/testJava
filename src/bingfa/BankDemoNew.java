/**   
* @Title: BankDemoNew.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-31 上午9:45:22 
* @version V1.0   
*/
package bingfa;

import java.util.AbstractQueue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import static tool.Print.*;
public class BankDemoNew {

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
		es.execute(new TellM(es));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.shutdownNow();
	}

}
class Cus{
	private static int count = 0;
	private final int id = count++;
	private final int time;
	public Cus(int time){
		this.time = time;
//		print("add" +this);
	}
	public String toString(){
		return "cus"+id+":"+time;
	}
	public int getTime(){
		return time;
	}
}
class CusM implements Runnable{
	private LinkedBlockingQueue<Cus> cusLine;
	private Random rand = new Random();
	public CusM(LinkedBlockingQueue<Cus> cusLine){
		this.cusLine = cusLine;
		
	}
	public void run(){
		try {
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(100));
				cusLine.put(new Cus(rand.nextInt(300)));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print(" not come in!");
		}
	}
}
class Tell implements Runnable,Comparable<Tell>{
	private static int count = 0;
	private final int id = count++;
	private LinkedBlockingQueue<Cus> cusLine;
	private Boolean isServered = true;
	private int serviceCount = 0 ;
	private ReentrantLock lock = new ReentrantLock();
	private Random rand = new Random();
	public Tell(LinkedBlockingQueue<Cus> cusLine){
		this.cusLine = cusLine;
		print(" add " + this);
	}
	public void run(){
		try {
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
				Cus cus = cusLine.take();
				TimeUnit.MILLISECONDS.sleep(cus.getTime());
				print(this+" serverd "+cus);
				synchronized(this){
					serviceCount++;
					while(!isServered)
						lock.lock();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print(this+" is over!");
		}
	}
	public synchronized void doSth(){
		lock.lock();
		isServered = false;
		serviceCount = 0;
		print(this+" do sth else!");
	}
	public synchronized void backWork(){
		if(isServered)
			return;
		lock.unlock();
		isServered = true;
		print(this+" back to work!");
	}
	public String toString(){
		return "Tell "+id+",count:"+serviceCount;
	}
	public int getCount(){
		return serviceCount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Tell o) {
		// TODO Auto-generated method stub
		return o.getCount()>count?1:(o.getCount()<count?-1:0);
	}

}
class TellM implements Runnable{
	private LinkedBlockingQueue<Cus> cusLine = new LinkedBlockingQueue<Cus>();
	private ExecutorService es;
	private PriorityBlockingQueue<Tell> pLine = new PriorityBlockingQueue<Tell>();
	private LinkedBlockingQueue<Tell> freeLine = new LinkedBlockingQueue<Tell>();
	public TellM(ExecutorService es){
		this.es = es;
		es.execute(new CusM(cusLine));
		Tell t = new Tell(cusLine);
		es.execute(t);
		pLine.add(t);
	}
	public void run(){
		try {
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(500);
				if(cusLine.size()==0||pLine.size()/cusLine.size()>2){
					print(cusLine.size()+" "+pLine.size());
					freeOne();
				}
				if(cusLine.size()/pLine.size()>2){
					print(cusLine.size()+" "+pLine.size());
					addOne();
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			for(Cus cus:cusLine)
				print(cus);
			for(Tell t:pLine)
				print("served:"+t);
			for(Tell t:freeLine)
				print("free:"+t);
		}
	}
	public void freeOne() throws InterruptedException{
		if(pLine.size()==1)
			return;
		Tell t = pLine.take();
		t.doSth();
		freeLine.put(t);
	}
	public void addOne() throws InterruptedException{
		if(freeLine.size()>0){
			Tell t = freeLine.take();
			t.backWork();
			pLine.put(t);		
		}else{
			Tell t = new Tell(cusLine);
			es.execute(t);
			pLine.add(t);	
		}
			
	}
}
