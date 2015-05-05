/**   
* @Title: ThreadLongNew.java 
* @Package bingfa 
* @Description:  两个协作线程，需要互相通信，我完成以后，你在完成。
* 完成之前，需要等待。
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-6 下午4:08:13 
* @version V1.0   
*/
package bingfa;
import static tool.Print.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
public class ThreadLongLock {

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
		Car1 car = new Car1();
		es.execute(new On(car));
		es.execute(new Off(car));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.shutdownNow();
		System.exit(0);
	}

}

class Car2 {
	private ReentrantLock lock = new ReentrantLock();
	private int count = 0;
	public synchronized void waxOn(){
		lock.lock();
		count++;
		print("wax on!");
		lock.unlock();
		
		
	}
	public synchronized void waxOff(){
		if(count!=0){
		lock.lock();
		print("wax off!");
		lock.unlock();
		}
	}
	
}
class On1 implements Runnable{
	private Car2 car;
	public On1(Car2 car){
		this.car = car;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		while(!Thread.interrupted()){
		car.waxOn();
		
			Thread.sleep(100);
		
		
	}} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}
class Off1 implements Runnable{
	private Car2 car;
	public Off1(Car2 car){
		this.car = car;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		while(!Thread.interrupted()){
		
		
			Thread.sleep(100);
		
		car.waxOff();
	}} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}

