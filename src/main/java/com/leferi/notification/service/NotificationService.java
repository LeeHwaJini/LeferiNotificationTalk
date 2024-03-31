package com.leferi.notification.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leferi.notification.dao.NotificationTempleteRepository;
import com.leferi.notification.dto.NotificationSendRequestDto;
import com.leferi.notification.dto.NotificationTempleteResponseDto;
import com.leferi.notification.model.NotificationTemplete;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@Service
public class NotificationService {

//	@Autowired
//	private NotificationTempleteRepository notificationTempleteRepository;

	@Transactional(readOnly = true)
	public List<NotificationTempleteResponseDto> getTemplateList() {
//		List<NotificationTemplete> result = notificationTempleteRepository.findAll();
//		List<NotificationTempleteResponseDto> list = result.stream().map(NotificationTempleteResponseDto::of)
//				.collect(Collectors.toList());
				
//		return list;
		return null;
	}
	
	@Async
	public String send(String templateId, List<NotificationSendRequestDto> reqList) throws Exception {
		/* 알림톡 설정값 */
	    String reqURL = "https://jupiter.lunasoft.co.kr/api/AlimTalk/message/send"; // 전송요청 URL
	    String userid = "lecommend";
	    String api_key = "xxsc4koiy5b5e792j9cv4a1jxz8nm5p2l8oqjuly";

        if(reqList == null) {
        	return "N";
        }
        
		// 파라미터 설정
	    HashMap<String, Object> param = new HashMap<String, Object>();
	    param.put("userid", userid);
	    param.put("api_key", api_key);
	    param.put("template_id", templateId);
	
	    List<HashMap<String, Object>> messageList = new ArrayList<>();
	    int i = 0;
	    for(NotificationSendRequestDto req : reqList) {
	    	HashMap<String, Object> message = new HashMap<String, Object>();
		    message.put("no", i);
		    message.put("tel_num", req.getTelNum());
		    message.put("msg_content", req.getMsgContent());
		    message.put("sms_content", req.getMsgContent());
//		    message.put("msg_content", "레코멘드(Le.commend) \n \n테스트님, 테스트쿠폰명을 사용 할 수 있는 기간이 1일 남았습니다. \n \n- 이 메시지는 고객님의 동의에 의해 지급된 쿠폰 안내 메시지입니다. \n- 레코멘드 고객센터 \n010-7746-8128");
//		    message.put("sms_content", "레코멘드(Le.commend) \n \n테스트님, 테스트쿠폰명을 사용 할 수 있는 기간이 1일 남았습니다. \n \n- 이 메시지는 고객님의 동의에 의해 지급된 쿠폰 안내 메시지입니다. \n- 레코멘드 고객센터 \n010-7746-8128");
		    message.put("use_sms", 1);

		    List<HashMap<String, Object>> btnUrlList = new ArrayList<>();
		    HashMap<String, Object> btnUrl = new HashMap<String, Object>();
		    
		    btnUrl.put("url_pc", req.getUrlPc()==null?"https://about.lecommend.com":req.getUrlPc());
		    btnUrl.put("url_mobile", req.getUrlMobile()==null?"https://about.lecommend.com":req.getUrlMobile());
		    btnUrlList.add(btnUrl);
		    
		    message.put("btn_url", btnUrlList);
		    
		    messageList.add(message);
		    
//		    MessageLog messageLog = MessageLog.builder()
//					.mlPhone(req.getTelNum())
//					.mlSendDate(Timestamp.valueOf(LocalDateTime.now()))
//					.mlText(req.getMsgContent())
//					.mlStatus((short)1)
//					.memIdx(req.getMemIdx())
//					.build();
//			
//		    messageLogRepository.saveAndFlush(messageLog);
		    
		    i++;
	    }
	    
	    param.put("messages", messageList);
        URL obj = null;
        obj = new URL(reqURL); // API URL

        HttpURLConnection con = (HttpURLConnection)obj.openConnection();
        con.setRequestMethod("POST"); // GET, POST
        con.setRequestProperty("Content-type", "application/json; charset=UTF-8");
        con.setDoOutput(true);
        // DATA
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = mapper.writeValueAsString(param);
        
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
		
		return null;
	}

