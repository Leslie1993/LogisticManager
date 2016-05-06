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


    /**
     * 手机号段归属地查询
     *
     * @param phoneSec 手机号段
     */
    public void getRemoteInfo(String phoneSec) {


        // 命名空间
        final String nameSpace = "http://WebXml.com.cn/";
        // 调用的方法名称
        final String methodName = "getMobileCodeInfo";
        // EndPoint
        final String endPoint = "http://webservice.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        // SOAP Action
        final String soapAction = "http://WebXml.com.cn/getMobileCodeInfo";


        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("mobileCode", phoneSec);
        rpc.addProperty("userId", "");

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
        String result = object.getProperty(0).toString();

        // 将WebService返回的结果显示在TextView中
        //resultView.setText(result);


    }


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


    public BigDecimal sum(BigDecimal x,BigDecimal y)
    {


        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "Sum";
        // EndPoint
        final String endPoint = "http://172.16.30.112/Service1.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/Sum";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入的两个参数mobileCode、userId
        rpc.addProperty("x1", x);
        rpc.addProperty("x1",y);

        // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);


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
        BigDecimal result = MathUtils.getBigDecimal(object.getProperty("SumResult")) ;

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



}


