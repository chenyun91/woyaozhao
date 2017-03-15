package org.china.xzb.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;

import org.china.xzb.R;
import org.china.xzb.activity.base.BaseActivity;
import org.china.xzb.utils.PreferenceUtil;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by hch on 2017/3/10.
 */

public class WelcomeActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getPermision();
    }

    private void getPermision() {
        start();
    }


    private void start() {
        //检查是否有READ_PHONE_STATE权限
        if (!PermissionsManager.getInstance().hasAllPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})) {
            //系统第二次弹出权限询问对话框，用户勾选不再提示的复选框后
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                //显示自定义对话框，说明为啥需要该权限
                showMyPermissionDialog();
                Toast.makeText(this,"showMyPermissionDialog",Toast.LENGTH_SHORT).show();
            } else {
                //显示系统自带权限对话框
                showSystemPermissionDialog();
            }
        } else {
            //以获得权限
            gotoMain();
        }
    }

    private void gotoMain() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (!PreferenceUtil.getBoolean(context, "isGuideShow")) {
                    Intent gIntent = new Intent(WelcomeActivity.this, GuideActivity.class);
                    startActivity(gIntent);
                } else {
                    Intent mIntent = new Intent(WelcomeActivity.this, HomeActivity.class);
                    startActivity(mIntent);
                }
                finish();
            }
        }, 2000);

    }

    private void showSystemPermissionDialog() {
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        gotoMain();
                    }

                    @Override
                    public void onDenied(String permission) {
                        finish();
                    }
                }
        );

    }

    private void showMyPermissionDialog() {
        showMessageOkCancel("需要获取手机定位，SD卡读取权限，相机，不开启将无法正常使用，请进入权限开启定位", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_NEGATIVE:
                        //跳转到设置界面
                        goToAppSetting();
                        break;
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;
                }
            }
        });

    }

    private void goToAppSetting() {
        Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
        myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
        startActivityForResult(myAppSettings, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (PermissionsManager.getInstance().hasPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
                Toast.makeText(WelcomeActivity.this, "ACCESS_COARSE_LOCATION permissions granted!", Toast.LENGTH_SHORT).show();
                gotoMain();
            } else {
                showMyPermissionDialog();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    private void showMessageOkCancel(String message, DialogInterface.OnClickListener mListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setNegativeButton("OK", mListener)
                .setPositiveButton("Cancel", mListener)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //授权成功
            } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                //拒绝授权
            }
        }
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
}
