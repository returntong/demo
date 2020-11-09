package com.example.demo.nb.nbpro.valveunitdata;


import com.example.demo.nb.anno.NBDicKey;
import com.example.demo.nb.anno.NbSub;
import com.example.demo.nb.nbpro.BaseEntity;

@NBDicKey(dict = "dict",key = "AB09**",index = 1,prop = "dataFlg")
public class ValveAC09 extends BaseEntity {

    @NbSub(length = 3)
    private String dataFlg;

    @NbSub(preProperty = "dataFlg",length = 1)
    private String isScreenCycle;

    @NbSub(preProperty = "isScreenCycle",length = 1)
    private String waterTSensor;
}
