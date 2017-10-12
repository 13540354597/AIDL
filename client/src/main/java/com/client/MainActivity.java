package com.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aidl.IMyAidlInterface;
import com.aidl.Person;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText et_num1, et_num2, et_num3;
    private Button btn_add;
    private List<Person> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        bind();
    }

    private void init() {
        et_num1 = findViewById(R.id.et_num1);
        et_num2 = findViewById(R.id.et_num2);
        et_num3 = findViewById(R.id.et_num3);
        btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int num1 = Integer.valueOf(et_num1.getText().toString());
                int num2 = Integer.valueOf(et_num2.getText().toString());

                try {
                    int res = iMyAidlInterface.add(num1, num2);

                    Person person = new Person("abs", "23");
                    list = iMyAidlInterface.addPerson(person);

                    et_num3.setText(res + "数据长度：" + list.size());


                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bind() {
        Intent intent = new Intent();
        //服务端的包名和类名
        intent.setComponent(new ComponentName("com.aidl", "com.aidl.IService"));
        // intent.setAction("com.aidl.IService");
        bindService(intent, con, Context.BIND_AUTO_CREATE);


    }

    IMyAidlInterface iMyAidlInterface;
    private ServiceConnection con = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);


        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            iMyAidlInterface = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(con);
    }
}
