/**   
* @Title: UsecaseTracker.java 
* @Package annotation 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-21 下午4:26:43 
* @version V1.0   
*/
package annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import annotation.types.UseCase;
import static tool.Print.*;
public class UsecaseTracker {
	
	public static void track(List<Integer> cases,Class<?> cl){
		Method[] m = cl.getMethods();
		for(Method md:m){
			UseCase uc = md.getAnnotation(UseCase.class);
			if(uc!=null){
				print("usecase:id "+uc.id()+",des is "+uc.des());
				cases.remove(Integer.valueOf(uc.id()));
			}
		}
		for(Integer i:cases){
			print("usecase "+i+" is missed!");
		}
	}
	
	/** 
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> cases = new ArrayList<Integer>();
		Collections.addAll(cases, 47,48,49,50);
		track(cases,PwdUtils.class);
	}

}
