/**   
* @Title: RestNew.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-24 上午9:45:35 
* @version V1.0   
*/
package bingfa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

import static tool.Print.*;
public class RestNew {

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
		es.execute(new Rest(es,5,4));
		TimeUnit.MILLISECONDS.sleep(5000);
		es.shutdownNow();
	}

}
enum Food{
	APPLE,
	PEAR,
	BANANA,
	ORANGE;
	public Food randFood(){
		Food[] food = Food.values();
		return food[new Random().nextInt(food.length)];
	}
}
class Plate{
	private Food f;
	private Order o;
	public Plate(Food f,Order o){
		this.f = f;
		this.o = o;
	}
	/**
	 * @return the c
	 */
	public Food getF() {
		return f;
	}
	/**
	 * @return the o
	 */
	public Order getO() {
		return o;
	}
	public String toString(){
		return "food:"+f;
	}
	
}
class Order{
	private Cust c;
	private WaitN w;
	private Food f;
	public Order(Cust c,WaitN w,Food f){
		this.c = c;
		this.f = f;
		this.w = w;
	}
	/**
	 * @return the c
	 */
	public Cust getC() {
		return c;
	}
	/**
	 * @return the w
	 */
	public WaitN getW() {
		return w;
	}
	/**
	 * @return the f
	 */
	public Food getF() {
		return f;
	}
	
}
class Cust implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private WaitN w;
	public Cust(WaitN w){ this.w = w; }
	public String toString(){ return "cust:"+id; }
	private SynchronousQueue<Plate> q = new SynchronousQueue<Plate>();
	public void deliver(Plate p) throws InterruptedException{
		q.put(p);
	}
	public void run(){
		try{
			for(Food f:Food.values()){
				f = f.randFood();
				w.pOrder(new Order(this,w,f));
				print(this+" eat "+q.take());
			}
			print(this+" leaved!-----------------"+id);
		}catch(InterruptedException e){
			print(this +" is voer!-------------"+id);
		}
	}
}
class WaitN implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private Rest re;
	public WaitN(Rest re){ this.re = re; }
	public String toString(){ return "wiater:"+id; }
	//盘子队列 接送盘子
	BlockingQueue<Plate> plate = new LinkedBlockingQueue<Plate>();
	public void place(Plate p) throws InterruptedException{
		plate.put(p);
	}
	public void pOrder(Order o) throws InterruptedException{
		re.order.put(o);
	}
	public void run(){
		try{
			while(!Thread.interrupted()){
				Plate p = plate.take();
				print(this+" deliver "+p+" to "+p.getO().getC());
				p.getO().getC().deliver(p);
			}
		}catch(InterruptedException e){
			print(this +" is voer!");
		}
	}
}
class ChefN implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private Rest re;
	private Random rand = new Random(47);
	public ChefN(Rest re){ this.re = re; }
	public String toString(){ return "chef:"+id; }
	public void run(){
		try{
			while(!Thread.interrupted()){
				Order o = re.order.take();
				Food f = o.getF();
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
				print(this+" make "+f+" for "+o.getC());
				Plate p = new Plate(f,o);
				o.getW().place(p);
				
			}
		}catch(InterruptedException e){
			print(this +" is voer!");
		}
	}
}
class Rest implements Runnable{
	private List<WaitN> wList = new ArrayList<WaitN>();
	private List<ChefN> cList = new ArrayList<ChefN>();
	private Random rand = new Random(47);
	BlockingQueue<Order> order = new LinkedBlockingQueue<Order>();
	private ExecutorService es;
	public Rest(ExecutorService es,int w,int c){
		this.es = es;
		for(int i=0;i<w;i++){
			WaitN wn = new WaitN(this);
			wList.add(wn);
			es.execute(wn);
		}
		for(int i=0;i<c;i++){
			ChefN cn = new ChefN(this);
			cList.add(cn);
			es.execute(cn);
		}
	}
	public void run(){
		try {
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(1000));
				WaitN w = wList.get(rand.nextInt(wList.size()));
				Cust c = new Cust(w);
				es.execute(c);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("rest closed!!!");
		}
	}
}