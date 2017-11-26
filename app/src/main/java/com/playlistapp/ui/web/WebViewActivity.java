package com.playlistapp.ui.web;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.playlistapp.R;
import com.playlistapp.ui.base.BaseActivity;
import com.playlistapp.utils.CommonUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

import static com.playlistapp.Constants.EXTRA_WEB_TITLE;
import static com.playlistapp.Constants.EXTRA_WEB_URL;


/**
 * Web View screen activity class.
 */
public class WebViewActivity extends BaseActivity implements WebViewMvpView {

    @Inject
    WebViewMvpPresenter<WebViewMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.textViewTitle)
    TextView mTextViewTitle;
    @BindView(R.id.webView)
    WebView mWebView;
    @BindView(R.id.progressBarLoad)
    ProgressBar mProgressBarLoad;

    private String mWebUrl;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initInjector() {
        Timber.d("Injecting \"WebView\" activity");
        getActivityComponent().inject(this);
    }

    @Override
    protected void initViews() {
        Timber.d("Trying to initialize view elements");
        mPresenter.onAttach(WebViewActivity.this);
        prepareToolbar();
        setWebView();
    }

    @SuppressWarnings("ConstantConditions")
    private void prepareToolbar() {
        if(mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return false;
    }

    /**
     * Method for setting web view.
     */
    private void setWebView() {
        Timber.d("Setting web view with url");
        if(getIntent().getExtras() != null) {
            mWebUrl = getIntent().getExtras().getString(EXTRA_WEB_URL);
            mTextViewTitle.setText(getIntent().getExtras().getString(EXTRA_WEB_TITLE));
            loadWebView(mWebUrl);
        }
    }

    /**
     * Method for loading web view post.
     */
    @SuppressLint("SetJavaScriptEnabled")
    public void loadWebView(String url) {
        Timber.d("Loading web view with url " + url);
        try {
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            mWebView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    Timber.d("Loading web view url finished successful " + url);
                    mPresenter.onPageFinished();
                }

                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                    super.onReceivedError(view, request, error);
                    Timber.d("Loading web view url finished with error " + error.getDescription());
                    Toast.makeText(WebViewActivity.this, error.getDescription(), Toast.LENGTH_SHORT).show();
                }

                @SuppressWarnings("deprecation")
                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Timber.d("Loading web view url finished with error " + description);
                    Toast.makeText(WebViewActivity.this, description, Toast.LENGTH_SHORT).show();
                }
            });
            mWebView.loadUrl(url);
        } catch (NullPointerException e) {
            Timber.e(e.getMessage());
        }
    }

    @OnClick(R.id.imageButtonCopy)
    public void onCopyClicked() {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(getResources().getString(R.string.str_clipboard_message), mWebUrl);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clipData);
            CommonUtils.toast(WebViewActivity.this, getString(R.string.str_copied));
        }
    }

    @Override
    public void hideProgressBar() {
        Timber.d("Hiding progress loading bar");
        if (mProgressBarLoad.getVisibility() == View.VISIBLE) {
            mProgressBarLoad.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }
}
