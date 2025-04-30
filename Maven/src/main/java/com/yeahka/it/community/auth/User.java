package com.yeahka.it.community.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    private String userId;
    private String userNo;
    private String aliasName;
    private String cnName;
    private String enName;
    private String showName;
    private String avatar;
    private String thumbAvatar;
    private String email;
    private int gender;
    private int status;
    private String createTime;
    private List<Integer> deptIds;
    private String mobile;
    private String position;
    private String primaryDeptId;
    private String primaryDeptName;
    private String qrCode;
}