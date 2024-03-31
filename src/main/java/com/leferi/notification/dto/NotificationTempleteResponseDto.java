package com.leferi.notification.dto;

import java.sql.Timestamp;

import com.leferi.notification.model.NotificationTemplete;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationTempleteResponseDto {

	private Long ntIdx;
    private String ntTemplateCode;
    private String ntTitle;
    private String ntText;
    private Character ntUseYn;
    private Timestamp ntRegDate;
    private String ntMemo;

    // 모델 복사
    public static NotificationTempleteResponseDto of(NotificationTemplete param) {
    	NotificationTempleteResponseDto result = new NotificationTempleteResponseDto();
        result.setNtIdx(param.getNtIdx());
        result.setNtTemplateCode(param.getNtTemplateCode());
        result.setNtTitle(param.getNtTitle());
        result.setNtText(param.getNtText());
        result.setNtUseYn(param.getNtUseYn());
        result.setNtRegDate(param.getNtRegDate());
        result.setNtMemo(param.getNtMemo());
        
        return result;
    }

}
