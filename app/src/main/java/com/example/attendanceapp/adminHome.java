package com.example.attendanceapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class adminHome extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Requests");
        arrayList.add("Employee");
        arrayList.add("Setting");
        tabLayout.setupWithViewPager(viewPager);

        prepareViewPager(viewPager,arrayList);


    }

    private void prepareViewPager(ViewPager viewPager, ArrayList<String> arrayList) {
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager());

        requests requests = new requests();
        for (int i =0; i<arrayList.size();i++){
            Bundle bundle = new Bundle();
            bundle.putString("title",arrayList.get(i));
            requests.setArguments(bundle);
            adapter.addfragment(requests,arrayList.get(i));
            requests = new requests();

        }
        viewPager.setAdapter(adapter);






    }

    private class MainAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList =new ArrayList<>();
        ArrayList<String> stringArrayList = new ArrayList<>();
        int[] imageList = {R.drawable.request, R.drawable.employee,R.drawable.settings};

        public void addfragment (Fragment fragment, String string){
            fragmentArrayList.add(fragment);
            stringArrayList.add(string);
        }
        public MainAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            Drawable drawable = ContextCompat.getDrawable(getApplicationContext(),imageList[position]);
            drawable.setBounds(0,0,drawable.getIntrinsicWidth(),drawable.getIntrinsicHeight());
            SpannableString spannableString = new SpannableString("     "+
                    stringArrayList.get(position));
            ImageSpan imageSpan = new ImageSpan(drawable,ImageSpan.ALIGN_BOTTOM);
            spannableString.setSpan(imageSpan,0,1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return  spannableString;

        }
    }
}