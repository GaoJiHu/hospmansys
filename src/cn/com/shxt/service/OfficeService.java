package cn.com.shxt.service;

import java.util.List;
import java.util.Map;

import cn.com.shxt.model.Office;
import cn.com.shxt.model.PageBean;
import cn.com.shxt.util.JdbcUtil;

public class OfficeService {
	JdbcUtil db = new JdbcUtil();
	
	//��ӿ�����Ϣ
	public int addOffice(Office office){
		return db.update("insert into office values(office_seq.nextval,'"+office.getName()+"')");
	}
	//�жϿ��Ҵ��ڲ�����
	public int officeIsExist(String officeName){
		List<Map<String, Object>>  list = db.query("select * from office where o_name='"+officeName+"'");
		return list.size();
	}
	//ɾ��������Ϣ	
	public int deleteOffice(String condition) {
		String sql = "select * from consumer where c_role='ҽ��' and c_o_id in ("+condition+")";
		if(db.query(sql).size()>0){
			String sql1 = "update consumer set c_o_id = 1 where c_role='ҽ��' and c_o_id in ("+condition+")";
			db.update(sql1);
		}
		return db.update("delete from office where o_id in ("+condition+")");
	}
	//�޸Ŀ���
	public int modifyOffice(Office office){
		//System.out.println("update office set o_name='"+office.getName()+"' where o_id="+office.getId()+"");
		return db.update("update office set o_name='"+office.getName()+"' where o_id="+office.getId()+"");
	}
	//��ѯ���п���
	public PageBean pageList(String sql,String currentPage){
		PageBean page = new PageBean();
		if (currentPage != null) {
			page.setCurrentPage(Integer.parseInt(currentPage));//���õ�ǰҳ��
		}
		page.setSql(sql);//���ݻ�����ѯ����pageBean������ƴ�ӷ�ҳ��ѯ
		page.setPageList(db.query(page.getSql()));//��÷�ҳ��ѯ��Ľ����
		page.setTotalPage(db.getCount(sql));//��������������pageBean�л�ȡ��ҳ��
		return page;
	}
	public List<Map<String, Object>> getAllOffice() {
		return db.query("select * from office ");
	}
}
