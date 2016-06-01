package com.example.dell.logisticmanager.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by dell on 2016/5/31.
 */
public class OrderUtils {

    private static String date ;
    private static long orderNum = 0l;

    /**
     * 生成订单编号
     * @return
     */
    public static synchronized String getOrderNo() {
        String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
        if(date==null||!date.equals(str)){
            date = str;
            orderNum  = 0l;
        }
        orderNum ++;
        long orderNo = Long.parseLong((date)) * 10000;
        orderNo += orderNum;;
        return orderNo+"";
    }


    public String getOrderNo_uuid()
    {
        String orderNo= UUID.randomUUID().toString().replace("-","");
        return orderNo;
    }
}
