package com.campaign.mvc.service;

import com.campaign.mvc.dao.MiscDAO;
import com.campaign.util.CIdDetail;
import com.campaign.util.StackTrace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Vivek on 3/7/17.
 * @project CampaignPageDB
 * @package com.campaign.mvc.service
 */

@Service
public class MiscServiceImpl implements MiscService{

    private static Logger logger = LoggerFactory.getLogger(MiscServiceImpl.class);

    @Autowired
    private MiscDAO miscDAO;

    @Override
    @Transactional(readOnly = true)
    public CIdDetail getDetail(long couponId) {
        CIdDetail cIdDetail = null;
        try{
            Object[] objects = miscDAO.getMerchantBranchDealsByCouponId(couponId);
            if(objects!=null){
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                cIdDetail = new CIdDetail(couponId,Long.valueOf(objects[0].toString()),objects[1].toString(),objects[2].toString(),objects[3]+"",df.parse(objects[4].toString()),df.parse(objects[5].toString()));
                String tNc = objects[6] !=null ? objects[6].toString() :null;
                cIdDetail.settNc(tNc.contains("<li>") ? tNc :("<li>"+tNc+"</li>"));
            }else{
                logger.info("detail not found "+couponId);
            }
        }catch (Exception e){
            logger.info("cid: "+couponId+" | "+ StackTrace.getRootCause(e,getClass().getName()));

        }
        return cIdDetail;
    }
}
