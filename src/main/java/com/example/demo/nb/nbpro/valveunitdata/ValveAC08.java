package com.example.demo.nb.nbpro.valveunitdata;


import com.example.demo.nb.anno.NBDicKey;
import com.example.demo.nb.anno.NbSub;
import com.example.demo.nb.nbpro.BaseEntity;

@NBDicKey(dict = "dict",key = "AB08**",index = 1,prop = "dataFlg")
public class ValveAC08 extends BaseEntity {

    @NbSub(length = 3)
    private String dataFlg;

    @NbSub(preProperty = "dataFlg",length = 42,encodeType = "ASCII")
    private String reserveCode;
}
