package com.vinod.ksi074.fasteruninstall.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.vinod.ksi074.fasteruninstall.R;
import com.vinod.ksi074.fasteruninstall.model.AppModel;

import java.util.List;

public class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.ViewHolder> {

    private List<AppModel> mApps;
    Context context;
    private int noOfAppsSelected;

    public AppsAdapter(List<AppModel> mApps) {
        this.mApps = mApps;
    }

    @NonNull
    @Override
    public AppsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
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
        appSize.setText(app.getSize() + "  MB");
        checkBox.setChecked(app.isChecked());

    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    private void updateCount() {
        noOfAppsSelected = 0;
        for (AppModel model : mApps) {
            if (model.isChecked()) {
                Log.d("name", model.getAppName());
                noOfAppsSelected++;
            }
        }
        Intent intent = new Intent("custom-message");
        //            intent.putExtra("quantity",Integer.parseInt(quantity.getText().toString()));
        intent.putExtra("quantity", noOfAppsSelected);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    public void updateList(List<AppModel> data) {
        mApps = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
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

            itemView.setOnClickListener(this);
            checkBox.setOnCheckedChangeListener(this);
        }

        @Override
        public void onClick(View v) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION)
                return;
            int pos = getAdapterPosition();
            AppModel clickedApp = mApps.get(pos);
            clickedApp.setChecked(true);
            Log.d("com.fullunistaller", clickedApp.getAppName());
            checkBox.setChecked(!checkBox.isChecked());
            updateCount();
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION)
                return;
            int pos = getAdapterPosition();
            AppModel clickedApp = mApps.get(pos);
            clickedApp.setChecked(isChecked);
            Log.d("com.fullunistaller", clickedApp.isChecked() + "");
            updateCount();
        }


    }
}
