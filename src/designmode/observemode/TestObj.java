/**   
* @Title: TestObj.java 
* @Package designmode.observemode 
* @Description:  推需要传参数 拉的话，则是整个对象作为观察者的属性
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-25 下午2:59:38 
* @version V1.0   
*/
package designmode.observemode;
import static tool.Print.*;
public class TestObj extends WatchProtype{

	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Watcher o = new WatchOne();
		TestObj to = new TestObj();
		to.add(o);
		to.change("ssss");
		to.change("bbbb");
		
	}
	public void change(String state){
		print("obj is "+state);
		this.notify(state);
	}

}

