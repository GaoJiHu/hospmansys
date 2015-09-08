package cn.com.shxt.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

public class UploadImage {
	public static Map<String,Object> getUpload(HttpServletRequest request){
		
		/* ����һ��map�洢request�����д��ݵ� */
		Map<String,Object> map = new HashMap<String,Object>();
		
		/* ����ϴ��ļ�Ŀ��Ŀ¼����ʵ·�� */
		String path = request.getSession().getServletContext().getRealPath(File.separator+"upload");
		
		/* �ж�Ŀ¼�Ƿ���ڣ��������򴴽�һ�� */
		File forlder = new File(path);
		if(!forlder.exists()){
			forlder.mkdir();
		}
		
		/* 1.��������Ƿ����ļ��ϴ����ͣ�����ǣ�������ļ��ϴ� */
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			/* 2.���������ļ���Ŀ���� */
			DiskFileItemFactory factory = new DiskFileItemFactory();
			
			/* 3.����ServletFileUploadʵ�� */
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			/* ���õ����ļ��ϴ�������5M */
			upload.setFileSizeMax(5*1024*1024);
			
			/* ����������������� */
			InputStream in = null ;
			OutputStream out = null;
			try{
				/* 4.�������������еı������һ�������� */
				FileItemIterator it = upload.getItemIterator(request);
				
				/* 5.���������� */
				while(it.hasNext()){
					FileItemStream item = it.next();// ���ÿ�������ݵ�������
					
					String name = item.getFieldName();//��ñ����е�name 
					in = item.openStream();
					
					/* �ж��Ƿ�����ͨ�� */
					if(item.isFormField()){
						/* �洢��ͨ�������� */
						map.put(name, Streams.asString(in,"UTF-8"));
						
					}else{
						/* �ϴ��ļ� */
						/* ����ϴ����ļ����ļ��� */
						String fileName = "";
						int start = item.getName().lastIndexOf(".");// ������һ����б�ܵ�λ��
						if(start > 0){
							fileName = item.getName().substring(start);
							//System.out.println(fileName);
						}else{
							fileName = item.getName();
						}
						if(fileName!=""){
							String saveName = new Date().getTime()+fileName;// ����Ҫ������ļ�����
							out = new FileOutputStream(new File(path,saveName));// �������������
							map.put("saveName", saveName);
							
							// ���������������д�뵽������У�Ŀ�����ڳ��������ĵ�upload�ļ����д����û��ϴ����ļ�
							// ����������� Ҫ�����ڻ���������ʹ���ֽ�����,1024Ϊ��������С-->1024byte
							byte[] buf = new byte[1024];
							int length ;// length�����ȡ���ֽ�����
							while((length=in.read(buf))>0){
								out.write(buf,0,length);
							}
						}else{
							map.put("saveName", "");
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(out != null){
					try {
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if(in != null){
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return map;
	} 
}
