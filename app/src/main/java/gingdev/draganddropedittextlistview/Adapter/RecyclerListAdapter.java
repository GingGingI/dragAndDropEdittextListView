package gingdev.draganddropedittextlistview.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gingdev.draganddropedittextlistview.Holder.ItemViewHolder;
import gingdev.draganddropedittextlistview.R;
import gingdev.draganddropedittextlistview.TouchHelper.ItemTouchHelperAdapter;
import gingdev.draganddropedittextlistview.TouchHelper.onStartDragListener;

public class RecyclerListAdapter extends RecyclerView.Adapter<ItemViewHolder> implements ItemTouchHelperAdapter {

    private final List<String> mItem = new ArrayList<>();

    private final onStartDragListener dragListener;

    public RecyclerListAdapter(Context context, onStartDragListener dragListener) {
        this.dragListener = dragListener;
        mItem.addAll(Arrays.asList(context.getResources().getStringArray(R.array.dummy_items)));
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ContentView를 Recycler_item로 지정.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent, false);
//        ViewHolder에 지정하고
        ItemViewHolder ivHolder = new ItemViewHolder(v);
//        리턴
        return ivHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemViewHolder holder, int position) {
//        mItem값 입력.
        holder.textView.setText(mItem.get(position));
//        ImageButton을 누르고있을때 onStartDrag활성화.
        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(holder);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
//        아이템의 위치가 변경되었을시 스왑 후 알림..
        Collections.swap(mItem, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
//        아이템이 삭제될시 삭제후 알림..
        mItem.remove(position);
        notifyItemRemoved(position);
    }
}

