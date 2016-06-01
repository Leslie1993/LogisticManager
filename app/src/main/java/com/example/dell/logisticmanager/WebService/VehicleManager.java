package com.example.dell.logisticmanager.WebService;


import com.example.dell.logisticmanager.Data.ArrayVehicle;
import com.example.dell.logisticmanager.Data.Order;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import static com.example.dell.logisticmanager.WebService.OrderManager.webOperate;


/**
 * Created by dell on 2016/5/5.
 */
public class VehicleManager {

    public List<ArrayVehicle> queryVehicle_byStatus(String status)
    {
        ArrayList<ArrayVehicle> lst = new ArrayList<ArrayVehicle>();
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "queryvehicleby_vehiclestatus_android";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/queryvehicleby_vehiclestatus_android";

        // 指定WebService的命名空间和调用的方法名
        SoapObject rpc = new SoapObject(nameSpace, methodName);
        rpc.addProperty("status",status);

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

                    ArrayVehicle vehicle=new ArrayVehicle();

                    //获取单条的数据
                    SoapObject soapChilds =(SoapObject)object.getProperty(i);

                    //对单个的数据进行再次遍历，把它的每行数据读取出来
                    for(int k=0;k<soapChilds.getPropertyCount();k++)
                    {
                        PropertyInfo propertyInfo=new PropertyInfo();
                        soapChilds.getPropertyInfo(k, propertyInfo);
                        //获取实体类的所有属性
                        Field[] field = vehicle.getClass().getDeclaredFields();
                        //遍历所有属性
                        for(int j=0 ; j<field.length ; j++){
                            //获取属性的名字
                            String name = field[j].getName();

                            //把每个属性的名字跟Web Service返回的k行进行对比
                            String ppinfoname=propertyInfo.getName().toLowerCase();
                            if(ppinfoname.equals(name.toLowerCase()))
                            {
                                //给实体类赋值，具体意思也不明白，资料就这样。
                                GetSetManager.setFieldValue(vehicle,name,field[j].getType(),
                                        soapChilds.getProperty(propertyInfo.getName()).toString());
                            }
                        }
                    }

                    lst.add(vehicle);
                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return lst;


    }

    public Boolean vehicleAllocate(Order order)
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
        rpc.addProperty("id",order.o_id);
        rpc.addProperty("num",order.vehicleNum);
        // 获取返回的结果

        SoapObject object=webOperate(rpc,endPoint,soapAction);
        Boolean result =Boolean.parseBoolean(object.getProperty("vehicleAllocate_androidResponse").toString());
        return result;

    }





}
