package gingdev.draganddropedittextlistview;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import gingdev.draganddropedittextlistview.Fragment.MainListFragment;
import gingdev.draganddropedittextlistview.Fragment.RecyclerEditListFragment;
import gingdev.draganddropedittextlistview.Fragment.RecyclerGridFragment;
import gingdev.draganddropedittextlistview.Fragment.RecyclerListFragment;

public class MainActivity extends AppCompatActivity implements MainListFragment.OnListItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        첫번째 실행(bundle이 없음.)일경우만
        if (savedInstanceState == null) {
//            Fragment실행
            MainListFragment fragment = new MainListFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.RcyContent, fragment)
                    .commit();
        }
    }


    @Override
    public void onListItemClicked(int position) {
        Fragment fragment = null;
//        MainListFragment에서 클릭한값가져와 리턴.
        switch (position) {
            case 0:
//                리스트
                fragment = new RecyclerListFragment();
//                Fragment실행.
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.RcyContent, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case 1:
//                그리드
                fragment = new RecyclerGridFragment();
//                Fragment실행.
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.RcyContent, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
            case 2:
//                그리드
                fragment = new RecyclerEditListFragment();
//                Fragment실행.
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.RcyContent, fragment)
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
