package com.campaign.util;

import java.util.Date;

/**
 * @author Vivek on 3/7/17.
 * @project CampaignPageDB
 * @package com.campaign.util
 */
public class CIdDetail {

    private long cid;

    private long mid;

    private String mname;

    private String sdesc;

    private String serverPath;

    private String image;

    private Date sdate;

    private Date edate;

    private String tNc;

    private String coupon;

    private String uid;

    public CIdDetail(long cid, long mid, String mname, String sdesc, String image, Date sdate, Date edate) {
        this.cid = cid;
        this.mid = mid;
        this.mname = mname;
        this.sdesc = sdesc;
        this.image = image;
        this.sdate = sdate;
        this.edate = edate;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getSdesc() {
        return sdesc;
    }

    public void setSdesc(String sdesc) {
        this.sdesc = sdesc;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Date getEdate() {
        return edate;
    }

    public void setEdate(Date edate) {
        this.edate = edate;
    }

    public String getCoupon() {
        return coupon;
    }

    public void setCoupon(String coupon) {
        this.coupon = coupon;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String gettNc() {
        return tNc;
    }

    public void settNc(String tNc) {
        this.tNc = tNc;
    }
}
