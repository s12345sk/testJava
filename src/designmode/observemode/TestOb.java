/**   
* @Title: TestOb.java 
* @Package designmode.observemode 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-25 下午3:28:17 
* @version V1.0   
*/
package designmode.observemode;

import java.util.Observable;

public class TestOb extends Observable{
	private int num;
	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestOb to = new TestOb();
		to.addObserver(new ObserverIm());
		to.addObserver(new ObserverIm());
		to.addObserver(new ObserverIm());
		to.setNum(1);
		to.setNum(3);
	}
	public void setNum(int n){
		num = n;
		this.setChanged();
		this.notifyObservers(num);
	}
	public int getNum(){
		return num;
	}

}
