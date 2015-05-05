/**   
* @Title: ObserverIm.java 
* @Package designmode.observemode 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-25 下午3:27:13 
* @version V1.0   
*/
package designmode.statemode;

import java.util.Observable;
import static tool.Print.*;

import java.util.Observer;
import static tool.Print.*;
public class ObserverIm implements Observer{

	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		User user = (User)o;
		if(user.getState()==UserState.NORMAL){
			print(user+" good luck ");
		}
		if(user.getState()==UserState.WARN){
			print(user+" waring! ");
		}
		if(user.getState()==UserState.FREEZE){
			user.clean();
			print(user+" free! ");
		}
			
	}

}
