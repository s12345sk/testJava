/**   
* @Title: LinkedStack.java 
* @Package fanxing 
* @Description:  链表的实现，可以自动增加。top总是在现在的位置，新增的节点
* top成为新节点，新的节点下一个位置为现在的top
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-15 下午8:20:36 
* @version V1.0   
*/
package fanxing;

public class LinkedStack<T> {
	private static class Node<U>{
		U item;
		Node<U> next;
		public Node(){
			item = null;
			next = null;
		}
		public Node(U item,Node<U> next){
			this.item = item;
			this.next = next;
		}
		public boolean isEnd(){
			return item==null&&next==null;
		}
	}
	private Node<T> top = new Node();
	public void put(T item){
		top = new Node<T>(item,top);
	
	}
	public  T get(){
		T rel = top.item;
		if(!top.isEnd())
			top = top.next;
		return rel;
	}
	public static void main(String[] args){
		LinkedStack<String> ln = new LinkedStack<String>();
		for(String s:"hello i am superMan".split(" "))
			ln.put(s);
		String item;
		while((item=ln.get())!=null)
				System.out.println(item);
	}
	

}
