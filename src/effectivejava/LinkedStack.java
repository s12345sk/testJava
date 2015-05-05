/**   
* @Title: LinkedStack.java 
* @Package effectivejava 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-24 下午1:41:22 
* @version V1.0   
*/
package effectivejava;

public class LinkedStack<U> {
	
	private class Node<U>{
		private U item;
		private Node<U> next;
		public Node(){
			item = null;
			next = null;
		}
		public Node(U item,Node<U> next){
			this.item = item;
			this.next = next;
		}
	}
	private Node<U> top = new Node();
	public void add(U item){
		top = new Node(item,top);
	}
	public boolean isEnd(){
		if(top.item==null&&top.next==null)
			return true;
		return false;
	}
	public U pop(){
		U item = top.item;
//		if(!isEnd())
			top = top.next;
		return item;
	}
	public static void main(String[] args){
		LinkedStack<String> ls = new LinkedStack();
		for(String s:"df e g".split(" "))
			ls.add(s);
		String ss;
		while((ss=ls.pop())!=null)
			System.out.println(ss);
	}

}
