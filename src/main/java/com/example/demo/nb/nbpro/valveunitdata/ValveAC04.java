package com.example.demo.nb.nbpro.valveunitdata;


import com.example.demo.nb.anno.NbSub;
import com.example.demo.nb.nbpro.BaseEntity;

public class ValveAC04 extends BaseEntity {

    @NbSub(length = 3)
    private String dataFlg;

    @NbSub(preProperty = "dataFlg",length = 1)
    private String modMode;

    @NbSub(preProperty = "modMode",length = 2,encodeType = "HEX")
    private Integer modValue;

}
