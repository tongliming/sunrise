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
@Table(name = "sunrise_user")
public class SunriseUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    /** 额外的信息, json 字符串存储 */
    @Column(name = "extra_info", nullable = false)
    private String extraInfo;

    /** 创建时间 */
    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /** 更新时间 */
    @LastModifiedDate
    @Column(name = "update_time", nullable = false)
    private Date updateTime;
}
