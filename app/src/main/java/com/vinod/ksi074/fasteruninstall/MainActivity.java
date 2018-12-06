package com.vinod.ksi074.fasteruninstall;

import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vinod.ksi074.fasteruninstall.adapter.AppsAdapter;
import com.vinod.ksi074.fasteruninstall.model.AppModel;
import com.vinod.ksi074.fasteruninstall.util.Constants;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "fasteruninstall";
    RecyclerView recyclerView;
    ArrayList<AppModel> apps;
    Toolbar toolbar;
    AppsAdapter adapter;
    private String sortChoice = "Sort by A-Z";

    private void initToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

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
        adapter = new AppsAdapter(apps);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private ArrayList<AppModel> getInstalledApps(boolean getSysPackages) {
        ArrayList<AppModel> res = new ArrayList<AppModel>();
        int flags = PackageManager.GET_META_DATA |
                PackageManager.GET_SHARED_LIBRARY_FILES |
                PackageManager.GET_UNINSTALLED_PACKAGES;
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(flags);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);

            if ((p.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 1) {
                AppModel newInfo = new AppModel();
                File file = new File(p.applicationInfo.publicSourceDir);
                long size = file.length();
                float sizeinmb = (float) size / (1024 * 1024);
                DecimalFormat twoDForm = new DecimalFormat("#.##");
                sizeinmb = Float.valueOf(twoDForm.format(sizeinmb));
                newInfo.setAppName(p.applicationInfo.loadLabel(getPackageManager()).toString());
                newInfo.setVersion(p.versionName);
                newInfo.setSize(sizeinmb + "");
                newInfo.setImage(p.applicationInfo.loadIcon(getPackageManager()));
                newInfo.setChecked(false);
                res.add(newInfo);
            }

        }

        return res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.sortBy) {
            showSortOptions();
            return true;
        }
        return false;
    }

    private void showSortOptions() {
        sortChoice = Constants.sortOptions[0];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose sorting method");
        builder.setSingleChoiceItems(Constants.sortOptions, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 1) {
                    Collections.sort(apps, new Comparator<AppModel>() {
                        @Override
                        public int compare(AppModel o1, AppModel o2) {
                            return o2.getAppName().compareTo(o1.getAppName());
                        }
                    });

                    adapter.updateList(apps);
                } else if (which == 0) {
                    Collections.sort(apps, new Comparator<AppModel>() {
                        @Override
                        public int compare(AppModel o1, AppModel o2) {
                            return o1.getAppName().compareTo(o2.getAppName());
                        }
                    });

                    adapter.updateList(apps);
                } else if (which == 2) {
                    Collections.sort(apps, new Comparator<AppModel>() {
                        @Override
                        public int compare(AppModel o1, AppModel o2) {
                            return o2.getSize().compareTo(o1.getSize());
                        }
                    });

                    adapter.updateList(apps);
                }
                dialog.dismiss();
            }

        });
        builder.show();

    }


}
