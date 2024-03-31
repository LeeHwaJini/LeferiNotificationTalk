package com.leferi.notification.dto;

import java.sql.Timestamp;

import com.leferi.notification.model.Admin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminResponseDto {

	private Long admIdx;
    private String admId;
    private String admPw;
    private String admName;
    private Integer admGradeCd;
    private Character admUseYn;
    private Timestamp admLastLoginDate;
    private Timestamp admRegDate;

    // 모델 복사
    public static AdminResponseDto of(Admin param) {
    	AdminResponseDto result = new AdminResponseDto();
    	result.setAdmIdx(param.getAdmIdx());
    	result.setAdmId(param.getAdmId());
    	result.setAdmPw(param.getAdmPw());
    	result.setAdmName(param.getAdmName());
    	result.setAdmGradeCd(param.getAdmGradeCd());
    	result.setAdmUseYn(param.getAdmUseYn());
    	result.setAdmLastLoginDate(param.getAdmLastLoginDate());
    	result.setAdmRegDate(param.getAdmRegDate());
    	
        return result;
    }

}
