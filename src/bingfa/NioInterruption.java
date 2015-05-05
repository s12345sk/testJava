/**   
* @Title: NioInterruption.java 
* @Package bingfa 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-3 下午3:20:42 
* @version V1.0   
*/
package bingfa;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NioInterruption {

	/**
	 * @throws InterruptedException 
	 * @throws IOException  
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ExecutorService es = Executors.newCachedThreadPool();
		ServerSocket ss = new ServerSocket(8080);
		InetSocketAddress isa = new InetSocketAddress("localhost",8080);
		SocketChannel sc1 = SocketChannel.open(isa);
		SocketChannel sc2 = SocketChannel.open(isa);
		Future<?> f = es.submit(new NioBlocked(sc1));
		es.execute(new NioBlocked(sc2));
		System.out.println("all close:");
		es.shutdown();
		
		System.out.println("all close cancel:");
		Thread.sleep(500);
		f.cancel(true);
		Thread.sleep(500);
		System.out.println("all close cancel close:");
		Thread.sleep(500);
		sc2.close();
	}

}
class NioBlocked implements Runnable{
	private final SocketChannel sc;
	public NioBlocked(SocketChannel sc){
		this.sc = sc;
	}
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("start read sth:");
			sc.read(ByteBuffer.allocate(3));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" exit !"+this);
	}
	
}