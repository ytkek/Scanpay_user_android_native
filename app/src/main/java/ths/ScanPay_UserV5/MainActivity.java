package ths.ScanPay_UserV5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import ths.ScanPay_UserV5.GetFunction.GetAllDiscoveryListTask;
import ths.ScanPay_UserV5.PostFunction.PostApp_TestToken_Task;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static String LoginID,Password,Page;
    public static Activity mainactivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            mainactivity=this;
            Intent intent = getIntent();
            LoginID =  intent.getExtras().getString("LoginID");
            Password =  intent.getExtras().getString("Password");
            Page = intent.getExtras().getString("Page");

           // new PostApp_TestToken_Task(MainActivity.mainactivity).execute(LoginID,Password);
            if(!TextUtils.isEmpty(Page))
            {
                Intent lol = new Intent(this,MessageCentreActivity.class);
                startActivity(lol);
            }
            else
            {

            }

            FirebaseMessaging.getInstance().subscribeToTopic(MainActivity.LoginID);
            FirebaseMessaging.getInstance().subscribeToTopic("all");
            //testing
           // Generic.SaveOtp("145546",this);
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            viewPager.setOffscreenPageLimit(3);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
            createTabIcons();

            tabLayout.getTabAt(1).select();

            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    int position = tab.getPosition();

                    if(position == 0)
                    {
                        View view = tab.getCustomView();
                        LinearLayout lol = (LinearLayout)view.findViewById(R.id.tab) ;
                        ImageView img = (ImageView)view.findViewById(R.id.img);
                        TextView text = (TextView)view.findViewById(R.id.text);
                        img.setImageResource(R.drawable.search_click);
                        text.setTextColor(Color.parseColor("#0094FF"));
                        lol.setBackgroundColor(Color.parseColor("#ffffff"));

                        DiscoveryFragment.indicator=true;
                        new GetAllDiscoveryListTask(MainActivity.mainactivity,DiscoveryFragment.recyclerView).execute(MainActivity.LoginID,MainActivity.Password);
                    }
                    if(position == 1)
                    {
                        View view = tab.getCustomView();
                        LinearLayout lol = (LinearLayout)view.findViewById(R.id.tab) ;
                        ImageView img = (ImageView)view.findViewById(R.id.img);
                        TextView text = (TextView)view.findViewById(R.id.text);
                        img.setImageResource(R.drawable.home_click);
                        text.setTextColor(Color.parseColor("#0094FF"));
                        lol.setBackgroundColor(Color.parseColor("#ffffff"));
                    }
                    if(position == 2)
                    {
                        View view = tab.getCustomView();
                        LinearLayout lol = (LinearLayout)view.findViewById(R.id.tab) ;
                        ImageView img = (ImageView)view.findViewById(R.id.img);
                        TextView text = (TextView)view.findViewById(R.id.text);
                        img.setImageResource(R.drawable.setting_click);
                        text.setTextColor(Color.parseColor("#0094FF"));
                        lol.setBackgroundColor(Color.parseColor("#ffffff"));
                    }

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    int position = tab.getPosition();

                    if(position == 0)
                    {
                        View view = tab.getCustomView();
                        LinearLayout lol = (LinearLayout)view.findViewById(R.id.tab) ;
                        ImageView img = (ImageView)view.findViewById(R.id.img);
                        TextView text = (TextView)view.findViewById(R.id.text);
                        img.setImageResource(R.drawable.search_unclick);
                        text.setTextColor(Color.parseColor("#ffffff"));
                        lol.setBackgroundColor(Color.parseColor("#0094FF"));
                    }
                    if(position == 1)
                    {
                        View view = tab.getCustomView();
                        LinearLayout lol = (LinearLayout)view.findViewById(R.id.tab) ;
                        ImageView img = (ImageView)view.findViewById(R.id.img);
                        TextView text = (TextView)view.findViewById(R.id.text);
                        img.setImageResource(R.drawable.home_unclick);
                        text.setTextColor(Color.parseColor("#ffffff"));
                        lol.setBackgroundColor(Color.parseColor("#0094FF"));
                    }
                    if(position == 2)
                    {
                        View view = tab.getCustomView();
                        LinearLayout lol = (LinearLayout)view.findViewById(R.id.tab) ;
                        ImageView img = (ImageView)view.findViewById(R.id.img);
                        TextView text = (TextView)view.findViewById(R.id.text);
                        img.setImageResource(R.drawable.setting_unclick);
                        text.setTextColor(Color.parseColor("#ffffff"));
                        lol.setBackgroundColor(Color.parseColor("#0094FF"));
                    }
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });

    }
    private View prepareTabView(int pos) {
        View view = getLayoutInflater().inflate(R.layout.custom_tab, null);
        ImageView img = view.findViewById(R.id.img);
        TextView text = view.findViewById(R.id.text);

        if(pos==1)
        {
            img.setImageResource(R.drawable.search_unclick);
            text.setTextColor(Color.WHITE);
            text.append("DISCOVERY");
        }
        if(pos == 2)
        {
            img.setImageResource(R.drawable.home_click);
            text.setTextColor(Color.parseColor("#0094FF"));
            text.append("HOME");
        }
        if(pos == 3)
        {
            img.setImageResource(R.drawable.setting_unclick);
            text.setTextColor(Color.WHITE);
            text.append("SETTING");
        }
        return view;
    }

    private void createTabIcons() {


        tabLayout.getTabAt(0).setCustomView(prepareTabView(1));
        tabLayout.getTabAt(1).setCustomView(prepareTabView(2));
        tabLayout.getTabAt(2).setCustomView(prepareTabView(3));
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DiscoveryFragment(), "");
        adapter.addFragment(new HomeFragment(), "");
        adapter.addFragment(new SettingFragment(), "");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
