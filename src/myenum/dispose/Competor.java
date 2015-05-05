/**   
* @Title: Competor.java 
* @Package myenum.dispose 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-21 上午10:52:14 
* @version V1.0   
*/
package myenum.dispose;

public interface Competor<T extends Competor<T>> {
	Outcome compete(T competor);
}
