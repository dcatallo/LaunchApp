package com.example.launchapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> {

    private List<AppInfo> appList;

    public AppAdapter(List<AppInfo> appList) {
        this.appList = appList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_app, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final AppInfo appInfo = appList.get(position);
        holder.appNameTextView.setText(appInfo.getAppName());

        holder.itemView.setOnClickListener(v -> {
            PackageManager packageManager = v.getContext().getPackageManager();
            Intent launchIntent = packageManager.getLaunchIntentForPackage(appInfo.getPackageName());
            if (launchIntent != null) {
                v.getContext().startActivity(launchIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView appNameTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            appNameTextView = itemView.findViewById(R.id.appNameTextView);
        }
    }
}

