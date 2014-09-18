package com.test;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPSIP {
	String url = "http://172.30.84.23:8080/setacct/";
	HttpClient httpClient = null;
	HttpPost httppost = null;
	JSONObject object = null;

	@BeforeMethod
	public void inti() {
		httpClient = new DefaultHttpClient();
		httppost = new HttpPost(url);
		httppost.setHeader("Pragma:", "no-cache");
		httppost.setHeader("Cache-Control", "no-cache");
		httppost.setHeader("Content-Type", "text/xml;charset = utf-8");
		object = new JSONObject();
	}

	// 测试AD
	@Test
	public void ad_Test() throws Exception {
		// {'sta':0,'acct':'wangdh3'}
		// {'sta':0,'acct':'wangdh'}
		object.put("sid", "ad");
		object.put("sn", "王");
		object.put("gn", "大虎");
		object.put("py", "wangdh");
	}

	@Test
	public void mail_Test() throws Exception {
		object.put("sid", "ex");
		object.put("acct", "wangdh16");
	}

	// 测试电话
	@Test
	public void phone_Test() throws Exception {
		// 错误参数{'sta':'1','msg':'user has exist'}
		// 正确参数{'sta':0,'tel':'5025'}
		object.put("sid", "ly");
		object.put("acct", "wangdh16");
	}

	@AfterMethod
	public void over() throws Exception {
		httppost.setEntity(new StringEntity(object.toString(), "UTF-8"));
		HttpResponse response = httpClient.execute(httppost);
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			String rev = EntityUtils.toString(response.getEntity());
			System.out.println(rev);
		}else {
			System.out.println("请求失败，错误代码为："+response.getStatusLine().getStatusCode());
		}
		httpClient.getConnectionManager().shutdown();
	}
}
