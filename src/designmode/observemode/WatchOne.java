/**   
* @Title: WatchOne.java 
* @Package designmode.observemode 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-25 下午3:00:21 
* @version V1.0   
*/
package designmode.observemode;
import static tool.Print.*;
public class WatchOne implements Watcher{

	
	/* (non-Javadoc)
	 * @see designmode.observemode.Watcher#update(java.lang.String)
	 */
	@Override
	public void update(String state) {
		// TODO Auto-generated method stub
		print("now it is "+state);
	}

}
