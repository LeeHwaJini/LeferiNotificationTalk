package com.leferi.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationSendRequestDto {

    private String telNum;
    private String msgContent;
    private String urlPc;
    private String urlMobile;

}
