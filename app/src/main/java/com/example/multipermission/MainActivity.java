package com.example.multipermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.multipermission.support.PermissionSupport;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainTag";

    private PermissionSupport permission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissionCheck();
    }

    private void permissionCheck(){
        // SDK 23버전 이하에서는 Permission 필요 x
        if(Build.VERSION.SDK_INT >= 23){
            permission = new PermissionSupport(this, this);

            if(!permission.checkPermission()){
                // 권한이 없으면,
                permission.requestPermission(); // 권한 요청
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (!permission.permissionResult(requestCode, permissions, grantResults)) {
            Toast.makeText(this, "권한 설정에서 권한을 허용하십시오.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}