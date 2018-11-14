package gingdev.draganddropedittextlistview.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gingdev.draganddropedittextlistview.Adapter.RecyclerListAdapter;
import gingdev.draganddropedittextlistview.R;
import gingdev.draganddropedittextlistview.TouchHelper.ItemTouchHelperCallback;
import gingdev.draganddropedittextlistview.TouchHelper.onStartDragListener;

public class RecyclerGridFragment extends Fragment implements onStartDragListener {

    private ItemTouchHelper itHelper;

    public RecyclerGridFragment(){}

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
        final RecyclerListAdapter adapter = new RecyclerListAdapter(getActivity(), this);

        RecyclerView rc = (RecyclerView) view;
        rc.setHasFixedSize(true);
        rc.setAdapter(adapter);
//        Grid 가로 갯수 설정.
        final int spanCnt = getResources().getInteger(R.integer.grid_column);
        final GridLayoutManager lm = new GridLayoutManager(getActivity(), spanCnt);
        rc.setLayoutManager(lm);
//        Adapter에게받은 값을 ItemTouchHelper에게 주기위해 지정.
        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        itHelper = new ItemTouchHelper(callback);
        itHelper.attachToRecyclerView(rc);
    }

//    받은콜백을 itemTouchHelper에게 전달.
    @Override
    public void onStartDrag(RecyclerView.ViewHolder holder) {
        itHelper.startDrag(holder);
    }
}
