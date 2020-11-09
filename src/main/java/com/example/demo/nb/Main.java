package com.example.demo.nb;

import com.example.demo.nb.nbpro.NbOutProLink;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

public class Main {
    /**
     * 测试
     *
     * @param args
     * @throws NoSuchFieldException
     * @throws InstantiationException
     * @throws ParseException
     * @throws IllegalAccessException
     * @throws UnsupportedEncodingException
     */
    public static void main(String[] args) throws NoSuchFieldException, InstantiationException, ParseException, IllegalAccessException, UnsupportedEncodingException {
        String base64Str = "aGgAaMBHCVkABwr/ARgOODk4NjExMTkyNjQwMDYxOTc3NDIpBRYVCSAXYAkAEBUVCSABADYAaEYHAFkJ////gSmQIAEAABwAACWZmUUoOXQAADV0AAATAAAAAAAAAAUQAAAQFRUJICAAEGAWLRY=";

        System.out.println(NbDecodeUtil.decodeToHex(base64Str));

        NbOutProLink decode = NbDecodeUtil.decode(base64Str, NbOutProLink.class);

        System.out.println(decode);

        String encode = NbDecodeUtil.encode(decode);

        System.out.println(base64Str.equals(encode));
    }


}
