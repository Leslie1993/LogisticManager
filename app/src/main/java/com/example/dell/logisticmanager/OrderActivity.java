package com.example.dell.logisticmanager;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.dell.logisticmanager.Data.Order;
import com.example.dell.logisticmanager.Utils.AssetsUtils;
import com.example.dell.logisticmanager.Utils.OrderUtils;
import com.example.dell.logisticmanager.WebService.OrderManager;

import java.util.ArrayList;
import cn.qqtheme.framework.picker.AddressPicker;
import cn.qqtheme.framework.picker.OptionPicker;

public class OrderActivity extends AppCompatActivity {

    protected EditText o_id;
    protected EditText cargoname;
    protected EditText cargoweight;
    //protected EditText vehicleNum;
    //protected EditText vehicleName;
    protected EditText startLocation;
    protected EditText endLocation;
    protected EditText transportWay;
    protected EditText consignor;
    protected EditText consignortel;
    protected EditText consignee;
    protected EditText consigneetel;

    //protected EditText pathSelect;

    private String[] vehicleArray;
    protected boolean isOrderSucceed;

    protected Handler orderHandle=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(isOrderSucceed)
            {
                Toast.makeText(OrderActivity.this, "succeed", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(OrderActivity.this, "failed", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initView();
        //初始化账号
        initOrderNum();

        Button start=(Button) findViewById(R.id.btn_startlocation);
        Button end=(Button) findViewById(R.id.btn_endlocation);
        //Button num=(Button) findViewById(R.id.btn_selectvehivle);
        Button path=(Button) findViewById(R.id.btn_path);
        Button submit=(Button) findViewById(R.id.btn_submit);
        Button back=(Button)findViewById(R.id.btnBack);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocation(startLocation);
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectLocation(endLocation);
            }
        });
   /*     num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initVehicleNum();
                selectVehicleNum(vehicleArray);
            }
        });*/
        path.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPath();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        isOrderSucceed=new OrderManager().addOrder(getOrder());
                        orderHandle.sendEmptyMessage(0);

                    }
                }).start();
            }
        });
    }

    private void initOrderNum()
    {

        o_id.setText(OrderUtils.getOrderNo());
        o_id.setEnabled(false);
    }

    /*
    * 初始化EditView
    * */
    private void initView()
    {

        o_id=(EditText) findViewById(R.id.et_oid);
        cargoname=(EditText) findViewById(R.id.et_cargoname);
        cargoweight=(EditText) findViewById(R.id.et_cargoweight);
        //vehicleNum=(EditText) findViewById(R.id.et_vehiclenum);
        //vehicleName=(EditText) findViewById(R.id.et_vehiclename);
        startLocation=(EditText) findViewById(R.id.et_Startlocation);
        endLocation=(EditText) findViewById(R.id.et_endLocation);
        transportWay=(EditText) findViewById(R.id.et_pathselect);
        consignor=(EditText) findViewById(R.id.et_consignor);
        consignortel=(EditText) findViewById(R.id.et_consignortel);
        consignee=(EditText) findViewById(R.id.et_consignee);
        consigneetel=(EditText)findViewById(R.id.et_consigneetel);
    }

    /*
    * 获取EditView上的数据
    * */
    protected Order getOrder()
    {
        Order order=new Order();
        order.o_id=this.o_id.getText().toString();
        order.cargoName=this.cargoname.getText().toString();
        order.cargoWeight=this.cargoweight.getText().toString();
        //order.vehicleNum=this.vehicleNum.getText().toString();
        //order.vehicleName=this.vehicleName.getText().toString();
        order.startLocation=this.startLocation.getText().toString();
        order.endLocation=this.endLocation.getText().toString();
        order.transportWay=this.transportWay.getText().toString();
        order.consignor=this.consignor.getText().toString();
        order.consignortel=this.consignortel.getText().toString();
        order.consignee=this.consignee.getText().toString();
        order.consigneetel=this.consigneetel.getText().toString();
        order.orderStatus="在途";
        //order.sendTime="";

        return order;
    }


    private void initVehicleNum()
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
                setText(transportWay,option);
            }
        });
        picker.show();
    }

    private void selectVehicleNum(String[] VehicleNum)
    {
        OptionPicker picker = new OptionPicker(this,VehicleNum);
        //picker.setOffset(2);
        picker.setSelectedIndex(1);
        picker.setTextSize(20);
        picker.setOnOptionPickListener(new OptionPicker.OnOptionPickListener() {
            @Override
            public void onOptionPicked(String option) {
                //showToast(option);
                //setText(vehicleNum,option);
            }
        });
        picker.show();

    }

    private void selectLocation(final EditText et)
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
                    if (et != null)
                        et.setText(county);
                }
            });
            picker.show();
        } catch (Exception e) {
            //showToast(e.toString());
        }
    }
}
