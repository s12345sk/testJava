/**   
* @Title: MapTest.java 
* @Package test 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-11-19 下午4:43:41 
* @version V1.0   
*/
package test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Util{
	int id;
	public Util(int id){
		this.id = id;
	}
	public String toString(){
		return id+"";
	}
}
public class MapTest {
	public static void main(String[] args){
		Map<Util,Integer> map = new HashMap<Util,Integer>();
		map.put(new Util(1),1);
		map.put(new Util(2),2);
		map.put(new Util(2),3);
		map.put(new Util(6),3);
		map.put(new Util(4),3);
		map.put(new Util(6),3);
		System.out.println(map.keySet());
		Map<Util,Integer> map1 = new TreeMap<Util,Integer>(new Comparator<Util>(){

			@Override
			public int compare(Util u1,Util u2) {
				// TODO Auto-generated method stub
				if((u1.id-u2.id)==0)
					return -1;
				return u1.id-u2.id;
			}
			
		});
		map1.putAll(map);
		System.out.println(map1.keySet());
		
	}

}
