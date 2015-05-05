/**   
* @Title: SemaphoreTest.java 
* @Package review 
* @Description: 三个小姐，十个客人慢慢来。每次说明是第几个，下次是谁
* 可以对有限制数目的可利用资源进行分配
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-9 下午2:08:26 
* @version V1.0   
*/
package review;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class SemaphoreTest extends Thread{
	private int id;
	private Pool pool;
	public SemaphoreTest(int id,Pool pool){
		this.id = id;
		this.pool = pool;
	}
	public void run(){
		XiaoJie xj = pool.getFuck(id);
		int time = (int)(Math.random()*1000);
		System.out.println("嫖客"+id+"开始艹小姐"+xj.getName()+"了！啊- -好爽- - 啊- -");
		try {
			Thread.sleep(time);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pool.afterFuck(xj);
	}
	
	public static void main(String[] args){
		ExecutorService es = Executors.newCachedThreadPool();
		Pool pool = new Pool();
		for(int i=0;i<20;i++)
			es.submit(new SemaphoreTest(i,pool));
		es.shutdown();
		
	}
	
	

}

class Pool{
	 private static final int SIZE = 3;
	 private static String[] names = new String[]{"li","yun","xu"};
	 private static Semaphore sh = new Semaphore(SIZE);
	 private static  XiaoJie[] xiaoJies = new XiaoJie[SIZE];
	 static boolean[] bool = new boolean[SIZE];
	 static{
		 for(int i=0;i<SIZE;i++)
			 xiaoJies[i] = new XiaoJie(names[i]);
	 }
	 public XiaoJie getFuck(int id){
		 try {
			 System.out.println(id+" 将要入场！");
			sh.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return canFuckNext();
	 }
	 public void afterFuck(XiaoJie s){
//		 sh.release();
//		 markAsCanFuck(s);
		 if(markAsCanFuck(s)){
			 s.setCishu(s.getCishu()+1);
			 System.out.println(s.getName()+"刚刚被艹完！已经被草了"+s.getCishu()+"次!");
			 sh.release();
		 }
		 
	 }
	 //对于循环操作，一定要注意循环的结束。
	 public synchronized  XiaoJie canFuckNext(){
		 XiaoJie xj = null;
		 for(int i=0;i<SIZE;i++){
			 if(!bool[i]){
				 bool[i] = true;
				 xj = xiaoJies[i];
				 System.out.println(xj.getName()+"马上要被艹了！她已经被艹了"+xj.getCishu()+"次！");
				 break;
				 
			 }
		 }
		 return xj;
	 }
	 public synchronized  boolean markAsCanFuck(XiaoJie s){
		 boolean bools = false;
		 System.out.println("-------------------"+s.getName()+"将要可以被艹啦！");
		 for(int i=0;i<SIZE;i++){
			 if(s==xiaoJies[i]){
				 bool[i] = false;
				 bools =  true;
				 System.out.println("-------------------"+s.getName()+"真的可以额！大家快去！");
				 break;
			 }
		 }
		 return bools;
	 }
	 
	
	
}
class XiaoJie{
	private String name;
	private int cishu = 0;
	public XiaoJie(String name){
		this.name = name;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the cishu
	 */
	public int getCishu() {
		return cishu;
	}
	/**
	 * @param cishu the cishu to set
	 */
	public void setCishu(int cishu) {
		this.cishu = cishu;
	}
	
	
}
