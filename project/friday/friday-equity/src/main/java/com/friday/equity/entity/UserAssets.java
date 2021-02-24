package com.friday.equity.entity;

import java.io.Serializable;

/**
 * (UserAssets)实体类
 *
 * @author makejava
 * @since 2021-02-24 16:09:59
 */
public class UserAssets implements Serializable {
    private static final long serialVersionUID = -69796075334981986L;
    /**
    * 资产id主键
    */
    private Integer assetsId;
    /**
    * 资产名
    */
    private String assetsName;
    /**
    * 资产所在地
    */
    private String assetsLocation;
    /**
    * 资产获得时间
    */
    private String assetsCreateTime;
    /**
    * 资产所有者
    */
    private String assetsOwner;
    /**
    * 资产分期（可没有）
    */
    private String assetsInstalment;
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
    private String assetsRemark;


    public Integer getAssetsId() {
        return assetsId;
    }

    public void setAssetsId(Integer assetsId) {
        this.assetsId = assetsId;
    }

    public String getAssetsName() {
        return assetsName;
    }

    public void setAssetsName(String assetsName) {
        this.assetsName = assetsName;
    }

    public String getAssetsLocation() {
        return assetsLocation;
    }

    public void setAssetsLocation(String assetsLocation) {
        this.assetsLocation = assetsLocation;
    }

    public String getAssetsCreateTime() {
        return assetsCreateTime;
    }

    public void setAssetsCreateTime(String assetsCreateTime) {
        this.assetsCreateTime = assetsCreateTime;
    }

    public String getAssetsOwner() {
        return assetsOwner;
    }

    public void setAssetsOwner(String assetsOwner) {
        this.assetsOwner = assetsOwner;
    }

    public String getAssetsInstalment() {
        return assetsInstalment;
    }

    public void setAssetsInstalment(String assetsInstalment) {
        this.assetsInstalment = assetsInstalment;
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

    public String getAssetsRemark() {
        return assetsRemark;
    }

    public void setAssetsRemark(String assetsRemark) {
        this.assetsRemark = assetsRemark;
    }

}