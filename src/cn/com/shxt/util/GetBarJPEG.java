package cn.com.shxt.util;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * author �Ż���
 * version 2013-5-14
 * description 
 */
public class GetBarJPEG {
	
	/**
	 * version 2013-5-14
	 * description ��ȡ��״ͼ����Դ�ķ��� 
	 * param ��Ҫ�����ַ���������Ϊ���������磺
	 * {"10,������Ա,������Ա","20,�г���Ա,�г���Ա","40,������Ա,������Ա"};
	 * ˵����������ÿ���ַ�����","������������
	 * ��һ�����ݣ����ĸ߶�
	 * �ڶ������ݣ�x����ÿ�����ӵ�˵������
	 * ���������ݣ����·�ͼ����ÿ�����ӵļ�Ҫ˵��
	 */
	public static CategoryDataset createDataset(String[] arr){
		DefaultCategoryDataset  dg = new DefaultCategoryDataset();
		for (String str : arr) {
			String[] subArr = str.split(",");
			dg.setValue(Double.parseDouble(subArr[0]), subArr[1], subArr[2]);
		}
		return dg;
	
	}
	
	public static void getBar(String title,CategoryDataset dataSet,HttpServletRequest request,String x,String y) throws Exception{
		JFreeChart jc = ChartFactory.createBarChart3D(title, x, y,
				dataSet, PlotOrientation.VERTICAL, true, true, false);
		//�ı�ͷ���������弰��������
		jc.setTitle(new TextTitle(title,new Font("����",Font.BOLD
				+Font.ITALIC,20)));
		//�ı��м�plot�����ݼ����������������ɫ
		CategoryPlot plot = (CategoryPlot) jc.getPlot();

		CategoryAxis categoryAxis = plot.getDomainAxis();
		NumberAxis numberaxis = (NumberAxis) plot.getRangeAxis();
		
		//����X�������ϵ�����    
		categoryAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 11));     
		//����X��ı�������     
		categoryAxis.setLabelFont(new Font("����", Font.PLAIN, 12));    
		//����Y�������ϵ�����    
		numberaxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN, 12));     
		//����Y��ı�������     
		numberaxis.setLabelFont(new Font("����", Font.PLAIN, 12));    
		//�ײ��������������     
		jc.getLegend().setItemFont(new Font("����", Font.PLAIN, 12));
		

		categoryAxis.setLabelFont(new Font("΢���ź�", Font.BOLD, 12));

		// ��������ʽ��jfreechartͳ�Ƶı���ӡ��ͼƬ��ŵ�Ӳ��
		String path = request.getSession().getServletContext()
        .getRealPath(File.separator + "chart");
		OutputStream os = new FileOutputStream(path+File.separator+"bar.jpg");
		// ������������ǰ�ͼ������׵�д�����дӶ�����ĵ�Ӳ����
		ChartUtilities.writeChartAsJPEG(os, jc, 800, 500);
		
		os.close();
	}
}
