package com.example.dell.logisticmanager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


import com.dacer.androidcharts.LineView;
import com.example.dell.logisticmanager.Data.ArrayTransport;
import com.example.dell.logisticmanager.WebService.FinancialManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainTab03 extends Fragment
{

	int randomint = 9;
	EditText m_start;
	EditText m_end;
	ImageButton query;
	ListView listView;

	List<ArrayTransport> lst;
	ArrayList<HashMap<String,String>> list;
	SimpleAdapter adapter;

	Handler queryHandle=new Handler()
	{
		@Override
		public void handleMessage(Message msg) {
			if(lst!=null&&!lst.isEmpty())
				updateList(lst);
			else
				Toast.makeText(getContext(), "没有返回数据", Toast.LENGTH_SHORT).show();

		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View newsLayout = inflater.inflate(R.layout.main_tab_03, container, false);
		final LineView lineView = (LineView)newsLayout.findViewById(R.id.line_view);

		//must*
		ArrayList<String> test = new ArrayList<String>();
		for (int i=0; i<randomint; i++){
			test.add(String.valueOf(i+1));
		}
		lineView.setBottomTextList(test);
		lineView.setDrawDotLine(true);
		lineView.setShowPopup(LineView.SHOW_POPUPS_NONE);

		m_start=(EditText) newsLayout.findViewById(R.id.startText);
		m_end=(EditText) newsLayout.findViewById(R.id.destinationText);
		query = (ImageButton) newsLayout.findViewById(R.id.searchButton);
		listView=(ListView)newsLayout.findViewById(R.id.resultView);

		Button lineButton = (Button)newsLayout.findViewById(R.id.line_button);
		lineButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//randomSet(lineView);
				setLine(lineView);
			}
		});

		randomSet(lineView);
		//return rootView;

		query.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				queryData();
			}
		});

		return newsLayout;
	}

	private void clearList()
	{
		if(lst!=null)
		{
			if(!lst.isEmpty())
			{
				list.clear();
				adapter.notifyDataSetChanged();
			}

		}



	}

	private void updateList(List<ArrayTransport> lst)
	{

		list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map=null;
		String[] from={"transport","distance"};
		int[] to={R.id.tv_transport,R.id.tv_distance};
		//将数据存放进ArrayList对象中，数据安排的结构是，ListView的一行数据对应一个HashMap对象，
		//HashMap对象，以列名作为键，以该列的值作为Value，将各列信息添加进map中，然后再把每一列对应
		//的map对象添加到ArrayList中

		for (ArrayTransport item:
			 lst) {
			map=new HashMap<String,String>();       //为避免产生空指针异常，有几列就创建几个map对象
			map.put("transport", item.transportway);
			map.put("distance", item.distance);
			list.add(map);
		}
/*		for(int i=0; i<4; i++){
			map=new HashMap<String,String>();       //为避免产生空指针异常，有几列就创建几个map对象
			map.put("id", userId[i]);
			map.put("name", userName[i]);
			list.add(map);
		}*/

		//创建一个SimpleAdapter对象
		adapter=new SimpleAdapter(getContext(),list,R.layout.item_transport,from,to);
		//调用ListActivity的setListAdapter方法，为ListView设置适配器
		listView.setAdapter(adapter);


	}


	private void queryData()
	{
		clearList();
		final String start=m_start.getText().toString();
		final String stop=m_end.getText().toString();
		new Thread(new Runnable() {
			@Override
			public void run() {
				lst=new FinancialManager().queryTransportWay_byLocation(start,stop);
				queryHandle.sendEmptyMessage(0);
			}
		}).start();

	}



	private void setLine(LineView lineView)
	{
		if (lst != null && !lst.isEmpty()) {
			ArrayList<ArrayList<Integer>> dataLists = new ArrayList<ArrayList<Integer>>();
			for (ArrayTransport item :
					lst) {
				ArrayList<Integer> dataList = new ArrayList<Integer>();
				dataList.add(Integer.valueOf(item.freight_2016_1));
				dataList.add(Integer.valueOf(item.freight_2016_2));
				dataList.add(Integer.valueOf(item.freight_2016_3));
				dataLists.add(dataList);

			}
			lineView.setDataList(dataLists);

		}


	}


	private void randomSet(LineView lineView){
		ArrayList<Integer> dataList = new ArrayList<Integer>();
		int random = (int)(Math.random()*9+1);
		for (int i=0; i<randomint; i++){
			dataList.add((int)(Math.random()*random));
		}

		ArrayList<Integer> dataList2 = new ArrayList<Integer>();
		random = (int)(Math.random()*9+1);
		for (int i=0; i<randomint; i++){
			dataList2.add((int)(Math.random()*random));
		}

		ArrayList<Integer> dataList3 = new ArrayList<Integer>();
		random = (int)(Math.random()*9+1);
		for (int i=0; i<randomint; i++){
			dataList3.add((int)(Math.random()*random));
		}

		ArrayList<ArrayList<Integer>> dataLists = new ArrayList<ArrayList<Integer>>();
		dataLists.add(dataList);
		dataLists.add(dataList2);
        dataLists.add(dataList3);

		lineView.setDataList(dataLists);
	}

}
