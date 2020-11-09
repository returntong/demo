package com.example.demo.nb.nbpro;


import com.example.demo.nb.anno.NbData;
import com.example.demo.nb.anno.NbSub;

@NbData
public class NbDataSensor extends BaseEntity{

    @NbSub(length = 4)
    private Integer currMill;


    @NbSub(preProperty = "currMill",length = 2)
    private Integer temp;


    @NbSub(preProperty = "temp",length = 2)
    private Integer humi;


    @NbSub(preProperty = "humi",length = 2)
    private Integer tempMo;

    @NbSub(preProperty = "tempMo",length = 2)
    private Integer humiMo;

}
