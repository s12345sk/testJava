/**   
* @Title: Test1.java 
* @Package effectivejava 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-22 下午8:20:45 
* @version V1.0   
*/
package effectivejava;

import java.util.Date;

public class Test1 {
	public static void cal(){
		Long l = 0l;
		long t1 = (new Date()).getTime();
		for(int i=0;i<Integer.MAX_VALUE;i++){
			l += i;
		}
		long t2 = (new Date()).getTime();
		System.out.println(l+",耗时为："+(t2-t1)+"毫秒");
	}
	public static void main(String[] args){
		cal();
	}

}
