package com.example.dell.logisticmanager.WebService;

import android.os.Message;

import com.example.dell.logisticmanager.Data.MathUtils;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.math.BigDecimal;

/**
 * Created by dell on 2016/5/4.
 */
public class WebAgent {





    public String getData(int param)
    {

        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "GetData";
        // EndPoint
        final String endPoint = "http://172.16.30.112/Service1.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/GetData";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("value", param);
        //rpc.addProperty("x1",y);

        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);


        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);

        HttpTransportSE transport = new HttpTransportSE(endPoint);
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取返回的数据
        SoapObject object = (SoapObject) envelope.bodyIn;
        // 获取返回的结果
       String result = object.getProperty("GetDataResult").toString() ;

        return result;


    }




    public boolean register(String id,String passWord)
    {


        return true;

    }

    public String login(String id,String passWord)
    {

        String md5Pass="";
        return md5Pass;

    }

    public boolean register(int id,String passWord)
    {


        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "userRegister";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/userRegister";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("id", id);
        rpc.addProperty("pwd",passWord);
        //rpc.addProperty("x1",y);

        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);


        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);

        HttpTransportSE transport = new HttpTransportSE(endPoint);
        boolean result;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            // 获取返回的数据
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 获取返回的结果
            result =Boolean.parseBoolean(object.getProperty("userRegisterResult").toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }



        return result;

    }


    public boolean login(int id,String passWord)
    {
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "checkUser";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/checkUser";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("id", id);
        rpc.addProperty("pwd",passWord);


        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);

        envelope.bodyOut = rpc;
        // 设置是否调用的是dotNet开发的WebService
        envelope.dotNet = true;
        // 等价于envelope.bodyOut = rpc;
        envelope.setOutputSoapObject(rpc);

        HttpTransportSE transport = new HttpTransportSE(endPoint);
        boolean result;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            // 获取返回的数据
            SoapObject object = (SoapObject) envelope.bodyIn;
            // 获取返回的结果
            result =Boolean.parseBoolean(object.getProperty("checkUserResult").toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return result;


    }



}