	public String sendTest() throws Exception {
		
//	    request.setCharacterEncoding(charsetType);
//	    response.setCharacterEncoding(charsetType);
//	    String  action     = nullcheck(request.getParameter("action"), "");
		
		String reqURL = "";
		reqURL = "https://jupiter.lunasoft.co.kr/api/AlimTalk/message/send"; // SMS 전송요청 URL
//        String user_id = base64Encode("smsmatchbaseball"); // SMS아이디
//        String secure = base64Encode("152297d3489d4d4c60b6ddaae571a604");//인증키
//        String msg = base64Encode(nullcheck(request.getParameter("msg"), ""));
//        String rphone = base64Encode(nullcheck(request.getParameter("rphone"), ""));
//        String sphone1 = base64Encode(nullcheck(request.getParameter("sphone1"), ""));
//        String sphone2 = base64Encode(nullcheck(request.getParameter("sphone2"), ""));
//        String sphone3 = base64Encode(nullcheck(request.getParameter("sphone3"), ""));
//        String rdate = base64Encode(nullcheck(request.getParameter("rdate"), ""));
//        String rtime = base64Encode(nullcheck(request.getParameter("rtime"), ""));
//        String mode = base64Encode("1");
//        String subject = "";
//        if(nullcheck(request.getParameter("smsType"), "").equals("L")) {
//            subject = base64Encode(nullcheck(request.getParameter("subject"), ""));
//        }
		
		
		
		
		// stringURL 에는 API URL 넣기
//        URL url = new URL(reqURL);
//        String line;
//        StringBuilder sb = new StringBuilder();
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("POST");
//        conn.setRequestProperty("Content-type", "application/json; charset=UTF-8");
//
//        // API 응답메시지를 불러와서 문자열로 저장
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
//        }
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        String text = sb.toString();
		
		
		// 파라미터 설정
	    HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userid", "lecommend");
		param.put("api_key", "xxsc4koiy5b5e792j9cv4a1jxz8nm5p2l8oqjuly");
		param.put("template_id", "50048");

	    List<HashMap<String, Object>> messageList = new ArrayList<>();
	    HashMap<String, Object> message = new HashMap<String, Object>();
		message.put("no", "0");
		message.put("tel_num", "01094542193");
		message.put("msg_content", "레코멘드(Le.commend) \n \n테스트님, 테스트쿠폰명을 사용 할 수 있는 기간이 1일 남았습니다. \n \n- 이 메시지는 고객님의 동의에 의해 지급된 쿠폰 안내 메시지입니다. \n- 레코멘드 고객센터 \n010-7746-8128");
		message.put("sms_content", "레코멘드(Le.commend) \n \n테스트님, 테스트쿠폰명을 사용 할 수 있는 기간이 1일 남았습니다. \n \n- 이 메시지는 고객님의 동의에 의해 지급된 쿠폰 안내 메시지입니다. \n- 레코멘드 고객센터 \n010-7746-8128");
		message.put("use_sms", 1);
		
	    List<HashMap<String, Object>> btnUrlList = new ArrayList<>();
	    HashMap<String, Object> btnUrl = new HashMap<String, Object>();
		btnUrl.put("url_pc", "https://about.lecommend.com");
		btnUrl.put("url_mobile", "https://about.lecommend.com");
		btnUrlList.add(btnUrl);
		
		message.put("btn_url", btnUrlList);
		
		messageList.add(message);
		
		param.put("messages", messageList);
		// 키 타입 필수 설명 예제
//	    userid 							// text(20) 	Y 	사용자 ID “userid”:”lunasoft”
//	    api_key 						// text(50) 	Y 	Api_key “api_key”:”key_1234”
//	    template_id						// integer 		Y 	플러스 친구 템플릿 ID “template_id”:”30001
//	    messages 						// array 		Y 	메시지 정보 (최대 1000 개)
//	    		no 						// text(20) 	Y 	메시지 번호 “no”:”0”
//	    		tel_num 				// text(20) 	Y 	받는 이 전화번호 “tel_num”:”01012345678”
//	    		reserve_time 			// datetime 	N 	예약 발송 시 발송 시간 “reserve_time”:”2019-06-01 00:00:00”
//	    		custom_key 				// text(40) 	N 	알림톡/SMS/LMS 성공 실패 여부 확인 시 unique key ※ Webhook url 을 루나소프트에 선등록 해주셔야 합니다.“custom_key”:”a1234”
//	    		msg_content 			// text(1000) 	Y 	메시지 내용 (최대 1000 자)“msg_content”:”안녕하세요.알림톡입니다.”
//	    		sms_content 			// text(1000) 	Y 	메시지 내용(최대 1000 개)“sms_content”:”안녕하세요.문자메세지입니다.”
//	    		use_sms 				// boolean 		Y 	실패시 sms 전송여부1 : 전송 0 : 미전송“use_sms”:”1”
//	    		app_user_id 			// text(50) 	N 	phone_number 혹은app_user_id 둘 중 하나는반드시 있어야 합니다.phone_number 와app_user_id 의 정보가 동시에 요청된 경우phone_number 로만 발송합니다.“app_user_id” : “12345”
//	    		carrier_code 			// text(2) 		N	택배사 코드(2.3 택배사 코드 참고)※ 배송조회 템플릿 ID 인 경우필수입니다.“carrier_code” : “1”
//	    		invoice_number 			// text(30) 	N 	운송장 번호※ 배송조회 템플릿 ID 인 경우필수입니다.“invoice_number” : “123456789”
//	    		price 					// integer 		N 	메시지 내 포함된 금액 정보 “price” : 75000
//	    		currency_code 			// text(3) 		N 	통화 코드 (메시지 내 포함된 금액 정보)※ KRW, USD, EUR ”currency_code”:”KRW”
//	    		title 					// text(50) 	N 	강조 내용 ※ 강조유형 템플릿 ID 인 경우필수입니다.“title” : ”CJ 택배 1234567”
//	    		btn_url 				// 				N 	버튼 URL “btn_url” : [{ (아래 참조) }]
//	    			url_pc 				// text(100) 	N	pc 버튼 URL “url_pc”:”https://ww.lunasoft.co.kr”
//	    			url_mobile 			// text(100) 	N 	mobile 버튼 URL “url_mobile”:”https://ww.lunasoft.co.kr/m”
//	    			scheme_android		// text(100) 	N 	Android 앱 링크 URL “scheme_android”:”lunaapps://open”
//	    			scheme_ios			// text(100) 	N 	IOS 앱 링크 URL “scheme_ios”:”lunaapps://open”
		
		
		URL obj = null;
		obj = new URL(reqURL); // API URL
		
		HttpURLConnection con = (HttpURLConnection)obj.openConnection();
		con.setRequestMethod("POST"); // GET, POST
		con.setRequestProperty("Content-type", "application/json; charset=UTF-8");
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
		
		
		
		
		
		
		
//
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
//        mapper.enable(JsonReadFeature.ALLOW_NON_NUMERIC_NUMBERS.mappedFeature());
//
//        Map<String, Object> map = null;
//        try {
//            map = mapper.readValue(text, Map.class);
//        }catch(Exception e) {
//            List<String> data = mapper.readValue(text, List.class);
//            map = new HashMap<>();
//            map.put("data", data);
//        }
//        
		System.out.println(text);
		
		return null;
	}

	/* Null check */
	public static String nullcheck(String str, String Defaultvalue) throws Exception {
		String ReturnDefault = "";
		if (str == null) {
			ReturnDefault = Defaultvalue;
		} else if (str == "") {
			ReturnDefault = Defaultvalue;
		} else {
			ReturnDefault = str;
		}
		return ReturnDefault;
	}

	/* BASE64 Encoder */
	public static String base64Encode(String str) throws java.io.IOException {
		Encoder encoder = Base64.getEncoder();
		byte[] strByte = str.getBytes("UTF-8");
		String result = encoder.encodeToString(strByte);
		return result;
	}

	/* BASE64 Decoder */
	public static String base64Decode(String str) throws java.io.IOException {
		Decoder decoder = Base64.getDecoder();
		byte[] strByte = decoder.decode(str);
		String result = new String(strByte);
		return result;
	}
}
