package ddwucom.mobile.mypager;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = (ViewPager) findViewById(R.id.pager);
        //미리 로딩할 아이템의 개수 설정
        pager.setOffscreenPageLimit(3);

        //뷰페이저 아답터 객체 : 뷰페이지에 보여줄 각 프래그먼트 관리
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

        fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        fragment3 = new Fragment3();
        adapter.addItem(fragment3);

        //뷰페이저에 아답터 연결
        pager.setAdapter(adapter);
    }

    class MyAdapter extends FragmentPagerAdapter{
        //프래그먼트를 담아둘 ArrayList 객체 생성
        ArrayList<Fragment> items = new ArrayList<Fragment>();
        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }

        //타이틀스트립 : 전체 프래그먼트의 개수 및 현재의 프래그먼트를 알려주는 역할
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "페이지 "+position;
        }
    }
}