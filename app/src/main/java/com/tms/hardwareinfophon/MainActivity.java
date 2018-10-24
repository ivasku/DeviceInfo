package com.tms.hardwareinfophon;

import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.FlipHorizontalTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.ScaleInOutTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.astuetz.PagerSlidingTabStrip;
import com.jaredrummler.android.device.DeviceName;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements Fragment_1.OnFragmentInteractionListener,
                                                              Fragment_2.OnFragmentInteractionListener,
                                                              Fragment_3.OnFragmentInteractionListener {

    private List<String> m_FragmentsTitle;
    public static MainActivity _Instance;
    private GLSurfaceView glSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _Instance = this;

        m_FragmentsTitle = new ArrayList<>();
        List<Fragment> fragments = getFragments();
        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),fragments));
        pager.setPageTransformer(true, new CubeInTransformer());

        getSupportActionBar().setTitle("Device Info - " + Build.MODEL);  // provide compatibility to all the versions
    }

    @Override
    protected void onStart() {
        super.onStart();

        m_FragmentsTitle.add("DEVICE");
        m_FragmentsTitle.add("SOC");
        m_FragmentsTitle.add("SYSTEM");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private List<Fragment> getFragments() {
        List<Fragment> fragm = new ArrayList<>();

        fragm.add(Fragment_2.newInstance("SecondFragment, Instance 1", "SecondFragment, Instance 1"));
        fragm.add(Fragment_1.newInstance("FirstFragment, Instance 1" , "FirstFragment, Instance 1"));
        fragm.add(Fragment_3.newInstance("ThirdFragment, Instance 1","ThirdFragment, Instance 1"));

        return fragm;
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int pos) {
            return this.fragments.get(pos);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return m_FragmentsTitle.get(position);
        }
    }



}
