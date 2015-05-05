/**   
* @Title: IntGenerator.java 
* @Package concurrent.event 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-1-6 下午9:37:16 
* @version V1.0   
*/
package concurrent.event;

public abstract class IntGenerator {
	private volatile boolean can = false;
	public abstract int next();
	public void cancel(){ can = true;}
	public boolean isCancle(){return can;}

}
