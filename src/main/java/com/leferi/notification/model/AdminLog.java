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

import com.leferi.notification.dto.AdminLogRequestDto;
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
@Table(schema = "app", name = "tbl_admin_log")
@Entity
@Builder
@DynamicUpdate
@DynamicInsert
public class AdminLog{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "al_idx", columnDefinition = "BIGINT")
    private Long alIdx;

    // 관리자 번호
    @Column(name = "adm_idx", columnDefinition = "BIGINT")
    private Long admIdx;

    // 관리자 로그 유형
    @Column(name = "al_log_type")
    private String alLogType;

    // 접근 IP
    @Column(name = "al_access_ip")
    private String alAccessIp;

    // 등록일시
    @Column(name = "al_reg_date")
    private Timestamp alRegDate;

    public void UpdateInfo(AdminLogRequestDto requestDto) {
    }


}
