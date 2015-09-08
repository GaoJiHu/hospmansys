package cn.com.shxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.faces.context.ResponseWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.shxt.model.PageBean;
import cn.com.shxt.service.ConsumerService;
import cn.com.shxt.service.RegisterService;

public class DiagnoseAddServlet extends HttpServlet {


	private static final long serialVersionUID = 6406964500119964917L;

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String doctorAccount = "";
		// �ȸ��ݵ�½ҽ������Һŵ��ˣ�����ʱ���Ⱥ�˳����
		if(request.getSession().getAttribute("user")==null||request.getSession().getAttribute("user")==""){
			PrintWriter out = response.getWriter();
			out.print("please login again ��");
		}else{
			doctorAccount = request.getSession().getAttribute("user").toString();
		}
		//����ҽ�����˺� ���id
		ConsumerService consumerService = new ConsumerService();
		List<Map<String, Object>>  list = consumerService.getConsumerId(doctorAccount);
		if(list.size()>0){
			RegisterService registerService = new RegisterService();
			String currentPage = request.getParameter("currentPage");
			String sql = "select * from register where r_doctor_id="+Integer.parseInt(list.get(0).get("C_ID").toString())+" and (r_status='�Һ���' or r_status='�Һ�') order by r_id asc";
			PageBean pageBean = registerService.pageList(sql, currentPage);
			
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("/diagnoseFunction/diagnoseAdd.jsp").forward(request, response);
		}
	}
}