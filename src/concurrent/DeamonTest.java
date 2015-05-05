/**   
* @Title: DeamonTest.java 
* @Package concurrent 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-5 下午9:10:53 
* @version V1.0   
*/
package concurrent;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeamonTest {
	public static void main(String[] args){
		for(int i=0;i<10;i++){
			Thread td = new Thread(new Hi());
			td.setDaemon(true);
			td.start();
		}
		try {
			System.out.println("all start now!");
			Thread.sleep(3300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
class Hi implements Runnable{

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread()+" "+this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
}