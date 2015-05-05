/**   
* @Title: Enums.java 
* @Package util 
* @Description:  随进选取枚举的工具类
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-1 上午9:50:33 
* @version V1.0   
*/
package util;

import java.util.Random;

public class Enums {
	private static Random rand = new Random();
	public static <T extends Enum<T>>  T rand(Class<T> cl){
		return rand(cl.getEnumConstants());
	}
	public static <T> T rand(T[] values){
		return values[rand.nextInt(values.length)];
	}

}
