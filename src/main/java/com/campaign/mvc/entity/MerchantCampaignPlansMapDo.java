package com.campaign.mvc.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * MerchantCampaignPlansMapDo generated by Vivek
 */

@Entity
@Table(name = "merchant_campaign_plans_map")
public class MerchantCampaignPlansMapDo implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_plan_map_pkey")
    private long merchantPlanMapPkey;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private Date updatedOn;

    @ManyToOne
    @JoinColumn(name = "merchant_fkey")
    private MerchantDo merchant;



    public long getMerchantPlanMapPkey() {
        return merchantPlanMapPkey;
    }

    public void setMerchantPlanMapPkey(long merchantPlanMapPkey) {
        this.merchantPlanMapPkey = merchantPlanMapPkey;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public MerchantDo getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantDo merchant) {
        this.merchant = merchant;
    }
}


