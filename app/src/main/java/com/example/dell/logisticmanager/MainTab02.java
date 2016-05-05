package com.example.dell.logisticmanager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainTab02 extends Fragment
{
	//@Bind(R.id.tabhost) FragmentTabHost mTabHost;

	// 图片
	@DrawableRes
	private int mImages[] = {
			R.drawable.tab_counter,
			R.drawable.tab_assistant,
			R.drawable.tab_contest,
			R.drawable.tab_center
	};

	// 标题
	private String mFragmentTags[] = {
			"counter",
			"assistant",
			"contest",
			"center"
	};


	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View messageLayout = inflater.inflate(R.layout.main_tab_02, container, false);
/*
		Context context=getActivity();


		//ButterKnife.bind(getActivity());


		TabHost tabHost = (TabHost) getActivity().findViewById(R.id.tabHost);
		// 如果没有继承TabActivity时，通过该种方法加载启动tabHost
		tabHost.setup();
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("第一个标签").setContent(
				R.id.view1));

		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("第三个标签")
				.setContent(R.id.view3));

		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("第二个标签")
				.setContent(R.id.view2));*/

		/*mTabHost.setup(context,getChildFragmentManager(), android.R.id.tabcontent);
		mTabHost.getTabWidget().setDividerDrawable(null); // 去掉分割线

		for (int i = 0; i < mImages.length; i++) {
			// Tab按钮添加文字和图片
			TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mFragmentTags[i]).setIndicator(getImageView(i));
			// 添加Fragment
			mTabHost.addTab(tabSpec, FragmentTab.class, null);
			// 设置Tab按钮的背景
			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.pedo_actionbar_bkg);
		}*/



		return messageLayout;
	}


	// 获得图片资源
	private View getImageView(int index) {
		@SuppressLint("InflateParams")
		View view = getActivity().getLayoutInflater().inflate(R.layout.view_tab_indicator, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.tab_iv_image);
		imageView.setImageResource(mImages[index]);
		return view;
	}

}
