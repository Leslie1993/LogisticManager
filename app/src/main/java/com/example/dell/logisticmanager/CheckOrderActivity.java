package com.example.dell.logisticmanager;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.search.core.VehicleInfo;
import com.example.dell.logisticmanager.Data.ArrayOrder;
import com.example.dell.logisticmanager.Data.ArrayVehicle;
import com.example.dell.logisticmanager.Data.Order;
import com.example.dell.logisticmanager.OrderModifyActivity;
import com.example.dell.logisticmanager.R;
import com.example.dell.logisticmanager.WebService.OrderManager;
import com.example.dell.logisticmanager.WebService.VehicleManager;

import java.util.List;

import cn.qqtheme.framework.picker.OptionPicker;

public class CheckOrderActivity extends Activity {

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

    TextView vehicleNum;
    TextView vehicleName;


    boolean isChecked;
    boolean isAllocated;
    String[] vehicleInfo;

    Handler checkHandle=new Handler()
    {

        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            if(isChecked)
            {
                Toast.makeText(CheckOrderActivity.this, "succeed", Toast.LENGTH_SHORT).show();
                finish();
            }
                else
                Toast.makeText(CheckOrderActivity.this, "failed", Toast.LENGTH_SHORT).show();
        }
    };

    Handler allcateHandle=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(isAllocated)
            {
                Toast.makeText(CheckOrderActivity.this, "指派成功", Toast.LENGTH_SHORT).show();
                isAllocated=false;
            }
            else
                Toast.makeText(CheckOrderActivity.this, "指派失败", Toast.LENGTH_SHORT).show();



        }
    };


    Handler vehicleHandle=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(vehicleInfo!=null&&vehicleInfo.length!=0)
                selectVehicleNum(vehicleInfo);
            else
                Toast.makeText(CheckOrderActivity.this, "没有可用车辆", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_order);
        initView();
        getCheckData();
        initButton();
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_check_order);
    }



    private void initButton()
    {

        Button start=(Button) findViewById(R.id.btn_startlocation);
        Button stop=(Button) findViewById(R.id.btn_endlocation);
        Button path=(Button) findViewById(R.id.btn_path);
        Button select=(Button) findViewById(R.id.btn_vehivleSelect);
        Button back=(Button)findViewById(R.id.btnBack);
        Button submit=(Button) findViewById(R.id.btn_submit);
        Button allocate=(Button) findViewById(R.id.btn_vehicleAllocate);
        start.setVisibility(View.INVISIBLE);
        stop.setVisibility(View.INVISIBLE);
        path.setVisibility(View.INVISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getVehicleInfo();
                    }
                }).start();

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Order order=new Order();
                        order.o_id=o_id.getText().toString();
                        isChecked=new OrderManager().checkOrder(order);
                        checkHandle.sendEmptyMessage(0);
                    }
                }).start();
            }
        });
        allocate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allcateVehicle();
            }
        });

    }
    private void allcateVehicle()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Order order=new Order();
                order.o_id=o_id.getText().toString();
                order.vehicleNum=vehicleNum.getText().toString();
                isAllocated=new VehicleManager().vehicleAllocate(order);
                allcateHandle.sendEmptyMessage(0);
            }
        }).start();

    }

    private void setText(String text)
    {
        String[] item=text.split("--");
        vehicleNum.setText(item[0]);
        vehicleName.setText(item[1]);

    }

    private void getVehicleInfo()
    {
        vehicleInfo=null;
        List<ArrayVehicle> lstVehivle=new VehicleManager().queryVehicle_byStatus("未分配");
        String[] vehecle;
        if(lstVehivle!=null&&!lstVehivle.isEmpty())
        {
            vehecle=new String[lstVehivle.size()];
            for(int i=0;i<lstVehivle.size();i++) {
                vehecle[i] = lstVehivle.get(i).vehiclenum + "--" + lstVehivle.get(i).vehiclename;
            }
            //Message msg=new Message();
            vehicleInfo=vehecle;
            vehicleHandle.sendEmptyMessage(0);
        }
        else
            Toast.makeText(CheckOrderActivity.this, "网络错误，或者没有可用车辆", Toast.LENGTH_SHORT).show();



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
                setText(option);
                //showToast(option);
                //setText(vehicleNum,option);
            }
        });
        picker.show();

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

        vehicleNum=(TextView)findViewById(R.id.tv_vehicleNum);
        vehicleName=(TextView) findViewById(R.id.tv_vehicleName);

        o_id.setEnabled(false);
        cargoname.setEnabled(false);
        cargoweight.setEnabled(false);
        startLocation.setEnabled(false);
        endLocation.setEnabled(false);
        transportWay.setEnabled(false);
        consignor.setEnabled(false);
        consignortel.setEnabled(false);
        consignee.setEnabled(false);
        consigneetel.setEnabled(false);
    }

    private void getCheckData()
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
