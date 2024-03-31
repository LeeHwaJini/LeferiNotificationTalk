package com.leferi.notification.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminLogRequestDto {

	private Long alIdx;
    private Long admIdx;
    private String alLogType;
    private String alAccessIp;
    private Timestamp alRegDate;
    
}
