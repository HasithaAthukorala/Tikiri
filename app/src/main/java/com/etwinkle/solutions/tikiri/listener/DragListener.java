package com.etwinkle.solutions.tikiri.listener;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;

import com.etwinkle.solutions.tikiri.adapter.ListAdapter;
import com.etwinkle.solutions.tikiri.R;
import com.etwinkle.solutions.tikiri.helper.SQLiteHelper;
import com.etwinkle.solutions.tikiri.model.Word;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.List;

public class DragListener implements View.OnDragListener {

    private boolean isDropped = false;
    private Listener listener;
    private Word word;
    private Context context;
    private OnFinishListener onFinishListener;

    public DragListener(Listener listener, Word word, Context context, OnFinishListener onFinishListener) {
        this.listener = listener;
        this.word = word;
        this.context = context;
        this.onFinishListener = onFinishListener;
    }

    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DROP:
                isDropped = true;
                int positionTarget = -1;
                String targetStr = "";
                String fromStr = "";
                boolean same = false;

                View viewSource = (View) event.getLocalState();
                int viewId = v.getId();
                final int flItem = R.id.frame_layout_item;
                final int tvEmptyListTop = R.id.tvEmptyListTop;
                final int tvEmptyListBottom = R.id.tvEmptyListBottom;
                final int rvTop = R.id.rvTop;
                final int rvBottom = R.id.rvBottom;

                switch (viewId) {
                    case flItem:
                    case tvEmptyListTop:
                    case tvEmptyListBottom:
                    case rvTop:
                    case rvBottom:

                        RecyclerView target;
                        switch (viewId) {
                            case tvEmptyListTop:
                                targetStr = "top";
                            case rvTop:
                                target = (RecyclerView) v.getRootView().findViewById(rvTop);
                                targetStr = "top";
                                break;
                            case tvEmptyListBottom:
                                targetStr = "bottom";
                            case rvBottom:
                                target = (RecyclerView) v.getRootView().findViewById(rvBottom);
                                targetStr = "bottom";
                                break;
                            default:
                                target = (RecyclerView) v.getParent();
                                positionTarget = (int) v.getTag();
                        }

                        if (viewSource != null) {
                            RecyclerView source = (RecyclerView) viewSource.getParent();
                            if (((RecyclerView) viewSource.getParent()).getId() == rvTop) {
                                fromStr = "top";
                            } else if (((RecyclerView) viewSource.getParent()).getId() == rvBottom) {
                                fromStr = "bottom";
                            } else if (((RecyclerView) viewSource.getParent()).getId() == tvEmptyListBottom) {
                                fromStr = "bottom";
                            } else if (((RecyclerView) viewSource.getParent()).getId() == tvEmptyListTop) {
                                fromStr = "top";
                            }

                            same = fromStr.equals(targetStr);

                            if (same) {
                                Log.d("levelll",String.valueOf(positionTarget));
                                ListAdapter adapterSource = (ListAdapter) source.getAdapter();
                                int positionSource = (int) viewSource.getTag();
                                int sourceId = source.getId();

                                String list = adapterSource.getList().get(positionSource);
                                List<String> listSource = adapterSource.getList();


                                listSource.remove(positionSource);
                                if (fromStr.equals("bottom")) {
                                    if (!same) {
                                        listSource.add(positionSource, "__");
                                    }
                                }
    //                            Log.d("samee",String.valueOf(same)+"  "+fromStr+"  "+targetStr);
                                adapterSource.updateList(listSource);
                                adapterSource.notifyDataSetChanged();

                                ListAdapter adapterTarget = (ListAdapter) target.getAdapter();
                                List<String> customListTarget = adapterTarget.getList();
                                if (positionTarget >= 0) {
                                    if (!same) {
                                        try {
                                            customListTarget.remove(positionTarget);
                                        } catch (Exception s) {
                                        }
                                    }
                                    customListTarget.add(positionTarget, list);
                                } else {
                                    customListTarget.add(list);
                                }
                                adapterTarget.updateList(customListTarget);
                                adapterTarget.notifyDataSetChanged();

                                if (sourceId == rvBottom && adapterSource.getItemCount() < 1) {
                                    listener.setEmptyListBottom(true);
                                }
                                if (viewId == tvEmptyListBottom) {
                                    listener.setEmptyListBottom(false);
                                }
                                if (sourceId == rvTop && adapterSource.getItemCount() < 1) {
                                    listener.setEmptyListTop(true);
                                }
                                if (viewId == tvEmptyListTop) {
                                    listener.setEmptyListTop(false);
                                }
                                String tempStr = "";
                                for (String string : customListTarget){
                                    tempStr += string;
                                }
                                if(tempStr.equals(word.getWord())){
                                    SQLiteHelper.updateLevel(String.valueOf(word.getLevel()));
                                    MDToast mdToast = MDToast.makeText(context, "හරි", MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                                    mdToast.show();
                                    onFinishListener.onFinish();
                                }
                            }else{
                                if((positionTarget >= 0)){
                                    Log.d("levelll",String.valueOf(positionTarget));
                                    ListAdapter adapterSource = (ListAdapter) source.getAdapter();
                                    int positionSource = (int) viewSource.getTag();
                                    int sourceId = source.getId();

                                    String list = adapterSource.getList().get(positionSource);
                                    List<String> listSource = adapterSource.getList();


                                    listSource.remove(positionSource);
                                    if (fromStr.equals("bottom")) {
                                        if (!same) {
                                            listSource.add(positionSource, "__");
                                        }
                                    }
                                    //                            Log.d("samee",String.valueOf(same)+"  "+fromStr+"  "+targetStr);
                                    adapterSource.updateList(listSource);
                                    adapterSource.notifyDataSetChanged();

                                    ListAdapter adapterTarget = (ListAdapter) target.getAdapter();
                                    List<String> customListTarget = adapterTarget.getList();
                                    if (positionTarget >= 0) {
                                        if (!same) {
                                            try {
                                                customListTarget.remove(positionTarget);
                                            } catch (Exception s) {
                                            }
                                        }
                                        customListTarget.add(positionTarget, list);
                                    } else {
                                        customListTarget.add(list);
                                    }
                                    adapterTarget.updateList(customListTarget);
                                    adapterTarget.notifyDataSetChanged();

                                    if (sourceId == rvBottom && adapterSource.getItemCount() < 1) {
                                        listener.setEmptyListBottom(true);
                                    }
                                    if (viewId == tvEmptyListBottom) {
                                        listener.setEmptyListBottom(false);
                                    }
                                    if (sourceId == rvTop && adapterSource.getItemCount() < 1) {
                                        listener.setEmptyListTop(true);
                                    }
                                    if (viewId == tvEmptyListTop) {
                                        listener.setEmptyListTop(false);
                                    }
                                    String tempStr = "";
                                    for (String string : customListTarget){
                                        tempStr += string;
                                    }
                                    if(tempStr.equals(word.getWord())){
                                        SQLiteHelper.updateLevel(String.valueOf(word.getLevel()));
                                        MDToast mdToast = MDToast.makeText(context, "හරි", MDToast.LENGTH_LONG, MDToast.TYPE_SUCCESS);
                                        mdToast.show();
                                        onFinishListener.onFinish();
                                    }
                                }
                            }
                        }
                        break;
                }
                break;
        }

        if (!isDropped && event.getLocalState() != null) {
            ((View) event.getLocalState()).setVisibility(View.VISIBLE);
        }
        return true;
    }
}