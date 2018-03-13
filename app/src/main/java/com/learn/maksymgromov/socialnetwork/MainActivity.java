package com.learn.maksymgromov.socialnetwork;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener{

    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.app_bar_layout) AppBarLayout appBarLayout;

    private Drawable navigationIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initActivity();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment f : fragments) {
                if(f != null && f.isVisible() && f.getTag().equalsIgnoreCase(AboutMeFragment.TAG)){
                    toolbar.setTitle("Antisocial social network");
                    toolbar.setNavigationIcon(navigationIcon);
                    createFragmentTransaction(NewsFragment.TAG);

                    fabNewsState();

                    initDrawer();
                }
            }
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        toolbar.setTitle("Antisocial social network");
        toolbar.setNavigationIcon(navigationIcon);
        createFragmentTransaction(NewsFragment.TAG);

        fabNewsState();
        initDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_about_me) {
            appBarLayout.setElevation(0);
            toolbar.setTitle(null);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            navigationView.setVisibility(View.INVISIBLE);

            createFragmentTransaction(AboutMeFragment.TAG);

            toolbar.setNavigationOnClickListener(this);
            fab.hide();
        } else if (id == R.id.nav_friends) {
            initDrawer();

            toolbar.setTitle("Friends");
            toolbar.setNavigationIcon(navigationIcon);

            createFragmentTransaction(FriendsFragment.TAG);

            fab.setImageResource(R.drawable.ic_add_black_24dp);
            fab.show();
        } else if (id == R.id.nav_news) {
            initDrawer();

            toolbar.setTitle("Antisocial social network");
            toolbar.setNavigationIcon(navigationIcon);

            createFragmentTransaction(NewsFragment.TAG);

            fabNewsState();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    public void initActivity() {
        toolbar.setTitle("Antisocial social network");
        setSupportActionBar(toolbar);

        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        initDrawer();

        navigationView.setNavigationItemSelectedListener(this);
        navigationIcon = toolbar.getNavigationIcon();

        LinearLayout linearLayout = (LinearLayout) navigationView.getHeaderView(0);
        Glide.with(this)
                .load(getDrawable(R.drawable.elon))
                .apply(RequestOptions.circleCropTransform())
                .into((ImageView) linearLayout.getChildAt(0));

        toolbar.setTitle("Antisocial social network");
        toolbar.setNavigationIcon(navigationIcon);

        createFragmentTransaction(NewsFragment.TAG);

        fabNewsState();
    }

    private void createFragmentTransaction(String tag) {
        Fragment fragment = FragmentFactory.newInstance(tag);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void fabNewsState() {
        fab.setImageResource(R.drawable.ic_create_black_24dp);
        fab.show();
    }
}
