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
public class ThreadLongNew {

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
	}

}

class Car1 {
	private boolean waxOn = false;
	public synchronized void waxOn(){
		print("wax on!");
		waxOn = true;
		notify();
	}
	public synchronized void waxOff(){
		print("wax off!");
		waxOn = false;
		notify();
	}
	public synchronized void waitOn(){
		if(waxOn==true)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public synchronized void waitOff(){
		if(waxOn==false)
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
class On implements Runnable{
	private Car1 car;
	public On(Car1 car){
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
		
		car.waitOn();
	}} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}
class Off implements Runnable{
	private Car1 car;
	public Off(Car1 car){
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
		car.waitOff();
		
			Thread.sleep(100);
		
		car.waxOff();
	}} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
}

