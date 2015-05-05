/**   
* @Title: SecondEnum.java 
* @Package myenum 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-20 下午8:02:29 
* @version V1.0   
*/
package myenum;


public enum SecondEnum {
	ONE("IT DOG"),
	TWO("ENO DOG"),
	THREE("LAW DOG");
	private String name;
	private SecondEnum(String s){
		name = s; 
	}
	public String toString(){
		return "dog is a dog!!"+getName();
	}
	public static void main(String[] args){
		for(SecondEnum se :SecondEnum.values())
			System.out.println(se+" "+se.getName()+" "+se.name());
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	

}
