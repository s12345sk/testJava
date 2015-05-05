/**   
* @Title: RegTest.java 
* @Package test 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-28 上午10:43:34 
* @version V1.0   
*/
package test;

import java.util.regex.Pattern;

public class RegTest {
	private static Pattern emoji = Pattern.compile(
			"[\ud80c\udc00-\ud89c\udfff]|[\u2600-\u27ff]",
	        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
	private static String filterEmoji(String content){
		return emoji.matcher(content).replaceAll("");
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
//		String s = "[\ud80c\udc00-\ud89c\udfff]|[\u2600-\u27ff]";
//		
		String ss = "你好 a smiley \uD83C\uDFA6 face\uD860\uDD5D \uD860\uDE07 \uD860\uDEE2 \uD863\uDCCA \uD863\uDCCD ";
//		 Pattern emoji = Pattern.compile(
//			        s,
//			        Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
//		String k =  emoji.matcher(ss).replaceAll("");
//		String k =  ss.replaceAll("ud", "");
		System.out.println(filterEmoji(ss));
//		System.out.println(k);
	}

}
