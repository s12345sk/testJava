/**   
* @Title: HorseRace.java 
* @Package bingfa 
* @Description:  一个赛马仿真小程序。通过cyclicbarrier循环阻塞。多个任务同时到达一个地方后，开始执行下一段操作。可进行多次计数。
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-18 上午11:25:19 
* @version V1.0   
*/
package bingfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static tool.Print.*;
public class HorseRace {
	private static final int FINISH_LINE = 75;
	private CyclicBarrier cb;
	private List<Horse> list = new ArrayList<Horse>();
	ExecutorService es = Executors.newCachedThreadPool();
	public HorseRace(int horseNum,final int pauseTime){
		cb = new CyclicBarrier(horseNum,new Runnable(){
			public void run(){
				for(int i=0;i<FINISH_LINE;i++)
					printnb("=");
				print();
				for(Horse h:list){
					h.getTrace();
				}
				for(Horse h:list){
					if(h.getStride()>=FINISH_LINE){
						print(h+" is winner!");
						es.shutdownNow();
						return;
					}
				}
				try {
					TimeUnit.MILLISECONDS.sleep(pauseTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					print("game over now!");
				}
			}
		});
		for(int i=0;i<horseNum;i++){
			Horse horse = new Horse(cb);
			list.add(horse);
			es.execute(horse);
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
		new HorseRace(7,200);
		
	}

}
class Horse implements Runnable{
	
	private static  int count = 0;//计数器
	private final int id = count++;//id
	private final CyclicBarrier cb;
	private int stride;
	private ReentrantLock lock = new ReentrantLock();
	private Random rand = new Random();
	public Horse(CyclicBarrier cb){
		this.cb = cb;
	}
	public int getStride(){
		return stride;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
		while(!Thread.interrupted()){
			lock.lock();
			stride += rand.nextInt(10);
			lock.unlock();
			
				cb.await();
			
		}} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("InterruptedException");
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			print("BrokenBarrierException");
		}
		
		
	}
	public void getTrace(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<stride;i++)
			sb.append("*");
		sb.append(id);
		print(sb.toString());
	}
	public String toString(){
		return "hosrse--"+id;
	}
	
}
