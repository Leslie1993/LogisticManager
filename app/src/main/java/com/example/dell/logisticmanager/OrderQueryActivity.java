package com.example.dell.logisticmanager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.logisticmanager.Data.ArrayOrder;
import com.example.dell.logisticmanager.Data.OrderInfo;
import com.example.dell.logisticmanager.Utils.PopupToolType;
import com.example.dell.logisticmanager.Utils.PopupWindowUtil;
import com.example.dell.logisticmanager.WebService.NetWork;
import com.example.dell.logisticmanager.WebService.OrderManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class OrderQueryActivity extends AppCompatActivity {



    private ArrayList<OrderInfo> lstInfo=new ArrayList<OrderInfo>();
    private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    private ListView lst;
    private ImageButton imagebtn_right;
    private TextView title;
    private int year,month,day;

    private List<ArrayOrder> lstOrder=new ArrayList<ArrayOrder>();
    private SimpleAdapter listItemAdapter;

    EditText startTime;
    EditText stopTime;

    int orderIndex;

    boolean isDel;

    Handler orderHandle=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(!lstOrder.isEmpty())
                updateList();

        }
    };
    Handler delHandle=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(isDel)
            {
                listItem.remove(orderIndex);
                listItemAdapter.notifyDataSetChanged();
                //重置删除标志
                isDel=false;
            }

            //super.handleMessage(msg);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_query);
        ImageButton start=(ImageButton)findViewById(R.id.btn_startTime);
        ImageButton stop=(ImageButton)findViewById(R.id.btn_endTime);
        Button back=(Button)findViewById(R.id.btnBack);
        //Button seartbtn = (Button) findViewById(R.id.btnSearch);
        imagebtn_right=(ImageButton) findViewById(R.id.imgBtnRight);
        title=(TextView) findViewById(R.id.tvTitle);
        lst = (ListView) findViewById(R.id.OrderlistView);
        startTime=(EditText) findViewById(R.id.et_startTime);
        stopTime=(EditText) findViewById(R.id.et_endTime);
        ImageButton search=(ImageButton)findViewById(R.id.ibtn_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateList();

            }
        });

