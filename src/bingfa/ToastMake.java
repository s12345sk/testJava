/**   
* @Title: ToastMake.java 
* @Package bingfa 
* @Description: 模拟吐司的制作流程。制作--》涂油--》涂水果 。中间的就是fifo队列了。制作完成待涂油队列，涂油完成待放水果队列。
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-10 下午7:59:42 
* @version V1.0   
*/
package bingfa;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import static tool.Print.*;
public class ToastMake {
	private Status status = Status.DRY;
	private final int id ;
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
		ToastQueue make = new ToastQueue();
		ToastQueue butt = new ToastQueue();
		es.execute(new Toast(make));
		es.execute(new Butted(make,butt));
		es.execute(new Jammed(butt));
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.shutdownNow();
		
	}
	
	public void butted(){
		status = Status.BUTTED;
	}
	public void jammed(){
		status = Status.JAMMED;
	}
	public enum Status{
		DRY,BUTTED,JAMMED
	}
	
	public ToastMake(int id){
		this.id = id;
	}
	public Status getStatus(){
		return status;
	}
	public int getId(){
		return id;
	}
	public String toString(){
		return "toast "+id+":"+status;
	}

}
class ToastQueue extends LinkedBlockingQueue<ToastMake>{}
class Toast implements Runnable{
	private ToastQueue tq;
	private Random rand = new Random(47);
	private int count = 0;
	public Toast(ToastQueue tq){
		this.tq = tq;
	}
	public void run(){
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(100+rand.nextInt(100));
				ToastMake tm = new ToastMake(count++);
				print(tm);
				tq.put(tm);
			}
		}catch(InterruptedException e){
			print("helloa,make has done by interrupted~!");
		}
		
	}
}
class Butted implements Runnable{
	private ToastQueue butt;
	private ToastQueue make;
	private Random rand = new Random(47);
	public Butted(ToastQueue make,ToastQueue butt){
		this.butt = butt;
		this.make = make;
	}
	public void run(){
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(100+rand.nextInt(100));
				ToastMake tm = make.take();
				tm.butted();
				print(tm);
				butt.put(tm);
			}
		}catch(InterruptedException e){
			print("helloa,Butted has done by interrupted~!");
		}
	}
}
class Jammed implements Runnable{
	private ToastQueue butt;
	private Random rand = new Random(47);
	public Jammed(ToastQueue butt){
		this.butt = butt;
	}
	public void run(){
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(100+rand.nextInt(100));
				ToastMake tm = butt.take();
				tm.jammed();
				print(tm);
			}
		}catch(InterruptedException e){
			print("helloa,Butted has done by interrupted~!");
		}
	}
}
