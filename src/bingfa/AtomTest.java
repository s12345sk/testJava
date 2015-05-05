/**   
* @Title: AtomTest.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-2-28 下午3:45:41 
* @version V1.0   
*/
package bingfa;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomTest implements Runnable{
	
	private volatile int i = 0;
	public int getValue(){
		return i;
	}
	private synchronized void add(){
		i += 2;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
			add();
	}
	public static void main(String[] args){
		
		ExecutorService es = Executors.newCachedThreadPool();
		AtomTest at = new AtomTest();
		es.execute(at);
		new Timer().schedule(new TimerTask(){
			public void run(){
				System.out.println("it is time to abort now!");
				System.exit(0);
			}
		}, 5000);
		while(true){
			int i = at.getValue();
			if(i%2!=0){
				System.out.println("我们中出了这个叛徒："+i);
				System.exit(0);
			}
		}
		
	}

}
