package test;

import java.util.Date;

public class StringTest {
	
	public static void main(String[] args){
		String s = "he ee dd";
		String[] info = s.split("|");
		System.out.println(info.length);
		System.out.println(new Date().toString());
		}
	public static void repalce(){
		String s = "dfg,eee,ddd";
		System.out.println(s);
		s = s.replaceAll(",", "，");
		System.out.println(s);
	}
	public static void repa(){
		String MOVE_TO_LOG_TABLE = "replace into audit_minisite_ugc_log (" // NL
				+ "id, minisite_id, ugc_id, title, body, tag, "// NL
				+ "url, pic, share_type, creator_id, creator_name, create_time, "// NL
				+ "operator_id, operator_name, operate_time, biz_type, audit_type, " // NL
				+ "audit_flag, is_pass) SELECT " // NL
				+ "id, minisite_id, ugc_id, title, body, tag, "// NL
				+ "url, pic, share_type, creator_id, creator_name, create_time, "// NL
				+ ":2 as operator_id, :3 as operatorName, operate_time, biz_type, audit_type, "// NL
				+ "audit_flag, :4 as is_pass "// NL
				+ "from audit_minisite_ugc where id in (:1) ";
//         System.out.println(MOVE_TO_LOG_TABLE);
		String tableIndex="02";
		if(tableIndex.startsWith("0"))tableIndex=tableIndex.substring(1); 
		System.out.println(tableIndex);
	}

}
