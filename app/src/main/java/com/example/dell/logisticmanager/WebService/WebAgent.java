package com.example.dell.logisticmanager.WebService;

import com.example.dell.logisticmanager.Data.Order;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

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


    public boolean addOrder(Order order)
    {
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "addOrder";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/addOrder";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入参数
        rpc.addProperty("o_id",order.o_id);
        rpc.addProperty("cargoname",order.cargoName);
        rpc.addProperty("cargoweight",order.cargoWeight);
        rpc.addProperty("vehiclenum",order.vehicleNum);
        rpc.addProperty("vehiclename",order.vehicleName);
        rpc.addProperty("startlocation",order.startLocation);
        rpc.addProperty("endlocation",order.endLocation);
        rpc.addProperty("transportway",order.transportWay);
        rpc.addProperty("consignor",order.consignor);
        rpc.addProperty("consignortel",order.consignortel);
        rpc.addProperty("consignee",order.consignee);
        rpc.addProperty("consigneetel",order.consigneetel);
        //rpc.addProperty("pic",order.pic);
        rpc.addProperty("orderstatus",order.orderStatus);
        //rpc.addProperty("sendtime",order.sendTime);
        //rpc.addProperty("ordertime",order.orderTime);








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

        Boolean result =Boolean.parseBoolean(object.getProperty("addOrderResult").toString())  ;

        return result;

    }


    public boolean delOrder(int id)
    {
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "deleteOrder";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/deleteOrder";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入参数
        rpc.addProperty("id",id);



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
            result =Boolean.parseBoolean(object.getProperty("deleteOrderResult").toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }



        return result;

    }


    public boolean modifiyOrder(Order order)
    {
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "modifyOrder";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/modifyOrder";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入参数
        rpc.addProperty("o_id",order.o_id);
        rpc.addProperty("cargoname",order.cargoName);
        rpc.addProperty("cargoweight",order.cargoWeight);
        rpc.addProperty("vehiclenum",order.vehicleNum);
        rpc.addProperty("vehiclename",order.vehicleName);
        rpc.addProperty("startlocation",order.startLocation);
        rpc.addProperty("endlocation",order.endLocation);
        rpc.addProperty("transportway",order.transportWay);
        rpc.addProperty("consignor",order.consignor);
        rpc.addProperty("consignortel",order.consignortel);
        rpc.addProperty("consignee",order.consignee);
        rpc.addProperty("consigneetel",order.consigneetel);
        //rpc.addProperty("pic",order.pic);
        rpc.addProperty("orderstatus",order.orderStatus);
        //rpc.addProperty("sendtime",order.sendTime);
        //rpc.addProperty("ordertime",order.orderTime);


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

        Boolean result =Boolean.parseBoolean(object.getProperty("modifyOrderResponse").toString())  ;

        return result;

    }

    public void queryOrder()
    {


    }


}


