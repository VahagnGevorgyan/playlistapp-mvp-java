package com.playlistapp.ui.base;


import com.playlistapp.R;
import com.playlistapp.data.IDataManager;
import com.playlistapp.data.network.data.error.ApiError;
import com.playlistapp.data.scheduler.ISchedulerProvider;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * onAttach() and onDetach(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final IDataManager mIDataManager;
    private final ISchedulerProvider mISchedulerProvider;
    private final CompositeDisposable mCompositeDisposable;

    private V mMvpView;

    @Inject
    public BasePresenter(
            IDataManager IDataManager,
            ISchedulerProvider ISchedulerProvider,
            CompositeDisposable compositeDisposable
    ) {
        this.mIDataManager = IDataManager;
        this.mISchedulerProvider = ISchedulerProvider;
        this.mCompositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mCompositeDisposable.dispose();
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }

    public IDataManager getDataManager() {
        return mIDataManager;
    }

    public ISchedulerProvider getSchedulerProvider() {
        return mISchedulerProvider;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable.isDisposed() ? new CompositeDisposable() : mCompositeDisposable;
    }

    @Override
    public void handleApiError(ApiError error) {

        if (error == null) {
            getMvpView().onError(R.string.some_error);
            return;
        }

        getMvpView().onError(error.getMessage());
    }
}
