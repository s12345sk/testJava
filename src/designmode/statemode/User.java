/**   
* @Title: User.java 
* @Package designmode.statemode 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-26 上午9:47:53 
* @version V1.0   
*/
package designmode.statemode;

import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static tool.Print.*;
public class User  extends Observable implements Runnable{
	private static int  co = 0;
	private final int id = co++;
	public static int all = 0;
	private int count = 0;
	private UserState us = UserState.NORMAL;
	public User(){
		this.addObserver(new ObserverIm());
	}
	public void run(){
		try {
			while(!Thread.interrupted()){
				if(us==UserState.FREEZE){
					TimeUnit.MILLISECONDS.sleep(300);
					appeal();
				}
				TimeUnit.MILLISECONDS.sleep(100);
				vote();
				
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("oh no ");
		}
	}
	public void vote(){
		if(us==UserState.FREEZE){
			print(this+" no auth now!");
			return;
		}
		count++;
		all++;
		setState(us.vote(count));
		
	}
	public void appeal(){
		setState(us.appeal());
	}
	private void setState(UserState us){
		
		if(this.us!=us){
			this.us = us;
			this.setChanged();
			this.notifyObservers();
		}
	}
	public UserState getState(){
		return us;
	}
	public String toString(){
		return "userid:"+id+",count:"+count;
	}
	public void clean(){
		all -=count;
		count = 0;
		
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
		es.execute(new User());
		es.execute(new User());
		es.execute(new User());
		es.execute(new Runnable(){
			public void run(){
				try {
					while(!Thread.interrupted()){
						Thread.sleep(500);
						print("---------now all is "+all);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					print("");
				}
			}
		});
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.shutdownNow();
	}

}
