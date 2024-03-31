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

import com.leferi.notification.dto.AdminRequestDto;
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
@Table(schema = "app", name = "tbl_admin")
@Entity
@Builder
@DynamicUpdate
@DynamicInsert
public class Admin{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adm_idx", columnDefinition = "BIGINT")
    private Long admIdx;

    // 관리자 아이디
    @Column(name = "adm_id")
    private String admId;

    // 관리자 비밀번호
    @Column(name = "adm_pw")
    private String admPw;

    // 관리자 이름
    @Column(name = "adm_name")
    private String admName;

    // 관리자 등급 [1:최고관리자 | 2:일반관리자]
    @Column(name = "adm_grade_cd")
    private Integer admGradeCd;

    // 사용 여부 [Y:사용 | N:미사용]
    @Column(name = "adm_use_yn")
    private Character admUseYn;

    // 최종 로그인일시
    @Column(name = "adm_last_login_date")
    private Timestamp admLastLoginDate;

    // 등록일시
    @Column(name = "adm_reg_date")
    private Timestamp admRegDate;

    public void UpdateInfo(AdminRequestDto requestDto) {
    }


}
