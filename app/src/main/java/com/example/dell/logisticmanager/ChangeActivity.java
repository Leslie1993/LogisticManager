package com.example.dell.logisticmanager;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dell.logisticmanager.WebService.WebAgent;

public class ChangeActivity extends AppCompatActivity {

    private ImageView ivDeleteText;
    private EditText etSearch;
    private Button searchBtn;

    private String data;



    //跨线程刷新界面
    Handler handlerGetData = new Handler() {
        public void handleMessage(Message msg) {

                etSearch.setText(data);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);


        ivDeleteText = (ImageView) findViewById(R.id.ivDeleteText);
        etSearch = (EditText) findViewById(R.id.etSearch);
        searchBtn=(Button) findViewById(R.id.btnSearch);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //网络访问只允许在子线程中使用
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        WebAgent agent=new WebAgent();
                        data =agent.getData(10);
                        handlerGetData.sendEmptyMessage(0);

                    }
                }).start();
                //String result=agent.getData(10);

                //etSearch.setText(result);
                //etSearch.setText("nihao");
            }
        });

        ivDeleteText.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                etSearch.setText("");
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub

            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    ivDeleteText.setVisibility(View.GONE);
                } else {
                    ivDeleteText.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
