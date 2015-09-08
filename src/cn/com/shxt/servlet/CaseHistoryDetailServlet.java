package cn.com.shxt.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.shxt.service.CaseHistoryService;
import cn.com.shxt.service.RegisterService;

public class CaseHistoryDetailServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//����֪���Һŵ�
		int registerId = Integer.parseInt(request.getParameter("registerId").toString());
		//���ݹҺŵ�id����ϱ��еĹҺ��иĳ������
		RegisterService registerService = new RegisterService();
		int result = registerService.modifyDiagnoseSituation(registerId);
		if(result > 0){//�Ѹĳ������
			//���ݹҺŵ��Ƿ��в�����Ž���ѡ��������ͬ�����ҳ��
			List<Map<String, Object>> list = registerService.getCaseId(registerId);
			if(list.size()>0){//˵���ùҺŵ��в����ţ�Ȼ����ݲ����Ų�ѯ����������Ϣ
				CaseHistoryService caseHistoryService = new CaseHistoryService();
				List<Map<String, Object>> list1 = caseHistoryService.getAllInfo(Integer.parseInt(list.get(0).get("R_CASE_ID").toString()));
				request.setAttribute("registerId", registerId);
				request.setAttribute("oneCaseHistory", list1);
				request.getRequestDispatcher("/diagnoseFunction/diagnoseAddSecond.jsp").forward(request, response);
			
			}else{//�Һŵ����޲�����Ҳ�ɿ���
			}
		}else{
			request.getRequestDispatcher("/diagnoseFunction/diagnoseAddFailure.jsp").forward(request, response);
		}
	}
}
