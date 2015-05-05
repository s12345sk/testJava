/**   
* @Title: Obj.java 
* @Package effectivejava 
* @Description:  避免创建不必要的对象
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-22 下午8:07:39 
* @version V1.0   
*/
package effectivejava;

import java.util.Calendar;
	
public class Obj {
	private static final long ST;
	private static final long EN;
	static{
		Calendar cl = Calendar.getInstance();
		cl.set(1990, 12, 2);
		ST = cl.getTimeInMillis();
		Calendar cl1 = Calendar.getInstance();
		cl1.set(1990, 12, 4);
		EN = cl1.getTimeInMillis();
	}
	public static boolean isBoomer(){
		return false;
	}

}
