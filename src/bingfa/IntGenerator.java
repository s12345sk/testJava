/**   
* @Title: IntGenerator.java 
* @Package bingfa 
* @Description:  产生int的工厂类
* @author kang.sun(kanggood@126.com)   
* @date 2015-2-28 上午9:55:50 
* @version V1.0   
*/
package bingfa;

public abstract class IntGenerator {
	private boolean isConcel;
	public abstract int next();
	public boolean isConcled(){
		return isConcel;
	}
	public void concel(){
		isConcel = true;
	}
}
