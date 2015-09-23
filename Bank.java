package com.test.json;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2015/9/23.
 */
public class Test1 {
    public static void main(String[] args) {
        //String url = "http://124.205.35.186:7070/urming_pkuie/system/getIndexHomePage";
        String url = "http://app.abchina.com/branch/common/BranchService.svc/Branch?p=2&c=18&b=113&q=&t=1&i=0";
        CloseableHttpClient httpClient = null;

        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                if(entity != null){
                    JSONObject jsonStr = JSONObject.fromObject(EntityUtils.toString(entity));
                    JSONArray serviceArray = jsonStr.getJSONArray("BranchSearchRests");
                    if(serviceArray != null && serviceArray.size() >0){
                        for(int i=0; i<serviceArray.size(); i++){
                        JSONObject obj = serviceArray.getJSONObject(i);
                        JSONObject json=obj.getJSONObject("BranchBank");
                        System.out.println(json.optString("Name"));
                            System.out.println("-----------------------------");
                        }

                    }
                }

            } else{
                System.out.println("请求有误，错误码为"+ response.getStatusLine().getStatusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if(httpClient != null){
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
