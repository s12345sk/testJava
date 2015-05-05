//package test;
//
//import com.xiaonei.admin.xoa.client.model.report.UserReportType;
//import com.xiaonei.admin.xoa.client.model.report.UserReportView;
//
//public class ReportInterface {
//	public static void main(String[] args) {
//		System.setProperty("xoa.hosts.admin.xoa.renren.com", "10.3.22.164:8188");
//		XoaClient client = new XoaClient();
//		UserReportView view = new UserReportView();
//		// 以下字段每种类型输入略有不同，详见举报类型 部分
//		view.setName("test");// 被举报人姓名
//		view.setContentId(101286);// 被举报的ugc的id，举报用户则是用户id
//		view.setContent("content");// 被举报的ugc内容
//		// view.setOwnerId(123);//ugc所以
//		//
//		view.setUrl("");
//		// view.setPid(0);
//		// 以下必须输入的字段，并且每种类型都是相同信息
//		// 举报类型
//		view.setUserReportType(UserReportType.MIND_POST);
//		// 举报理由
//		view.setReason(531);
//		// 被举报人id
//		view.setReportUserId(123);
//		// 举报人id
//		view.setProsecutor(123);
//		// 举报人姓名
//		view.setProseName("");
//		// 举报附加说明
//		view.setRemark("");
//		UserReportService service = ServiceFactories.getFactory().getService(
//				UserReportService.class, client);
//		String result = service.saveUserReport(view).submit()
//				.awaitUninterruptibly().getContent();
//		System.out.println(result);
//
//	}
//
//}
