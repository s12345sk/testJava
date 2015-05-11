//package concurrent;
//
///**   
//* @Title: SendXiao.java 
//* @Package concurrent 
//* @Description:  
//* @author kang.sun(kanggood@126.com)   
//* @date 2014-12-25 下午2:49:59 
//* @version V1.0   
//*/
//
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
//public class SendXiao {
//	public static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10000);
//
//
//	public static void main(String[] args) throws NumberFormatException, IOException{
//		  
//		ExecutorService es = Executors.newFixedThreadPool(6);
//		for(String s:args){
//		es.execute(new Get(s));		
//		}
//		for(int i=0;i<5;i++)
//			es.execute(new Send());
//		
//		
//	}
//	
//	
//
//}
//class Get implements Runnable{
//	private String fileName;
//	public Get(String fileName){
//		this.fileName = fileName;
//	};
//
//	/* (non-Javadoc)
//	 * @see java.lang.Runnable#run()
//	 */
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		
//	
//		String s;
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(new File(fileName)));
//			while((s=br.readLine())!=null){
////			System.out.println(s);
//				SendXiao.queue.put(s);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
//}
//class Send implements Runnable{
//	private static int count=0;
//	private static int sum=0;
//	private final int id = ++count;
//	private static final String send_msg_url = "https://api.renren.com/v2/public/platform/text/send";
//	private static final String accessToken="240208|6.bd0ad482a0824be07de6d6dd2fc4018d.2592000.1422082800-413096671";
//	private static final String message1="【站内信下线通知】\r\n亲爱的用户朋友们，站内信将和我们告别了：我们计划在2015年1月14日至21号分批下线站内信系统。请在截止日期之前通过“msg.renren.com”进入站内信整理、收藏过往的内容吧（到时，系统会删除所有站内信内容）。给您带来的不便和困扰表示抱歉。以后我们会用公众号代替站内信给大家发送消息。";
//	private static final String message="【站内信下线通知】\r\n亲爱的用户朋友们，遗憾的告诉大家：站内信将和我们告别了。为了方便大家整理过往的回忆，我们特开发了一键下载功能。站内信系统将在2015年1月14日—2月9号之间分批下线，请在截止日之前通过“http://msg.renren.com”进入站内信整理、收藏过往的内容吧（到时，系统会删除所有站内信内容）。给您带来的不便和困扰表示抱歉。虽然站内信下线了，但其他通讯功能不受影响，以后我们会用公众号代替站内信给大家发送消息。";
//	HttpClient client = new HttpClient();
//	/* (non-Javadoc)
//	 * @see java.lang.Runnable#run()
//	 */
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		//100次未取出数据，则停止执行
//		int sum=0;
//		
//		while(true){
//			String userId;
//			try {
//				userId = SendXiao.queue.take();
//				if(userId==null){
//					System.out.print(" kong "+(sum++));
//					Thread.sleep(1000);
//					if(sum==10)break;
//					continue;
//				}
//				
//				send(userId);
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				break;
//			}
//			
//		}
//
//	
//	}
//	//处理方法
//	private void send(String userId) {
//		
//
//	PostMethod method = new PostMethod(send_msg_url);
//		client.getHttpConnectionManager().getParams()
//				.setConnectionTimeout(1000);
//		
//		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
//				"UTF-8");
//		method.addParameter("access_token", accessToken);
//		method.addParameter("content", message);
//		method.addParameter("userId", String.valueOf(userId));
//
//		try {
//			client.executeMethod(method);
//			System.out.println("thread---"+userId+"--"+id+":"+(++sum)+":result:"+method.getResponseBodyAsString());
//		} catch (HttpException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			
//		method.releaseConnection();
//		}
//	}
//	
//}
