/**   
* @Title: DaemonThreadFactory.java 
* @Package concurrent 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-5 下午9:32:30 
* @version V1.0   
*/
package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class DaemonThreadFactory implements ThreadFactory{

	/* (non-Javadoc)
	 * @see java.util.concurrent.ThreadFactory#newThread(java.lang.Runnable)
	 */
	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r);
		t.setDaemon(true);
		return t;
	}
	public static void main(String[] args){
		ExecutorService es = Executors.newCachedThreadPool(new DaemonThreadFactory());
		for(int i=0;i<10;i++){
			es.execute(new Hi());
		}
		System.out.println(" end ");
		try {
			Thread.sleep(3200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

