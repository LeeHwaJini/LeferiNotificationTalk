package com.leferi.notification.controller.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leferi.notification.dto.NotificationSendRequestDto;
import com.leferi.notification.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class MainController {

	@Autowired
	private NotificationService notificationService;
	
    @GetMapping("")
    public String main(Model model){
        return "forward:/dashboard/main";
    }
    
    @GetMapping("/test")
    public String test(Model model){
    	notificationService.getTemplateList();
    	
    	// 알림톡 처리
		try {

			List<NotificationSendRequestDto> sendReqList = new ArrayList<NotificationSendRequestDto>();
			NotificationSendRequestDto sendReq = new NotificationSendRequestDto();
			sendReq.setTelNum("01094542193");
			
			String message = "레코멘드(Le.commend)\n" + 
					"\n" + 
					"#{활동명}님을 위한 쿠폰이 발급되었습니다. #{쿠폰명}\n" + 
					"\n" + 
					"- 이 메시지는 고객님의 동의에 의해 지급된 쿠폰 안내 메시지입니다.\n" + 
					"- 레코멘드 고객센터\n" + 
					"010-7746-8128";
			message = message.replace("#{활동명}", "발송테스트")
							.replace("#{쿠폰명}", "테스트");
			
			sendReq.setMsgContent(message);
//			messageReq.setUrlMobile("http://www.lecommend.com/toLcmd?link=%2Fmypage%2Fcoupon");
//			messageReq.setUrlPc("http://www.lecommend.com/toLcmd?link=%2Fmypage%2Fcoupon");
			
			sendReqList.add(sendReq);
			notificationService.send("50040", sendReqList);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
        return "login";
    }
    
    @GetMapping("/apitest")
    public String apitest(Model model){
    	
		try {

			/* oauth 인증 - 토큰 발급*/
//		    String reqURL = "https://instance_name.my.salesforce.com/services/oauth2/token"; // 전송요청 URL
//		    String client_id = "3MVG9n_HvETGhr3Crtv2g77o6OEgpgtWqWQmXF4NXuoiU7lDXWdEnSWq2r1lSNFnGxlounXd3yUwgr2YpUP3B";
//		    String client_secret = "33C805878AEB36F840D6462D10725FE1E0A4E4D22E94288021A73A47FF354564";

			/*
			// 테스트용
			String reqURL = "http://shsoftnet-dev-ed.develop.my.salesforce.com/services/oauth2/token"; // 전송요청 URL
//			String reqURL = "http://shsoftnet-dev-ed.develop.my.salesforce.com/services/oauth2/token"; // 전송요청 URL
		    String client_id = "3MVG95mg0lk4bati0SfkgEgFeavZrgHLqCx7Cq44G0F.qUhLda7JLPlHjl_Do4MZyHQcvubOqyrhGPZb3x5S7";
		    String client_secret = "F9424732C1A2DCA45B0DFA419296FC5CBBE2F97C2E7F5E9EBCA67DB03ED31A02";
			
			// 파라미터 설정
		    JSONObject param = new JSONObject();
		    param.put("grant_type", "client_credentials");
		    param.put("client_id", client_id);
		    param.put("client_secret", client_secret);
		
	        URL obj = null;
	        obj = new URL(reqURL); // API URL

	        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	        con.setRequestMethod("POST"); // GET, POST
	        con.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
//	        con.setRequestProperty("Content-type", "application/json; charset=UTF-8");
	        con.setDoOutput(true);
	        // DATA
	        OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
	        wr.write(param.toString());
	        wr.flush();
	        // API 호출
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream(), "UTF-8"));
	        String line;
	        StringBuffer sb = new StringBuffer();
	        while((line = in.readLine()) != null){
	            sb.append(line);
	        }
	        in.close();
	        con.disconnect();
	        String text = sb.toString();
	        ObjectMapper mapper = new ObjectMapper();
	        Map<String, Object> map = mapper.readValue(text, Map.class);
	        
	        
	        System.out.println(text);*/
			
			
			// httpcon로하면 오류나서 웹으로하니 됨
//			https://login.salesforce.com/services/oauth2/authorize?response_type=token&client_id=3MVG95mg0lk4bati0SfkgEgFeavZrgHLqCx7Cq44G0F.qUhLda7JLPlHjl_Do4MZyHQcvubOqyrhGPZb3x5S7&redirect_uri=https%3A%2F%2Fhoon-dev.xyz%2FapitestCallback&state=mystate
//			https://shsoftnet-dev-ed.develop.my.salesforce.com/services/oauth2/authorize?response_type=token&client_id=3MVG95mg0lk4bati0SfkgEgFeavZrgHLqCx7Cq44G0F.qUhLda7JLPlHjl_Do4MZyHQcvubOqyrhGPZb3x5S7&redirect_uri=https%3A%2F%2Fhoon-dev.xyz%2FapitestCallback&state=mystate

				
			// 테스트용
		    String client_id = "3MVG95mg0lk4bati0SfkgEgFeavZrgHLqCx7Cq44G0F.qUhLda7JLPlHjl_Do4MZyHQcvubOqyrhGPZb3x5S7";
		    String client_secret = "F9424732C1A2DCA45B0DFA419296FC5CBBE2F97C2E7F5E9EBCA67DB03ED31A02";
		    
		    // 파라미터 설정
//		    JSONObject param = new JSONObject();
		    ObjectMapper mapper = new ObjectMapper();
		    HashMap<String, Object> param = new HashMap<String, Object>();
		    
			String reqURL = "https://shsoftnet-dev-ed.develop.my.salesforce.com/services/oauth2/authorize"; // 전송요청 URL
		    String redirect_uri = URLEncoder.encode("https://hoon-dev.xyz/apitestCallback", "UTF-8");
			param.put("response_type", "token");
		    param.put("client_id", client_id);
//		    param.put("redirect_uri", "https://localhost:8080/apitestCallback");
		    param.put("redirect_uri", redirect_uri);
		    param.put("state", "mystate");
		    
		    String jsonStr = mapper.writeValueAsString(param);
		    
//			String reqURL = "http://shsoftnet-dev-ed.develop.my.salesforce.com/services/oauth2/token"; // 전송요청 URL
//		    param.put("grant_type", "client_credentials");
//		    param.put("client_id", client_id);
//		    param.put("client_secret", client_secret);
		
	        URL obj = null;
	        obj = new URL(reqURL); // API URL

	        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	        con.setRequestMethod("POST"); // GET, POST
	        con.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
//		        con.setRequestProperty("Content-type", "application/json; charset=UTF-8");
	        con.setDoInput(true);
	        con.setDoOutput(true);
	        // DATA
	        OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
	        wr.write(jsonStr);
	        wr.flush();
	        // API 호출
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream(), "UTF-8"));
	        String line;
	        StringBuffer sb = new StringBuffer();
	        while((line = in.readLine()) != null){
	            sb.append(line);
	        }
	        in.close();
	        con.disconnect();
	        String text = sb.toString();
	        mapper = new ObjectMapper();
	        Map<String, Object> map = mapper.readValue(text, Map.class);
	        
	        
	        System.out.println(text);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
        return "apitest";
    }
    
    @GetMapping("/apitestCallback")
    public String apitest(Model model, HttpServletRequest request, @RequestParam Map<String, Object> requestParam){
    	
    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
    	requestParam.forEach((key, value) -> {	
    		System.out.println(key + " : " + value);	
    	});	
    	
    	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
		try {

			/* oauth 인증 - 토큰 발급*/
//		    String reqURL = "https://instance_name.my.salesforce.com/services/oauth2/token"; // 전송요청 URL
//		    String client_id = "3MVG9n_HvETGhr3Crtv2g77o6OEgpgtWqWQmXF4NXuoiU7lDXWdEnSWq2r1lSNFnGxlounXd3yUwgr2YpUP3B";
//		    String client_secret = "33C805878AEB36F840D6462D10725FE1E0A4E4D22E94288021A73A47FF354564";

			/*
			// 테스트용
			String reqURL = "http://shsoftnet-dev-ed.develop.my.salesforce.com/services/oauth2/token"; // 전송요청 URL
//			String reqURL = "http://shsoftnet-dev-ed.develop.my.salesforce.com/services/oauth2/token"; // 전송요청 URL
		    String client_id = "3MVG95mg0lk4bati0SfkgEgFeavZrgHLqCx7Cq44G0F.qUhLda7JLPlHjl_Do4MZyHQcvubOqyrhGPZb3x5S7";
		    String client_secret = "F9424732C1A2DCA45B0DFA419296FC5CBBE2F97C2E7F5E9EBCA67DB03ED31A02";
			
			// 파라미터 설정
		    JSONObject param = new JSONObject();
		    param.put("grant_type", "client_credentials");
		    param.put("client_id", client_id);
		    param.put("client_secret", client_secret);
		
	        URL obj = null;
	        obj = new URL(reqURL); // API URL

	        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	        con.setRequestMethod("POST"); // GET, POST
	        con.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
//	        con.setRequestProperty("Content-type", "application/json; charset=UTF-8");
	        con.setDoOutput(true);
	        // DATA
	        OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
	        wr.write(param.toString());
	        wr.flush();
	        // API 호출
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream(), "UTF-8"));
	        String line;
	        StringBuffer sb = new StringBuffer();
	        while((line = in.readLine()) != null){
	            sb.append(line);
	        }
	        in.close();
	        con.disconnect();
	        String text = sb.toString();
	        ObjectMapper mapper = new ObjectMapper();
	        Map<String, Object> map = mapper.readValue(text, Map.class);
	        
	        
	        System.out.println(text);*/
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
        return "apitestCallback";
    }
    
    @GetMapping("/getList")
    public String getList(Model model, @RequestParam Map<String, Object> requestParam){
    	
		try {
		
		/*{access_token=00D5h0000099gvH%21AQIAQJ_tJ4HglhqDXn3jSyOxCF0sis0YAEizsZvTA9AT2HpPddLEqFiZ2YOA3vi1wGwOR5heAPtCJjxbKauXTIqnDI46VVSs, 
		instance_url=https%3A%2F%2Fshsoftnet-dev-ed.develop.my.salesforce.com, 
		id=https%3A%2F%2Flogin.salesforce.com%2Fid%2F00D5h0000099gvHEAQ%2F0055h00000AUA5rAAH, 
		issued_at=1705796993050, 
		signature=QvP7hccPR4VFPFE4MNjunhaiBQ2O3Ei4Nn6OH%2FN9mP4%3D, 
		state=mystate, 
		scope=full, 
		token_type=Bearer}*/


		    // 파라미터 설정
//		    JSONObject param = new JSONObject();
		    ObjectMapper mapper = new ObjectMapper();
		    HashMap<String, Object> param = new HashMap<String, Object>();
		    
			String reqURL = "https://shsoftnet-dev-ed.develop.my.salesforce.com/services/data/v59.0/sobjects"; // 전송요청 URL
		    
		    String jsonStr = mapper.writeValueAsString(param);
		    
	        URL obj = null;
	        obj = new URL(reqURL); // API URL

	        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	        con.setRequestMethod("GET"); // GET, POST
	        con.setRequestProperty("Content-type", "application/json;");
	        con.setRequestProperty("Authorization", "Bearer " + requestParam.get("access_token"));
	        con.setDoInput(true);
	        con.setDoOutput(true);
	        // DATA
	        OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
	        wr.write(jsonStr);
	        wr.flush();
	        // API 호출
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream(), "UTF-8"));
	        String line;
	        StringBuffer sb = new StringBuffer();
	        while((line = in.readLine()) != null){
	            sb.append(line);
	        }
	        in.close();
	        con.disconnect();
	        String text = sb.toString();
	        mapper = new ObjectMapper();
	        Map<String, Object> map = mapper.readValue(text, Map.class);
	        
	        
	        System.out.println(text);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		try {
			
		    // 파라미터 설정
		    ObjectMapper mapper = new ObjectMapper();
		    HashMap<String, Object> param = new HashMap<String, Object>();
		    
			String reqURL = "https://shsoftnet-dev-ed.develop.my.salesforce.com/services/data"; // 전송요청 URL
		    
		    String jsonStr = mapper.writeValueAsString(param);
		    
	        URL obj = null;
	        obj = new URL(reqURL); // API URL

	        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	        con.setRequestMethod("GET"); // GET, POST
//	        con.setRequestProperty("Content-type", "application/x-www-form-urlencoded; charset=UTF-8");
	        con.setRequestProperty("Content-type", "application/json; charset=UTF-8");
	        con.setRequestProperty("Authorization", "Bearer " + requestParam.get("access_token"));
	        con.setDoInput(true);
	        con.setDoOutput(true);
	        // DATA
	        OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
	        wr.write(jsonStr);
	        wr.flush();
	        // API 호출
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream(), "UTF-8"));
	        String line;
	        StringBuffer sb = new StringBuffer();
	        while((line = in.readLine()) != null){
	            sb.append(line);
	        }
	        in.close();
	        con.disconnect();
	        String text = sb.toString();
	        mapper = new ObjectMapper();
	        Map<String, Object> map = mapper.readValue(text, Map.class);
	        
	        
	        System.out.println(text);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
        return "login";
    }
    
    @GetMapping("/autoLogin")
    public String autoLogin(Model model){
    	
		try {
			// 레페리
			String USERNAME     = "ContentsBlock.dev@gmail.com";
		    String PASSWORD     = "contentsblock2024";
		    String LOGINURL     = "https://login.salesforce.com";
//		    String SITEURL     = "https://leferi.my.salesforce.com";
		    String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
		    String CLIENTID     = "3MVG9n_HvETGhr3Crtv2g77o6OEgpgtWqWQmXF4NXuoiU7lDXWdEnSWq2r1lSNFnGxlounXd3yUwgr2YpUP3B";
		    String CLIENTSECRET = "33C805878AEB36F840D6462D10725FE1E0A4E4D22E94288021A73A47FF354564";

		    // 테스트용
//		    String USERNAME     = "hoony@shsoftnet.com";
//		    String PASSWORD     = "1234qwer!";
//		    String LOGINURL     = "https://login.salesforce.com";
////		    String SITEURL     = "https://shsoftnet-dev-ed.develop.my.salesforce.com";
//		    String GRANTSERVICE = "/services/oauth2/token?grant_type=password";
//		    String CLIENTID     = "3MVG95mg0lk4bati0SfkgEgFeavZrgHLqCx7Cq44G0F.qUhLda7JLPlHjl_Do4MZyHQcvubOqyrhGPZb3x5S7";
//		    String CLIENTSECRET = "F9424732C1A2DCA45B0DFA419296FC5CBBE2F97C2E7F5E9EBCA67DB03ED31A02";
			
		    // 파라미터 설정
//		    JSONObject param = new JSONObject();
		    ObjectMapper mapper = new ObjectMapper();
		    HashMap<String, Object> param = new HashMap<String, Object>();
		    
		    String reqURL = LOGINURL + 
                    GRANTSERVICE + 
                    "&client_id=" + CLIENTID + 
                    "&client_secret=" + CLIENTSECRET +
                    "&username=" + USERNAME +
                    "&password=" + PASSWORD;
		    
//			String reqURL = "https://shsoftnet-dev-ed.develop.my.salesforce.com/services/oauth2/authorize"; // 전송요청 URL
//		    String redirect_uri = URLEncoder.encode("https://hoon-dev.xyz/apitestCallback", "UTF-8");
//			param.put("response_type", "token");
//		    param.put("client_id", client_id);
//		    param.put("redirect_uri", "https://localhost:8080/apitestCallback");
//		    param.put("redirect_uri", redirect_uri);
//		    param.put("state", "mystate");
		    
		    String jsonStr = mapper.writeValueAsString(param);
		    
	        URL obj = null;
	        obj = new URL(reqURL); // API URL

	        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
	        con.setRequestMethod("GET"); // GET, POST
		        con.setRequestProperty("Content-type", "application/json; charset=UTF-8");
	        con.setDoInput(true);
	        con.setDoOutput(true);
	        // DATA
	        OutputStreamWriter wr= new OutputStreamWriter(con.getOutputStream());
	        wr.write(jsonStr);
	        wr.flush();
	        // API 호출
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(con.getInputStream(), "UTF-8"));
	        String line;
	        StringBuffer sb = new StringBuffer();
	        while((line = in.readLine()) != null){
	            sb.append(line);
	        }
	        in.close();
	        con.disconnect();
	        String text = sb.toString();
	        mapper = new ObjectMapper();
	        Map<String, Object> map = mapper.readValue(text, Map.class);
	        
	        
	        System.out.println(text);
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        ObjectMapper mapper2 = new ObjectMapper();
		    HashMap<String, Object> param2 = new HashMap<String, Object>();
		    
			String reqURL2 = "https://leferi.my.salesforce.com/services/data/v59.0/sobjects/Opportunity/listviews"; // 전송요청 URL
		    
		    String jsonStr2 = mapper2.writeValueAsString(param2);
		    
	        URL obj2 = null;
	        obj2 = new URL(reqURL2); // API URL

	        HttpURLConnection con2 = (HttpURLConnection)obj2.openConnection();
	        con2.setRequestMethod("GET"); // GET, POST
	        con2.setRequestProperty("Content-type", "application/json;");
	        con2.setRequestProperty("Authorization", "Bearer " + map.get("access_token"));
	        con2.setDoInput(true);
	        con2.setDoOutput(true);
	        // DATA
//	        OutputStreamWriter wr2= new OutputStreamWriter(con2.getOutputStream());
//	        wr2.write(jsonStr2);
//	        wr2.flush();
	        // API 호출
	        BufferedReader in2 = new BufferedReader(
	                new InputStreamReader(con2.getInputStream(), "UTF-8"));
	        String line2;
	        StringBuffer sb2 = new StringBuffer();
	        while((line2 = in2.readLine()) != null){
	            sb2.append(line2);
	        }
	        in2.close();
	        con2.disconnect();
	        String text2 = sb2.toString();
	        mapper2 = new ObjectMapper();
	        Map<String, Object> map2 = mapper2.readValue(text2, Map.class);
	        
	        
	        System.out.println(text2);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
        return "apitest";
    }
    
}
