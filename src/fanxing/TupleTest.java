/**   
* @Title: TupleTest.java 
* @Package fanxing 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-11 下午9:55:38 
* @version V1.0   
*/
package fanxing;
import util.*;
public class TupleTest {
//	//原先的只能实例化指定类型的，改进后的可以实例化任意类型的参数，并且使用同一个方法，通过传的参数确定怎么使用。工厂模式
//	static TwoTuple<String,Integer> f(){
//		return new TwoTuple("hello",12);
//	}
//	static ThreeTuple<A,String,Integer> g(){
//		return new ThreeTuple(new A(),"hello",12);
//	}
//	static FourTuple<A,B,String,Integer> h(){
//		return new FourTuple(new A(),new B(),"hello",12);
//	}
//	static FiveTuple<A,B,A,String,Integer> i(){
//		return new FiveTuple(new A(),new B(),new A(),"hello",12);
//	}
//	private static class Node<T>{
//		T item;
//		Node<T> node;
//	}
//	static <A,B> TwoTuple<A,B> tuple(A a,B b){
//		return new TwoTuple(a,b);
//	}
//	static <A,B,C> ThreeTuple<A,B,C> tuple(A a,B b,C c){
//		return new ThreeTuple(a,b,c);
//	}
//	static <A,B,C,D> TwoTuple<A,B> tuple(A a,B b,C c,D d){
//		return new FourTuple(a,b,c,d);
//	}
//
//	
//	public static void main(String[] args){
////		System.out.println(f());
////		System.out.println(g());
////		System.out.println(h());
////		System.out.println(i());
//		System.out.println(tuple(1,3,"sd"));
//		System.out.println(tuple(1,3));
//		System.out.println(tuple(new A(),new B()));
//		System.out.println(tuple(new A(),new B(),3,4));
//	}
//
}
class A{}
class B{}
