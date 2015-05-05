/**   
* @Title: DeadLock.java 
* @Package bingfa 
* @Description:  设计精髓在于哲学家对象有固定的动作，可以直接启动，所以采用这种方式。
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-17 下午4:55:52 
* @version V1.0   
*/
package bingfa;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import static tool.Print.*;
public class DeadLock {

	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int num = 5;
		Chops[] co = new Chops[num];
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=0;i<num;i++){
			co[i] = new Chops(i);
		}
		for(int i=0;i<num;i++){
			if(i<num-1)
				es.execute(new Philo(co[i],co[(i+1)%num],i,i));
			else
				es.execute(new Philo(co[0],co[i],i,i));
		}
	}

}
class Chops{
	private ReentrantLock lock = new ReentrantLock();
	private final int id;
	public Chops(int id){
		this.id = id;
		}
	public void getLock(){
		lock.lock();
	}
	public void freeLock(){
		lock.unlock();
	}
	public boolean getStatus(){
		return lock.isLocked();
	}
	public String toString(){
		return "chops--"+id+":"+getStatus();
	}
	
}
class Philo implements Runnable{
	private Chops left;
	private Chops right;
	private final int ponder;
	private final int id;
	private Random rand = new Random(47);
	public Philo(Chops left,Chops right,int id,int ponder){
		this.left = left;
		this.right = right;
		this.ponder = ponder;
		this.id = id;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(!Thread.interrupted()){
				print(this+" is thinking");
				pause();
				left.getLock();
				print(this+" get left"+left);
				right.getLock();
				print(this+" get right"+right);
				print(this+" is eating");
				pause();
				left.freeLock();
				right.freeLock();
			}
		}catch(InterruptedException e){
			
		}
	}
	public void pause() throws InterruptedException{
		
			TimeUnit.MILLISECONDS.sleep(ponder*rand.nextInt(20));
		
	}
	public String toString(){
		return "philo--"+id;
	}
}
