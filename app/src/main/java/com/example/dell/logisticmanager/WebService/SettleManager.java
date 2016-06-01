package com.example.dell.logisticmanager.WebService;

import org.ksoap2.serialization.SoapObject;

import static com.example.dell.logisticmanager.WebService.OrderManager.webOperate;

/**
 * Created by dell on 2016/6/1.
 */
public class SettleManager {
    public Boolean settleOrder(String id)
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
        rpc.addProperty("id",id);
        //rpc.addProperty("num",order.vehicleNum);
        // 获取返回的结果

        SoapObject object=webOperate(rpc,endPoint,soapAction);
        Boolean result =Boolean.parseBoolean(object.getProperty("settle_androidResponse").toString());
        return result;

    }

}
