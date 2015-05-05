package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;
public class YaliTest extends Thread{
	Socket socket = null;
    DataOutputStream output =null;
    DataInputStream input = null;
    String ip = "127.0.0.1";
//	String ip = "130.1.10.221";
//	String ip = "192.168.190.135";
	int port = 1301;
	public void run(){
		
		
		//ReturnMessage response = null;
		
		try{
			
			
			socket = new Socket(ip, port);
			socket.setSoTimeout(60 * 10000);
			System.out.println("socket is"+socket);
			output = new DataOutputStream(socket.getOutputStream());
//			测试单笔核查
//			String rqstMsg = "<CFX><MSG><orgCode>313261068019</orgCode><交易码>0001</交易码><MsgNo>0001</MsgNo><Name1>王三</Name1><ID1>345623198909124234</ID1><UserCode>6801</UserCode><BusinessCode>00</BusinessCode></MSG></CFX>";			
			String rqstMsg = "<CFX><MSG><ID1>345623198909124234</ID1><Name1>王三</Name1><BusinessCode>01</BusinessCode><MsgNo>0001</MsgNo><交易码>0001</交易码><UserCode>admin</UserCode><orgCode>0045601</orgCode></MSG></CFX>";
//			测试200笔批量核查申请
//			String rqstMsg = "<CFX><MSG><FilePath>c:\\temp\\200test.txt</FilePath><MsgNo>1008</MsgNo><BusinessCode>01</BusinessCode><交易码>1001prepare</交易码><UserCode>admin</UserCode><orgCode>313703001113</orgCode></MSG></CFX>";			
//			测试普通批量核查申请
//			String rqstMsg = "<CFX><MSG><FilePath>test.txt</FilePath><MsgNo>1001</MsgNo><BusinessCode>01</BusinessCode><交易码>1001prepare</交易码><UserCode>admin</UserCode><orgCode>0100</orgCode></MSG></CFX>";			
//			String rqstMsg = "<CFX><MSG><orgCode>313261000018</orgCode><FilePath>VFP201310160001</FilePath><交易码>1001prepare</交易码><MsgNo>1001</MsgNo><UserCode>0000001</UserCode><BusinessCode>01</BusinessCode></MSG></CFX>";
//			测试批量核查结果查询
//			String rqstMsg = "<CFX><MSG><FilePath>201309120707122302.txt</FilePath><MsgNo>1008</MsgNo><BusinessCode>01</BusinessCode><交易码>batchresult</交易码><UserCode>admin</UserCode><orgCode>313703001113</orgCode></MSG></CFX>";			
//			测试单笔反馈
//			String rqstMsg = "<CFX><MSG><orgCode>0010</orgCode><ID1>345623198909124234</ID1><Name1>12</Name1><TransCode>0005</TransCode><BusinessCode>01</BusinessCode><MsgNo>0005</MsgNo><交易码>0005</交易码><CheckResult1>05</CheckResult1><Remark1>12</Remark1><UserCode>admin</UserCode></MSG></CFX>";
//			System.out.println("发送报文为：" + rqstMsg);
			
			byte[] packet = rqstMsg.getBytes();
            
	        String lenC = Integer.toString(packet.length);
	        lenC = ("00000"+lenC);
	        int len = lenC.length();
	        lenC = lenC.substring(len-6,len);
	        output.write(lenC.getBytes("gbk"));
            output.write(packet);
            output.flush();
// 		    System.out.println("fasong");
            input = new DataInputStream(socket.getInputStream());
            byte[] size_head = new byte[6];
            input.read(size_head, 0, 6);//读出报文长度
   		 int length = new Integer(new String(size_head,"utf-8")).intValue();
   		 
   		 byte[] head_5666 = new byte[length];
   		input.read(head_5666);//读出报文内容
//            System.out.println("接收报文为:" + new String(head_5666));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
            //关闭连接
            if (socket != null) {
                try {
                	socket.close();
                } catch (IOException e) {
                }
            }
            if(input!= null){
                try {
                	input.close();
//                	System.out.println("关闭DataInputStream 成功...");
                } catch (IOException e) {
                }
            } 
            if(output!= null){
                try {
                	output.close();
//                	System.out.println("关闭DataOutputStream 成功...");
                } catch (IOException e) {
                }
            }
        }
	}

	public static void main(String[] args) throws InterruptedException {
			ExecutorService service =  Executors.newFixedThreadPool(10);
			for(int i=0;i<99;i++){
				Thread.sleep(1000);
			  service.execute(new YaliTest());
//			  System.out.println(i);
			}
			System.exit(1);
			
			
			
		
		

	}

}
