//package test;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//import com.xiaonei.xce.XceAdapter;
//
//public class FileToDb {
//	static {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//	static int count = 0;
//
//	public static void main(String[] args) throws Exception {
//		    if(args.length==0){
//		    	System.out.println("please give a value to args!");
//		    	return;
//		    }
//		
//	     	File f = new File(""+args[0]);
//		    FileReader fr = new FileReader(f);
//		    BufferedReader br = new BufferedReader(fr);
//			Connection conn = XceAdapter.getInstance().getReadConnection("audit_content");
//			String sql = "replace into audit_minisite_ugc"+
//			" (id, minisite_id, ugc_id, title, body, tag, url, "+
//			"pic, share_type, creator_id, creator_name, create_time, operator_id,  "+
//			"operate_time, biz_type, audit_type, audit_flag) "+
//			"SELECT id, minisite_id, ugc_id, title, body, tag, url, pic, share_type, "+
//			"creator_id, creator_name, create_time, operator_id, operate_time, 7, audit_type, audit_flag"+
//			" from audit_minisite_ugc_log where minisite_id =? and biz_type=2 and is_pass=1;";
//			String s;
//			 int k=0;
//			  while((s=br.readLine())!= null){
//                  String[] str = s.split( " ");
//                  int id = Integer. parseInt(str[0]);  
//                  PreparedStatement pstmt = conn.prepareStatement(sql);
//                  pstmt.setInt(1, id);
//                  pstmt.execute();
//      			 pstmt.close();
//      			 System.out.println("导入第"+(++k)+"个小站"+id+"的照片成功！");
//			  }
//			
//			
//			
//			conn.close();
//			fr.close();
//			br.close();
//			System.out.println("it's all over now!!!");
//		}
//	
//
//}
