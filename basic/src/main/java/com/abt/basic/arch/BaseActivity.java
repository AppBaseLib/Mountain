package com.abt.basic.arch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;

import com.abt.mountain.R;

import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * Author: WeiQi
 * Date: 2019/4/29 16:30
 * Description: BaseActivity
 */
public abstract class BaseActivity<V extends BaseFragment<VM, TM>, VM extends BaseObservable & IViewModel, TM>
        extends AppCompatActivity
        implements ISupportActivity {

    private SupportActivityDelegate DELEGATE = new SupportActivityDelegate(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initContainer();
        if (enableRequestPermission()) {
            // TODO
        } else {
            V fragment = setRootFragment();
            if (savedInstanceState == null) {
                DELEGATE.loadRootFragment(R.id.container, fragment);
            }
        }
    }

    private boolean enableRequestPermission() {
        return false;
    }

    public abstract V setRootFragment();

    private void initContainer() {
        @SuppressLint("RestrictedApi")
        ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.container);
        setContentView(container);
    }

    @Override
    protected void onDestroy() {
        DELEGATE.onDestroy();
        super.onDestroy();
    }

    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
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
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public void post(Runnable runnable) {
        DELEGATE.post(runnable);
    }

    @Override
    public void onBackPressedSupport() {
        DELEGATE.onBackPressedSupport();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        DELEGATE.onBackPressed();
    }
}
