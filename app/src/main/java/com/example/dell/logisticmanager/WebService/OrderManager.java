package com.example.dell.logisticmanager.WebService;

import com.example.dell.logisticmanager.Data.ArrayLogin;
import com.example.dell.logisticmanager.Data.ArrayOrder;
import com.example.dell.logisticmanager.Data.Order;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by dell on 2016/5/5.
 */
public class OrderManager {




    public static SoapObject webOperate(SoapObject rpc,String endPoint,String soapAction)
    {

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

        return object;
    }

    public boolean addOrder(Order order)
    {
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "addOrder_android";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/addOrder_android";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入参数
        rpc.addProperty("o_id",order.o_id);
        rpc.addProperty("cargoname",order.cargoName);
        rpc.addProperty("cargoweight",order.cargoWeight);
        //rpc.addProperty("vehiclenum",order.vehicleNum);
        //rpc.addProperty("vehiclename",order.vehicleName);
        rpc.addProperty("startlocation",order.startLocation);
        rpc.addProperty("endlocation",order.endLocation);
        rpc.addProperty("transportway",order.transportWay);
        rpc.addProperty("consignor",order.consignor);
        rpc.addProperty("consignortel",order.consignortel);
        rpc.addProperty("consignee",order.consignee);
        rpc.addProperty("consigneetel",order.consigneetel);
        rpc.addProperty("orderstatus",order.orderStatus);


        /*// 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10);*/


        /*envelope.bodyOut = rpc;
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
        SoapObject object = (SoapObject) envelope.bodyIn;*/
        // 获取返回的结果
        SoapObject object=webOperate(rpc,endPoint,soapAction);

        return Boolean.parseBoolean(object.getProperty("addOrder_androidResult").toString());

    }

    /*
    *删除订单
    */
    public boolean delOrder(String id)
    {
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "deleteOrder_android";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/deleteOrder_android";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        // 设置需调用WebService接口需要传入参数
        rpc.addProperty("id",id);



       /* // 生成调用WebService方法的SOAP请求信息,并指定SOAP的版本
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
        }*/

        //获取结果
        SoapObject object=webOperate(rpc,endPoint,soapAction);
        Boolean result=Boolean.parseBoolean(object.getProperty("deleteOrder_androidResult").toString());
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



        // 获取返回的结果

        SoapObject object=webOperate(rpc,endPoint,soapAction);
        Boolean result =Boolean.parseBoolean(object.getProperty("modifyOrderResponse").toString());
        return result;

    }

    public ArrayList<ArrayOrder> queryOrder_all()
    {
        ArrayList<ArrayOrder> lst = new ArrayList<ArrayOrder>();
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "queryOrder_all_android";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/queryOrder_all_android";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

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
        try
        {
            // 获取返回的数据
            SoapObject object = (SoapObject) envelope.getResponse();

            //如果获取的是个集合，就对它进行下面的操作
            if(object.getName()=="anyType")
            {
                //遍历Web Service获得的集合
                for(int i=0;i<object.getPropertyCount();i++){

                    ArrayOrder order=new ArrayOrder();

                    //获取单条的数据
                    SoapObject soapChilds =(SoapObject)object.getProperty(i);

                    //对单个的数据进行再次遍历，把它的每行数据读取出来
                    for(int k=0;k<soapChilds.getPropertyCount();k++)
                    {
                        PropertyInfo propertyInfo=new PropertyInfo();
                        soapChilds.getPropertyInfo(k, propertyInfo);
                        //获取实体类的所有属性
                        Field[] field = order.getClass().getDeclaredFields();
                        //遍历所有属性
                        for(int j=0 ; j<field.length ; j++){
                            //获取属性的名字
                            String name = field[j].getName();

                            //把每个属性的名字跟Web Service返回的k行进行对比
                            String ppinfoname=propertyInfo.getName().toLowerCase();
                            if(ppinfoname.equals(name.toLowerCase()))
                            {
                                //给实体类赋值，具体意思也不明白，资料就这样。
                                GetSetManager.setFieldValue(order,name,field[j].getType(),
                                        soapChilds.getProperty(propertyInfo.getName()).toString());
                            }
                        }
                    }

                    lst.add(order);
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lst;

    }

    /*
    * 审核订单
    * */
    public Boolean checkOrder(Order order)
    {
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "checkOrder_android";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/checkOrder_android";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);

        rpc.addProperty("id",order.o_id);
        SoapObject object=webOperate(rpc,endPoint,soapAction);

        return Boolean.valueOf(object.getProperty("checkOrder_android").toString());


    }

}
