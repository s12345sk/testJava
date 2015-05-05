/**   
* @Title: UseCase.java 
* @Package annotation.types 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-21 下午1:47:38 
* @version V1.0   
*/
package annotation.types;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
	public int id();
	public String des() default "this is des ";
}
