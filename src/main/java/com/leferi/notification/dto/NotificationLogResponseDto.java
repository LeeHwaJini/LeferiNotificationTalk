package com.leferi.notification.dto;

import java.sql.Timestamp;

import com.leferi.notification.model.NotificationLog;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationLogResponseDto {

	private Long nlIdx;
    private String nlPhone;
    private Timestamp nlSendDate;
    private String nlText;
    private Integer nlStatus;

    // 모델 복사
    public static NotificationLogResponseDto of(NotificationLog param) {
    	NotificationLogResponseDto result = new NotificationLogResponseDto();
    	result.setNlIdx(param.getNlIdx());
    	result.setNlPhone(param.getNlPhone());
    	result.setNlSendDate(param.getNlSendDate());
    	result.setNlText(param.getNlText());
    	result.setNlStatus(param.getNlStatus());
        
        return result;
    }

}
