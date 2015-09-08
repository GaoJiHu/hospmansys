package cn.com.shxt.model;

import java.util.List;
import java.util.Map;

public class PageBean {
	private int currentPage = 1;// ��ǰҳ
	private int totalPage;// ��ҳ��
	private int pageSize = 4;// ÿҳ��ʾ����
	private String sql;
	private List<Map<String, Object>> pageList;// ��ҳ��ѯ��õ��Ľ����

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalCount) {
		if (totalCount % pageSize == 0) {
			this.totalPage = totalCount / pageSize;
		} else {
			this.totalPage = totalCount / pageSize + 1;
		}
		
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {

		this.sql = "select * from (select rownum rn ,t.* from (" + sql
				+ ") t where rownum <= " + currentPage * pageSize
				+ ") where rn >= " + ((currentPage - 1) * pageSize + 1);
	}

	public List<Map<String, Object>> getPageList() {
		return pageList;
	}

	public void setPageList(List<Map<String, Object>> pageList) {
		this.pageList = pageList;
	}
	
}
