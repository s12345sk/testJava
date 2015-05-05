/**   
* @Title: WatchProtype.java 
* @Package designmode.observemode 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-3-25 下午2:55:00 
* @version V1.0   
*/
package designmode.observemode;

import java.util.ArrayList;
import java.util.List;

public class WatchProtype {
		
	private List<Watcher> list = new ArrayList<Watcher>();
	public void add(Watcher watch){
		list.add(watch);
	};
	public void remove(Watcher watch){
		list.remove(watch);
	}
	public void notify(String state){
		for(Watcher w:list){
			w.update(state);
		}
	}

}
