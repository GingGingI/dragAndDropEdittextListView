package gingdev.draganddropedittextlistview.Holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import gingdev.draganddropedittextlistview.R;
import gingdev.draganddropedittextlistview.TouchHelper.ItemTouchHelperViewHolder;

public class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {

    public final TextView textView;
    public final ImageView imageView;

    public ItemViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text);
        imageView = (ImageView) itemView.findViewById(R.id.dragBtn);
    }

    @Override
    public void onItemSelected() {
        itemView.setBackgroundColor(Color.LTGRAY);
    }
    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(0);
    }

}