package com.example.mynavdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity
        extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    private Fragment fragmentNews;
    private Fragment fragmentParams;
    private Fragment fragmentProfile;

    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PARAMS = 1;
    private static final int FRAGMENT_PROFILE = 2;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureToolbar();
        configureDrawerLayout();
        configureNavigationView();
        showFirstFragment();
    }

    private void showFirstFragment()
    {
        Fragment visibleFragment = getSupportFragmentManager().findFragmentById(R.id.activity_main_frame_layout);
        if (visibleFragment == null)
        {
            showNewsFragment();
            // Indiquer dans le menu qu'on a sélectionné le fragment news
            navigationView.getMenu().getItem(0).setChecked(true);
        }
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.activity_main_drawer_news:
                this.showFragment(FRAGMENT_NEWS);
                break;
            case R.id.activity_main_drawer_profile:
                this.showFragment(FRAGMENT_PROFILE);
                break;
            case R.id.activity_main_drawer_settings:
                this.showFragment(FRAGMENT_PARAMS);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void showFragment(int fragmentID)
    {
        switch (fragmentID)
        {
            case FRAGMENT_NEWS:
                this.showNewsFragment();
                break;
            case FRAGMENT_PROFILE:
                this.showProfileFragment();
                break;
            case FRAGMENT_PARAMS:
                this.showParamsFragment();
                break;
            default:
                break;
        }
    }

    private void showNewsFragment()
    {
        if (this.fragmentNews == null) this.fragmentNews = NewsFragment.newInstance();
        startTransactionFragment(this.fragmentNews);
    }
    private void showProfileFragment()
    {
        if (this.fragmentProfile == null) this.fragmentProfile = ProfileFragment.newInstance();
        startTransactionFragment(this.fragmentProfile);
    }
    private void showParamsFragment()
    {
        if (this.fragmentParams == null) this.fragmentParams = ParamsFragment.newInstance();
        startTransactionFragment(this.fragmentParams);
    }

    private void startTransactionFragment(Fragment fragment)
    {
        if (!fragment.isVisible())
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }

    private void configureToolbar()
    {
        toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void configureDrawerLayout()
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView()
    {
        navigationView = (NavigationView) findViewById(R.id.activity_maint_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
}