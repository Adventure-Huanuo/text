package com.example.lee.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lee on 2017/5/14.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragmentList;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        mFragmentManager = fm;
        mFragmentList = fragmentList;
    }

    @Override//返回当前fragment的位置，那么根据返回的位置，我们设置为对应的fragment，再返回即可
    public
    Fragment getItem(int position)
    {
        return mFragmentList.get(position);
    }

    @Override//表示fragment的个数
    public int getCount() {

        return mFragmentList.size();
    }
}
