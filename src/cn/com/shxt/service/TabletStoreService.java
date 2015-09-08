package cn.com.shxt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.shxt.model.PageBean;
import cn.com.shxt.model.TabletStore;
import cn.com.shxt.util.JdbcUtil;

public class TabletStoreService {
	JdbcUtil db = new JdbcUtil();

	public int addTabStore(TabletStore tabletStore) {
		String sql = "insert into tablet_store values(tablet_store_seq.nextval,"+tabletStore.getTabKindId()+"," +
				"'"+tabletStore.getTabName()+"',"+tabletStore.getFactoryId()+",to_date('"+tabletStore.getProDate()+"','YYYY-MM-DD')," +
				"to_date('"+tabletStore.getOverDate()+"','YYYY-MM-DD'),'"+new Date().getTime()+"',"+tabletStore.getCount()+"," +
				"'"+tabletStore.getUnit()+"','"+tabletStore.getInprice()+"','"+tabletStore.getOutprice()+"',to_date('"+new Date().toLocaleString().split(" ")[0]+"','YYYY-MM-DD'),'����',0)";
		return db.update(sql);
	}

	//��ѯҩƷ���
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
	//�޸�ʱ����id��detail��鵽������Ϣ
	public List<Map<String, Object>> getTabStore(int tabStoreId) {
		String sql = "select * from tablet_store where t_s_id="+tabStoreId+" ";
		return db.query(sql);
	}
	//����ҩƷ�����Ϣ
	public int modifyTabStore(TabletStore tabletStore) {
		String sql = "update tablet_store set t_k_id="+tabletStore.getTabKindId()+"," +
		"t_s_name='"+tabletStore.getTabName()+"',t_s_factory="+tabletStore.getFactoryId()+",t_s_prodate=to_date('"+tabletStore.getProDate()+"','YYYY-MM-DD')," +
		"t_s_overdate=to_date('"+tabletStore.getOverDate()+"','YYYY-MM-DD'),t_s_count="+tabletStore.getCount()+"," +
		"t_s_unit='"+tabletStore.getUnit()+"',t_s_inprice='"+tabletStore.getInprice()+"',t_s_outprice='"+tabletStore.getOutprice()+"',t_s_indate=to_date('"+new Date().toLocaleString().split(" ")[0]+"','YYYY-MM-DD') where t_s_id="+tabletStore.getId()+"";
		//System.out.println(sql);
		return db.update(sql);
	}
	//�õ�����ҩƷ�������κ�
	public List<Map<String, Object>> getAllOrders() {
		String sql = "select t_s_id,T_S_ORDER from tablet_store where t_s_status='����'";
		return db.query(sql);
	}
	//�õ�ҩƷ����
	public List<Map<String, Object>> getAllTabNames() {
		String sql = "select t_s_id,T_S_NAME from tablet_store where t_s_status='����'";
		return db.query(sql);
	}
	//��ҩ
	public List<Map<String, Object>> getOne(int tabStoreId) {
		String sql = "select t_s_id,t_s_count,t_s_sale from tablet_store where t_s_id="+tabStoreId+"";
		return db.query(sql);
	}
	//�޸Ŀ���������˻�ʱ��
	public int modify(TabletStore tabletStore,int client) {
		String sql = "";
		if(tabletStore.getCount()==0){
			sql = "update tablet_store set t_s_count=0,t_s_status='��ɾ��' where t_s_id="+tabletStore.getId()+" ";
		}else {
			sql = "update tablet_store set t_s_count="+tabletStore.getCount()+" where t_s_id="+tabletStore.getId()+"";
		}
		return db.update(sql);
	}
	//��ҩ ���ص�Ǯ
	public Float getMoney(TabletStore tabletStore,int count) {
		String sql = "select t_s_inprice from tablet_store where t_s_id="+tabletStore.getId()+"";
		float inprice = Float.parseFloat(db.query(sql).get(0).get("T_S_INPRICE").toString());
		return inprice*count;
	}
	//�޸Ŀ����������ҩ��
	public int modifySaleCount(TabletStore tabletStore){
		String sql = "update tablet_store set t_s_count=t_s_count-"+tabletStore.getCount()+",t_s_sale=t_s_sale+"+tabletStore.getCount()+" where t_s_id="+tabletStore.getId()+"";
		//System.out.println(sql);
		return db.update(sql);
	}
	//����ҩƷ����id�õ�ҩƷ����
	public List<Map<String, Object>> getAllTabName(int tabletKindId) {
		String sql = "select t_s_id,t_s_name from tablet_store where t_k_id ="+tabletKindId+"";
		//System.out.println(sql);
		return db.query(sql);
	}
	//����register��id��ѯ �۸�Ϳ��
	public List<Map<String, Object>> getAllTabPriceCount(int tabletStoreId) {
		String sql = "select * from tablet_store where t_s_id="+tabletStoreId+"";
		//System.out.println(sql);
		return db.query(sql);
	}
	
}
