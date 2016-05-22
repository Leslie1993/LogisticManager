package com.example.dell.logisticmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.example.dell.logisticmanager.Utils.AssetsUtils;

import java.util.ArrayList;

import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.OptionPicker;

public class OrderActivity extends AppCompatActivity {

    EditText startLocation;
    EditText endLocation;
    EditText vehicleNum;
    EditText pathSelect;
    private String[] vehicleArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        pathSelect=(EditText) findViewById(R.id.et_pathselect);
        startLocation=(EditText) findViewById(R.id.et_Startlocation);
        vehicleNum=(EditText) findViewById(R.id.et_vehiclenum);
        Button start=(Button) findViewById(R.id.btn_startlocation);
        Button num=(Button) findViewById(R.id.btn_selectvehivle);
        Button path=(Button) findViewById(R.id.btn_path);
        Button submit=(Button) findViewById(R.id.btn_submit);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocation();
            }
        });
        num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initialNum();
                selectVehicleNum(vehicleArray);
            }
        });
        path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPath();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                    }
                }).start();
                finish();
            }
        });
    }


    private void initialNum()
    {
        this.vehicleArray=new String[]{"川A123--王五","川B34343--张三","川C454545--赵四"};
    }

    private void setText(EditText et,String msg)
    {
        et.setText(msg);

    }

    private void selectPath()
    {
        OptionPicker picker = new OptionPicker(this,new String[]{"专线","代理","普货"});
        //picker.setOffset(2);
        picker.setSelectedIndex(1);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(String option) {
                //showToast(option);
                setText(pathSelect,option);
            }
        });
        picker.show();
    }

    public void selectVehicleNum(String[] VehicleNum)
    {
        OptionPicker picker = new OptionPicker(this,VehicleNum);
        //picker.setOffset(2);
        picker.setSelectedIndex(1);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(String option) {
                //showToast(option);
                setText(vehicleNum,option);
            }
        });
        picker.show();

    }

    private void selectLocation()
    {

        try {
            ArrayList<AddressPicker.Province> data = new ArrayList<AddressPicker.Province>();
            String json = AssetsUtils.readText(this, "city.json");
            data.addAll(JSON.parseArray(json, AddressPicker.Province.class));
            AddressPicker picker = new AddressPicker(this, data);
            //picker.setHideProvince(true);
            picker.setSelectedItem("四川", "成都", "郫县");
            picker.setOnAddressPickListener(new AddressPicker.OnAddressPickListener() {
                @Override
                public void onAddressPicked(String province, String city, String county) {
                    //showToast(city + county);
                    setText(startLocation,county);
                }
            });
            picker.show();
        } catch (Exception e) {
            //showToast(e.toString());
        }
    }
}
