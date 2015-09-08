package cn.com.shxt.util;

import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;

/**
 * author �Ż���
 * version 2013-5-14
 * description 
 */
public class GetPieJPEG {
	/**
	 * version 2013-5-14
	 * description ��ȡ��ͼ����Դ�ķ��� 
	 * param ����Map����
	 * Map�е�key�Ǳ���ͼ�е����� ��String��
	 * Map�е�value�Ǳ���ͼ�еİٷֱ� ��Double��
	 */
	public static DefaultPieDataset getDataset(Map<String,Double> map) {

		DefaultPieDataset dpd = new DefaultPieDataset();

		// �������ݼ�һ����ΪString�ζ�̬���ö�����ΪdoubleΪվ��ͼ�ı���Ϊ����
		Set<Entry<String,Double>> set = map.entrySet();
		for (Entry<String,Double> entry : set) {
			dpd.setValue(entry.getKey(), entry.getValue());
		}

		return dpd;

	}
	
	public static void getPie(String title,DefaultPieDataset dataSet,HttpServletRequest request) throws Exception{
		JFreeChart jc = ChartFactory.createPieChart3D(title,
				dataSet, true, true, false);
		// ȡͷ
		jc.setTitle(new TextTitle(title, new Font("����", Font.BOLD, 22)));

		// ȡ�ײ�ͼ�� ����ȡ��һ�����㿪ȡ
		LegendTitle legend = jc.getLegend(0);
		// ���õײ�ͼ������������������ɫ�����С
		legend.setItemFont(new Font("΢���ź�", Font.BOLD, 14));
		// ȡ�м�plot����
		PiePlot plot = (PiePlot) jc.getPlot();
		// �����м�plot��������
		plot.setLabelFont(new Font("����", Font.BOLD, 16));

		// ��������ʽ��jfreechartͳ�Ƶı���ӡ��ͼƬ��ŵ�Ӳ��
		String path = request.getSession().getServletContext()
        .getRealPath(File.separator + "chart");
		OutputStream os = new FileOutputStream(path+File.separator+"pie.jpg");
		// ������������ǰ�ͼ������׵�д�����дӶ�����ĵ�Ӳ����
		ChartUtilities.writeChartAsJPEG(os, jc, 800, 500);
		
		os.close();
	}
}
