//package test;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//
//import com.renren.ugc.model.album.Album;
//import com.xiaonei.admin.biz.model.audit.AuditAlbumView;
//import com.xiaonei.xce.XceAdapter;
//
//public class HistoryPornUser {
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
//		    if(args.length==0){
//		    	System.out.println("please give a value to args!");
//		    	return;
//		    }
//		
//	     	File f = new File("/opt/file/"+args[0]);
//		    FileReader fr = new FileReader(f);
//		    BufferedReader br = new BufferedReader(fr);
//			Connection conn = XceAdapter.getInstance().getReadConnection("audit_content");
//			
//			String s;
//			 int k=0;
//			  while((s=br.readLine())!= null){
//                  String[] str = s.split( " ");
//                  int id = Integer. parseInt(str[0]);  
//                  Album[] albums = photoAuditAdapter.getAlbums( id,587360935,  0, 100000);
//                  if(albums==null||albums.length==0){
//               	   logger.info("id为"+id+"的用户无相册");
//               	   continue;
//               	   }
//                  for(Album al:albums){
//                  AuditAlbumView av = new AuditAlbumView();
//                  av.setAlbumId(al.getId());
//                  av.setOwnerId(al.getOwnerId());
//                  av.setBizType(32);
//                  av.setAuditFlag(0);
//                  auditAlbumDAO.save(av);
//                  j++;
//                  }
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
//
//
//}
