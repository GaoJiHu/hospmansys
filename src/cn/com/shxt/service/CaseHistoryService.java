package cn.com.shxt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.shxt.model.CaseHistory;
import cn.com.shxt.model.PageBean;
import cn.com.shxt.util.JdbcUtil;

public class CaseHistoryService {
	JdbcUtil db = new JdbcUtil();

	//��Ӳ���
	public int addCaseHistory(CaseHistory caseHistory) {
		String sql = "insert into case_history values (case_history_seq.nextval,'"+caseHistory.getName()+"'," +
				"'"+caseHistory.getSex()+"',"+caseHistory.getAge()+",'"+caseHistory.getPhoto()+"'," +
						"'"+caseHistory.getPhone()+"',"+caseHistory.getProvinceId()+","+caseHistory.getCityId()+"," +
				  	"to_date('"+new Date().toLocaleString().split(" ")[0]+"','YYYY_MM_DD'),'����')";
		//System.out.println(sql);
		return db.update(sql);
	}

	public PageBean pageList(String sql, String currentPage) {
		PageBean page = new PageBean();
		if (currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));//���õ�ǰҳ��
		}
		page.setSql(sql);//���ݻ�����ѯ����pageBean������ƴ�ӷ�ҳ��ѯ
		page.setPageList(db.query(page.getSql()));//��÷�ҳ��ѯ��Ľ����
		page.setTotalPage(db.getCount(sql));//��������������pageBean�л�ȡ��ҳ��
		return page;
	}
	// ����id���ַ
	public String getAddrName(String pId , String cId){
		String proId = db.query("select * from province where p_id="+Integer.parseInt(pId)+"").get(0).get("P_NAME").toString();
		String cityId = db.query("select * from city where c_id="+Integer.parseInt(cId)+"").get(0).get("C_NAME").toString();
		return proId+","+cityId;
	}
	//ɾ������ ��״̬��Ϊ��ɾ��
	public int deleteCaseHistorys(String caseHisIds) {
		String sql = "update case_history set c_h_status = '��ɾ��' where c_h_id in ("+caseHisIds+")";
		return db.update(sql);
	}
	//����id����Һ�ʱ��
	public List<Map<String, Object>> getCaseDate(int caseId){
		String sql = "select C_H_DATE from case_history where c_h_id="+caseId+"";
		return db.query(sql);
	}
	//���ݲ����Ų�ѯ���в�����Ϣ
	public List<Map<String, Object>> getAllInfo(int r_case_id) {
		String sql = "select * from case_history where c_h_id = "+r_case_id+"";
		return db.query(sql);
	}
}
