/**   
* @Title: GreenHouse.java 
* @Package bingfa 
* @Description:  使用ScheduledThreadPoolExecutor定时线程池产生器进行。水，灯，昼夜切换，记录等任务重复循环执行。
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-19 下午3:14:23 
* @version V1.0   
*/
package bingfa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import static tool.Print.*;
public class GreenHouse {
	private boolean light;
	private boolean water;
	private String timeStat = "Day";
	private ScheduledThreadPoolExecutor st = new ScheduledThreadPoolExecutor(10);
	public synchronized void setTime(String time){
		timeStat = time;
	}
	public synchronized String getTime(){
		return timeStat;
	}
	//延迟多久后执行
	public void schedule(Runnable run,int delay,TimeUnit unit){
		st.schedule(run, delay, unit);
	}
	//一段时间开始后，循环执行
	public void repeat(Runnable run,int init,int period,TimeUnit unit){
		st.scheduleAtFixedRate(run, init, period, unit);
	}
	class LightOn implements Runnable{
		public void run(){
			print("light on !");
			light = true;
		}
	}
	class LightOff implements Runnable{
		public void run(){
			print("light off !");
			light = false;
		}
	}
	class WaterOn implements Runnable{
		public void run(){
			print("water on !");
			water = true;
		}
	}
	class WaterOff implements Runnable{
		public void run(){
			print("water off !");
			water = false;
		}
	}
	class Day implements Runnable{
		public void run(){
			print("day !");
			setTime("day");
		}
	}
	class Night implements Runnable{
		public void run(){
			print("night !");
			setTime("night");
		}
	}
	class Bell implements Runnable{
		public void run(){
			print("bi ling ling !!!");
		}
	}
	static class DataPoint{
		final Calendar time;
		final float temp;
		final float humiy;
		public DataPoint(Calendar time,float temp,float humiy){
			this.time = time;
			this.temp = temp;
			this.humiy = humiy;
		}
		public String toString(){
			return time.getTime()+",temp:"+temp+",humiy:"+humiy;
		}

	}
	int tempD = 1;
	int humiD = 1;
	private Random rand = new Random(47);
	float  temp = 28.0f;
	float  humiy = 45.0f;
	Calendar time = Calendar.getInstance();
	{
		time.set(Calendar.MINUTE, 30);
		time.set(Calendar.SECOND, 00);
	}
	private List<DataPoint> list = new ArrayList<DataPoint>();
	class Record implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			print("collect datapoint!");
			synchronized(GreenHouse.this){
			time.set(Calendar.MINUTE, time.get(Calendar.MINUTE)+30);
			if(rand.nextInt(5)==4)
				tempD = -tempD;
			temp = temp + tempD*(1.0f+rand.nextFloat());
			if(rand.nextInt(5)==4)
				humiD = -humiD;
			humiy = humiy + humiD*(1.0f+rand.nextFloat());
			list.add(new DataPoint((Calendar)time.clone(),temp,humiy));
			}
		}
		
	}
	class Over implements Runnable{
		public void run(){
			print("it`s all over now!");
			st.shutdownNow();
			new Thread(){
				public void run(){
					for(DataPoint dp:list){
						print(dp);
					}
				}
			}.start();
		}
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
		GreenHouse gh = new GreenHouse();
		gh.schedule(gh.new Over(), 5, TimeUnit.SECONDS);
		gh.repeat(gh.new Bell(), 100, 500, TimeUnit.MILLISECONDS);
		gh.repeat(gh.new LightOn(), 0, 500, TimeUnit.MILLISECONDS);
		gh.repeat(gh.new LightOff(), 200, 500, TimeUnit.MILLISECONDS);
		gh.repeat(gh.new WaterOn(), 0, 500, TimeUnit.MILLISECONDS);
		gh.repeat(gh.new WaterOff(), 10, 500, TimeUnit.MILLISECONDS);
		gh.repeat(gh.new Record(), 500, 500, TimeUnit.MILLISECONDS);
		gh.repeat(gh.new Day(), 0, 2000, TimeUnit.MILLISECONDS);
		gh.repeat(gh.new Night(), 1400, 2000, TimeUnit.MILLISECONDS);
	}

}
