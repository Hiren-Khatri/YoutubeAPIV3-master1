package app.com.VatsalyaCoachingAcademy;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout = null;
    private ViewPager viewPager = null;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        firebaseAuth = FirebaseAuth.getInstance();

        //setting the tabs title
        tabLayout.addTab(tabLayout.newTab().setText("Channel"));
        tabLayout.addTab(tabLayout.newTab().setText("PlayList"));
        tabLayout.addTab(tabLayout.newTab().setText("Live"));

        //setup the view pager
        final PagerAdapter adapter = new app.com.VatsalyaCoachingAcademy.adapters.PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this,Login.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.profileMenu:{
                startActivity(new Intent(this,ProfileActivity.class));
                break;
            }
            case R.id.aboutusMenu:{
                startActivity(new Intent(this,AboutusActivity.class));
                break;
            }
            case R.id.logoutMenu:{
                logout();
                Toast.makeText(this, "Logged Out!Login Again...", Toast.LENGTH_SHORT).show();

            }
        }        return super.onOptionsItemSelected(item);
    }
}
