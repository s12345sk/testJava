/**   
* @Title: Input.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2015-4-17 下午5:30:54 
* @version V1.0   
*/
package myenum;

public enum Input{
	YI(1),WU(3),SHI(4),
	COLE(2),MIAN(6),WATER(16),
	ABORT{
		int getValue() {
		throw new RuntimeException("abort don`t need this !!");
		}
	},
	STOP{
		int getValue() {
			throw new RuntimeException("stop don`t need this !!");
		}
	};
	private int value;
	 Input(int value){
		this.value = value;
	}
	 Input(){}
	int getValue(){
		return value;
	}
	
}
