package com.sunrise.model.pojo;

import com.sunrise.model.base.BaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoucherOrders extends BaseModel {

    private String orderNo;
    private Integer fkVoucherId;
    private Integer fkDinerId;
    private String qrcode;
    private int payment;
    private int status;
    private int orderType;
    private int fkSeckillId;

}