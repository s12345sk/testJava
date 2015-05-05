/**   
* @Title: DoorOhters.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-2 下午4:41:22 
* @version V1.0   
*/
package bingfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DoorOhters implements Runnable{
	private static Count count = new Count();
	private static List<DoorOhters> list = new ArrayList<DoorOhters>();
	private final int id;
	private int number = 0;
	private static boolean cancel = false;
	public static void setFull(){
		cancel = true;
	}
	public DoorOhters(int id){
		this.id = id;
		list.add(this);
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
		for(int i=0;i<5;i++){
			es.execute(new DoorOhters(i));
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DoorOhters.setFull();
		es.shutdown();
		try {
			if(!es.awaitTermination(250, TimeUnit.MILLISECONDS))
				System.out.println("hahahhhahah sbaaaa");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Total :"+DoorOhters.getTotal());
		System.out.println("Sum :"+DoorOhters.getSum());
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!cancel){
			synchronized(this){
				number++;
			}
			System.out.println(this+",total:"+count.inc());
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("stopping"+ this);
	}
	public String toString(){
		return "door "+id+","+getValue();
	}
	public synchronized int getValue(){
		return number;
	}
	public static int getTotal(){
		return count.getCount();
	}
	public static int getSum(){
		int sum = 0;
		for(DoorOhters dot:list)
			sum += dot.getValue();
		return sum;
	}

}
class Count{
	private int count = 0;//计数
	private Random rand = new Random(47);
	public synchronized int inc(){
		int temp = count;
		if(rand.nextBoolean())
			Thread.yield();
		return (count = ++temp);
	}
	public synchronized int getCount(){
		return count;
	}
}