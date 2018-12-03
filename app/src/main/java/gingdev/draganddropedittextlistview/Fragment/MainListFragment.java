package gingdev.draganddropedittextlistview.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import gingdev.draganddropedittextlistview.R;

public class MainListFragment extends ListFragment {

    private OnListItemClickListener itemClickListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        itemClickListener = (OnListItemClickListener) activity;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        values/String에 있는 MainItems가져옴.
        final String[] items = getResources().getStringArray(R.array.Main_items);
//        안드로이드 기본레이아웃중 하나인 simplelist에맞춰서 어댑터설정.
//        처음 recycler뷰가 그리드로보여줄지 리스트로보여줄지 확인.
        final ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
    }

//    ItemClickListener클릭시 누른위치 반환.
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        itemClickListener.onListItemClicked(position);
    }

//    콜백을위한 interface.
    public interface OnListItemClickListener {
        void onListItemClicked(int position);
    }
}

