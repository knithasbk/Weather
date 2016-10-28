package com.example.tm.weather.View.MainAcivity.MainTabFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tm.weather.R;

/**
 * Created by TM on 28/10/2016.
 */

public class DiagramFragment extends Fragment {
    public DiagramFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.diagram_fragment,null);
        return view;
    }
}
