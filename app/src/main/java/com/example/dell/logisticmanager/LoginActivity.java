package com.example.dell.logisticmanager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.logisticmanager.WebService.WebAgent;

/**
 * Created by dell on 2016/5/14.
 */
public class LoginActivity extends Activity {


    private Button loginButton;
    private boolean isSucceed;
    private boolean isRegister;


    //跨线程刷新界面
    Handler handlerLogin = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if(isSucceed)
                intentMain();
            else
                Toast.makeText(LoginActivity.this, "密码或用户名错误", Toast.LENGTH_SHORT).show();

        }
    };

    Handler handlerRegister=new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            if(isRegister)
                Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
        }
    };
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login);


        final EditText Id=(EditText) findViewById(R.id.username_edit);
        final EditText Pass=(EditText) findViewById(R.id.password_edit);
        final TextView Link=(TextView) findViewById(R.id.register_link);

        loginButton=(Button) findViewById(R.id.signin_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //网络访问只允许在子线程中使用
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int id=Integer.parseInt(Id.getText().toString());
                        String pass=Pass.getText().toString();
                        isSucceed=certification(id,pass);
                        handlerLogin.sendEmptyMessage(0);

                    }
                }).start();

            }
        });


        Link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);


                builder.setIcon(R.drawable.ic_launcher);
                builder.setTitle("请输入注册信息");
                //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
                View alertview = LayoutInflater.from(LoginActivity.this).inflate(R.layout.alertdialog_register, null);
                //    设置我们自己定义的布局文件作为弹出框的Content
                builder.setView(alertview);

                final EditText registerid=(EditText)alertview.findViewById(R.id.et_id);
                final EditText registerpass=(EditText)alertview.findViewById(R.id.et_pass);
                final EditText confirmpass=(EditText)alertview.findViewById(R.id.et_passconfirm);


                builder.setPositiveButton("注册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (!confirmPass(registerpass.getText().toString(),confirmpass.getText().toString()))
                        {
                            Toast.makeText(LoginActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                isRegister=register(Integer.parseInt(registerid.getText().toString()),registerpass.getText().toString());
                                handlerRegister.sendEmptyMessage(0);
                                    //Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            }
                        }).start();

                        Toast.makeText(LoginActivity.this, "注册", Toast.LENGTH_SHORT).show();

                    }
                });


                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(LoginActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    }
                });

                builder.show();

            }
        });


    }

    private boolean confirmPass(String passA,String passB)
    {

        return passA.equals(passB);

    }

    private boolean register(int id,String pass)
    {
        WebAgent agent=new WebAgent();
        boolean result=agent.register(id,pass);
        return result;
    }

    private boolean certification(int id,String pass)
    {
        WebAgent agent=new WebAgent();
        boolean result=agent.login(id,pass);
        return result;

    }

    private void intentMain()
    {

        Intent intent =new Intent();
        intent.setClass(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }


}
