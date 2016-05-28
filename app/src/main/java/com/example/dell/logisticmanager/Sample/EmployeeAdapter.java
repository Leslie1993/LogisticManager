package com.example.dell.logisticmanager.Sample;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2016/5/27.
 */
public class EmployeeAdapter {


    //"总经理","市场部","综合办","技术安全部","财务部","运营部"
    public Map InitEmployee()
    {
        Map<String,List<Employee>> map=new HashMap<String,List<Employee>>();
        List<Employee> e0=new ArrayList<Employee>();
        for(int i=0;i<2;i++)
        {
            Employee e=new Employee();
            e.setName("xieyu");
            e.setPhoneNum(1233445454);
            e.setPosition("经理");
            e0.add(e);
        }
        map.put("总经理",e0);

        List<Employee> e1=new ArrayList<Employee>();
            for(int i=0;i<10;i++)
            {
                Employee e=new Employee();
                e.setName("xieyu");
                e.setPhoneNum(1233445454);
                e.setPosition("员工");
                e1.add(e);
            }
        map.put("市场部",e1);


        List<Employee> e2=new ArrayList<Employee>();
        for(int i=0;i<4;i++)
        {
            Employee e=new Employee();
            e.setName("xieyu");
            e.setPhoneNum(1233445454);
            e.setPosition("员工");
            e2.add(e);
        }
        map.put("综合办",e1);


        List<Employee> e3=new ArrayList<Employee>();
        for(int i=0;i<4;i++)
        {
            Employee e=new Employee();
            e.setName("xieyu");
            e.setPhoneNum(1233445454);
            e.setPosition("员工");
            e3.add(e);
        }
        map.put("技术安全部",e3);


        List<Employee> e4=new ArrayList<Employee>();
        for(int i=0;i<4;i++)
        {
            Employee e=new Employee();
            e.setName("xieyu");
            e.setPhoneNum(1235455656);
            e.setPosition("员工");
            e4.add(e);
        }
        map.put("财务部",e4);

        List<Employee> e5=new ArrayList<Employee>();
        for(int i=0;i<4;i++)
        {
            Employee e=new Employee();
            e.setName("xieyu");
            e.setPhoneNum(1233445454);
            e.setPosition("员工");
            e5.add(e);
        }
        map.put("运营部",e5);

        return map;
    }

}
