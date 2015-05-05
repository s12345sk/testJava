//package test;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import com.xiaonei.xce.XceAdapter;
//
//public class InnerEmail {
//
//	static {
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) 
//			e.printStackTrace();
//		}
//	}
//	static int count = 0;
//	private static final String TABLE = "admin_config";
//
//	public static void main(String[] args) throws Exception {
//		testFile();
//	}
//	public static void testFile() throws Exception{
//		File f = new File("D:\\emailModule" );
//	    FileReader fr = new FileReader(f);
//	    BufferedReader br = new BufferedReader(fr);
//	    String s;
//	    while((s=br.readLine())!=null){
//			System.out.println(s);
//			String[] info = s.split("\\|");
//		    System.out.println(info[0]+"---"+info[1].toUpperCase()+"---"+info[3]);
//			}
//	}
//	
//	public static void test() throws Exception{
//
//		
//     	File f = new File("/opt/file/emailModule" );
//	    FileReader fr = new FileReader(f);
//	    BufferedReader br = new BufferedReader(fr);
//		Connection conn = XceAdapter.getInstance().getReadConnection("audit_content");
//		String sql = "insert into "+TABLE+"(id,name,value,des) values(?,?,?,?) ";
//
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		System.out.println(conn.getMetaData().getURL() + "|" + sql);
//		String s;
//		while((s=br.readLine())!=null){
//			System.out.println(s);
//			String[] info = s.split("|");
//			pstmt.setInt(1, Integer.parseInt(info[0]));
//			pstmt.setString(2, info[1]);
//			pstmt.setString(3, info[2]);
//			pstmt.setString(4, info[3]);
//			boolean bool = pstmt.execute();
//			if(bool){
//				System.out.println("导入id为"+Integer.parseInt(info[0])+"的数据成功！");
//			}else
//				System.out.println("导入id为"+Integer.parseInt(info[0])+"的数据失败！");
//			
//		}
////		pstmt.setInt(1, 2);
////		pstmt.setInt(2, 0);
////		ResultSet rs = pstmt.executeQuery();
////		
////		int content = 0;
////		while(rs.next()) {
////			content = rs.getInt("minisite_id");
////			fw.write(content + "\r\n");
////			System.out.println(content);
////			
////		}
//		pstmt.close();
//		conn.close();
//		fr.close();
//	
//	}
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
