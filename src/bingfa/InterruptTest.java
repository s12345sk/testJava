/**   
* @Title: InterruptTest.java 
* @Package bingfa 
* @Description:  线程的中断方法示例
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-2 下午5:41:02 
* @version V1.0   
*/
package bingfa;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class InterruptTest {
	private static ExecutorService es = Executors.newCachedThreadPool();
	public static void test(Runnable run){
		Future<?> f = es.submit(run);
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ahhahah shou dong de gogogogo !"+run.getClass().getName());
		f.cancel(true);
		System.out.println("ahhahah shou dong de finish !"+run.getClass().getName());
	}
	public static void testWithHand() throws IOException{
		ServerSocket ss = new ServerSocket(8080);
		InputStream ins = new Socket("localhost",8080).getInputStream();
		IoBlocked io = new IoBlocked(ins);
		es.execute(io);
		IoBlocked io1 = new IoBlocked(System.in);
		es.execute(io1);
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("现在开是关闭全部线程：");
		es.shutdown();
		System.out.println("Closing:"+ins.getClass().getName());
		ins.close();
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Closing:"+System.in.getClass().getName());
		System.in.close();
		
		
	}
	/**
	 * @throws IOException  
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
//		InterruptTest.test(new SleepBolcked());
//		InterruptTest.test(new IoBlocked(System.in));
//		System.in.close();
//		InterruptTest.test(new SynchronizedBlocked());
//		System.out.println("f i n i s h !");
//		System.exit(0);
		testWithHand();
	}

}

class SleepBolcked implements Runnable{

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			TimeUnit.SECONDS.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
class IoBlocked implements Runnable{
	private InputStream in;
	public IoBlocked(InputStream in){
		this.in = in;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("io read start:gogogogogo");
		try {
			in.read();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(Thread.currentThread().isInterrupted())
				System.out.println("io bolck is interrupt!!");
			else
				e.printStackTrace();
		}
		System.out.println("io read start:finish");
	}
	
}

class SynchronizedBlocked implements Runnable{
	private synchronized void f(){
		while(true)
			Thread.yield();
	}
	public SynchronizedBlocked(){
		new Thread(){
			public void run(){
				f();
			}
		}.start();
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("hah  this is a question of SynchronizedBlocked:gogogog!");
		f();
		System.out.println("syn is over now!");
	}
	
}