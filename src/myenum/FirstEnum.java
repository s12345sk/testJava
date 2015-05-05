/**   
* @Title: FirstEnum.java 
* @Package myenum 
* @Description:  枚举类型。也就是static final的类型，已经确定的类的一种。可以很多种放在一起
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-20 下午7:49:59 
* @version V1.0   
*/
package myenum;

public class FirstEnum {
	public static void main(String[] args){
		for(Types type:Types.values()){
			System.out.print(type+" "+type.ordinal()+" ");
			System.out.print(type.compareTo(Types.TWO)+" ");
			System.out.print(type.equals(Types.TWO)+" ");
			System.out.print((type==Types.TWO)+" ");
			System.out.println(type.getDeclaringClass()+" "+type.name()+" "+type.hashCode()+"\r\n");
		}
		for(String s:"ONE TWO THREE FOUR".split(" ")){
			try{
			Types ty = Enum.valueOf(Types.class, s);
			System.out.println(ty);
			}catch(Exception e){
				System.out.println("hehe,doubi!");
			}
		}
	}

}
enum Types{
	ONE,
	TWO,
	THREE
}
