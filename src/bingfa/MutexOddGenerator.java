/**   
* @Title: MutexOddGenerator.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-2-28 上午10:55:32 
* @version V1.0   
*/
package bingfa;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexOddGenerator extends IntGenerator{
	private int value = 0;
	private Lock lock = new ReentrantLock();

	public int next() {
		lock.lock();
		try {
			
			value++;
			Thread.yield();
			value++;
			return value;
		} finally {
			lock.unlock();
		}
		
	}
	public static void main(String[] args){
		System.out.println(test());
		EventChecker.test(new MutexOddGenerator());
	}
	public static  int test(){
		int i = 1;
		try{
			System.out.println(1);
			return 5;
		}finally{
			System.out.println(3);
//			return 6;
//			return 3;
		}
		
		
	}
	
}
