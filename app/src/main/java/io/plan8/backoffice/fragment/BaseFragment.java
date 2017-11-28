package io.plan8.backoffice.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by SSozi on 2017. 11. 28..
 */

public abstract class BaseFragment extends Fragment {
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("lifeCycle", "Fragment onAttach :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("lifeCycle", "Fragment onCreate :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("lifeCycle", "Fragment onCreateView :: " + getClass().getSimpleName() + "  ::  " + hashCode());
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("lifeCycle", "Fragment onActivityCreated :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("lifeCycle", "Fragment onStart :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("lifeCycle", "Fragment onResume :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("lifeCycle", "Fragment onPause :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("lifeCycle", "Fragment onStop :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("lifeCycle", "Fragment onDestroyView :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("lifeCycle", "Fragment onDestroy :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("lifeCycle", "Fragment onDetach :: " + getClass().getSimpleName() + "  ::  " + hashCode());
    }
}