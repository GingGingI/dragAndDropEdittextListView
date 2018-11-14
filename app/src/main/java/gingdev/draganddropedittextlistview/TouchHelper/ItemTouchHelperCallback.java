package gingdev.draganddropedittextlistview.TouchHelper;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    public static final float ALPHA_FULL = 1.0f;

    private final ItemTouchHelperAdapter adapter;

    public ItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        Layout Manager에기반해 movement flag설
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) { // Grid
//            드래그
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
//            스와이프
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else { // List
//            드래그
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
//            스와이프
            final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder holder, RecyclerView.ViewHolder target) {
        if (holder.getItemViewType() != target.getItemViewType()) {
            return false;
        }

//        어댑터에게 이동을알리기.
        adapter.onItemMove(holder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder holder, int direction) {
//        어댑터에게 해당아이템삭제 알리기.
        if (direction == ItemTouchHelper.END) {
//            오른쪽으로 했을때만.
            adapter.onItemDismiss(holder.getAdapterPosition());
        }
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder holder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//            스와이프의 dX값을가져와 Alpha적용.
            if (dX > 0) {
                final float alpha = ALPHA_FULL - Math.abs(dX) / (float) holder.itemView.getWidth();
                holder.itemView.setAlpha(alpha);
                holder.itemView.setTranslationX(dX);
            }
        } else {
            super.onChildDraw(c, recyclerView, holder, dX, dY, actionState, isCurrentlyActive);
        }

    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder holder, int actionState) {
//        활성화된 아이템만 변경하면 되므로.
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (holder instanceof ItemTouchHelperViewHolder) {
//                뷰홀더에게 해당아이템이 선택되었음을 알리기.
                ItemTouchHelperViewHolder itemHolder = (ItemTouchHelperViewHolder) holder;
                itemHolder.onItemSelected();
            }
        }
        super.onSelectedChanged(holder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder holder) {
        super.clearView(recyclerView, holder);
//        뷰돌려놓기.
        holder.itemView.setAlpha(ALPHA_FULL);

        if (holder instanceof ItemTouchHelperViewHolder) {
            ItemTouchHelperViewHolder itemHolder = (ItemTouchHelperViewHolder) holder;
            itemHolder.onItemClear();
        }
    }
}
