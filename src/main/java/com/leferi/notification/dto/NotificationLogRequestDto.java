package com.leferi.notification.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationLogRequestDto {

	private Long nlIdx;
    private String nlPhone;
    private Timestamp nlSendDate;
    private String nlText;
    private Integer nlStatus;
    
}
