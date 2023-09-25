package com.example.launchapp;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView appRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appRecyclerView = findViewById(R.id.appRecyclerView);
        appRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<AppInfo> appList = getInstalledApps();
        AppAdapter appAdapter = new AppAdapter(appList);
        appRecyclerView.setAdapter(appAdapter);
    }

    private List<AppInfo> getInstalledApps() {
        List<AppInfo> apps = new ArrayList<>();
        PackageManager packageManager = getPackageManager();
        List<ApplicationInfo> installedApps = packageManager.getInstalledApplications(0);

        for (ApplicationInfo appInfo : installedApps) {
            if ((appInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) { // Exclude system apps
                //if (appInfo.loadLabel(packageManager).toString().equals("Conecto GO Debug")) {
                    apps.add(new AppInfo(
                            appInfo.loadLabel(packageManager).toString(),
                            appInfo.packageName
                    ));
                //    return apps;
                //}
            }
        }
        return apps;
    }

    public void openAppSettings(View view) {
        Intent intent = new Intent(android.provider.Settings.ACTION_HOME_SETTINGS);
        startActivity(intent);
    }

}