/**   
* @Title: Number.java 
* @Package annotation.database 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-22 下午2:46:50 
* @version V1.0   
*/
package annotation.database;
import static tool.Print.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@DbTable(name="TEST")
public class Number {
	
	@SQLString(30)
	private String firstName;
	@SQLString(value=50,name="SK")
	private String lastName;
	@SQLInteger(constrains=@Constrains(unique=true))
	private Integer age;
	@SQLString(value=50,constrains=@Constrains(primaryKey=true))
	private String handle;
	public String toString(){
		return handle;
	}
	
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the age
	 */
	public Integer getAge() {
		return age;
	}

	/**
	 * @return the handle
	 */
	public String getHandle() {
		return handle;
	}

	/**
	 * @throws ClassNotFoundException  
	 * @Title: main 
	 * @Description: 
	 * @param @param args    设定文件 
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Class<?> cl = Class.forName("annotation.database.Number");
		print(cl.getName());
		print(cl.getSimpleName());
		DbTable db = cl.getAnnotation(DbTable.class);
		if(db==null){
			print("db is null!");
			return;
		}
		String dbName = db.name();
		if(dbName.length()<1)
			dbName = cl.getSimpleName().toUpperCase();
		List<String> coDef = new ArrayList<String>();
		for(Field fi:cl.getDeclaredFields()){
			Annotation[] an = fi.getAnnotations();
			if(an.length<1)
				continue;
			if(an[0] instanceof SQLInteger){
				SQLInteger si = (SQLInteger)an[0];
				String name = si.name();
				if(name.length()<1)
					name = fi.getName().toUpperCase();
				coDef.add(name+" INTEGER "+getCons(si.constrains()));
				
			}
			if(an[0] instanceof SQLString){
				SQLString si = (SQLString)an[0];
				String name = si.name();
				if(name.length()<1)
					name = fi.getName().toUpperCase();
				coDef.add(name+" VARCHAR("+si.value()+") "+getCons(si.constrains()));
				
			}
			StringBuffer sb = new StringBuffer();
			sb.append("CREATE TABLE "+dbName+" (");
			for(String name:coDef)
				sb.append("\r\n "+name+",");
			print("-------------- SQL IS ----------------");
			print(sb.substring(0, sb.length()-1)+")");
			
		
		}
	}
	private static String getCons(Constrains con){
		StringBuffer sb = new StringBuffer();
		if(!con.allowNull())
			sb.append(" NOT NULL ");
		if(con.unique())
			sb.append(" UNIQUE ");
		if(con.primaryKey())
			sb.append(" PRIMARY KEY ");
		return sb.toString();
	}

}
