/**   
* @Title: LockTest.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-31 上午10:11:01 
* @version V1.0   
*/
package bingfa;

import java.util.concurrent.locks.ReentrantLock;
import static tool.Print.*;
public class LockTest {
	static int count = 0;
	/**
	 * @throws InterruptedException  
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		final ReentrantLock lock = new ReentrantLock();
		lock.lock();
		new Thread(){public void run(){while(true){
			lock.lock();
			print(++count);
		}
		}}.start();
		Thread.sleep(1000);
		lock.unlock();
	}

}
