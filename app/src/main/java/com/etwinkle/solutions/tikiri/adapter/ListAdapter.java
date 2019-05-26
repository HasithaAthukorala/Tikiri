package com.etwinkle.solutions.tikiri.adapter;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.etwinkle.solutions.tikiri.R;
import com.etwinkle.solutions.tikiri.listener.DragListener;
import com.etwinkle.solutions.tikiri.listener.Listener;
import com.etwinkle.solutions.tikiri.listener.OnFinishListener;
import com.etwinkle.solutions.tikiri.model.Word;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>
        implements View.OnTouchListener {

    private List<String> list;
    private Listener listener;
    private Word word;
    private Context context;
    private OnFinishListener onFinishListener;

    public ListAdapter(List<String> list, Listener listener, Word word, Context context, OnFinishListener onFinishListener) {
        this.list = list;
        this.listener = listener;
        this.word = word;
        this.context = context;
        this.onFinishListener = onFinishListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
                parent.getContext()).inflate(R.layout.list_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.text.setText(list.get(position));
        holder.frameLayout.setTag(position);
        holder.frameLayout.setOnTouchListener(this);
        holder.frameLayout.setOnDragListener(new DragListener(listener,word,context,onFinishListener));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    v.startDragAndDrop(data, shadowBuilder, v, 0);
                } else {
                    v.startDrag(data, shadowBuilder, v, 0);
                }
                return true;
        }
        return false;
    }

    public List<String> getList() {
        return list;
    }

    public void updateList(List<String> list) {
        this.list = list;
    }

    public DragListener getDragInstance() {
        if (listener != null) {
            return new DragListener(listener,word,context,onFinishListener);
        } else {
            Log.e("ListAdapter", "Listener wasn't initialized!");
            return null;
        }
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
//
        TextView text;
        FrameLayout frameLayout;

        ListViewHolder(View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text);
            frameLayout = (FrameLayout) itemView.findViewById(R.id.frame_layout_item);

        }
    }
}
