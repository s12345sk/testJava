/**   
* @Title: EventChecker.java 
* @Package concurrent.event 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-6 下午9:40:37 
* @version V1.0   
*/
package concurrent.event;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventChecker extends Thread{
	private IntGenerator ig;
	private int id;
	public EventChecker(IntGenerator ig,int id){
		this.id = id;
		this.ig = ig;
	}
	public void run(){
		
			while(!ig.isCancle()){
				int i = ig.next();
//				
				if(i%2!=0){
					System.out.println(i+"不是偶数啊！");
					ig.cancel();
				}
				System.out.println(i);
			}
		
	}
	public static void test(IntGenerator ig,int count){
		System.out.println("ctrl + c to exit!");
		ExecutorService es = Executors.newCachedThreadPool();
		for(int i=0;i<count;i++)
			es.execute(new EventChecker(ig,i));
		es.shutdown();
	}
	public static void t(IntGenerator ig){
		test(ig,10);
	}
	public static void main(String[] args){
		IntGenerator ig = new IntC();
		t(ig);
	}

}
