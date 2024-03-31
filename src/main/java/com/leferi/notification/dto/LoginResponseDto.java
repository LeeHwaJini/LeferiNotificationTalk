package com.leferi.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponseDto {

	private String admId;
	private String admName;
    private Integer admGradeCd;
}
