package cn.com.shxt.model;

public class Register {
	private int id ; 
	private int caseId=0 ; //��������
	private String dealerId ; //�Һž������˺� Ҳ��Ψһ��
	private int doctorId=0 ; //����ҽ������
	private int officeId ; //��������
	private String type ; //���� ҽ�� ��ͨ
	private String status ; //״̬ �Һ� ��� �� ��Ͻ���
	private String description ; //�������
	private String result ; //��Ͻ��
	private String measure ; //���Ʒ���
	private String tablet ; //ҩƷ��
	private float cost ; //ҩƷ���
	private String otherItem ; //�����շ���Ŀ
	private float otherCost ; // �����շѽ��
	private String date ; //���ʱ��
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCaseId() {
		return caseId;
	}
	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}
	public String getDealerId() {
		return dealerId;
	}
	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public int getOfficeId() {
		return officeId;
	}
	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getTablet() {
		return tablet;
	}
	public void setTablet(String tablet) {
		this.tablet = tablet;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public String getOtherItem() {
		return otherItem;
	}
	public void setOtherItem(String otherItem) {
		this.otherItem = otherItem;
	}
	public float getOtherCost() {
		return otherCost;
	}
	public void setOtherCost(float otherCost) {
		this.otherCost = otherCost;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
