package com.example.demo.nb.nbpro.hmeterunitdata;


import com.example.demo.nb.anno.NbSub;
import com.example.demo.nb.nbpro.BaseEntity;

public class HMeterEmpty extends BaseEntity {

    @NbSub(length = 1)
    private String dataFlagD10;

    @NbSub(preProperty = "dataFlagD10",length = 1)
    private String dataFlagD11;

    @NbSub(preProperty = "dataFlagD11",length = 1)
    private String sNo = "00";
}
