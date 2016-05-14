package com.example.dell.logisticmanager.WebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by dell on 2016/5/14.
 */
public class ListDemo {


    public class Arraylogin {
        int id;
        String pass;
        String type;
        String registertime;

    }

    public ArrayList<Arraylogin> queryUsers() {

        ArrayList<Arraylogin> lst = new ArrayList<Arraylogin>();
        // 命名空间
        final String nameSpace = "http://tempuri.org/";
        // 调用的方法名称
        final String methodName = "queryUsers_all_android";
        // EndPoint
        final String endPoint = "http://124.161.78.174/ZKDJservice.svc";
        // SOAP Action
        final String soapAction = "http://tempuri.org/IService1/queryUsers_all_android";

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
        boolean result;
        try {
            // 调用WebService
            transport.call(soapAction, envelope);
            // 获取返回的数据


        } catch (Exception e) {
            e.printStackTrace();

        }
        try
        {

            SoapObject object = (SoapObject) envelope.getResponse();

            //如果获取的是个集合，就对它进行下面的操作
            if(object.getName()=="ArrayOfLogin")
            {
                //遍历Web Service获得的集合
                for(int i=0;i<object.getPropertyCount();i++){

                    Arraylogin login=new Arraylogin();

                    //获取单条的数据
                    SoapObject soapChilds =(SoapObject)object.getProperty(i);

                    //对单个的数据进行再次遍历，把它的每行数据读取出来
                    for(int k=0;k<soapChilds.getPropertyCount();k++)
                    {
                        PropertyInfo propertyInfo=new PropertyInfo();
                        soapChilds.getPropertyInfo(k, propertyInfo);
                        //获取实体类的所有属性
                        Field[] field = login.getClass().getDeclaredFields();
                        //遍历所有属性
                        for(int j=0 ; j<field.length ; j++){
                            //获取属性的名字
                            String name = field[j].getName();

                            //把每个属性的名字跟Web Service返回的k行进行对比
                            String ppinfoname=propertyInfo.getName().toLowerCase();
                            if(ppinfoname.equals(name.toLowerCase()))
                            {
                                //调用个我也看不懂的方法,给实体类赋值，具体意思也不明白，资料就这样。
                                GetSetManager.setFieldValue(login,name,field[j].getType(),
                                        soapChilds.getProperty(propertyInfo.getName()).toString());
                            }
                        }
                    }

                    lst.add(login);
                }
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }




        return lst;


    }
}
