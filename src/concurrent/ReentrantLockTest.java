/**   
* @Title: ReentrantLockTest.java 
* @Package concurrent 
* @Description: TODO(用一句话描述该文件做什么) 
* @author:kang.sun1@renren-inc.com   
* @date 2014-9-23 下午8:23:06 
* @version V1.0   
*/ 
package concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author renren
 *
 */
public class ReentrantLockTest extends Thread {
	private Lock chicken;
	private String userName;
	public ReentrantLockTest(String userName,Lock chicken){
		this.userName = userName;
		this.chicken = chicken;
	}
	public void run(){
		chicken.print(userName);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService es = Executors.newFixedThreadPool(3);
		Lock chicken = new Lock();
		for(int i=0;i<10;i++)
			es.execute(new ReentrantLockTest("pk:"+i,chicken));
		es.shutdown();

	}
	
	

}
/**
 * 这其实相当于是一个互斥类，一个上完以后，才能第二个上。
 * 噗- -我邪恶了。
 * 一次只能一个人用。所以可以用传参的方式，来获取每个使用者的特色。
 * @author renren
 *
 */
class Lock{
	private ReentrantLock lock = new ReentrantLock() ;
//	private int id;
//	public Lock(int id,ReentrantLock lock){
//		this.id = id;
//		this.lock = lock;
//	}
	public void print(String userName){
		System.out.println(userName+"before :the chicken is fu?"+lock.isLocked()+" "+lock.getHoldCount());
		lock.lock();
//		lock.lock();
		System.out.println(userName+"after :the chicken is fu?"+lock.isLocked()+" "+lock.getHoldCount());
		try{
			System.out.println(userName+" fu happy now!");
			Thread.sleep(1000);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
}
