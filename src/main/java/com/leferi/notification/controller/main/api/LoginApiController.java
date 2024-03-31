package com.leferi.notification.controller.main.api;


import java.security.InvalidParameterException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leferi.notification.dto.AdminRequestDto;
import com.leferi.notification.dto.AdminResponseDto;
import com.leferi.notification.dto.CommonRestResponse;
import com.leferi.notification.dto.LoginRequestDto;
import com.leferi.notification.dto.LoginResponseDto;
import com.leferi.notification.exception.AbsBaseException;
import com.leferi.notification.exception.InternalServerException;
import com.leferi.notification.service.AdminService;
import com.leferi.notification.utils.CommonRestResponseUtil;

@RestController
@RequestMapping("/api/admin")
public class LoginApiController {
    
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<CommonRestResponse<LoginResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) throws Exception {

        try{
            AdminResponseDto admin = adminService.getAdminInfo(loginRequestDto.getAdmId());

            // TODO: 로그인 상태 체크
            
            
            if(!adminService.checkPassword(admin.getAdmId(), admin.getAdmPw())) {
            	// 로그인 실패 시 처리
            	
                throw new InvalidParameterException();
            }

            // TODO: 로그인 로그 처리
           

            // 최종로그인일자 갱신처리
//            managerAuthService.updateLoginDate(findOne.getMngrId());
//
//            LoginManagerResponseDto loginRespDto = LoginManagerResponseDto.builder()
//                    .mngrId(findOne.getMngrId())
//                    .jwtToken(managerAuthService.CreateJwtToken(findOne.getMngrId()))
//                    .build()
//                    ;
            
            //관리자 로그인 Access Log 저장
//            AddManagerAccsLogRequestDto accessLogRequestDto = new AddManagerAccsLogRequestDto();
//			  accessLogRequestDto.setMngrIdx(findOne.getMngrIdx());
//			  accessLogRequestDto.setMalDeviceCd(ClientUtils.getMngrDeviceType(request));
//			  accessLogRequestDto.setMalAccessIp(ClientUtils.getApprochIp(request));
//			  accessLogRequestDto.setMalTypeCd(MngrAccsLogTyp.LOGIN);
//			  accessLogRequestDto.setMalUrlPath(request.getRequestURI());
//			  managerService.addMngrAccessLog(accessLogRequestDto);
//            return LcmdRestRespUtil.OK("로그인", loginRespDto);
            
            return null;
        }
        catch(AbsBaseException e) {
            return CommonRestResponseUtil.BAD_REQUEST(e);
        }
        catch(Exception e) {
        	return CommonRestResponseUtil.INTERNAL_SERVER_ERROR(new InternalServerException(e));
        }
    }

    @PostMapping("/regist")
    public ResponseEntity<CommonRestResponse<LoginResponseDto>> registAdmin(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) throws Exception {

        try{
        	AdminResponseDto admin = adminService.getAdminInfo(loginRequestDto.getAdmId());
            return null;
        }
        catch(AbsBaseException e) {
            return CommonRestResponseUtil.BAD_REQUEST(e);
        }
        catch(Exception e) {
        	return CommonRestResponseUtil.INTERNAL_SERVER_ERROR(new InternalServerException(e));
        }
    }


}
