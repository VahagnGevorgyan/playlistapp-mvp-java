package com.playlistapp.ui.home;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.playlistapp.R;
import com.playlistapp.ui.base.BaseActivity;
import com.playlistapp.ui.home.tracks.TracksFragment;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Home screen activity class.
 */
public class HomeActivity extends BaseActivity
        implements HomeMvpView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    HomeMvpPresenter<HomeMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.layout_container)
    DrawerLayout mDrawer;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_home;
    }

    @Override
    protected void initInjector() {
        Timber.d("Injecting \"Home\" activity");
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViews() {
        Timber.d("Trying to initialize view elements");
        mPresenter.onAttach(HomeActivity.this);
    }

    @Override
    protected void prepareLayout() {
        super.prepareLayout();
        prepareToolbar();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

    }

    @SuppressWarnings("ConstantConditions")
    private void prepareToolbar() {
        setSupportActionBar(mToolbar);
        prepareNavigationDrawer();
    }

    /**
     * Prepares Navigation drawer with items.
     */
    private void prepareNavigationDrawer() {
        Timber.d("Preparing Navigation view and drawer");
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }
        };
        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
        mPresenter.onNavMenuCreated();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.layout_container);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_tracks:
                showTracksFragment();
                break;
            case R.id.nav_favorites:
                break;
            case R.id.nav_tools:
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_share:
                break;
            default:
                break;
        }
        Toast.makeText(this, "Open fragment " + item.getTitle(), Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.layout_container);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showTracksFragment() {
        Timber.d("Showing \"Tracks\" fragment");
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(TracksFragment.TAG)
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .replace(R.id.layoutMainContainer, TracksFragment.newInstance())
                .commit();
    }
}
