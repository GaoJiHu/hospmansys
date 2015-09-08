package cn.com.shxt.service;

import java.util.List;
import java.util.Map;

import cn.com.shxt.model.PageBean;
import cn.com.shxt.model.TabletKind;
import cn.com.shxt.util.JdbcUtil;

public class TabletKindService {
	JdbcUtil db = new JdbcUtil();
	
	//�ж�ҩƷ�����Ƿ����
	public int tabKindIsExist(String tabKindName) {
		List<Map<String, Object>> list = db.query("select * from tablet_kind where t_k_name='"+tabKindName+"' and t_k_status='����'");
		return list.size();
	}
	//���ҩƷ����
	public int addTabKind(TabletKind tabletKind) {
		String sql = "insert into tablet_kind values (tablet_kind_seq.nextval,'"+tabletKind.getName()+"','����')";
		return db.update(sql);
	}
	//�޸�����
	public int modifyTabKind(TabletKind tabletKind){
		String sql = "update tablet_kind set t_k_name='"+tabletKind.getName()+"',t_k_status='"+tabletKind.getStatus()+"' where t_k_id="+tabletKind.getId()+"";
		return db.update(sql);
	}
	//ɾ��ҩƷ����  
	public int deleteTabKind(String condition) {
		String sql = "update tablet_kind set t_k_status='��ɾ��' where t_k_id in ("+condition+")";
		return db.update(sql);
	}
	//��ѯ��������
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
	public List<Map<String, Object>> getAllKinds() {
		return db.query("select * from tablet_kind where t_k_status = '����' ");
	}
	//����id��ѯҩƷ����
	public String getTabKindName(int tabKindId){
		String sql = "select T_K_NAME from tablet_kind where T_K_ID="+tabKindId+"";
		//System.out.println(sql);
		return db.query(sql).get(0).get("T_K_NAME").toString();
	}
}
