package com.example.dell.logisticmanager;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends FragmentActivity
{

	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments = new ArrayList<Fragment>();
	
	
	/**
	 * 底部四个按钮
	 */
	private LinearLayout mTabBtnWeixin;
	private LinearLayout mTabBtnFrd;
	private LinearLayout mTabBtnAddress;
	private LinearLayout mTabBtnSettings;


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

		
		initView();
		
		

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
		{

			@Override
			public int getCount()
			{
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0)
			{
				return mFragments.get(arg0);
			}
		};
		
		mViewPager.setAdapter(mAdapter);
		
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener()
		{

			private int currentIndex;

			@Override
			public void onPageSelected(int position)
			{
				resetTabBtn();
				switch (position)
				{
				case 0:
					((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
							.setImageResource(R.drawable.tab_weixin_pressed);
					break;
				case 1:
					((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
							.setImageResource(R.drawable.tab_find_frd_pressed);
					break;
				case 2:
					((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
							.setImageResource(R.drawable.tab_address_pressed);
					break;
				case 3:
					((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
							.setImageResource(R.drawable.tab_settings_pressed);
					break;
				}

				currentIndex = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{

			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
			}
		});

	}
	
	protected void resetTabBtn()
	{
		((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
				.setImageResource(R.drawable.tab_weixin_normal);
		((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
				.setImageResource(R.drawable.tab_find_frd_normal);
		((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
				.setImageResource(R.drawable.tab_address_normal);
		((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
				.setImageResource(R.drawable.tab_settings_normal);
	}

	private void initView()
	{

		mTabBtnWeixin = (LinearLayout) findViewById(R.id.id_tab_bottom_weixin);
		mTabBtnFrd = (LinearLayout) findViewById(R.id.id_tab_bottom_friend);
		mTabBtnAddress = (LinearLayout) findViewById(R.id.id_tab_bottom_contact);
		mTabBtnSettings = (LinearLayout) findViewById(R.id.id_tab_bottom_setting);

		MainTab01 tab01 = new MainTab01();
		MainTab02 tab02 = new MainTab02();
		MainTab03 tab03 = new MainTab03();
		MainTab04 tab04 = new MainTab04();
		mFragments.add(tab01);
		mFragments.add(tab02);
		mFragments.add(tab03);
		mFragments.add(tab04);

		mTabBtnWeixin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(MainActivity.this, "demo", Toast.LENGTH_SHORT).show();
			}
		});
	}

	//@Override
	/*public void onClick(View v)
	 {
		 switch (v.getId())
		 {
			 case R.id.id_tab_bottom_weixin:
				 setTabSelection(0);
				 break;
			 case R.id.id_tab_bottom_friend:
				 setTabSelection(1);
				 break;
			 case R.id.id_tab_bottom_contact:
				 setTabSelection(2);
				 break;
			 case R.id.id_tab_bottom_setting:
				 setTabSelection(3);
				 break;

			 default:
				 break;
		 }

	 }*/


	/**
	 * 根据传入的index参数来设置选中的tab页。
	 *
	 */
	/*private void setTabSelection(int index) {


		// 重置按钮
		resetTabBtn();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
			case 0:
				// 当点击了消息tab时，改变控件的图片和文字颜色
				((ImageButton) mTabBtnWeixin.findViewById(R.id.btn_tab_bottom_weixin))
						.setImageResource(R.drawable.tab_weixin_pressed);
				if (mTab01 == null) {
					// 如果MessageFragment为空，则创建一个并添加到界面上
					mTab01 = new MainTab01();
					transaction.add(R.id.id_content, mTab01);
				} else {
					// 如果MessageFragment不为空，则直接将它显示出来
					transaction.show(mTab01);
				}
				break;
			case 1:
				// 当点击了消息tab时，改变控件的图片和文字颜色
				((ImageButton) mTabBtnFrd.findViewById(R.id.btn_tab_bottom_friend))
						.setImageResource(R.drawable.tab_find_frd_pressed);
				if (mTab02 == null) {
					// 如果MessageFragment为空，则创建一个并添加到界面上
					mTab02 = new MainTab02();
					transaction.add(R.id.id_content, mTab02);
				} else {
					// 如果MessageFragment不为空，则直接将它显示出来
					transaction.show(mTab02);
				}
				break;
			case 2:
				// 当点击了动态tab时，改变控件的图片和文字颜色
				((ImageButton) mTabBtnAddress.findViewById(R.id.btn_tab_bottom_contact))
						.setImageResource(R.drawable.tab_address_pressed);
				if (mTab03 == null) {
					// 如果NewsFragment为空，则创建一个并添加到界面上
					mTab03 = new MainTab03();
					transaction.add(R.id.id_content, mTab03);
				} else {
					// 如果NewsFragment不为空，则直接将它显示出来
					transaction.show(mTab03);
				}
				break;
			case 3:
				// 当点击了设置tab时，改变控件的图片和文字颜色
				((ImageButton) mTabBtnSettings.findViewById(R.id.btn_tab_bottom_setting))
						.setImageResource(R.drawable.tab_settings_pressed);
				if (mTab04 == null) {
					// 如果SettingFragment为空，则创建一个并添加到界面上
					mTab04 = new MainTab04();
					transaction.add(R.id.id_content, mTab04);
				} else {
					// 如果SettingFragment不为空，则直接将它显示出来
					transaction.show(mTab04);
				}
				break;
		}
		transaction.commit();
	}*/

}
