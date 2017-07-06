package com.campaign.mvc.dao;

import com.campaign.mvc.entity.MerchantBranchDealsDo;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Vivek on 3/7/17.
 * @project CampaignPageDB
 * @package com.campaign.mvc.dao.dao
 */

@Repository
public class MiscDAOImpl implements MiscDAO{

    @Autowired
    private SessionFactory factory;

    @Override
    public Object[] getMerchantBranchDealsByCouponId(long couponId) {
        Object[] objects = null;

        ProjectionList projectionList = Projections.projectionList();
        projectionList
                .add(Projections.property("m.merchantPkey"))
                .add(Projections.property("m.merchantName"))
                .add(Projections.property("shortDescription"))
                .add(Projections.property("dealImage"))
                .add(Projections.property("startDatetime"))
                .add(Projections.property("endDatetime"))
                .add(Projections.property("campaignTnC"));



        Criteria criteria = factory.getCurrentSession().createCriteria(MerchantBranchDealsDo.class)
                .add(Restrictions.eq("couponId",couponId))
                .createAlias("merchantBranchDetailsBranchDealsMaps","mbdbddm")
                .createAlias("mbdbddm.merchantBranchDetail","mbd")
                .createAlias("mbd.merchant","m")
                .setProjection(projectionList);

        if(!criteria.list().isEmpty() && criteria.list().size()>0){
            objects = (Object[])criteria.list().get(0);
        }

        return objects;
    }
}
