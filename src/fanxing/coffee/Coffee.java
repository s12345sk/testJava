/**   
* @Title: Coffee.java 
* @Package fanxing.coffee 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-15 下午9:24:11 
* @version V1.0   
*/
package fanxing.coffee;

public class Coffee {
	private static int count = 0;
	private final  int id = count++;
	public String toString(){
		return getClass().getSimpleName()+" "+id;
	}
	

}
