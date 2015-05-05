/**   
* @Title: BenDi.java 
* @Package bingfa 
* @Description:  ThreadLocal 创建与对象关联的副本
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-2 下午3:06:52 
* @version V1.0   
*/
package bingfa;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BenDi {

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
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=0;i<5;i++){
			es.execute(new Access(i));
		}
		es.shutdown();
		Thread.sleep(1000);
		System.exit(0);
	}

}
class Access implements Runnable{
	private final int id;
	public Access(int i){
		id = i;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!Thread.currentThread().isInterrupted()){
			ThreadHoleder.inc();
			System.out.println(this);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Thread.yield();
		}
	}
	public String toString(){
		return "id:"+id+",value:"+ThreadHoleder.get();
	}
	
}
class ThreadHoleder{
	private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
		private Random rand = new Random(47);
		protected synchronized Integer initialValue(){
			return rand.nextInt(10000);
		}
	} ;
	public static int get(){
		return value.get();
	}
	public static void inc(){
		value.set(value.get()+1);
	}
}
