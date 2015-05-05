//package test;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.List;
//
//import com.xiaonei.xce.XceAdapter;
//
//public class ListToFile {
//
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
//		
//	     	File f = new File("/opt/file/xiaozhan" );
//		    FileWriter fw = new FileWriter(f);
//			Connection conn = XceAdapter.getInstance().getReadConnection("audit_content");
//			String sql = "select distinct minisite_id from audit_minisite_ugc_log"+
//			             " and where biz_type =? and is_pass =? "+
//			             "and creator_id!=operator_id";
//			String MOVE_TO_LOG_TABLE = "replace into audit_minisite_ugc (" // NL
//	                + "id, minisite_id, ugc_id, title, body, tag, "// NL
//	                + "url, pic, share_type, creator_id, creator_name, create_time, "// NL
//	                + "operator_id, operator_name, operate_time, biz_type, audit_type, " // NL
//	                + "audit_flag) SELECT " // NL
//	                + "id, minisite_id, ugc_id, title, body, tag, "// NL
//	                + "url, pic, share_type, creator_id, creator_name, create_time, "// NL
//	                + "operator_id,operatorName, operate_time, 7, audit_type, "// NL
//	                + "audit_flag " // NL
//	                + "from audit_minisite_ugc_log where id =671101770 and biz_type=2 and is_pass=1 " ;
//
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			System.out.println(conn.getMetaData().getURL() + "|" + sql);
//		
//			pstmt.setInt(1, 2);
//			pstmt.setInt(2, 0);
//			ResultSet rs = pstmt.executeQuery();
//			
//			int content = 0;
//			while(rs.next()) {
//				content = rs.getInt("minisite_id");
//				fw.write(content + "\r\n");
//				System.out.println(content);
//				
//			}
//			pstmt.close();
//			conn.close();
//			fw.close();
//		}
//		
//
//	
//
//	public void listToFile(List<Integer> list, String name) {
//		File f = new File("/opt/file/" + name);
//		FileWriter fw = null;
//		try {
//			fw = new FileWriter(f);
//			for (int id : list)
//				fw.write(id + "\r\n");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				fw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//	}
//
//}
