package com.example.demo.nb.nbpro.valveunitdata;


import com.example.demo.nb.anno.NBDicKey;
import com.example.demo.nb.anno.NbSub;
import com.example.demo.nb.nbpro.BaseEntity;

@NBDicKey(dict = "dict",key = {"AC****","BBFF**","DDAA**"},index = 1,prop = "dataFlg")
public class ValveEmpty extends BaseEntity {

    @NbSub(length = 3)
    private String dataFlg;

}
