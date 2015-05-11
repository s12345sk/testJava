///**   
//* @Title: SendXiao.java 
//* @Package concurrent 
//* @Description:  
//* @author kang.sun(kanggood@126.com)   
//* @date 2014-12-25 下午2:49:59 
//* @version V1.0   
//*/
//package concurrent;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.BlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.TimeUnit;
//
//import java.util.concurrent.Executors;
//
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//
//public class CopyOfSendXiao {
//	public static BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
//	
//
//	public static void main(String[] args) throws NumberFormatException, IOException{
//		  
//		ExecutorService es = Executors.newFixedThreadPool(6);
//		for(String s:args){
//		es.execute(new Get1());		
//		}
//		for(int i=0;i<4;i++)
//			es.execute(new Send1());
//		
//	}
//	
//	
//
//}
//class Get1 implements Runnable{
//	
//
//	/* (non-Javadoc)
//	 * @see java.lang.Runnable#run()
//	 */
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//	
//		
//			
//			for(int i=0;i<100;i++){
////			System.out.println(s);
//				try {
//					CopyOfSendXiao.queue.put(i);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			
//		
//		
//	}
//	
//}
//class Send1 implements Runnable{
//
//
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		//100次未取出数据，则停止执行
//		int sum=0;
//		
//		while(true){
//			int userId;
//			try {
//				userId = CopyOfSendXiao.queue.take();
//				
//					System.out.println(userId+"  "+CopyOfSendXiao.queue.size());
//					Thread.sleep(1000);
//					sum++;
//					
//				
//				
//			
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				
//			}
//			
//		}
//		
//	
//	}
//	
//	
//}
