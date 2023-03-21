package com.sunrise.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @program: sunrise
 * @description: 用户数据
 * @author: T.LM
 * @date: 2023-03-02 19:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_diners")
public class SunriseUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "roles")
    private String roles;

    @Column(name = "is_valid")
    private Integer isValid;

    /** 创建时间 */
    @CreatedDate
    @Column(name = "create_date", nullable = false)
    private Date createDate;

    /** 更新时间 */
    @LastModifiedDate
    @Column(name = "update_date", nullable = false)
    private Date updateDate;
}
