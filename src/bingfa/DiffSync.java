/**   
* @Title: DiffSync.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-2 下午2:50:46 
* @version V1.0   
*/
package bingfa;

public class DiffSync {
	public Object syncObject = new Object();
	public synchronized void f(){
		for(int i=0;i<5;i++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" f()");
		}
	}
	public void g(){
		synchronized(syncObject){
			for(int i=0;i<5;i++){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(" g()");
			}
		}
	}
	public static void main(String[] args){
		final DiffSync di = new DiffSync();
		new Thread(){
			public void run(){
				di.f();
			}
		}.start();
		di.g();
	}

}
