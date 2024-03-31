package com.leferi.notification.dto;

import java.sql.Timestamp;

import javax.persistence.Column;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdminRequestDto {

	private Long admIdx;
    private String admId;
    private String admPw;
    private String admName;
    private Integer admGradeCd;
    private Character admUseYn;
    private Timestamp admLastLoginDate;
    private Timestamp admRegDate;
    
}
