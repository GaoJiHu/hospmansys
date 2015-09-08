package cn.com.shxt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.shxt.model.PageBean;
import cn.com.shxt.model.Register;
import cn.com.shxt.util.JdbcUtil;

public class RegisterService {
	JdbcUtil db = new JdbcUtil();
	//���ݿ���Id��ѯ�ÿ���ҽ��
	public List<Map<String, Object>> getAllDoctors(int officeId,String date) {
		String sql = "select c_id,r_doctor_id,c_name,count(*) as num from consumer,register where r_office_id="+officeId+"  and c_o_id="+officeId+" and r_status='�Һ���' and r_date=to_date('"+date+"','YYYY-MM-DD') having c_id=r_doctor_id group by c_id,r_doctor_id,c_name order by count(*) desc";
		//System.out.println(sql);
		return db.query(sql);
	}
	public List<Map<String, Object>> getAllDoctorsWithoutDate(int officeId) {
		String sql = "select c_id,r_doctor_id,c_name,count(*) as num from consumer,register where r_office_id="+officeId+"  and c_o_id="+officeId+" and r_status='�Һ���' having c_id=r_doctor_id group by c_id,r_doctor_id,c_name order by count(*) desc";
		//System.out.println(sql);
		return db.query(sql);
	}
	public List<Map<String, Object>> getAllDoctor(int officeId) {
		String sql = "select c_id,c_name from consumer where c_o_id = "+officeId+"";
		//System.out.println(sql);
		return db.query(sql);
	}
	//�õ����в�����
	public List<Map<String, Object>> getAllCaseIds() {
		String sql = "select c_h_id,c_h_date from case_history where c_h_status='����' order by c_h_id desc";
		return db.query(sql);
	}
	//��ӹҺţ���ϣ���
	public int addRegister(Register register) {
		String sql = "";
		if(register.getCaseId()>0&&register.getDoctorId()>0){
			sql = "insert into register (r_id,r_case_id,r_dealer_id,r_doctor_id,r_office_id,r_status,r_date,r_type) " +
			"values (register_seq.nextval,"+register.getCaseId()+",'"+register.getDealerId()+"',"+register.getDoctorId()+","+register.getOfficeId()+",'�Һ���'," +
					"to_date('"+new Date().toLocaleString().split(" ")[0]+"','YYYY-MM-DD'),'"+register.getType()+"')";
		}else if(register.getCaseId()==0&&register.getDoctorId()>0){
			sql = "insert into register (r_id,r_dealer_id,r_doctor_id,r_office_id,r_status,r_date,r_type) " +
			"values (register_seq.nextval,'"+register.getDealerId()+"',"+register.getDoctorId()+","+register.getOfficeId()+",'�Һ���',to_date('"+new Date().toLocaleString().split(" ")[0]+"','YYYY-MM-DD'),'"+register.getType()+"')";
		}else if(register.getCaseId()>0&&register.getDoctorId()==0){
			sql = "insert into register (r_id,r_case_id,r_dealer_id,r_office_id,r_status,r_date,r_type) " +
			"values (register_seq.nextval,"+register.getCaseId()+",'"+register.getDealerId()+"',"+register.getOfficeId()+",'�Һ���',to_date('"+new Date().toLocaleString().split(" ")[0]+"','YYYY-MM-DD'),'"+register.getType()+"')";
		}else {
			sql = "insert into register (r_id,r_dealer_id,r_office_id,r_status,r_date,r_type) " +
			"values (register_seq.nextval,'"+register.getDealerId()+"',"+register.getOfficeId()+",'�Һ���',to_date('"+new Date().toLocaleString().split(" ")[0]+"','YYYY-MM-DD'),'"+register.getType()+"')";
		}
		//System.out.println(sql);
		
		return db.update(sql);
	}
	//���ʱ����ҺŵĲ�����Ϣ
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
	//ԭ���޲����ŵĹҺŵ� ��ӹҺű��
	public int resetRegister(String registerId) {
		String sql = "select max(c_h_id) as id from case_history ";
		String c_h_id = db.query(sql).get(0).get("ID").toString();
		String sql1 = "update register set r_case_id="+Integer.parseInt(c_h_id)+" where r_id="+Integer.parseInt(registerId)+"";
		//System.out.println(sql1);
		return db.update(sql1);
	}
	//���ݹҺ�Id��ѯ������
	public List<Map<String, Object>> getCaseId(int registerId) {
		String sql = "select r_case_id from register where r_id = "+registerId+"";
		return db.query(sql);
	}
	//���ݹҺ�Id��ѯ������Ϣ
	public List<Map<String, Object>> getAllPastInfo(int c_h_Id){
		String sql = "select * from register where r_case_id = "+c_h_Id+" and r_status='��Ͻ���'";
		//System.out.println(sql);
		return db.query(sql);
	}
	//�������Ŵ���ʱ ��һ�ν������״̬  �ѹҺ��иĳ������
	public int modifyDiagnoseSituation(int registerId) {
		String sql = "update register set r_status = '�����' where r_id="+registerId+"";
		return db.update(sql);
	}
	//��������Ϣ �ڶ�������register��description��
	public int addRegisterSecond(Register register) {
		String sql = "update register set r_status='��Ͻ���',r_description='"+register.getDescription()+"',r_result='"+register.getResult()+"',r_measure='"+register.getMeasure()+"',r_tablet='"+register.getTablet()+"',r_cost="+register.getCost()+",r_otheritem='"+register.getOtherItem()+"',r_othercost='"+register.getOtherCost()+"',r_date=to_date('"+new Date().toLocaleString().split(" ")[0]+"','YYYY-MM-DD') where r_id="+register.getId()+"";
		//System.out.println(sql);
		return db.update(sql);
	}
	//���ݹҺű��id��ѯ����id
	public List<Map<String, Object>> getOfficeId(int registerId) {
		String sql = "select r_office_id from register where r_id="+registerId+"";
		return db.query(sql);
	}
	
}
