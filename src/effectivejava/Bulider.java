/**   
* @Title: Bulider.java 
* @Package effectivejava 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-10-11 下午7:57:57 
* @version V1.0   
*/
package effectivejava;

public class Bulider {
	private int id;
	private String mark;
	private int size;
	private String name;
	private int age;
	private String location;
	private Bulider(Build bul){
		id = bul.id;
		mark = bul.mark;
		size = bul.size;
		name = bul.name;
		age = bul.age;
		location = bul.location;
	}
	public static void main(String[] args){
		Bulider bul = 
				new Bulider.Build(0, "test").age(3).size(23).location("hz").name("lihai").bulider();
		System.out.println(bul.age);
	}
	public static class Build{
		private int id;
		private String mark;
		private int size = 0;
		private String name = "";
		private int age = 1;
		private String location = "";
		public Build(int id,String mark){
			this.id = id;
			this.mark = mark;
		}
		public Build  size(int size){
			this.size = size;
			return this;
		}
		public Build name(String name){
			this.name = name;
			return this;
		}
		public Build age(int age){
			this.age = age;
			return this;
		}
		public Build location(String location){
			this.location = location;
			return this;
		}
		public Bulider bulider(){
			return new Bulider(this);			
		}
	}

}
