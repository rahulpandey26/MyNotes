package com.example.mynotes.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.mynotes.R;

public class BaseFragment extends Fragment {

    private int mLayoutId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        FrameLayout fragmentLayoutContainer = view.findViewById(R.id.layout_container);
        inflater.inflate(mLayoutId, fragmentLayoutContainer);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
    }

    protected void setLayout(int layoutId) {
        mLayoutId = layoutId;
    }
}
