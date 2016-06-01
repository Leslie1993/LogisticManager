package com.example.dell.logisticmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dell.logisticmanager.Data.ArrayOrder;
import com.example.dell.logisticmanager.Data.OrderInfo;
import com.example.dell.logisticmanager.WebService.OrderManager;


public class OrderModifyActivity extends OrderActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getModifyData();
        Button submit=(Button) findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOrderSucceed=new OrderManager().modifiyOrder(getOrder());
                orderHandle.sendEmptyMessage(0);
            }
        });
    }

    private void getModifyData()
    {
        ArrayOrder info=new ArrayOrder();
        info=(ArrayOrder)this.getIntent().getSerializableExtra("Order");
        o_id.setText(info.o_id);
        cargoname.setText(info.cargoname);
        cargoweight.setText(info.cargoweight);
        //vehicleNum.setText(info.vehiclenum);
        //vehicleName.setText(info.vehiclename);
        startLocation.setText(info.startlocation);
        endLocation.setText(info.endlocation);
        transportWay.setText(info.transportway);
        consignor.setText(info.consignor);
        consignortel.setText(info.consignortel);
        consignee.setText(info.consignee);
        consigneetel.setText(info.consigneetel);




    }
}
