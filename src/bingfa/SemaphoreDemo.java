/**   
* @Title: SemaphoreDemo.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-20 下午3:35:35 
* @version V1.0   
*/
package bingfa;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import static tool.Print.*;
public class SemaphoreDemo {

	/**
	 * @throws InterruptedException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException  
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, InterruptedException {
		// TODO Auto-generated method stub
		int size = 25;
		final Pool<Fat> pool = new Pool<Fat>(Fat.class,size);
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=0;i<size;i++)
			es.execute(new CheckOut<Fat>(pool));
		print("all this is checked!");
		
		
		List<Fat> list = new ArrayList<Fat>();
		for(int i=0;i<size;i++){
			Fat f = pool.get();
			print("------try to get "+f);
			list.add(f);
		}
		Future<?> sub = es.submit(new Runnable(){
			public void run(){
				print("this is zuo si !");
				try {
					pool.get();
					print("zuo si cheng gong !");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					print("zuo si shi bai  ! what the huck !");
				}
			}
		} );
		Thread.sleep(1000);
		sub.cancel(true);
		for(Fat f :list)
			pool.release(f);
		for(Fat f :list)
			pool.release(f);
		es.shutdown();
				
	}

}
class Pool<T>{
	private boolean[] check;
	private int size;
	private List<T> list = new ArrayList<T>();
	private Semaphore sm;
	public Pool(Class<T> obj,int size) throws InstantiationException, IllegalAccessException{
		this.size = size;
		sm = new Semaphore(size,true);
		check = new boolean[size];
		for(int i=0;i<size;i++){
			list.add(obj.newInstance());
		}
	}
	public  T get() throws InterruptedException{
		sm.acquire();
		return getItem();
	}
	private synchronized T getItem(){
		for(int i=0;i<size;i++)
			if(!check[i]){
				check[i] = true;
				T t = list.get(i);
				printnb(t+" is out!");
				return t;
			}
		return null;
	}
	public void release(T t){
		if(releaseItem(t))
			sm.release();
	}
	private synchronized boolean releaseItem(T t){
		int index = list.indexOf(t);
		if(index==-1)return false;
		
			if(check[index]){
				check[index] = false;
				printnb(t+" is in!");
				return true;
			}
		
		return false;
	}
}
class Fat{
	private static int count = 0;
	private final int id = count++;
	public String toString(){
		return "fat:"+id;
	}
}
class CheckOut<T> implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private Pool<T> pool;
	public CheckOut(Pool<T> pool){
		this.pool = pool;
	}
	public void run(){
		try {
			T t = pool.get();
			print(this+" get "+t);
			TimeUnit.MILLISECONDS.sleep(1000);
			print(this+" release "+t);
			pool.release(t);
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("hucj");
		}
	}
	public String toString(){
		return "check["+id+"]";
	}
}