package gingdev.draganddropedittextlistview.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import gingdev.draganddropedittextlistview.Holder.EditItemViewHolder;
import gingdev.draganddropedittextlistview.R;
import gingdev.draganddropedittextlistview.TouchHelper.ItemTouchHelperAdapter;
import gingdev.draganddropedittextlistview.TouchHelper.onStartDragListener;

public class RecyclerEditAdapter extends RecyclerView.Adapter<EditItemViewHolder> implements ItemTouchHelperAdapter {

    private final List<String> mItem = new ArrayList<>();

    private final onStartDragListener dragListener;
    int pressposition = 0;

    public RecyclerEditAdapter(Context context, onStartDragListener dragListener) {
        this.dragListener = dragListener;
        mItem.addAll(Arrays.asList(context.getResources().getStringArray(R.array.dummy_items_edit)));
    }

    @NonNull
    @Override
    public EditItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        ContentView를 Recycler_item로 지정.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items_edit, parent, false);
//        ViewHolder에 지정하고
        EditItemViewHolder eivHolder = new EditItemViewHolder(v);
//        리턴
        return eivHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final EditItemViewHolder holder, final int position) {
        this.pressposition = position;
        holder.editView.setText(mItem.get(position));
        holder.editView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mItem.set(pressposition, s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        notifyItemRangeChanged(position, mItem.size());
    }

    public void AddView() {
        mItem.add("");
        notifyItemInserted(mItem.size() - 1);
    }
}
