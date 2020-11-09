package com.example.demo.nb.nbpro.valveunitdata;


import com.example.demo.nb.anno.NBDicKey;
import com.example.demo.nb.anno.NbBitSub;
import com.example.demo.nb.anno.NbData;
import com.example.demo.nb.anno.NbSub;
import com.example.demo.nb.nbpro.BaseEntity;


@NBDicKey(dict = "dict",key = "A017**",index = 1,prop = "dataFlg")
@NbData
public class ValveA017Ret extends BaseEntity {

    @NbSub(length = 3)
    private String dataFlg;

    @NbSub(preProperty = "dataFlg",length = 2)
    private String ST;

    @NbBitSub(prop = "ST",index = 0,len = 2)
    private Integer valveStat;


    @NbBitSub(prop = "ST",index = 2,len = 1)
    private Integer valveTimeout;


    @NbBitSub(prop = "ST",index = 3,len = 1)
    private Integer valveToggleStat;



    @NbBitSub(prop = "ST",index = 4,len = 1)
    private Integer inWaterStat;


    @NbBitSub(prop = "ST",index = 5,len = 1)
    private Integer retWaterStat;

    @NbBitSub(prop = "ST",index = 6,len = 1)
    private Integer volStat;

    @NbBitSub(prop = "ST",index = 13,len = 1)
    private Integer outVolBreak;

    @NbBitSub(prop = "ST",index = 15,len = 1)
    private Integer openLidAlarm;

    @NbBitSub(prop = "ST",index = 8,len = 2)
    private Integer valveCtl;
}
