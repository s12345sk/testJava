/**   
* @Title: CyclicBarrierTest.java 
* @Package concurrent 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-9 下午7:58:02 
* @version V1.0   
*/
package concurrent;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
	private static int[][] times = {{1,2,4,2,3},{5,3,6,1,2},{4,2,2,1,5}};
	private static String[] places = {"shanDong","shangHai","beiJing","nanYang","xiaMen"};
	private static String[] names = {"leg","bike","mobile"};
	private static int i = 0;
	private static CyclicBarrier cb = new CyclicBarrier(3,new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("哈哈哈，目标"+(places[i++])+"已达成！");
		}
		
	});
	public static void main(String[] args){
		ExecutorService es = Executors.newFixedThreadPool(3);
		for(int i=0;i<3;i++)
			es.execute(new Tour(names[i],times[i],cb,places));
		es.shutdown();
	}

}
class Tour extends Thread{
	private String name;//旅行团名字
	private int[] times;//时间
	private CyclicBarrier cb;//几个团需要保持一致
	private String[] places;
	public Tour(String name,int[] times,CyclicBarrier cb,String[] places){
		this.name = name;
		this.times = times;
		this.cb = cb;
		this.places = places;
	}
	public void run(){
		for(int i=0;i<times.length;i++){
			try {
				Thread.sleep(times[i]*1000);
				System.out.println(now()+name+" arrived "+places[i]);
				cb.await();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static String  now(){
		SimpleDateFormat smf = new SimpleDateFormat("HH:mm:ss");
		return smf.format(new Date())+":";
	}
}
