package com.lhh.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DynamicReadFileInfoUtil {
	private static String TAG = "DynamicReadFileInfoUtil";

	/**
	 * ��ָ���ļ���ȡָ����ͷ�����ݣ������Զ�ɾ������ͷ���
	 * @param contentFile ָ���ļ�
	 * @param startTag	ָ�����
	 * @return
	 */
	public String readInfoWithStart(String contentFilePath,String startTag){
		String returnContent = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(contentFilePath));
			String str;
			while ((str = reader.readLine()) != null) {
				if (str.startsWith(startTag)) {
					str.replaceAll(" ", "");
					returnContent = str.replaceAll(startTag + "=", "");
					LogUtil.d(TAG, "returnContent: " + returnContent);
				}
			}	
			reader.close();
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
		return returnContent;
	}
}
