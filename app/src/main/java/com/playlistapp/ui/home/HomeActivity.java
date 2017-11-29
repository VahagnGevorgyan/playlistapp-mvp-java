package com.playlistapp.ui.home;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.playlistapp.BuildConfig;
import com.playlistapp.R;
import com.playlistapp.eventbus.event.OpenWebViewEvent;
import com.playlistapp.service.TestService;
import com.playlistapp.ui.base.BaseActivity;
import com.playlistapp.ui.home.settings.SettingsFragment;
import com.playlistapp.ui.home.tracks.TracksFragment;
import com.playlistapp.ui.web.WebViewActivity;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

import static com.playlistapp.Constants.EXTRA_WEB_TITLE;
import static com.playlistapp.Constants.EXTRA_WEB_URL;

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

        mServiceComponent = new ComponentName(this, TestService.class);
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentByTag(SettingsFragment.TAG);
            if (fragment == null) {
                super.onBackPressed();
            } else {
                onFragmentDetached(SettingsFragment.TAG);
            }
        }
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
            unlockDrawer();
        }
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
                showSettingsFragment();
                break;
            case R.id.nav_about:
                break;
            case R.id.nav_share:
                break;
            default:
                break;
        }
        Toast.makeText(this, "Open fragment " + item.getTitle(), Toast.LENGTH_SHORT).show();
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showSettingsFragment() {
        Timber.d("Showing \"Settings\" fragment");
        lockDrawer();
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.layout_container, SettingsFragment.newInstance(), SettingsFragment.TAG)
                .commit();
    }

    @Override
    public void showTracksFragment() {
        Timber.d("Showing \"Tracks\" fragment");
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TracksFragment.TAG);
        if (fragment == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.layoutMainContainer, TracksFragment.newInstance(), TracksFragment.TAG)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(TracksFragment.TAG)
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .replace(R.id.layoutMainContainer, TracksFragment.newInstance())
                    .commit();
        }
    }

    @OnClick(R.id.btnFilter)
    public void onFilterClicked() {
        showSettingsFragment();
    }

    @Subscribe
    public void onOpenWebViewEvent(OpenWebViewEvent event) {
        Timber.d("Trying to open \"Web view\" activity " + event.getWebUrl());
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(EXTRA_WEB_URL, event.getWebUrl());
        intent.putExtra(EXTRA_WEB_TITLE, event.getWebTitle());
        startActivity(intent);
    }

    @Override
    public void lockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void unlockDrawer() {
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }


    /////////////////////////
    // JOB SERVICE

    @Override
    protected void onStop() {
        // A service can be "started" and/or "bound". In this case, it's "started" by this Activity
        // and "bound" to the JobScheduler (also called "Scheduled" by the JobScheduler). This call
        // to stopService() won't prevent scheduled jobs to be processed. However, failing
        // to call stopService() would keep it alive indefinitely.
        stopService(new Intent(this, TestService.class));
        super.onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        // Start service and provide it a way to communicate with this class.
//        Intent startServiceIntent = new Intent(this, TestService.class);
//        startService(startServiceIntent);

//       mPresenter.testInterval();
    }

    @OnClick(R.id.start_btn)
    public void onStartClicked() {
        mPresenter.testInterval();
    }

    @OnClick(R.id.stop_button)
    public void onStopClicked() {
        mPresenter.finishInterval();
    }

    private ComponentName mServiceComponent;
    private int mJobId = 0;
    public static final String WORK_DURATION_KEY =
            BuildConfig.APPLICATION_ID + ".WORK_DURATION_KEY";

    /**
     * Executed when user clicks on SCHEDULE JOB.
     */
    public void scheduleJob(View v) {
        JobInfo.Builder builder = new JobInfo.Builder(mJobId++, mServiceComponent);

        String delay = "1";
        if (!TextUtils.isEmpty(delay)) {
            builder.setMinimumLatency(Long.valueOf(delay) * 100);
        }
        String deadline = "5";
        if (!TextUtils.isEmpty(deadline)) {
            builder.setOverrideDeadline(Long.valueOf(deadline) * 100);
        }
        boolean requiresUnmetered = false;
        boolean requiresAnyConnectivity = true;
        if (requiresUnmetered) {
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
        } else if (requiresAnyConnectivity) {
            builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        }
        builder.setRequiresDeviceIdle(false);
        builder.setRequiresCharging(false);

        // Extras, work duration.
        PersistableBundle extras = new PersistableBundle();
        String workDuration = "";
        if (TextUtils.isEmpty(workDuration)) {
            workDuration = "1";
        }
        extras.putLong(WORK_DURATION_KEY, Long.valueOf(workDuration) * 100);

        builder.setExtras(extras);

        // Schedule job
        Timber.d("::: Scheduling job");
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (tm != null) {
            tm.schedule(builder.build());
        }
    }

    /**
     * Executed when user clicks on CANCEL ALL.
     */
    public void cancelAllJobs(View v) {
        Timber.d("::: Cancel All Jobs");
        JobScheduler tm = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        if (tm != null) {
            tm.cancelAll();
        }
    }

    /**
     * Executed when user clicks on FINISH LAST TASK.
     */
    public void finishJob(View v) {
        Timber.d("::: Finish Job");
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        List<JobInfo> allPendingJobs = jobScheduler.getAllPendingJobs();
        if (allPendingJobs.size() > 0) {
            // Finish the last one
            int jobId = allPendingJobs.get(0).getId();
            jobScheduler.cancel(jobId);
            Timber.d("::: Cancel job " + jobId);
        } else {
            Timber.d("::: No jobs to cancel");
        }
    }


}
