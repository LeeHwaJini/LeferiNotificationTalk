package com.leferi.notification.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.leferi.notification.dto.NotificationLogRequestDto;
import com.leferi.notification.dto.NotificationTempleteRequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "app", name = "tbl_notification_log")
@Entity
@Builder
@DynamicUpdate
@DynamicInsert
public class NotificationLog{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nl_idx", columnDefinition = "BIGINT")
    private Long nlIdx;

    // 발송번호
    @Column(name = "nl_phone")
    private String nlPhone;

    // 발송일시
    @Column(name = "nl_send_date")
    private Timestamp nlSendDate;

    // 발송내용
    @Column(name = "nl_text")
    private String nlText;

    // 발송상태 [1:발송전 | 2:발송완료 | 3:발송실패]
    @Column(name = "nl_status")
    private Integer nlStatus;

    public void UpdateInfo(NotificationLogRequestDto requestDto) {
    }


}
