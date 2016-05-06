package com.example.dell.logisticmanager.Data;

import android.text.Html;

import com.example.dell.logisticmanager.R;

import java.util.ArrayList;
import java.util.List;

public class SampleData {
    public static final int SAMPLE_DATA_ITEM_COUNT =20;

    public static ArrayList<String> generateSampleData() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

    /*    for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            //String s="设置";
            //第一种方式
            //Html.fromHtml("<font color='#145A14'>s</font>");
           //String j=getResources().getString(R.string.exchange_txt_hint);
            data.add("");
        }*/
        String s="下达订单";
        data.add(s);
        s="修改订单";
        data.add(s);
        s="装车单据";
        data.add(s);
        s="修改装车单据";
        data.add(s);
        s="车辆指派";
        data.add(s);
        s="交付凭证";
        data.add(s);


        for(int i=6;i<SAMPLE_DATA_ITEM_COUNT;i++)
        {
            data.add("");

        }




        return data;
    }

}
