package com.vinod.ksi074.fasteruninstall;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.vinod.ksi074.fasteruninstall.adapter.AppsAdapter;
import com.vinod.ksi074.fasteruninstall.model.AppModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<AppModel> apps;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        apps = getInstalledApps(false);
        Collections.sort(apps, new Comparator<AppModel>() {
            @Override
            public int compare(AppModel o1, AppModel o2) {
                return o1.getAppName().compareTo(o2.getAppName());
            }
        });
        AppsAdapter adapter = new AppsAdapter(apps);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initToolbar() {
        // For Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    private ArrayList<AppModel> getInstalledApps(boolean getSysPackages) {
        ArrayList<AppModel> res = new ArrayList<AppModel>();
        int flags = PackageManager.GET_META_DATA |
                PackageManager.GET_SHARED_LIBRARY_FILES |
                PackageManager.GET_UNINSTALLED_PACKAGES;
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(flags);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);

            if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {

            } else {
                AppModel newInfo = new AppModel();
                newInfo.setAppName(p.applicationInfo.loadLabel(getPackageManager()).toString());
                newInfo.setVersion(p.versionCode + "");
                newInfo.setSize(p.lastUpdateTime + "");
                newInfo.setImage(p.applicationInfo.loadIcon(getPackageManager()));
                newInfo.setChecked(false);
                res.add(newInfo);
            }


        }

        return res;
    }


}
