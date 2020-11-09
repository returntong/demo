package com.example.demo.nb.nbpro.valveunitdata;

import com.example.demo.nb.anno.NBDicKey;
import com.example.demo.nb.anno.NbSub;
import com.example.demo.nb.nbpro.BaseEntity;

@NBDicKey(dict = "dict",key = "A02D**",index = 1,prop = "dataFlg")
public class ValveA02C extends BaseEntity {

    @NbSub(length = 3)
    private String dataFlg = "01";

    @NbSub(preProperty = "dataFlg",length = 2,encodeType = "HEX")
    private Integer cleanCycle;
}
