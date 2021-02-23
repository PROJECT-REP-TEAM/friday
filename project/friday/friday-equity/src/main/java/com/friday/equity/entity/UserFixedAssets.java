package com.friday.equity.entity;

import java.io.Serializable;

/**
 * (UserFixedAssets)实体类
 *
 * @author makejava
 * @since 2021-02-23 16:14:02
 */
public class UserFixedAssets implements Serializable {
    private static final long serialVersionUID = 760053559862097187L;
    /**
    * 资产id主键
    */
    private Integer fixedId;
    /**
    * 资产名
    */
    private String fixedName;
    /**
    * 资产所在地
    */
    private String fixedLocation;
    /**
    * 资产获得时间
    */
    private String fixedCreateTime;
    /**
    * 资产所有者
    */
    private String fixedOwner;
    /**
    * 资产分期（可没有）
    */
    private String fixedInstalment;
    /**
    * 每期价格
    */
    private String instalmentPrice;
    /**
    * 剩余期限
    */
    private String instalmentSurplus;
    /**
    * 备注
    */
    private String fixedRemark;


    public Integer getFixedId() {
        return fixedId;
    }

    public void setFixedId(Integer fixedId) {
        this.fixedId = fixedId;
    }

    public String getFixedName() {
        return fixedName;
    }

    public void setFixedName(String fixedName) {
        this.fixedName = fixedName;
    }

    public String getFixedLocation() {
        return fixedLocation;
    }

    public void setFixedLocation(String fixedLocation) {
        this.fixedLocation = fixedLocation;
    }

    public String getFixedCreateTime() {
        return fixedCreateTime;
    }

    public void setFixedCreateTime(String fixedCreateTime) {
        this.fixedCreateTime = fixedCreateTime;
    }

    public String getFixedOwner() {
        return fixedOwner;
    }

    public void setFixedOwner(String fixedOwner) {
        this.fixedOwner = fixedOwner;
    }

    public String getFixedInstalment() {
        return fixedInstalment;
    }

    public void setFixedInstalment(String fixedInstalment) {
        this.fixedInstalment = fixedInstalment;
    }

    public String getInstalmentPrice() {
        return instalmentPrice;
    }

    public void setInstalmentPrice(String instalmentPrice) {
        this.instalmentPrice = instalmentPrice;
    }

    public String getInstalmentSurplus() {
        return instalmentSurplus;
    }

    public void setInstalmentSurplus(String instalmentSurplus) {
        this.instalmentSurplus = instalmentSurplus;
    }

    public String getFixedRemark() {
        return fixedRemark;
    }

    public void setFixedRemark(String fixedRemark) {
        this.fixedRemark = fixedRemark;
    }

}