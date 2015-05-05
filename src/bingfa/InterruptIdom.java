/**   
* @Title: InterruptIdom.java 
* @Package bingfa 
* @Description:  finally 从句保证处理情况无论在什么情况下，都会触发
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-5 上午10:17:33 
* @version V1.0   
*/
package bingfa;
import static tool.Print.*;

import java.util.concurrent.TimeUnit;
public class InterruptIdom {

	/**
	 * @throws InterruptedException  
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thread t = new Thread(new Blocked());
		t.start();
		t.sleep(1050);
		t.interrupt();
	}

}
class NeedClean{
	private final int id;
	public NeedClean(int id){
		this.id = id;
		print("create clean "+id);
	}
	public void clean(){
		print("cleaned id is "+id);
	}
}
class Blocked implements Runnable{
	private volatile double d = 0.0;
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			while(!Thread.interrupted()){
				NeedClean nc1 = new NeedClean(1);
				try{
					print("sleeping 1 seconds will");					
					TimeUnit.SECONDS.sleep(1);
					
					NeedClean nc2 = new NeedClean(2);
					try{
						print("start  clacute");
						for(int i=0;i<25000;i++)
							d = d + (Math.E+d)/(Math.PI+d);
						print("calcute finish");
					}finally{
						nc2.clean();
					}
				}finally{
					nc1.clean();
				}
			}
		}catch(InterruptedException e){
			print("exit with the interrupt!");
		}
	}
	
}
