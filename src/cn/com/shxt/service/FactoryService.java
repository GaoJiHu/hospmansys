package cn.com.shxt.service;

import java.util.List;
import java.util.Map;

import cn.com.shxt.model.Factory;
import cn.com.shxt.model.PageBean;
import cn.com.shxt.util.JdbcUtil;

public class FactoryService {
	JdbcUtil db = new JdbcUtil();
	
	//�õ�����ҩ��
	public List<Map<String,Object>> getAllFactory(){
		return db.query("select * from factory ");
	}
	
	// �õ�����ҩ�� ���ڷ�ҳ
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
	//�ж�ҩ���Ƿ����
	public int factoryIsExist(String factoryName) {
		String sql1 = "select count(*) as num from factory where f_name = '"+factoryName+"' ";
		return Integer.parseInt(db.query(sql1).get(0).get("NUM").toString());
	}
	//���ҩ��
	public int addFactory(Factory factory) {
		String sql = "insert into factory values(factory_seq.nextval,'"+factory.getName()+"',"+factory.getProvinceId()+","+factory.getCityId()+")";
		return db.update(sql);
	}
	//�õ�ʡ �� ����
	public String getAddrName(String pId,String cId){
		String proId = db.query("select * from province where p_id="+Integer.parseInt(pId)+"").get(0).get("P_NAME").toString();
		String cityId = db.query("select * from city where c_id="+Integer.parseInt(cId)+"").get(0).get("C_NAME").toString();
		return proId+","+cityId;
	}
	//ɾ��ҩ����Ϣ
	public int deleteFactorys(String factoryIds) {
		String sql = "delete from factory where f_id in ("+factoryIds+")";
		return db.update(sql);
	}
	//�޸�ʱ ģ̬���� Ϊ�õ�detail
	public Factory getOne(String facId) {
		List<Map<String, Object>> list = db
				.query("select * from factory where f_id = " + facId);
		Map<String, Object> map = list.get(0);
		Factory fac = new Factory();
		fac.setId(Integer.parseInt(map.get("F_ID").toString()));
		fac.setName(map.get("F_NAME").toString());
		fac.setProvinceId(Integer.parseInt(map.get("F_PROVINCE").toString()));
		fac.setCityId(Integer.parseInt(map.get("F_CITY").toString()));
		return fac;
	}

	public int modifyFactory(Factory factory) {
		return db.update("update factory set f_name='"+factory.getName()+"',f_province="+factory.getProvinceId()+",f_city="+factory.getCityId()+" where f_id="+factory.getId()+"");
	}
	//����id��ѯ��������
	public String getFactoryName(int facId){
		String sql = "select f_name from factory where f_id="+facId+"";
		return db.query(sql).get(0).get("F_NAME").toString();
	}
}
