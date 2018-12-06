package com.vinod.ksi074.fasteruninstall;

import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vinod.ksi074.fasteruninstall.adapter.AppsAdapter;
import com.vinod.ksi074.fasteruninstall.model.AppModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<AppModel> apps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        apps = getInstalledApps(false);
        AppsAdapter adapter = new AppsAdapter(apps);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<AppModel> getInstalledApps(boolean getSysPackages) {
        ArrayList<AppModel> res = new ArrayList<AppModel>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((!getSysPackages) && (p.versionName == null)) {
                continue;
            }
            AppModel newInfo = new AppModel();
            newInfo.setAppName(p.applicationInfo.loadLabel(getPackageManager()).toString());
            newInfo.setVersion(p.versionCode + "");
            newInfo.setSize("have to set");
            newInfo.setImage(p.applicationInfo.loadIcon(getPackageManager()));
            newInfo.setChecked(false);
            res.add(newInfo);
        }
        return res;
    }
}
