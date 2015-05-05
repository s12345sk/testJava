/**   
* @Title: Generates.java 
* @Package fanxing 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-16 下午9:10:14 
* @version V1.0   
*/
package fanxing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import fanxing.coffee.Coffee;

import util.Generator;

public class Generates {
	public static <T> Collection<T> fill(Collection<T> co,Generator<T> gen,int n){
		for(int i=0;i<n;i++)
			co.add(gen.next());
		return co;
	}
	public static void main(String[] args){
		Collection<Coffee> co= fill(new ArrayList<Coffee>(),new CoffeeGenerator(),10);
		for(Coffee c:co)
			System.out.println(c);
		Collection<Integer> cl = fill(new ArrayList<Integer>(),new FibonacciGenerate(),10);
		for(int i:cl)
			System.out.println(i);
	}
	
	public <E extends Comparable<E>> E getMax(List<E> e){		
		Iterator<E> ite = e.iterator();
		E rel = ite.next();
		while(ite.hasNext()){
			E f = ite.next();
			if(rel.compareTo(f)<0)
				rel = f;
		}
		return rel;
	}

}
