package gingdev.draganddropedittextlistview.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gingdev.draganddropedittextlistview.Adapter.RecyclerListAdapter;
import gingdev.draganddropedittextlistview.TouchHelper.ItemTouchHelperCallback;
import gingdev.draganddropedittextlistview.TouchHelper.onStartDragListener;

public class RecyclerListFragment extends Fragment implements onStartDragListener {

    private ItemTouchHelper itHelper;

    //    Contianer를 Recycler로 지정.
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return new RecyclerView(container.getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Adapter설정.
        RecyclerListAdapter adapter = new RecyclerListAdapter(getActivity(), this);
//        RecyclerView설정.
        RecyclerView rc = (RecyclerView) view;
        rc.setHasFixedSize(true);
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
}
