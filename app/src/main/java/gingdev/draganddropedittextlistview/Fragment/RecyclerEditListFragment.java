package gingdev.draganddropedittextlistview.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gingdev.draganddropedittextlistview.Adapter.RecyclerEditAdapter;
import gingdev.draganddropedittextlistview.Adapter.RecyclerListAdapter;
import gingdev.draganddropedittextlistview.R;
import gingdev.draganddropedittextlistview.TouchHelper.ItemTouchHelperCallback;
import gingdev.draganddropedittextlistview.TouchHelper.onStartDragListener;

public class RecyclerEditListFragment extends Fragment implements onStartDragListener, View.OnClickListener {

    private ItemTouchHelper itHelper;
    private RecyclerEditAdapter adapter;
    private Button AddBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (inflater != null) {
            return inflater.inflate(R.layout.edit_view, container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Adapter설정.
         adapter = new RecyclerEditAdapter(getActivity(), this);
//        RecyclerView설정.
        RecyclerView rc = (RecyclerView) view.findViewById(R.id.editrecycle);
        AddBtn = (Button) view.findViewById(R.id.AddBtn);
        AddBtn.setOnClickListener(this);
        rc.setAdapter(adapter);
        rc.setLayoutManager(new LinearLayoutManager(getActivity()));
//        ItemTouchHelper 지정.
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        itHelper = new ItemTouchHelper(callback);
        itHelper.attachToRecyclerView(rc);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        itHelper.startDrag(holder);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AddBtn :
                adapter.AddView();
                break;
        }
    }
}
