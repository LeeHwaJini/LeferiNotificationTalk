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
@Table(schema = "app", name = "tbl_notification_template")
@Entity
@Builder
@DynamicUpdate
@DynamicInsert
public class NotificationTemplete{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nt_idx", columnDefinition = "BIGINT")
    private Long ntIdx;

    // 알림톡 템플릿 코드
    @Column(name = "nt_template_code")
    private String ntTemplateCode;

    // 템플릿명
    @Column(name = "nt_title")
    private String ntTitle;

    // 템플릿 내용
    @Column(name = "nt_text")
    private String ntText;

    // 사용 여부 [Y:사용 | N:미사용]
    @Column(name = "nt_use_yn")
    private Character ntUseYn;

    // 등록일시
    @Column(name = "nt_reg_date")
    private Timestamp ntRegDate;

    // 메모
    @Column(name = "nt_memo")
    private String ntMemo;

    public void UpdateInfo(NotificationTempleteRequestDto requestDto) {
    }


}
