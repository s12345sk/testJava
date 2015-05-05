/**   
* @Title: MultiLock.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-4 上午9:59:02 
* @version V1.0   
*/
package bingfa;

import java.util.concurrent.TimeUnit;

public class MultiLock {
	public synchronized void f1(int count){
		System.out.println("f1 use f2 --->"+count);
		if(count-- >0)
			f2(count);
	}
	public synchronized void f2(int count){
		System.out.println("f2 use f1 --->"+count);
		if(count-- >0)
			f1(count);
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
		final MultiLock lo = new MultiLock();
//		lo.f1(10);
		new Thread(){
			public void run(){
				lo.f1(10);
			}
		}.start();
//		try {
//			TimeUnit.SECONDS.sleep(3);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		new Thread(){
			public void run(){
				lo.f2(12);
			}
		}.start();
	}

}
