/**   
* @Title: ExchangerDemo.java 
* @Package bingfa 
* @Description:  Exchanger demo 将两个线程的内容进行交换
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-20 下午6:09:39 
* @version V1.0   
*/
package bingfa;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static tool.Print.*;
public class ExchangerDemo {
   
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
	    List<Baymax> empty = new ArrayList<Baymax>();
		List<Baymax> full = new ArrayList<Baymax>();
		Exchanger<List<Baymax>> ex = new Exchanger<List<Baymax>>();
		ExecutorService es = Executors.newCachedThreadPool();
		es.execute(new InitT(empty,ex));
		es.execute(new CusT1(full,ex));
		TimeUnit.SECONDS.sleep(3);
		es.shutdownNow();
	}

}
class InitT implements Runnable{
	List<Baymax> empty;
	Exchanger<List<Baymax>> ex;
	public InitT(List<Baymax> empty,Exchanger<List<Baymax>> ex){
		this.empty = empty;
		this.ex = ex;
	}
	public void run(){
		try{
		while(!Thread.interrupted()){
			Baymax bm = getbm();
			print("make "+bm);
			empty.add(bm);
			
				TimeUnit.MILLISECONDS.sleep(100);
			
			if(empty.size()==10){
			
					print("oh full!");
					empty = ex.exchange(empty);
					print("oh empty!"+empty.size());
				
			}
		}} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("it is all over now!");
		}
	}
	public Baymax getbm(){
		return new Baymax();
	}
}
class CusT1 implements Runnable{
	List<Baymax> full;
	Exchanger<List<Baymax>> ex;
	public CusT1(List<Baymax> full,Exchanger<List<Baymax>> ex){
		this.full = full;
		this.ex =ex;
	}
	public void run(){
		try{	
		while(!Thread.interrupted()){
		if(full.size()==0){//这个判断是没有必要的
			
				print("oh no!");
				full = ex.exchange(full);
				print("oh yes!"+full.size());
				print(full);
				for(Baymax bm:full){
					print("cus "+bm);
					
					
					TimeUnit.MILLISECONDS.sleep(100);
				}
				full.clear();
				print(full.size());
			
		
		}
	}} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		print("cus over");
	}
}
}
class Baymax{
	private static int count = 0;
	private final int id = count++;
	public String toString(){
		return "baymax "+id;
	}
}
