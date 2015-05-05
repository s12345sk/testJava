/**   
* @Title: UI.java 
* @Package concurrent 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-6 下午9:13:02 
* @version V1.0   
*/
package concurrent;

import java.io.IOException;

public class UI extends Thread{
	private static double d = 1;
	public UI(){
		setDaemon(true);
		start();
	}
	public void run(){
		while(d>0)
			d =d+ (Math.PI +Math.E)/d;
	}
	public static void main(String[] args) throws IOException{
		new UI();
		System.in.read();
		System.out.println(d);
	}

}
