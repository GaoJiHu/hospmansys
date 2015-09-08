package cn.com.shxt.service;

import java.util.List;
import java.util.Map;

import cn.com.shxt.util.JdbcUtil;

public class StatisticService {
	JdbcUtil db = new JdbcUtil();
	
	//��ѯ����
	public List<Map<String, Object>> getOffice(){
		String sql = "select * from office where o_id not in (1)";
		return db.query(sql);
	}
	
	//��ѯ������ϸ���
	public List<Map<String, Object>> getCount(int officeId){
		String sql = "select count(*) num  from register where r_status='��Ͻ���'and r_office_id="+officeId+"";
		return db.query(sql);
	}
	//��ѯҩƷ���ĸ���������ҩ�����飩
	public List<Map<String, Object>> getTabletCount(int officeId) {
		String sql = "select t_store_id,sum(t_s_count) num from tablet_sale where t_office_id="+officeId+" group by t_store_id";
		return db.query(sql);
	}
	//��ѯothercost
	public List<Map<String, Object>> getOthercost(int officeId){
		return db.query("select sum(r_othercost) othercost from register where r_status='��Ͻ���' and r_office_id="+officeId+"");
	}
	//����ҩƷ���id ���ҩƷ�ۼ�
	public List<Map<String, Object>> getTabletOutPrice(int storeId) {
		String sql = "select t_s_outprice from tablet_store where t_s_id = "+storeId+"";
		return db.query(sql);
	}
	//��ѯ������ϸ���
	public List<Map<String, Object>> getCountWithDate(int officeId,
			String startDate, String endDate) {
		String sql = "select count(*) num  from register where r_status='��Ͻ���' and r_office_id="+officeId+" and (r_date between to_date('"+startDate+"','YYYY-MM-DD') and to_date('"+endDate+"','YYYY-MM-DD'))";
		//System.out.println(sql);
		return db.query(sql);
	}
	//��ѯҩƷ���ĸ���������ҩ�����飩
	public List<Map<String, Object>> getTabletCountWithDate(int officeId,
			String startDate, String endDate) {
		String sql = "select t_store_id,sum(t_s_count) num from tablet_sale where t_office_id="+officeId+" and (t_s_date between to_date('"+startDate+"','YYYY-MM-DD') and to_date('"+endDate+"','YYYY-MM-DD')) group by t_store_id";
		return db.query(sql);
	}
	//��ѯothercost
	public List<Map<String, Object>> getOthercost(int officeId,String startDate, String endDate){
		String sql = "select sum(r_othercost) othercost from register where r_status='��Ͻ���' and r_office_id="+officeId+" and (r_date between to_date('"+startDate+"','YYYY-MM-DD') and to_date('"+endDate+"','YYYY-MM-DD'))";
		//System.out.println(sql);
		return db.query(sql);
	}
	
	//����ͳ�� ��״ͼ
	//��ѯ ҩƷ�������� ����ҩƷ���Id,��ҩʱ�� ����
	public List<Map<String, Object>> getTabletStoreIdAndTime(String year){
		String sql = "select t_store_id,t_s_date,sum(t_s_count) num from tablet_sale where t_s_date between to_date('"+year+"-01-01','YYYY-MM-DD') and to_date('"+year+"-12-31','YYYY-MM-DD') group by t_store_id,t_s_date";
		//System.out.println(sql);
		return db.query(sql);
	}
	//��ѯothercost
	public  List<Map<String, Object>> getOthercost(String year,String month){
		String sql = "select r_date,sum(r_othercost) othercost from register where r_status='��Ͻ���' and r_date between to_date('"+year+"-"+month+"-01','YYYY-MM-DD') and to_date('"+year+"-"+month+"-31','YYYY-MM-DD') group by r_date";
		return db.query(sql);
	}
	
	//�����ϸ������鵽�˿��id������id����ۺ��ۼ۵Ĳ��
	public List<Map<String, Object>> getPrice(String tabletStoreId) {
		String sql = "select t_s_outprice-t_s_inprice profit from tablet_store where t_s_id="+Integer.parseInt(tabletStoreId)+"";
		//System.out.println(sql);
		return db.query(sql);
	}
}
