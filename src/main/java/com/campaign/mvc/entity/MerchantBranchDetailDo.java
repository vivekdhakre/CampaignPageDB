package com.campaign.mvc.entity;


import javax.persistence.*;
import java.util.Date;

/**
 * MerchantBranchDetailDo generated by Vivek
 */

@Entity
@Table(name = "merchant_branch_detail")
public class MerchantBranchDetailDo implements java.io.Serializable {


    @Id
    @GeneratedValue
    @Column(name = "merchant_branch_pkey")
    private long merchantBranchPkey;

    @Column(name = "short_description")
    private String shortDescription;

    @Column(name = "p_mobile_no")
    private String PMobileNo;

    @Column(name = "o_mobile_no")
    private String OMobileNo;

    @Column(name = "p_phone_no")
    private String PPhoneNo;

    @Column(name = "o_phone_no")
    private String OPhoneNo;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "email")
    private String email;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "xdistance")
    private Double xdistance;

    @Column(name = "ydistance")
    private Double ydistance;

    @Column(name = "zdistance")
    private Double zdistance;

    @ManyToOne
    @JoinColumn(name = "merchant_fkey")
    private MerchantDo merchant;


    public long getMerchantBranchPkey() {
        return merchantBranchPkey;
    }

    public void setMerchantBranchPkey(long merchantBranchPkey) {
        this.merchantBranchPkey = merchantBranchPkey;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPMobileNo() {
        return PMobileNo;
    }

    public void setPMobileNo(String PMobileNo) {
        this.PMobileNo = PMobileNo;
    }

    public String getOMobileNo() {
        return OMobileNo;
    }

    public void setOMobileNo(String OMobileNo) {
        this.OMobileNo = OMobileNo;
    }

    public String getPPhoneNo() {
        return PPhoneNo;
    }

    public void setPPhoneNo(String PPhoneNo) {
        this.PPhoneNo = PPhoneNo;
    }

    public String getOPhoneNo() {
        return OPhoneNo;
    }

    public void setOPhoneNo(String OPhoneNo) {
        this.OPhoneNo = OPhoneNo;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Double getXdistance() {
        return xdistance;
    }

    public void setXdistance(Double xdistance) {
        this.xdistance = xdistance;
    }

    public Double getYdistance() {
        return ydistance;
    }

    public void setYdistance(Double ydistance) {
        this.ydistance = ydistance;
    }

    public Double getZdistance() {
        return zdistance;
    }

    public void setZdistance(Double zdistance) {
        this.zdistance = zdistance;
    }

    public MerchantDo getMerchant() {
        return merchant;
    }

    public void setMerchant(MerchantDo merchant) {
        this.merchant = merchant;
    }
}


