package com.abt.basic.arch;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import androidx.core.app.Fragment;
import androidx.core.app.FragmentActivity;
import androidx.databinding.BaseObservable;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Author: WeiQi
 * Date: 2019/4/29 17:16
 * Description:
 */
public abstract class BaseFragment<VM extends BaseObservable & IViewModel, TM>
        extends Fragment
        implements IView<VM, TM>, ISupportFragment {

    private final SupportFragmentDelegate DELEGATE = new SupportFragmentDelegate(this);
    protected FragmentActivity _mActivity;

    protected TM mToolbarViewModel;
    protected VM mViewModel;

    @Override
    public void setViewModel(VM viewModel) {
        this.mViewModel = viewModel;
    }

    @Override
    public void setToolbarViewModel(TM toolbarViewModel) {
        this.mToolbarViewModel = toolbarViewModel;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //EventBus.getDefault().register(this);
        DELEGATE.onAttach((Activity) context);
        _mActivity = DELEGATE.getActivity();
    }

    //@Subscribe(threadMode = ThreadMode.MAIN)
    public void onResponse(String str) { }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
    }

    protected abstract VM createViewModel();

    protected abstract TM createToolbarViewModel();

    private VM findOrCreateVieModel() {
        VM viewModel = createViewModel();
        if (this instanceof INavigator) {
            viewModel.setNavigator((INavigator) this);
        }
        return viewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
        mToolbarViewModel = createToolbarViewModel();
        if (this instanceof INavigator) {
            ((IViewModel)mToolbarViewModel).setNavigator((INavigator) this);
        }
        this.setViewModel(findOrCreateVieModel());
        if (null != mToolbarViewModel) {
            this.setToolbarViewModel(mToolbarViewModel);
        }
    }

    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public void enqueueAction(Runnable runnable) {
        DELEGATE.enqueueAction(runnable);
    }

    @Override
    public void post(Runnable runnable) {
        DELEGATE.post(runnable);
    }

    @Override
    public void onEnterAnimationEnd(@Nullable Bundle savedInstanceState) {
        DELEGATE.onEnterAnimationEnd(savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        DELEGATE.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onSupportVisible() {
        DELEGATE.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        DELEGATE.onSupportVisible();
    }

    @Override
    public boolean isSupportVisible() {
        return DELEGATE.isSupportVisible();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        DELEGATE.setFragmentResult(resultCode, bundle);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        DELEGATE.onFragmentResult(requestCode, resultCode, data);
    }

    @Override
    public void onNewBundle(Bundle args) {
        DELEGATE.onNewBundle(args);
    }

    @Override
    public void putNewBundle(Bundle newBundle) {
        DELEGATE.putNewBundle(newBundle);
    }

    @Override
    public boolean onBackPressedSupport() {
        return true;
    }

    public final BaseActivity getProxyActivity() {
        return (BaseActivity) _mActivity;
    }
}
