/**   
* @Title: Container.java 
* @Package fanxing 
* @Description:  基本的泛型测试，在类开始时候指定特殊的类型。告诉编译器，你需要什么类型，显示说明类型
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-11 下午9:18:11 
* @version V1.0   
*/
package fanxing;

public class Container<T> {
	private T sth;
	public Container(T sth){
		this.sth = sth;
	}
	public T get(){
		return sth;
	}
	public  <E> void set(E e){
		
	}
	public static void main(String[] args){
		Container<String> c = new Container<String>("hello world!");
		String s = c.sth;
		System.out.println(s);
		
		Container<Person> p = new Container<Person>(new Person());
		System.out.println(p.get().getClass().getName());
		Container<Thing> t = new Container<Thing>(new Thing());
		System.out.println(t.get().getClass().getName());
	}

}

class Mobile{
	private Person p;
	public Mobile(Person p){
		this.p = p;
	}
	public Person get(){return p;}
}
class Person{}
class Thing{}