/**   
* @Title: Stack.java 
* @Package effectivejava 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-22 下午8:37:05 
* @version V1.0   
*/
package effectivejava;

import java.util.LinkedList;

public  class Stack<T> {
	private T[] elements;
	private int size = 0;
	private static final int DEFAULT = 3;
	public  Stack(){
		//泛型类不是真正的类，是不可以实例化的
		elements = (T[])new Object[DEFAULT]; 
	}
	public void put(T item){
		if(canPut())
			elements[size++] = item;
		else
			System.out.println("full");
	}
	public boolean canPut(){
		if(size<DEFAULT)
			return true;
		return false;
	}
	//重大问题。数组对象并未清除。如果是get的话，应该清理当前的对象
	public T get(){
		if(size<=0){
			System.out.println("no space!");
			return null;
		}
		return elements[--size];
	}
	public static void main(String[] args){
		Stack<String> st = new Stack();
		LinkedList ls = new LinkedList();
		for(String s:"dfgdddeg".split("|"))
			st.put(s);
		for(int i=0;i<10;i++)
			System.out.println(st.get());
	}

}
