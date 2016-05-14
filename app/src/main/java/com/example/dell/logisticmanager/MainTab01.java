package com.example.dell.logisticmanager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.etsy.android.grid.StaggeredGridView;
import com.example.dell.logisticmanager.Data.SampleAdapter;
import com.example.dell.logisticmanager.Data.SampleData;
import com.example.dell.logisticmanager.WebService.WebAgent;

import java.util.ArrayList;

public class MainTab01 extends Fragment implements AbsListView.OnItemClickListener
{

	public static final String SAVED_DATA_KEY = "SAVED_DATA";
	private static final int FETCH_DATA_TASK_DURATION = 2000;

	private StaggeredGridView mGridView;
	private SampleAdapter mAdapter;

	private ArrayList<String> mData;

	Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//使setmenu生效
		//setHasOptionsMenu(true);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{


		View newsLayout = inflater.inflate(R.layout.main_tab_01, container, false);
		//获取activity的上下文
		context= getActivity();

		//newsLayout.setTitle("SGV");
		mGridView = (StaggeredGridView)newsLayout.findViewById(R.id.grid_view);

		LayoutInflater layoutInflater = getActivity().getLayoutInflater();

		View header = layoutInflater.inflate(R.layout.list_item_header_footer, null);
		View footer = layoutInflater.inflate(R.layout.list_item_header_footer, null);
		TextView txtHeaderTitle = (TextView) header.findViewById(R.id.txt_title);
		TextView txtFooterTitle =  (TextView) footer.findViewById(R.id.txt_title);
		txtHeaderTitle.setText("THE HEADER!");
		txtFooterTitle.setText("THE FOOTER!");

		mGridView.addHeaderView(header);
		mGridView.addFooterView(footer);
		mGridView.setEmptyView(newsLayout.findViewById(android.R.id.empty));
		mAdapter = new SampleAdapter(context, R.id.txt_line1);

		// do we have saved data?
		if (savedInstanceState != null) {
			mData = savedInstanceState.getStringArrayList(SAVED_DATA_KEY);
			fillAdapter();
		}

		if (mData == null) {
			mData = SampleData.generateSampleData();
		}


		mGridView.setAdapter(mAdapter);

		mGridView.setOnItemClickListener(this);

		fetchData();



		return newsLayout;
	
	}

	private void fillAdapter() {
		for (String data : mData) {
			mAdapter.add(data);
		}
	}

	private void fetchData() {
		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				//SystemClock.sleep(FETCH_DATA_TASK_DURATION);
				return null;
			}

			@Override
			protected void onPostExecute(Void aVoid) {
				fillAdapter();
			}
		}.execute();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu,MenuInflater inflater) {
		//getActivity().getMenuInflater().inflate(R.menu.activity_sgv_empty_view, menu);
		//return true;

		inflater.inflate(R.menu.activity_sgv_empty_view, menu);
		//super.onCreateOptionsMenu(menu,inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		mAdapter.clear();
		fetchData();
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
		switch (position)
		{
			case 1:
				Intent itent=new Intent();
				itent.setClass(getActivity(),OrderQueryActivity.class);
				startActivity(itent);
				break;
			default:
				break;

		}

		Toast.makeText(context, "Item Clicked: " + position, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSaveInstanceState(final Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putStringArrayList(SAVED_DATA_KEY, mData);
	}


	public void add()
	{
		WebAgent agent=new WebAgent();


	}

}
