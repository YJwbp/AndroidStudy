package com.bpwei.examples.fragment;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wbp on 2017/1/15.
 */

public class BaseFragment extends Fragment {

    protected String TAG = getClass().getSimpleName();
    Unbinder unbinder;
    boolean hasUsedBindView = false;
    @Nullable
    @Override
    @CallSuper
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "Fragment onCreateView");
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(onCreateFragmentFromThisLayout(), container, false);
        unbinder = ButterKnife.bind(this,view);
        hasUsedBindView = true;
        return view;
    }

    int onCreateFragmentFromThisLayout(){
        throw new UnsupportedOperationException("must have a valid fragment layout");
    }

    @Override
    public void onDestroyView() {
        Log.d(TAG, "Fragment onDestroyView");
        if (!hasUsedBindView) throw new UnsupportedOperationException("Must use super OnCreateView");
        super.onDestroyView();
        unbinder.unbind();
    }
}
