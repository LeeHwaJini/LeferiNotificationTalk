package com.leferi.notification.dto;

import java.sql.Timestamp;

import com.leferi.notification.model.AdminLog;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminLogResponseDto {

	private Long alIdx;
    private Long admIdx;
    private String alLogType;
    private String alAccessIp;
    private Timestamp alRegDate;

    // 모델 복사
    public static AdminLogResponseDto of(AdminLog param) {
    	AdminLogResponseDto result = new AdminLogResponseDto();
        result.setAlIdx(param.getAlIdx());
        result.setAdmIdx(param.getAdmIdx());
        result.setAlLogType(param.getAlLogType());
        result.setAlAccessIp(param.getAlAccessIp());
        result.setAlRegDate(param.getAlRegDate());
        
        return result;
    }

}
