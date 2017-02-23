package com.xuema.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HTTPUtil {

	public static void main(String[] args) throws ClientProtocolException,
			IOException {
		String link = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=t0lLhSR4L6YoceoF4O8j9-i5keHS0TgVPJH2X17hoCvg2BodnTFZZmBSTLyALoraEs2bUkUp7BmEP3wUAxWyKYdY7Ma1Gv0MbavAbSH5Pic&openid=oY83Wjm2Mm_UOkm7sJaH4usyc_Kw&lang=zh_CN";
		System.out.println(doGet(link));
	}

	public static String doGet(String url) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet g = new HttpGet(url);
		HttpResponse response = client.execute(g);
		HttpEntity e = response.getEntity();
		String str = EntityUtils.toString(e, "utf-8");
		return str;
	}
}
