/**   
* @Title: Restaurant.java 
* @Package bingfa 
* @Description: 餐馆。生产者和消费者。 
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-10 下午5:11:35 
* @version V1.0   
*/
package bingfa;
import static tool.Print.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Restaurant {
     public Chef chef = new Chef(this);
     public Waiter wait = new Waiter(this);
     public Meal meal;
     ExecutorService es = Executors.newCachedThreadPool();
     public Restaurant(){
    	 es.execute(chef);
    	 es.execute(wait);
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
		new Restaurant();
	}

}
class Meal{
	private final int order;
	public Meal(int order){
		this.order = order;
	}
	public String toString(){
		return "Meal:"+order;
	}
}
class Waiter implements Runnable{
	private Restaurant res;
	public Waiter(Restaurant res){
		this.res = res;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
		while(!Thread.interrupted()){
			synchronized(this){
			while(res.meal==null){
				
					wait();
				
			}
			}
			synchronized(res.chef){
			print("wait ~"+res.meal);
			res.meal = null;
			res.chef.notifyAll();
			}
		}} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("dddddddddddddd");
		}
	}
	
}
class Chef implements Runnable{
	private Restaurant res;
	private int count = 0;
	public Chef(Restaurant res){
		this.res = res;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
		while(!Thread.interrupted()){
			synchronized(this){
			while(res.meal!=null){
				
					wait();
				
			}
			}
			if(++count==10){
				print("oh!!!!!full!!!!!!!");
				res.es.shutdownNow();
			}
			synchronized(res.wait){
			res.meal = new Meal(count);
			print("gogogo!"+res.meal);
			res.wait.notifyAll();
			}
			
				Thread.sleep(100);
			
		}} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("hahahahah");
		}
	}
	
}