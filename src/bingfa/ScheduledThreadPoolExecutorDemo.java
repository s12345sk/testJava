/**   
* @Title: ScheduledThreadPoolExecutorDemo.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-19 下午4:50:30 
* @version V1.0   
*/
package bingfa;
import static tool.Print.*;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class ScheduledThreadPoolExecutorDemo {

	
	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(3);
		stpe.schedule(new TestDemo("haha"), 100, TimeUnit.MILLISECONDS);
		stpe.scheduleAtFixedRate(new TestDemo("at"), 0,  100, TimeUnit.MILLISECONDS);
		stpe.scheduleWithFixedDelay(new TestDemo("with"), 0, 100, TimeUnit.MILLISECONDS);
		try {
			TimeUnit.MILLISECONDS.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			
		}finally{
			stpe.shutdownNow();
		}
	}

}
class TestDemo implements Runnable{
	private static int count = 0;
	private String type;
	public TestDemo(String type){
		this.type = type;
	}
	public void run(){
		try {
			TimeUnit.MILLISECONDS.sleep(100);
			print(type+"--demo:"+count++);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			print("over");
			return;
		}
		
	}
}
