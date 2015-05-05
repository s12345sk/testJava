/**   
* @Title: Door.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-2 下午3:50:27 
* @version V1.0   
*/
package bingfa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Door implements Runnable{
	private final int id;
	private static Lock lock = new ReentrantLock();
	public Door(int id){
		this.id = id;
	}
	private static int count = 0;
	private volatile boolean isFull = false;
	public void setFull(){
		isFull = true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(!isFull){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lock.lock();
			try{
			if(count>=25){
				setFull();
				break;
			}
			count++;			
			System.out.println("door "+id+" 又进来一位，现在是"+count+".");
			}finally{
			lock.unlock();
			}
		}
	}
	
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
		for(int i=0;i<4;i++){
			es.execute(new Door(i));
		}
		es.shutdown();
	}

}
