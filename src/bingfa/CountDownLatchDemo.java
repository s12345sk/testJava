/**   
* @Title: CountDownLatchDemo.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-18 上午10:41:04 
* @version V1.0   
*/
package bingfa;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import static tool.Print.*;
public class CountDownLatchDemo {

	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int size = 100;
		ExecutorService es = Executors.newCachedThreadPool();
		CountDownLatch cdt = new CountDownLatch(size);
		for(int i=0;i<size;i++)
			es.execute(new ThreadBefore(cdt,i));
		for(int i=0;i<10;i++)
			es.execute(new ThreadAfter(cdt,i));
		es.shutdown();
	}

}
class ThreadBefore implements Runnable{
	
	private CountDownLatch dt;
	private final int id;
	private Random rand = new Random(47);
	public ThreadBefore(CountDownLatch dt,int id){
		this.dt = dt;
		this.id = id;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
			print(this+" has done--dt is "+dt.getCount());
			dt.countDown();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String toString(){
		return "before thread "+id;
	}
	
}
class ThreadAfter implements Runnable{
	
	private CountDownLatch dt;
	private final int id;
	private Random rand = new Random(47);
	public ThreadAfter(CountDownLatch dt,int id){
		this.dt = dt;
		this.id = id;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			print(this+"-----------------------------COMING-------------------------------");
			dt.await();
			print(this+" is over now");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	public String toString(){
		return "after thread "+id;
	}
}