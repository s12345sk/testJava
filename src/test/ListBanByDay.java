//package test;
//
//
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import com.xiaonei.xce.XceAdapter;
//
//public class ListBanByDay {
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
//		    if(args.length!=2)
//		    {
//		    	System.out.println("请输入起止时间！");
//		    	return;
//		    }
//	     	File f = new File("/opt/file/banRecork" );
//		    FileWriter fw = new FileWriter(f);
//		    fw.write("封禁时间    封禁人数     " + "\r\n");
//			Connection conn = XceAdapter.getInstance().getReadConnection("audit_user");
//			String fromTime = args[0];
//			String toTime = args[1];
//			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//			 Date ft = sdf.parse(fromTime);
//			 Date tt = sdf.parse(toTime);
//			 int content;
//			 while(ft.getTime()<=tt.getTime()){
//				 String sql = "select count(*) from ban_record where time>=? and time<?";
//				 PreparedStatement pstmt = conn.prepareStatement(sql);
////					System.out.println(conn.getMetaData().getURL() + "|" + sql);
//				
//					pstmt.setString(1, sdf.format(ft)+" 00:00:00");
//					pstmt.setString(2, sdf.format(ft)+" 23:59:59");
//					
//					ResultSet rs = pstmt.executeQuery();
//					while(rs.next()) {
//						content = rs.getInt(1);
//						fw.write(sdf.format(ft)+"     "+content + "\r\n");
//						System.out.println(content);
//						
//					}
//					Calendar ca = Calendar.getInstance();
//					ca.setTime(ft);
//					ca.add(Calendar.DATE, 1);
//					ft = ca.getTime();
//			 }
//			            
//			
//
//		
//			
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
