public class API_Sample {
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String url = "http://rtd.test.com/rotdam";
		HttpClient httpClient = new DefaultHttpClient();
		
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("reqid", "DcsHdazzT6McMRiBmJGzrnBy3yS5jbea"));
		nvps.add(new BasicNameValuePair("servname", "prc"));
		nvps.add(new BasicNameValuePair("group", "mail-service"));
		nvps.add(new BasicNameValuePair("ver", "1.0.0"));
		nvps.add(new BasicNameValuePair("fn", "CMSMail"));
		nvps.add(new BasicNameValuePair("args.ars", "zhangyj@126.com,gongss@126.com,yanll@126.com"));
		nvps.add(new BasicNameValuePair("args.sub", "主题4"));
		nvps.add(new BasicNameValuePair("args.txt", "邮件正文4"));
		nvps.add(new BasicNameValuePair("args.acc", ""));
		nvps.add(new BasicNameValuePair("args.user", "rotdam"));
		
		//HttpResponse response = httpClient.execute(httpPost);
		
		HttpResponse response = reqUtils.doPost(httpClient, url, nvps);
		if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			String rev = EntityUtils.toString(response.getEntity());
			System.out.println(rev);
			
		}else {
			System.out.println("请求失败，错误代码为："+response.getStatusLine().getStatusCode());
		}
		httpClient.getConnectionManager().shutdown();				
		
	}
}
