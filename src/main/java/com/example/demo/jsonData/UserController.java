package com.example.demo.jsonData;

import com.example.demo.dto.ValveInfoDTO;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {


    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public void getUser(@PathVariable Long user, @RequestHeader("Content-Type") String contentType) {
        System.out.println(contentType);
        System.out.println(user);
    }

    @RequestMapping(value = "/{user}/customers", method = RequestMethod.GET)
    public void getUserCustomers(@PathVariable Long user) {
        System.out.println(user);
    }

    @RequestMapping(value = "/{user}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long user) {
        System.out.println(user);
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
       /* String data = "eyJ1c2VyX25hbWUiOiJhZG1pbiIsInNjb3BlIjpbImFsbCJdLCJvcmdhbml6YXRpb24iOlsicXVhbmd1byIsInBpbmdsaWFuZyJdLCJyb2xlcyI6WyJBRE1JTiJdLCJleHAiOjE2MDA2Nzk3OTEsImp0aSI6ImNiMjBlMzUxLTBiN2MtNDRmMC04NTdlLTkyNWQxNTc3ZTVjZCIsImNsaWVudF9pZCI6ImFkbWluIn0";

        byte[] bytes = Base64.getDecoder().decode(data);
        String string = new String(bytes, "UTF-8");
        System.out.println(string);

        String encoded = Base64.getEncoder().encodeToString(string.getBytes("UTF-8"));
        System.out.println(encoded);*/
        String s = "68680068C047095900070AFF01180E383938363131313932363430303631393737343229051615092017600900101515092001003600684607005909FFFFFF812990200100001C000025999945283974000035740000130000000000000005100000101515092020001060162D16";
        List<String> arrayList = getListStr(s, 2);
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

        ValveInfoDTO valveInfoDTO = ValveInfoDTO.builder().waterInletTemperature(waterInletTemperature)
                .returnWaterTemperature(returnWaterTemperature).totalQuantityHeat(totalQuantityHeat).valveOpening(valveOpening).valveStatus(valveStatus).build();
        System.out.println(valveInfoDTO.toString());

    }

    /**
     * 把String按一定长度拆分，返回ListString
     *
     * @param str
     * @param len
     * @return
     */

    public static List<String> getListStr(String str, int len) {
        List<String> listStr = new ArrayList<>();
        int strLen = str.length();
        int start = 0;
        int num = len;
        String temp = null;
        while (true) {
            try {
                if (num >= strLen) {
                    temp = str.substring(start, strLen);
                } else {
                    temp = str.substring(start, num);
                }
            } catch (Exception e) {
                break;
            }
            listStr.add(temp);
            start = num;
            num = num + len;
        }
        return listStr;
    }

    //判断该字符串是否为 00
    public static String judgeString(StringBuffer buffer, String string) {
        Integer integer = Integer.valueOf(string);
        if (buffer.length() > 0) {
            buffer.append(string);
        } else if (integer > 0) {
            buffer.append(string);
        }
        return buffer.toString();
    }
}
