package com.example.dell.logisticmanager;

import android.os.Bundle;
import com.example.dell.logisticmanager.Data.OrderInfo;


public class OrderModifyActivity extends OrderActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testData();
    }

    private void testData()
    {
        OrderInfo info=new OrderInfo();
        info=(OrderInfo)this.getIntent().getSerializableExtra("Order");
        //this.endLocation.setText(info.id);
        this.startLocation.setText(info.explain);
        this.vehicleNum.setText(info.explain);

    }
}
