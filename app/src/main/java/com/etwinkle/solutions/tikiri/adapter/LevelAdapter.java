package com.etwinkle.solutions.tikiri.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.etwinkle.solutions.tikiri.R;
import com.etwinkle.solutions.tikiri.activity.PlayActivity;
import com.etwinkle.solutions.tikiri.model.Level;

import java.util.ArrayList;
import java.util.List;

public class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.LevelViewHolder> {

    private Context context;
    private List<Level> levelList;

    public LevelAdapter(Context context, List<Level> levelList) {
        this.context = context;
        this.levelList = levelList;
    }

    @NonNull
    @Override
    public LevelViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.level_row, viewGroup, false);
        return new LevelAdapter.LevelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LevelViewHolder levelViewHolder, final int i) {
        Log.d("levellee23t2t3",String.valueOf(levelList.get(0).getStatus()));
        levelViewHolder.text.setText(String.valueOf(levelList.get(i).getId()));
        if (i == 0){
            levelViewHolder.text.setVisibility(View.VISIBLE);
            levelViewHolder.lock.setVisibility(View.GONE);
        }else if(levelList.get(i-1).getStatus() == 0) {
            levelViewHolder.text.setVisibility(View.GONE);
            levelViewHolder.lock.setVisibility(View.VISIBLE);
        }else{
            levelViewHolder.text.setVisibility(View.VISIBLE);
            levelViewHolder.lock.setVisibility(View.GONE);
        }
        if((i==0)||(levelList.get(i-1).getStatus() == 1)){
            levelViewHolder.frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,PlayActivity.class);
                    intent.putExtra("level",String.valueOf(levelList.get(i).getId()));
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return levelList.size();
    }

    class LevelViewHolder extends RecyclerView.ViewHolder {
        //
        TextView text;
        FrameLayout frameLayout;
        ImageView lock;

        LevelViewHolder(View itemView) {
            super(itemView);

            lock = (ImageView) itemView.findViewById(R.id.lock);
            text = (TextView) itemView.findViewById(R.id.text);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.frame_layout_item);

        }
    }


}