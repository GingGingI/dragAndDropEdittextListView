package gingdev.draganddropedittextlistview.Holder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import gingdev.draganddropedittextlistview.R;
import gingdev.draganddropedittextlistview.TouchHelper.ItemTouchHelperViewHolder;

public class EditItemViewHolder  extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    public final EditText editView;
    public final ImageView imageView;

    public EditItemViewHolder(View itemView) {
        super(itemView);
        editView = (EditText) itemView.findViewById(R.id.edit);
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
