package com.leferi.notification.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationTempleteRequestDto {

	private Long ntIdx;
    private String ntTemplateCode;
    private String ntTitle;
    private String ntText;
    private Character ntUseYn;
    private Timestamp ntRegDate;
    private String ntMemo;
    
}
