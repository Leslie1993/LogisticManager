package com.example.dell.logisticmanager;

import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.logisticmanager.Utils.PopupToolType;
import com.example.dell.logisticmanager.Utils.PopupWindowUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderQueryActivity extends AppCompatActivity {

    public class OrderInfo
    {
        String id;
        String explain;
        String info;

    }

    private ArrayList<OrderInfo> lstInfo=new ArrayList<OrderInfo>();
    private ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
    private ListView lst;
    private ImageButton imagebtn_right;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_query);

        Button seartbtn = (Button) findViewById(R.id.btnSearch);
        imagebtn_right=(ImageButton) findViewById(R.id.imgBtnRight);
        title=(TextView) findViewById(R.id.tvTitle);
        lst = (ListView) findViewById(R.id.OrderlistView);
        seartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listItem.isEmpty())
                    updateList();
            }
        });


      lst.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
          @Override
          public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
              contextMenu.setHeaderTitle("长按菜单-ContextMenu");
              //contextMenu.setHeaderTitle()
              contextMenu.add(0, 0, 0, "删除订单");
              //contextMenu.addSubMenu(0,0,1,"sub1");
              //contextMenu.addSubMenu(0,0,2,"sub2");
              contextMenu.add(0, 1, 0, "修改订单");



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

    }

    public Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case PopupToolType.BUTTON_ONE:
                    //editCaseType();
                    setTitle("全部订单");
                    break;
                case PopupToolType.BUTTON_TWO:
                    //delCaseType();
                    setTitle("未完成订单");
                    break;
                case PopupToolType.BUTTON_THREE:
                    //changeView();
                    setTitle("完成订单");
                    break;
            }
        }
    };

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
                Toast.makeText(OrderQueryActivity.this, "shanchu", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(OrderQueryActivity.this, "xiugai", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }




    public void getData()
    {

        for(int i=0;i<10;i++)
        {
            OrderInfo order=new OrderInfo();
            order.id= String.valueOf(i);
            order.explain="demo";
            order.info="3434";
            lstInfo.add(order);
        }



    }

    //更新listview
    public void updateList()
    {

        getData();

        if(!lstInfo.isEmpty())
            for (OrderInfo item:lstInfo
                 ) {

                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemImage", R.drawable.set_pancel);//图像资源的ID
                map.put("ItemTitle", item.id);
                map.put("ItemText", item.explain);
                listItem.add(map);

            }

        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this, listItem,//数据源
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

            this.setContentView(root);
        }


        @Override
        public void onClick(View view) {

        }
    }

}