/*        seartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listItem.isEmpty())
                    updateList();
            }
        });*/





      lst.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
          @Override
          public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {

              contextMenu.setHeaderTitle("订单操作");
              //contextMenu.setHeaderTitle()
              contextMenu.add(0, 0, 0, "删除订单");
              //contextMenu.addSubMenu(0,0,1,"sub1");
              //contextMenu.addSubMenu(0,0,2,"sub2");
              contextMenu.add(0, 1, 0, "修改订单");
              contextMenu.add(0, 2, 0, "审核订单");



          }
      });

        imagebtn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindowTool dw = new PopupWindowTool(view, mHandler);
                dw.setBackgroundDrawable(OrderQueryActivity.this.getResources()
                        .getDrawable(R.drawable.title_function_bg));
                dw.showLikePopDownMenu();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showCalender(startTime);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalender(stopTime);
            }
        });

    }


    public void showCalender(final EditText et)
    {
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(calendar.YEAR);
        month=calendar.get(calendar.MONTH);
        day=calendar.get(calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog=new DatePickerDialog(OrderQueryActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String time=i+"-"+(i1+1)+"-"+i2;
                et.setText(time);
            }
        },year,month,day);
        datePickerDialog.show();

    }


    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PopupToolType.BUTTON_ONE:
                    //editCaseType();
                    setTitle("全部订单");
                    queryOrder_all();
                    break;
                case PopupToolType.BUTTON_TWO:
                    //delCaseType();
                    setTitle("完成订单");
                    testData();
                    updateList();

                    break;
                case PopupToolType.BUTTON_THREE:
                    //changeView();
                    setTitle("未完成订单");
                    clearItem();
                    break;
                case PopupToolType.BUTTON_FOUR:
                    setTitle("自定义查询");
                    showMenu();
                    break;
            }
        }
    };



    private void queryOrder_all()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(NetWork.isNetworkAvailable(getApplicationContext()))
                {
                    lstOrder.clear();
                    lstOrder=new OrderManager().queryOrder_all();
                    orderHandle.sendEmptyMessage(0);
                }
                else
                    Toast.makeText(OrderQueryActivity.this, "网络连接错误", Toast.LENGTH_SHORT).show();

            }
        }).start();

    }

    private void showMenu()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderQueryActivity.this);
        builder.setIcon(R.drawable.ic_launcher);
        builder.setTitle("自定义查询");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View v = LayoutInflater.from(OrderQueryActivity.this).inflate(R.layout.search_selection, null);
        ImageButton start=(ImageButton)v.findViewById(R.id.btn_startTime);
        ImageButton stop=(ImageButton) v.findViewById(R.id.btn_endTime);
        final EditText menu_stat=(EditText) v.findViewById(R.id.et_startTime);
        final EditText menu_end=(EditText) v.findViewById(R.id.et_endTime);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(v);

        //final EditText username = (EditText)view.findViewById(R.id.username);
        //final EditText password = (EditText)view.findViewById(R.id.password);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalender(menu_stat);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalender(menu_end);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        builder.show();

    }

    private void setTitle(String status)
    {
        title.setText(status);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                .getMenuInfo();

        switch (item.getItemId())
        {

            case 0:
                delOrder(info.position);
                //int lstitem=info.position;
                //Toast.makeText(OrderQueryActivity.this,lstitem+"shanchu", Toast.LENGTH_SHORT).show();

                break;
            case 1:
                //Toast.makeText(OrderQueryActivity.this, "xiugai", Toast.LENGTH_SHORT).show();
                intentActivity(info.position,OrderModifyActivity.class);
                break;
            case 2:
                intentActivity(info.position,CheckOrderActivity.class);
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void delOrder(final int index)
    {
        final ArrayOrder order=lstOrder.get(index);
        new Thread(new Runnable() {
            @Override
            public void run() {
                isDel=new OrderManager().delOrder(order.o_id);
                orderIndex=index;
                delHandle.sendEmptyMessage(0);
            }
        }).start();

    }


    private void intentActivity(int index,Class activity)
    {
    /*  OrderInfo order=new OrderInfo();
        order.info="123";
        order.id="123";
        order.explain="123";*/
        ArrayOrder order=lstOrder.get(index);
        Bundle buddle=new Bundle();
        buddle.putSerializable("Order",order);
        Intent intent=new Intent();
        intent.setClass(OrderQueryActivity.this,activity);
        intent.putExtras(buddle);
        startActivity(intent);

    }

    public void getData()
    {

        for(int i=0;i<1;i++)
        {
            OrderInfo order=new OrderInfo();
            order.id= String.valueOf(i);
            order.explain="demo";
            order.info="3434";
            lstInfo.add(order);
        }



    }



    private void testData()
    {

        if(!lstOrder.isEmpty())
            lstOrder.clear();
        for(int i=0;i<1;i++)
        {
            ArrayOrder order=new ArrayOrder();
            order.o_id="123";
            order.orderstatus="123";
            lstOrder.add(order);

        }

    }

    public void clearItem()
    {

        listItem.clear();
        if(listItemAdapter!=null)
            listItemAdapter.notifyDataSetChanged();

    }

    //更新listview
    public void updateList()
    {

        //getData();
        //情况listview的显示
        //lst.setAdapter(null);
        clearItem();

        if(!lstOrder.isEmpty())
            for (ArrayOrder item:lstOrder
                 ) {

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemImage", R.drawable.set_pancel);//图像资源的ID
                map.put("ItemTitle",item.o_id);
                map.put("ItemText", item.orderstatus);
                listItem.add(map);

            }


        //生成适配器的Item和动态数组对应的元素
        listItemAdapter = new SimpleAdapter(this, listItem,//数据源
                R.layout.item_layout,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemTitle", "ItemText"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.ItemImage, R.id.ItemTitle, R.id.ItemText}
        );

        //添加并且显示
        lst.setAdapter(listItemAdapter);


    }




    //Popup相关
    private static class PopupWindowTool extends PopupWindowUtil implements
            View.OnClickListener {

        private Handler mHandler;

        public PopupWindowTool(View anchor, Handler handler) {
            super(anchor);
            this.mHandler = handler;
        }

        @Override
        protected void onCreate() {
            // inflate layout
            LayoutInflater inflater = (LayoutInflater) this.anchor.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View root = null;
            int[] location = new int[2];
            anchor.getLocationOnScreen(location);
            root = (View) inflater.inflate(R.layout.popup_top_right, null);

            // ---------------------------------------------------------------------------
            LinearLayout btnOne = (LinearLayout) root.findViewById(R.id.btnOne);
            btnOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Message msg = mHandler.obtainMessage();
                    msg.what = PopupToolType.BUTTON_ONE;
                    mHandler.sendMessage(msg);
                    dismiss();
                }
            });

            LinearLayout btnTwo = (LinearLayout) root.findViewById(R.id.btnTwo);
            btnTwo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Message msg = mHandler.obtainMessage();
                    msg.what = PopupToolType.BUTTON_TWO;
                    mHandler.sendMessage(msg);
                    dismiss();
                }
            });

            LinearLayout btnThree = (LinearLayout) root
                    .findViewById(R.id.btnThree);
            btnThree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Message msg = mHandler.obtainMessage();
                    msg.what = PopupToolType.BUTTON_THREE;
                    mHandler.sendMessage(msg);
                    dismiss();
                }
            });


            LinearLayout btnFour = (LinearLayout) root
                    .findViewById(R.id.btnFour);
            btnFour.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Message msg = mHandler.obtainMessage();
                    msg.what = PopupToolType.BUTTON_FOUR;
                    mHandler.sendMessage(msg);
                    dismiss();
                }
            });

            this.setContentView(root);
        }


        @Override
        public void onClick(View view) {

        }
    }

}
