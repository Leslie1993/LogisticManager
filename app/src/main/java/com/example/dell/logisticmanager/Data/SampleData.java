package com.example.dell.logisticmanager.Data;

import android.text.Html;

import com.example.dell.logisticmanager.R;

import java.util.ArrayList;
import java.util.List;

public class SampleData {

    public static final int SAMPLE_DATA_ITEM_COUNT =20;

    public static ArrayList<String> generateSampleData() {
        final ArrayList<String> data = new ArrayList<String>(SAMPLE_DATA_ITEM_COUNT);

        for (int i = 0; i < SAMPLE_DATA_ITEM_COUNT; i++) {
            //String s="设置";
            //第一种方式
            //Html.fromHtml("<font color='#145A14'>s</font>");
           //String j=getResources().getString(R.string.exchange_txt_hint);
            data.add("");
        }

        return data;
    }

}
