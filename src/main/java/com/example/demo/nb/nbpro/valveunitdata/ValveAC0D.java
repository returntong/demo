package com.example.demo.nb.nbpro.valveunitdata;


import com.example.demo.nb.anno.NBDicKey;
import com.example.demo.nb.anno.NbSub;
import com.example.demo.nb.nbpro.BaseEntity;

@NBDicKey(dict = "dict",key = "AB0D**",index = 1,prop = "dataFlg")
public class ValveAC0D extends BaseEntity {

    @NbSub(length = 3)
    private String dataFlg;

    @NbSub(preProperty = "dataFlg",length = 4)
    private Integer workTime;
}
