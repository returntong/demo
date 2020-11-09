package com.example.demo.jsonData;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dto.ValveInfoDTO;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class DataParser {


    public static void main(String[] args) {
        String json = "{\n" +
                "  \"notifyType\": \"deviceDataChanged\",\n" +
                "  \"deviceId\": \"c20b7a1e-ef9b-4876-a1da-28419c8b54fd\",\n" +
                "  \"gatewayI\": \"c20b7a1e-ef9b-4876-a1da-28419c8b54fd\",\n" +
                "  \"requestId\": \"None\",\n" +
                "  \"service\": {\n" +
                "    \"serviceId\": \"shujushangchuan\",\n" +
                "    \"serviceType\": \"shujushangchuan\",\n" +
                "    \"data\": {\n" +

                "      \"data\": \"aGgAaMBaAJAAAQr/AQAPODk4NjExMjAyMDcwMTQ1MTQ2NTIYGQkTECAJYAkSUBESECABADYAaEYBAJAAAVkJgSmQIAEAABoAACWZmTAihQQAAHcEAAAFAAAAAAAAAAVQABJQERIQICABIBQW5RY=\"\n" +
                "    },\n" +

                "    \"eventTime\": \"20200915T080532Z\"\n" +
                "  }\n" +
                "}";

        DataParser dataParser = new DataParser();
        boolean isTrue = dataParser.isValid(json);
        System.out.println(isTrue);

        ValveInfoDTO valveInfoDTO = dataParser.getDataAnalysis(json);
        System.out.println(valveInfoDTO.toString());


    }

    public boolean isValid(String jsonString){
        try {
            JSONObject.parseObject(jsonString);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public ValveInfoDTO getDataAnalysis(String jsonData) {
        Gson gson = new Gson();
        HashMap<String, Object> hashMap = gson.fromJson(jsonData, HashMap.class);
        String notifyType = (String) hashMap.get("notifyType");
        String deviceId = (String) hashMap.get("deviceId");

        //获取云平台服务内容
        Object service = hashMap.get("service");
        String jsonservice = gson.toJson(service);
        HashMap<String, Object> serviceMap = gson.fromJson(jsonservice, HashMap.class);

        String serviceId = (String) serviceMap.get("serviceId");
        String serviceType = (String) serviceMap.get("serviceType");

        //获取data
        Object data = serviceMap.get("data");
        String jsonString = gson.toJson(data);
        HashMap<String, Object> dataMap = gson.fromJson(jsonString, HashMap.class);
        //获取到待解析的数据
        String dataJ = dataMap.get("data").toString();
        ArrayList<String> arrayList = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        //base64 解析
        byte[] decoded = Base64.getDecoder().decode(dataJ);
        for (byte b : decoded) {
            String s = String.format("%x", b);
            arrayList.add(s);
            stringBuffer.append(s);
        }
        System.out.println(stringBuffer.toString());
        //当前进水温度
        String waterInletTemperature = arrayList.get(75) + "." + arrayList.get(74);
        //回水温度
        String returnWaterTemperature = arrayList.get(77) + "." + arrayList.get(76);

        //累计热量
        StringBuffer heatbuffer = new StringBuffer();
        judgeString(heatbuffer, arrayList.get(93));
        judgeString(heatbuffer, arrayList.get(92));
        judgeString(heatbuffer, arrayList.get(91));
        judgeString(heatbuffer, arrayList.get(90));
        String totalQuantityHeat = heatbuffer.toString();

        //阀门开度
        StringBuffer valvebuffer = new StringBuffer();
        judgeString(valvebuffer, arrayList.get(96));
        judgeString(valvebuffer, arrayList.get(95));
        String valveOpening = valvebuffer.toString();

        //阀门状态 0 关 1 开
        String valveStatus = arrayList.get(104);

        ValveInfoDTO valveInfoDTO = ValveInfoDTO.builder().notifyType(notifyType).deviceId(deviceId).serviceId(serviceId).serviceType(serviceType).waterInletTemperature(waterInletTemperature)
                .returnWaterTemperature(returnWaterTemperature).totalQuantityHeat(totalQuantityHeat).valveOpening(valveOpening).valveStatus(valveStatus).build();

        return valveInfoDTO;

    }

    //判断该字符串是否为 00
    public String judgeString(StringBuffer buffer, String string) {
        Integer integer = Integer.valueOf(string);
        if (buffer.length() > 0) {
            buffer.append(string);
        } else if (integer > 0) {
            buffer.append(string);
        }
        return buffer.toString();
    }

}
