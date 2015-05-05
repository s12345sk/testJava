/**   
* @Title: DbTable.java 
* @Package annotation.database 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-21 下午6:07:22 
* @version V1.0   
*/
package annotation.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DbTable {
	public String name() default "";
}
