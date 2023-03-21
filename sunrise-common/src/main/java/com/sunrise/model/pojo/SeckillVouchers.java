package com.sunrise.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sunrise.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SeckillVouchers extends BaseModel {

    private Integer fkVoucherId;
    private int amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

}