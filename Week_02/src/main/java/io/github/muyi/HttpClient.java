package io.github.muyi;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {
	private static final String URL = "http://localhost:8088";

	public static void main(String[] args) {
		httpClientGet(URL);
		okHttpGet(URL);
	}

	private static void okHttpGet(String url) {
		OkHttpClient okHttpClient = new OkHttpClient();
		Request request = new Request
				.Builder()
				.url(url)
				.build();
		Call call = okHttpClient.newCall(request);
		try(Response response = call.execute();) {
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void httpClientGet(String url) {
		HttpGet httpGet = new HttpGet(url);
		try (CloseableHttpClient client = HttpClientBuilder.create().build();
		     CloseableHttpResponse response = client.execute(httpGet)) {
			HttpEntity entity = response.getEntity();
			System.out.println(EntityUtils.toString(entity));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
