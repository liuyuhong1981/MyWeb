/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年3月27日
 */
package org.lyh.myweb.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpURLConnectionUtil {

	/**
	 * Call web service by HTTP POST
	 * 
	 * @param url
	 * @param requestContent
	 * @return
	 * @throws Exception
	 */
	public static String postRequest(String url, String requestContent) throws Exception {
		HttpURLConnection conn = null;
		OutputStream os = null;
		InputStream is = null;
		String s = "";

		try {
			URL wsUrl = new URL(url);
			conn = (HttpURLConnection) wsUrl.openConnection();

			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
			conn.setConnectTimeout(120000);

			os = conn.getOutputStream();
			os.write(requestContent.getBytes());

			is = conn.getInputStream();

			byte[] b = new byte[1024];
			int len = 0;

			while ((len = is.read(b)) != -1) {
				String ss = new String(b, 0, len, "UTF-8");
				s += ss;
			}

			is.close();
			os.close();
			conn.disconnect();
		} catch (Exception e) {
			throw e;
		} finally {
			is.close();
			os.close();
			conn.disconnect();
		}

		System.out.println(s);
		return s;
	}
}
