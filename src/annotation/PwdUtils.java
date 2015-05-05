/**   
* @Title: PwdUtils.java 
* @Package annotation 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-21 下午2:12:01 
* @version V1.0   
*/
package annotation;

import java.util.List;

import annotation.types.UseCase;

public class PwdUtils {
	
	@UseCase(id=47,des="this is validator")
	public boolean validatorPwd(String pwd){
		return pwd.matches("\\w*\\d\\w*");
	}
	
	@UseCase(id=48)
	public String encryptPwd(String pwd){
		return new StringBuilder(pwd).reverse().toString();
	}
	
	@UseCase(id=49,des=" the pwd can`t equal previously used ones ")
	public boolean checkPre(List<String> list,String pwd){
		return !list.contains(pwd);
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

	}

}
