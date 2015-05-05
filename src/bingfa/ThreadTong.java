/**   
* @Title: ThreadTong.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-6 下午3:27:25 
* @version V1.0   
*/
package bingfa;
import static tool.Print.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class ThreadTong {

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
		Car car = new Car();
		es.execute(new WaxOn(car));
		es.execute(new WaxOff(car));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.shutdownNow();
		
	}

}
class Car{
	private boolean waxOn = false;
	private int count = 0;
	public void wax(){
		synchronized(this){
			print("wax on!");
			waxOn = true;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void waxOff(){
		synchronized(this){
			print("wax off!");
			waxOn = false;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public boolean ifWax(){
		print(count++);
		return waxOn;
	}
}
class WaxOn implements Runnable{
	private Car car;
	public WaxOn(Car car){
		this.car = car;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(!car.ifWax())
				car.wax();
		}
	}
	
}
class WaxOff implements Runnable{
	private Car car;
	public WaxOff(Car car){
		this.car = car;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		//这种写法会导致无限判断的吧？好二的写法啊。
		//问题在于处理完以后，需要让等待的知道。这样就不用无限的判断了。
		while(true){
			if(car.ifWax())
			car.waxOff();
		}
	}
	
}
