package com.example.dell.logisticmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.logisticmanager.WebService.WebAgent;

public class MainTab01 extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return  inflater.inflate(R.layout.main_tab_01, container, false);
	
	}

	public void add()
	{
		WebAgent agent=new WebAgent();


	}

}
