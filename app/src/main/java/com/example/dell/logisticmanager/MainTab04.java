package com.example.dell.logisticmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class MainTab04 extends Fragment {

    private class Person
    {
        String name;
        String phoneNum;
        int picUrl;
        int codeUrl;
    }

    private static final String TAG ="maintab" ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View newsLayout = inflater.inflate(R.layout.main_tab_04, container, false);
        //获取activity的上下文
        final Context context = getActivity();


        //绑定Layout里面的ListView
        //ListView list = (ListView)findViewById(R.id.listView);
        ListView list = (ListView) newsLayout.findViewById(R.id.listView);
        //ListView list1 = (ListView) findViewById(R.id.ListView);
        //生成动态数组，加入数据
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
//		for(int i=0;i<10;i++)
//		{
//			HashMap<String, Object> map = new HashMap<String, Object>();
//			map.put("ItemImage", R.drawable.ic_launcher);//图像资源的ID
//			map.put("ItemTitle", "Level "+i);
//			map.put("ItemText", "Finished in 1 Min 54 Secs, 70 Moves! ");
//			listItem.add(map);
//		}
        //初始化list数据
        initializeList(listItem);

        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(context, listItem,//数据源
                R.layout.item_layout,//ListItem的XML实现
                //动态数组与ImageItem对应的子项
                new String[]{"ItemImage", "ItemTitle", "ItemText"},
                //ImageItem的XML文件里面的一个ImageView,两个TextView ID
                new int[]{R.id.ItemImage, R.id.ItemTitle, R.id.ItemText}
        );

        //初始化个人信息
        initializePerson(newsLayout);

        //添加并且显示
        list.setAdapter(listItemAdapter);

        //添加点击
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {


                switch (arg2)
                {
                    case 0:
                        Toast.makeText(context, "单击了修改", Toast.LENGTH_SHORT).show();
                        intentFunc(context,arg2);
                        break;
                    case 1:
                        Toast.makeText(context, "单击照相", Toast.LENGTH_SHORT).show();
                        intentFunc(context,arg2);
                        break;
                    case 2:
                        Toast.makeText(context, "单击了个人信息", Toast.LENGTH_SHORT).show();
                        intentFunc(context,arg2);
                        break;
                    case 3:
                        Toast.makeText(context, "单击了设置", Toast.LENGTH_SHORT).show();
                        intentFunc(context,arg2);
                        break;
                    default:
                        break;


                }


            }
        });

        //添加长按点击
        list.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("长按菜单-ContextMenu");
                menu.add(0, 0, 0, "弹出长按菜单0");
                menu.add(0, 1, 0, "弹出长按菜单1");
                Toast.makeText(context, "长按了", Toast.LENGTH_SHORT).show();
            }
        });


        return newsLayout;

    }

//    //长按菜单响应函数
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        //setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目");
//
//        //Toast.makeText(context, "长按了", Toast.LENGTH_SHORT).show();
//        Log.d(TAG, "onContextItemSelected: 长按菜单");
//        return super.onContextItemSelected(item);
//    }

    //设置界面的跳转
    private void intentFunc(Context context,int id)
    {

        Intent intent=new Intent();
        switch (id)
        {
            case 0:
                intent.setClass(context,ChangeActivity.class);
                break;
            case 1:
                intent.setClass(context,CameraActivity.class);
                break;
            case 2:
                intent.setClass(context,PersonnalActivity.class);
                break;
            case 3:
                intent.setClass(context,SettingActivity.class);
                break;

        }
        startActivity(intent);


    }

    private void initializePerson(View v) {

        Person p=new Person();
        p.name="谢宇";
        p.phoneNum="手机:123455677778";
        p.picUrl=R.drawable.set_person;
        p.codeUrl=R.drawable.set_code;

        ImageView imagePerson=(ImageView)v.findViewById(R.id.imagePersonView);
        TextView name=(TextView)v.findViewById(R.id.textNameView);
        TextView phone=(TextView)v.findViewById(R.id.textPhoneView);
        ImageView imageCode=(ImageView)v.findViewById(R.id.imageCodeView);

        imagePerson.setBackgroundResource(p.picUrl);
        name.setText(p.name);
        phone.setText(p.phoneNum);
        imageCode.setBackgroundResource(p.codeUrl);
        
    }

    private void initializeList(ArrayList<HashMap<String, Object>> lst) {


        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.set_pancel);//图像资源的ID
        map.put("ItemTitle", "修改");
        map.put("ItemText", "可以双击");
        lst.add(map);


        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.set_camera);
        map.put("ItemTitle", "照相");
        map.put("ItemText", "可以双击");
        lst.add(map);

        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.set_key);
        map.put("ItemTitle", "个人信息");
        map.put("ItemText", "可以双击");
        lst.add(map);

        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.set_setting);
        map.put("ItemTitle", "设置");
        map.put("ItemText", "可以双击");
        lst.add(map);


        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.set_clock);
        map.put("ItemTitle", "闹钟");
        map.put("ItemText", "可以双击");
        lst.add(map);

        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.set_phone);
        map.put("ItemTitle", "电话");
        map.put("ItemText", "可以双击");
        lst.add(map);



    }


}
