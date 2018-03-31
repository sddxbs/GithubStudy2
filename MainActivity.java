package com.l.androidstudy0804a;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.l.anddroidstudy0804b.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private MyConn conn;
    private IMyAidlInterface.Stub myBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void ok(View view){
        Intent intent=new Intent();
        intent.setAction("cn.com.aa.Service");
        intent.setPackage("com.l.anddroidstudy0804b");
        conn=new MyConn();
        bindService(intent,conn,BIND_AUTO_CREATE);
        try {
            myBinder.callMethdService();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder= (IMyAidlInterface.Stub) IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
