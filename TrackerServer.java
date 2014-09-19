package com.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/*
響應格式：
frame_cmd=11002&uid=1001&result=0&body={"return":0,"message":"sucess :)","count":2,"urls":["http://ip1:port1/test.mp4","http://ip2:port2/test.mp4"]}
說明：
return為錯誤碼，0表示成功，非零為對應錯誤碼;
message為返回信息字符串;
count為返回的urls數量;
urls為返回的url。

*/

public class TrackerTest {

	String url = "http://114.80.177.158:8080/track";
	String param_insert = "frame_cmd=11001&uid=1001&body={\"action\":1,\"ip\":\"localhost\",\"port\":1127,\"path\":\"/data/\",\"filename\":\"test.mp4\"}";
	String param_delete = "frame_cmd=11001&uid=1001&body={\"action\":2,\"ip\":\"localhost\",\"port\":1127,\"path\":\"/data/\",\"filename\":\"test.mp4\"}";
	String param_select = "frame_cmd=11001&uid=1001&body={\"action\":3,\"ip\":\"114.80.177.156\",\"filename\":\"dcb3c753-440b-41f0-96c7-59b627c2687d_5.mp4\"}";
	HttpClient httpClient = null;
	HttpGet httpget = null;
	HttpResponse response = null;

	@BeforeMethod
	public void init() {
		httpClient = new DefaultHttpClient();
	}
	//插入文件
	@Test
	public void a_insertFileTest() throws Exception {
		param_insert = replaceURL(param_insert);
		String result = getResponse(url + "?" + param_insert);
		Assert.assertEquals(result, "0");
	}
	//查询文件
	@Test
	public void b_selectFileTest() throws Exception {
		param_select = replaceURL(param_select);
		String result = getResponse(url + "?" + param_select);
		Assert.assertEquals(result, "0");
	}
	//删除文件
	@Test
	public void c_deleteFileTest() throws Exception {
		param_delete = replaceURL(param_delete);
		String result = getResponse(url + "?" + param_delete);
		Assert.assertEquals(result, "0");
	}
	//将URL中特殊字符做转义处理
	private String replaceURL(String url) {
		url = url.replace(" ", "%20");
		url = url.replace("\"", "%22");
		url = url.replace(":", "%3A");
		url = url.replace(",", "%2C");
		url = url.replace("/", "%2F");
		url = url.replace("{", "%7B");
		url = url.replace("}", "%7D");
		return url;
	}
	//获取服务器返回数据
	private String getResponse(String url) throws Exception {
		httpget = new HttpGet(url);
		response = httpClient.execute(httpget);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String rev = EntityUtils.toString(response.getEntity());
			System.out.println(rev);
			String[] data = rev.split("&");
			String[] result = data[2].split("=", 2);
			return result[1];
		} else {
			System.out.println("请求失败，错误代码为："
					+ response.getStatusLine().getStatusCode());
		}
		return null;
	}

	@AfterMethod
	public void end() {
		httpClient.getConnectionManager().shutdown();
	}
}
