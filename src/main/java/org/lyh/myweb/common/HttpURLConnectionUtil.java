/*
 * HPE Confidential
 * Copyright © 2017 HPE, Inc.
 * 
 * Created By Liu Yuhong - 2017年3月27日
 */
package org.lyh.myweb.common;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class HttpURLConnectionUtil {
	public static Logger logger = Logger.getLogger(HttpURLConnectionUtil.class);

	public static void postRequest(String url, String requestContent) throws Exception {
		URL wsUrl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();

		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		conn.setConnectTimeout(120000);

		OutputStream os = conn.getOutputStream();
		os.write(requestContent.getBytes());

		InputStream is = conn.getInputStream();

		byte[] b = new byte[1024];
		int len = 0;
		String s = "";
		while ((len = is.read(b)) != -1) {
			String ss = new String(b, 0, len, "UTF-8");
			s += ss;
		}
		logger.info(s);

		is.close();
		os.close();
		conn.disconnect();
	}
}
