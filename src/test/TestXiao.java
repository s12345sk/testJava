/**   
* @Title: TestXiao.java 
* @Package test 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-4 上午11:16:39 
* @version V1.0   
*/
package test;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class TestXiao {

	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	private static final String send_msg_url = "https://api.renren.com/v2/public/platform/text/send";
	private static final String accessToken="240208|6.bd0ad482a0824be07de6d6dd2fc4018d.2592000.1422082800-413096671";
	private static final String message="【站内信下线通知】\r\n亲爱的用户朋友们，站内信将和我们告别了：我们计划在2015年1月14日下线站内信系统。请在截止日期之前通过“msg.renren.com”进入站内信整理、收藏过往的内容吧（到时，系统会删除所有站内信内容）。给您带来的不便和困扰表示抱歉。以后我们会用公众号代替站内信给大家发送消息。";
	private static final String message1="客服回复了<a href='http://help.renren.com'   target='_blank'>您的问题</a>";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(send_msg_url);

		
		client.getHttpConnectionManager().getParams()
				.setConnectionTimeout(1000);
		
		method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,
				"UTF-8");
		method.addParameter("access_token", accessToken);
		method.addParameter("content", message1);
		method.addParameter("userId", String.valueOf("587360935"));

		try {
			client.executeMethod(method);
//			System.out.println("thread---"+id+":"+(++sum)+":result:"+method.getResponseBodyAsString());
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			method.releaseConnection();
		}
	

	}

}
