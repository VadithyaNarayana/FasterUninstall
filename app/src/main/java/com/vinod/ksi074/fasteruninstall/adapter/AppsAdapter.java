package com.vinod.ksi074.fasteruninstall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinod.ksi074.fasteruninstall.R;
import com.vinod.ksi074.fasteruninstall.model.AppModel;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private List<AppModel> mApps;

    public AppsAdapter(List<AppModel> mApps) {
        this.mApps = mApps;
    }

    @NonNull
    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View appView = inflater.inflate(R.layout.item_app, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(appView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull AppsAdapter.ViewHolder viewHolder, int position) {
        AppModel app = mApps.get(position);
        AppCompatImageView imageView;
        AppCompatTextView appName;
        AppCompatTextView appVersion;
        AppCompatTextView appSize;
        AppCompatCheckBox checkBox;
        imageView = viewHolder.imageView;
        appName = viewHolder.appName;
        appVersion = viewHolder.appVersion;
        appSize = viewHolder.appSize;
        checkBox = viewHolder.checkBox;
        imageView.setImageDrawable(app.getImage());
        appName.setText(app.getAppName());
        appVersion.setText(app.getVersion());
        appSize.setText(app.getSize());
        checkBox.setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imageView;
        private AppCompatTextView appName;
        private AppCompatTextView appVersion;
        private AppCompatTextView appSize;
        private AppCompatCheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            appName = itemView.findViewById(R.id.appName);
            appVersion = itemView.findViewById(R.id.appVersion);
            appSize = itemView.findViewById(R.id.appSize);
            checkBox = itemView.findViewById(R.id.checkbox);

        }
    }
}
