package com.ssafy.myapp.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.myapp.db.repository.UserRepository;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;



@Service
public class KakaoAuthService {
	
	@Autowired
	UserRepository userRepository;
	
	public String getKakaoAccessToken (String code) {
		System.out.println("인가코드 : "+code);
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=57b3e70c7269489ba0958ddee6ee7321"); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=http://localhost:8080/api/auth/kakao"); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            
            ObjectMapper mapper = new ObjectMapper(); 
            
            try 
            { // convert JSON string to Map 
            	Map<String, String> map = mapper.readValue(result, Map.class); // it works  
            	access_Token=(String)map.get("access_token");
            	refresh_Token=(String)map.get("refresh_token");
            } catch (IOException e) {
            	e.printStackTrace(); 
            }
            
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 


        return access_Token;
    }
	
	public Map<String, String> createKakaoUser(String token) {
		
		
		System.out.println(token);
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		
		Map<String, String> userInfo = new HashMap<>();

	    //access_token을 이용하여 사용자 정보 조회
	    try {
	       URL url = new URL(reqURL);
	       HttpURLConnection conn = (HttpURLConnection) url.openConnection();

	       conn.setRequestMethod("POST");
	       conn.setDoOutput(true);
	       conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

	       //결과 코드가 200이라면 성공
	       int responseCode = conn.getResponseCode();
	       System.out.println("responseCode : " + responseCode);

	       //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
	       BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	       String line = "";
	       String result = "";

	       while ((line = br.readLine()) != null) {
	           result += line;
	       }
	       System.out.println("response body : " + result);
	       
	       ObjectMapper mapper = new ObjectMapper(); 
	       String nickname=null;
	       String email=null;

	       try { // convert JSON string to Map 
	    	   Map<String, Object> map = mapper.readValue(result, Map.class); // it works  
	    	   Map<String,Object> kakao_account=(Map<String,Object>) map.get("kakao_account");
	    	   Map<String,Object> profile=(Map<String,Object>) kakao_account.get("profile");
	    	   nickname=(String) profile.get("nickname");
	    	   email=(String) kakao_account.get("email");
	    	   
	       } catch (IOException e) {
	    	   e.printStackTrace(); 
	    	   return null;
	       }
	       
	       userInfo.put("nickname", nickname);
	       userInfo.put("email",email);
	      
//	       //Gson 라이브러리로 JSON파싱
//	       JsonParser parser = new JsonParser();
//	       JsonElement element = parser.parse(result);

//	       int id = element.getAsJsonObject().get("id").getAsInt();
//	       boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
//	       String email = "";
//	       if(hasEmail){
//	           email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
//	       }
//
//	       System.out.println("id : " + id);
//	       System.out.println("email : " + email);

	       br.close();

	       } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	       }
	    return userInfo;
	 }
}