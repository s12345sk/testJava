/**   
* @Title: BlockingQueueTest.java 
* @Package concurrent 
* @Description: TODO(用一句话描述该文件做什么) 
* @author:kang.sun1@renren-inc.com   
* @date 2014-9-23 下午8:51:33 
* @version V1.0   
*/ 
package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author renren
 *
 */
public class BlockingQueueTest extends Thread {
	
	public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(4);
	private int i;
	public BlockingQueueTest(int i){
		this.i = i;
	}
	public void run(){
		try {
			queue.put(i);
			System.out.println(i+" is in!size: "+queue.size());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=0;i<20;i++)
			es.execute(new BlockingQueueTest(i));
		Thread get = new Thread(){
			public void run(){
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				if(queue.isEmpty()){
					System.out.println("queue is empty now!");
					break;
				}else{
					try {
						int i = queue.take();
						System.out.println(i+" is out!size: "+queue.size());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			}
			
		};
		es.execute(get);
		es.shutdown();

	}

}
