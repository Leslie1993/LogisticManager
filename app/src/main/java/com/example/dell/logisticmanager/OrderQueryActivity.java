package com.example.dell.logisticmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_query);

        Button seartbtn = (Button) findViewById(R.id.btnSearch);
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

}
