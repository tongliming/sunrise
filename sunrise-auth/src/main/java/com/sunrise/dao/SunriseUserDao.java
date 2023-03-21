package com.sunrise.dao;

import com.sunrise.entity.SunriseUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: sunrise
 * @description:
 * @author: Mr.Wang
 * @data: 2023-03-02 19:42
 **/
public interface SunriseUserDao extends JpaRepository<SunriseUser, Long> {
    SunriseUser findByUsername(String username);
    SunriseUser findByUsernameAndPassword(String username, String password);
}
