/**   
* @Title: ScheduledThreadTest.java 
* @Package concurrent 
* @Description:  定时调度任务测试
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-9 下午8:29:01 
* @version V1.0   
*/
package concurrent;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class ScheduledThreadTest {
	public static void main(String[] args){
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);
		final ScheduledFuture  sf = ses.scheduleAtFixedRate(new Beeper(1), 2, 3, SECONDS);
		final ScheduledFuture  sf1 = ses.scheduleWithFixedDelay(new Beeper(2), 2, 5, SECONDS);
		ses.schedule(new Runnable(){
			public void run(){
				sf.cancel(true);
				sf1.cancel(true);
				System.out.println("孩儿们回家了！");
			}

		}, 30, SECONDS);
//		ses.shutdown();
	}

}
class Beeper implements Runnable{
	private int id;
	public Beeper(int id){
		this.id = id;
	}
	private int count = 1;
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			System.out.println(now()+id+" 已经震动"+(count++)+"次。");
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}
	public static String  now(){
		SimpleDateFormat smf = new SimpleDateFormat("HH:mm:ss");
		return smf.format(new Date())+":";
	}
	
}
