package com.example.dell.logisticmanager.Data;

import com.example.dell.logisticmanager.WebService.WebAgent;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dell on 2016/5/5.
 * 数据验证类
 */
public class Certification {


    public static String md5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }


    public boolean isCertificated(String id,String passWord)
    {
        String passBuffer = md5(passWord);
        WebAgent agent = new WebAgent();

        String backPass = agent.login(id, passBuffer);

        if (passBuffer.equals(backPass))


            return true;
        else
            return false;
    }


    public boolean isRegister(String id,String passWord)
    {


        String passBuffer=md5(passWord);

        WebAgent agent=new WebAgent();

        if (agent.register(id, passBuffer))
            return true;
        else
            return false;
    }





}
