package com.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import static android.content.ContentValues.TAG;

/**
 * Created by TR 105 on 2017/9/1.
 */

public class IService extends Service {

    private List<Person> list;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        list = new ArrayList<>();
        return iBinder;
    }


    private IBinder iBinder = new IMyAidlInterface.Stub() {


        @Override
        public int add(int mum1, int mum2) throws RemoteException {

            Log.e(TAG, "你输入的参数是：" + mum1 + "和" + mum2);
            return mum1 + mum2;
        }

        @Override
        public List<Person> addPerson(Person person) throws RemoteException {
            Log.e(TAG, "添加的Person的name：" + person.getName() + "age" + person.getAge());
            list.add(person);

            return list;
        }
    };
}
