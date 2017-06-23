package com.outlook.dev.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;
import com.outlook.dev.auth.AuthHelper;
import com.outlook.dev.auth.IdToken;
import com.outlook.dev.auth.TokenResponse;
import com.outlook.dev.service.OutlookService;
import com.outlook.dev.service.OutlookServiceBuilder;
import com.outlook.dev.service.OutlookUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

  @RequestMapping(value="/authorize", method=RequestMethod.POST)
  public String authorize(
      @RequestParam("code") String code, 
      @RequestParam("id_token") String idToken,
      @RequestParam("state") UUID state,
      HttpServletRequest request) throws ClientProtocolException, IOException{ {
    // Get the expected state value from the session
    HttpSession session = request.getSession();
    UUID expectedState = (UUID) session.getAttribute("expected_state");
    UUID expectedNonce = (UUID) session.getAttribute("expected_nonce");
	  //System.out.println("token=");

    // Make sure that the state query parameter returned matches
    // the expected state
    if (state.equals(expectedState)) {
    	IdToken idTokenObj = IdToken.parseEncodedToken(idToken, expectedNonce.toString());
    	if (idTokenObj != null) {
    	  TokenResponse tokenResponse = AuthHelper.getTokenFromAuthCode(code, idTokenObj.getTenantId());
    	  System.out.println("tokenrecieve="+tokenResponse.getAccessToken());
    	  TokenResponse newtoken = AuthHelper.ensureTokens(tokenResponse,idTokenObj.getTenantId() );
    	  System.out.println("newtoken="+newtoken.getAccessToken());
    	  eventget(newtoken.getAccessToken());
    	  
    	  session.setAttribute("accessToken", tokenResponse.getAccessToken());
    	  session.setAttribute("userConnected", true);
    	  session.setAttribute("userName", idTokenObj.getName());
    	// Get user info
    	  OutlookService outlookService = OutlookServiceBuilder.getOutlookService(tokenResponse.getAccessToken(), null);
    	  OutlookUser user;
    	  try {
    	    user = outlookService.getCurrentUser().execute().body();
    	    session.setAttribute("userEmail", user.getMail());
    	  } catch (IOException e) {
    	    session.setAttribute("error", e.getMessage());
    	  }
    	  session.setAttribute("userTenantId", idTokenObj.getTenantId());
    	} else {
    	  session.setAttribute("error", "ID token failed validation.");
    	}
    }
    else {
      session.setAttribute("error", "Unexpected state returned from authority.");
    }
    return "mail";
  }
}
  public void eventget(String token) throws ClientProtocolException, IOException {
	//TokenResponse tokenResponse = new TokenResponse();
	  HttpClient client = new DefaultHttpClient();
	  HttpGet request = new HttpGet("http://graph.microsoft.com/v1.0/me/events");
	  //httpget.addHeader("content-type", "application/json");
	  //System.out.println("checkingprint"+token);
	  request.addHeader("Authorization", "Bearer "+token);
	  HttpResponse response = client.execute(request);
	  BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
	  String line = "";
	  while ((line = rd.readLine()) != null) {
	    System.out.println(line);
	  }
	  
  }
  @RequestMapping("/logout")
  public String logout(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.invalidate();
    return "redirect:/index.html";
  }
}