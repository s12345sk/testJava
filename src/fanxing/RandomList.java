/**   
* @Title: RandomList.java 
* @Package fanxing 
* @Description: 容器，用来存储不同的内容 
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-15 下午8:50:03 
* @version V1.0   
*/
package fanxing;

import java.util.ArrayList;
import java.util.Random;

public class RandomList<T> {
	private ArrayList<T> list = new ArrayList<T>();
	Random rand = new Random(47);
	public void put(T item){
		list.add(item);
	}
	public T get(){
		if(list.size()==0)
			return null;
		return list.get(rand.nextInt(list.size()));
	}
	public static void main(String[] args){
		RandomList<String> rl = new RandomList<String>();
		System.out.println(rl.get());
		for(String s:"hello i am a good man ha ha ha ha".split(" "))
			rl.put(s);
		for(int i=0;i<4;i++)
			System.out.println(rl.get());
	}
	
}
