package restclient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
public class ApacheHttpClientPost {
	/**
	 * @param args
	 * @throws JSONException
	 */
	public static void main(String[] args) throws JSONException{

		  try {

			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost postRequest = new HttpPost(
				"https://graph.microsoft.com/v1.0/me/events");
			postRequest.addHeader("Authorization", "Bearer EwAwA8l6BAAU7p9QDpi/D7xJLwsTgCg3TskyTaQAAWLF3aucYyLnHVNDSPWP9GgcTM3+S3+BjQcdk/bWg25UC3YbZLTBhxfGrwxntNdInbOVwMOCJtL9B3M3e059F+DmQB3N/+S0e4j2ok5kMaMf3JwboBmt/nPP9mxX2oNYglFGUQlYxA/KXuEqR+xnIJYFjE7iygUGhKSIPCGySquJcdgnctCId2qPPWqvEwEyCcMVZkpvIxtdYrCbJKJqfFnsRvtFFUYR5ui6VQHyY23keUasmdAjtBxLLZZGjxIOd90ZmrdSQd7V2/ePEJOP8fPy98WJPf39YLdMQ4j6AV95ln7zzMTRryin25kwiIxJX5XUxlgvGqHRnXvU8//lMx4DZgAACOsGFFbn8I4gAALPXqgD1ih58YQ6xffLKVL5wvzfA7ZTRhiEVoD/GjQrruIsW68GLL9OE2K5/I9LMvHan5rTgwDacftMTS+Xk8oVlSYskpZu+soMxByc/P7QMP0lyUUsQZSpgJjxsgeRMRiNlun2zpT2fzHxiQmDNqwfLc9ls3+6cUE6zlhjjY4FQgUPaNakuOaQcrIk4CyuecTKz76b8CoNrgHuOpTaA71sD6uPd0OyZcnZFT4qJ0UFvjq5rpVi3NBtTHX6HEwFbKAHb6LyvmOFCY6/hxevMpLxjkJ5eYI6p7n5vYPadBUmTvTH1Nq4PwSxwO0On0IYSosuV77oH14ZNMi/dqdx/fY9tVmGLz+W5eTxI9qHiMeHTZnT4X6ucvHdFI2nTkgrPoRHmBkquPkiUGTSCwVZ/137GOMvj6cor3B0Di4KPYk0UcJUGjR/GrLxUeeMVqtWvI+Q1leZe5qY6HX0sE5KJr6V2a2kzTRvQSikuaNz9M9ZTBSC9RfbrWNVqBfCqlvQL++E/sU7NNnHSuPCflMDN6XrRCzBgFbqSGTPy431+vXCG+Kays+2u4K9oS0+KAlejXugKuTYSOzFq5c5SNoXLiGZvk3Bh21KTTjcTGx0QS0yvuioO6CYl32vslfgCnvezhEra3F4+4NwxFhyHVm5Mac5CEaDiLMsMmBNDBMAe/uT+DQC");
			postRequest.addHeader("Content-type","application/json");
			JSONObject json = new JSONObject();
			json.put("subject", "testevent");
			StringEntity se = new StringEntity( json.toString());
			//JSONObject inputJsonObj = new JSONObject();
			//inputJsonObj.put("subject", "testevent");
			//String Data = JSON.stringify(json); 
			//inputJsonObj.put("start", "2017-06-22T10:18:50.473Z");
			//inputJsonObj.put("end", "");
			//StringEntity input = new StringEntity("{\"qty\":100,\"name\":\"iPad 4\"}");
			//inputJsonObj.toString();
			se.setContentType("application/json");
			postRequest.setEntity(se);

			HttpResponse response = httpClient.execute(postRequest);

			if (response.getStatusLine().getStatusCode() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(
	                        new InputStreamReader((response.getEntity().getContent())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			httpClient.getConnectionManager().shutdown();

		  } catch (MalformedURLException e) {

			e.printStackTrace();

		  } catch (IOException e) {

			e.printStackTrace();

		  }

		}
}
