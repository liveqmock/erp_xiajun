package com.wangqin.globalshop.order.app.service.util_service.impl;

import com.wangqin.globalshop.biz1.app.dal.mapperExt.SequenceUtilMapperExt;
import com.wangqin.globalshop.order.app.service.util_service.OrderISequenceUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderSequenceUtilServiceImpl implements OrderISequenceUtilService {

    @Autowired
    private SequenceUtilMapperExt seqMappper;

    @Override
    public Long gainSkuSequence() {
        return seqMappper.gainSkuSequence();
    }

    @Override
    public Long gainItemSequence() {
        return seqMappper.gainItemSequence();
    }

    @Override
    public Long gainPOSequence() {
        return seqMappper.gainPOSequence();
    }

    @Override
    public Long gainERPOSequence() {
        return seqMappper.gainERPOSequence();
    }

    @Override
    public Long gainRECOSequence() {
        return seqMappper.gainRECOSequence();
    }

    @Override
    public Long gainORDSequence() {
        return seqMappper.gainORDSequence();
    }

    @Override
    public Long gainPKGSequence() {
        return seqMappper.gainPKGSequence();
    }

    @Override
    public Long gainTASKSequence() {
        return seqMappper.gainTASKSequence();
    }

    @Override
    public Long gainINVOUTSequence() {
        return seqMappper.gainINVOUTSequence();
    }

}