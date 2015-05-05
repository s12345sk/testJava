/**   
* @Title: Factory.java 
* @Package effective 
* @Description:  工厂模式
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-10 下午8:46:48 
* @version V1.0   
*/
package designmode;

public class Factory {
	public static void main(String[] args){
		Credit cr = CreditFactory.getCredit();
		cr.creditPut(34);
	}

}
class CreditFactory{
	public static Credit getCredit(){
		if(ifOnline())
			return new Online();
		else
			return new Offline();
	}
	public static boolean ifOnline(){
		double t = Math.random();
		if(t>0.5)
			return false;
		return true;
	}
}
interface Credit{
	//放贷
	public void creditPut(int id);
}

class Online implements Credit{
	
	public void creditPut(int id){
		System.out.println("online!");
	}
	
}
class Offline implements Credit{
	
	public void creditPut(int id){
		System.out.println("offline!");
	}
	
}
