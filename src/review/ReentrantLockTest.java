/**   
* @Title: ReentrantLockTest.java 
* @Package review 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-9 下午3:56:30 
* @version V1.0   
*/
package review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest extends Thread{
	private int id;
	private LockTest lt;
	public ReentrantLockTest(int id,LockTest lt){
		this.id = id;
		this.lt = lt;
	}
	public void run(){
		try {
			lt.doSth(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
		ExecutorService es = Executors.newFixedThreadPool(3);
		LockTest lt = new LockTest();
		for(int i=0;i<10;i++){
			es.submit(new ReentrantLockTest(i,lt));
		}
		System.out.println("-------------------------");
		es.shutdown();
	}

}
class LockTest{
	private ReentrantLock rt = new ReentrantLock();
	public void doSth(int id) throws InterruptedException{
		System.out.println(id+"开始等待！");
		rt.lock();
		System.out.println(id+"开始买买买！");
		Thread.sleep(1000);
		rt.unlock();
		System.out.println(id+"没钱走人！");
	}
}
