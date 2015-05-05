/**   
* @Title: Constrains.java 
* @Package annotation.database 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-22 上午11:14:24 
* @version V1.0   
*/
package annotation.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constrains {
	public boolean primaryKey() default false;
	public boolean allowNull() default true;
	public boolean unique() default false;
}
