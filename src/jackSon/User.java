/**   
* @Title: User.java 
* @Package JackSon 
* @Description:  
* @author kang.sun(kanggood@126.com)   
* @date 2014-12-3 下午6:47:53 
* @version V1.0   
*/
package jackSon;

public class User {
	public static class Name{
		private String first;
		private String last;
		/**
		 * @return the first
		 */
		public String getFirst() {
			return first;
		}
		/**
		 * @param first the first to set
		 */
		public void setFirst(String first) {
			this.first = first;
		}
		/**
		 * @return the last
		 */
		public String getLast() {
			return last;
		}
		/**
		 * @param last the last to set
		 */
		public void setLast(String last) {
			this.last = last;
		}
		
	}
	private Name name;
	private String gender1;
	private boolean verified;
	private byte[] userImage;
	/**
	 * @return the name
	 */
	public Name getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(Name name) {
		this.name = name;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender1;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender1 = gender;
	}
	/**
	 * @return the verified
	 */
	public boolean isVerified() {
		return verified;
	}
	/**
	 * @param verified the verified to set
	 */
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	/**
	 * @return the userImage
	 */
	public byte[] getUserImage() {
		return userImage;
	}
	/**
	 * @param userImage the userImage to set
	 */
	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}
	
	public String toString(){
		return "name:"+name.first+","+name.last+"\nGender:"+gender1+"\nuserImage:"+userImage.toString();
	}
	

}
