/**   
* @Title: FilledList.java 
* @Package rtti 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-11-19 下午3:18:56 
* @version V1.0   
*/
package rtti;

import java.util.ArrayList;
import java.util.List;

class Counter{
	private static int count = 0;
	private final int id = ++count;
	public String toString(){
		return "count "+id;
	}
}

public class FilledList<T> {
	private Class<T> type;
	public  FilledList(Class<T> type){
		this.type = type;
	}
	public List<T> fill(int size) throws InstantiationException, IllegalAccessException{
		List<T> list = new ArrayList<T>(size);
		for(int i=0;i<size;i++){
			list.add(type.newInstance());
		}
		return list;
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException{
		FilledList<Counter> fl = new FilledList(Counter.class);
		System.out.println(fl.fill(10));
	}

}
