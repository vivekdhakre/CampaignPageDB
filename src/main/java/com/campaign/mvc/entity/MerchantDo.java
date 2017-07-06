package com.campaign.mvc.entity;



import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="merchant")
public class MerchantDo implements java.io.Serializable {


    @Id
    @GeneratedValue
    @Column(name="merchant_pkey")
    private long merchantPkey;

    @Column(name="merchant_name")
    private String merchantName;

    @Column(name="merchant_display_name")
    private String merchantDisplayName;

    @Column(name="merchant_description")
    private String merchantDescription;

    @Column(name="p_mobile_no")
    private String PMobileNo;

    @Column(name="o_mobile_no")
    private String OMobileNo;

    @Column(name="p_phone_no")
    private String PPhoneNo;

    @Column(name="o_phone_no")
    private String OPhoneNo;

    @Column(name="merchant_logo")
    private String merchantLogo;

    @Column(name="website_url")
    private String websiteUrl;

    @Column(name="wap_url")
    private String wapUrl;

    @Column(name="short_code")
    private String shortCode;

    @Column(name="latitude")
    private Double latitude;

    @Column(name="longitude")
    private Double longitude;

    @Column(name="address_line1")
    private String addressLine1;

    @Column(name="address_iine2")
    private String addressIine2;

    @Column(name="pincode")
    private String pincode;

    @Column(name="tollfreeno")
    private String tollfreeno;

    @Column(name="created_by")
    private String createdBy;

    @Column(name="created_on")
    private Date createdOn;

    @Column(name="updated_by")
    private String updatedBy;

    @Column(name="updated_on")
    private Date updatedOn;


    @Column(name = "system_auth_fkey")
    private long systemAuthFkey;



    public long getMerchantPkey() {
        return merchantPkey;
    }

    public void setMerchantPkey(long merchantPkey) {
        this.merchantPkey = merchantPkey;
    }



    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getMerchantDisplayName() {
        return merchantDisplayName;
    }

    public void setMerchantDisplayName(String merchantDisplayName) {
        this.merchantDisplayName = merchantDisplayName;
    }

    public String getMerchantDescription() {
        return merchantDescription;
    }

    public void setMerchantDescription(String merchantDescription) {
        this.merchantDescription = merchantDescription;
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

    public String getMerchantLogo() {
        return merchantLogo;
    }

    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getWapUrl() {
        return wapUrl;
    }

    public void setWapUrl(String wapUrl) {
        this.wapUrl = wapUrl;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
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

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressIine2() {
        return addressIine2;
    }

    public void setAddressIine2(String addressIine2) {
        this.addressIine2 = addressIine2;
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

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getTollfreeno() {
        return tollfreeno;
    }

    public void setTollfreeno(String tollfreeno) {
        this.tollfreeno = tollfreeno;
    }

    public long getSystemAuthFkey() {
        return systemAuthFkey;
    }

    public void setSystemAuthFkey(long systemAuthFkey) {
        this.systemAuthFkey = systemAuthFkey;
    }


}


