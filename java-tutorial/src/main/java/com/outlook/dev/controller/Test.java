package com.outlook.dev.controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;




public class Test {
	public static void main(String[] args) throws ClientProtocolException, IOException {
  	  //TokenResponse tokenResponse = new TokenResponse();
		  HttpClient client = new DefaultHttpClient();
		  HttpGet request = new HttpGet("http://graph.microsoft.com/v1.0/me/events");
		  //httpget.addHeader("content-type", "application/json");
		  request.addHeader("Authorization", "Bearer EwA4A8l6BAAU7p9QDpi/D7xJLwsTgCg3TskyTaQAAXrU2IkTQ8it34Z+7C6kV+85BwFNXHB5OlwTBR7QPgVDNFFzc5uO64Zsie8wPJDgkYSNOCrtOv30K7ydwJJdZfy8pj/ODCYB974DBUd1+lfY/2NTxY1hukpt7cVPoz42IUwPSSryvgr5Q0U/NB4EK2oi4MXS3u9gN53QDsMVyMMajlgy1LBDuQ5ARB8zjhxAdnIkbgyiH++YG+mSXIyneIhIC1RSZDHeqK5TC7LpHlqGb3rnffJYNyokTtHJ6/EMRnUH9GeFBbY3olWwT1JJiVNf211ICbo1pVNn7zP27kMjDgO8WT/4SzdhrYnLC9U3X2RbJ1kUNx1ocrawWCM5/BQDZgAACP8keRF8S+b9CAKXN9xMP94+FDqlGOVpF9pCLhAsBdh5iPbaQsUoJ1O16PlHBBf6ogZZmMM4kpIu9Aa6zzxvbNrhBIfef+2duq+RZ0tl8KlRmjCQ9k+4ram85sfF6H2LGHjdO+6A0SDZU+zpKSiAtEK2oZhKANrAGEWo/gDnle6t6RT6NMwrn4Fzyh9i9i8zhC+ajWaHDpD7nVJ0lhzqWjoKo/TJ7cLHSsJBKpDVFEVn1mENWbnBDY1ISuH66gJ5IsLf2ZYRaEJouFLH04AQ/Qh7biko47dBLva03nHh6Mzwa/n74Xx8ZzU2Bys1QXlwjMroOQ1znu5J1HhE03V8+3kZmqfDNAhDYPjpCXp0F5zLcHrqmZ8OF7jg0LvRLANikgaJcYWI6zVcmij+7hA821DS5qYXhLP9LRYu01iwwF2j1f5POz9KrVi87KCfDUjWJG0vuEiST7HsUWtyv80GESp+ixvUgTv17FEp9W68iFcMzd0t1SIyBzhcH3ZBDMQznmE5y4hgI7YMuFJ91E35YsCV3ThunZkSr99nr2ZtlWPKGEvhxRLp90ZpZB7cJYxibuppTA79/3zLjEDfRximuRDvYilULgmXUH7ZgLz4XiL3hkklL9DTLZp73QF/6D/NryEBc2pRGrBcul3q+gKttlQwjJiIr0jyNak1nM4JkntITep+h6gLriuhuH8IxxLq5KgqPgI=");
		  HttpResponse response = client.execute(request);
		  BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		  String line = "";
		  while ((line = rd.readLine()) != null) {
		    System.out.println(line);
		  }
		 }
}
