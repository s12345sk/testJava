/**   
* @Title: EventChecker.java 
* @Package bingfa 
* @Description:  验证int是否正常工作
* @author kang.sun(kanggood@126.com)   
* @date 2015-2-28 上午9:59:13 
* @version V1.0   
*/
package bingfa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventChecker implements Runnable{
	private IntGenerator ing;
	private int id;
	public EventChecker(IntGenerator ing,int id){
		this.ing = ing;
		this.id = id;
	}
	public void run(){
		while(!ing.isConcled()){
			int i = ing.next();
			if(i%2!=0){
				System.out.println("EventChecker---"+id+":我们这里出了一个叛徒！他就是"+i);
				ing.concel();
			}
		}
	}
	public static void test(IntGenerator ing,int count){
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=0;i<count;i++){
			es.execute(new EventChecker(ing,i));
		}
//		es.shutdown();
	}
	public static void test(IntGenerator ing){
		test(ing,10);
	}
	
}
