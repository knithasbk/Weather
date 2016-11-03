package com.example.tm.weather.View.MainAcivity.MainTabFragments.DiagramFragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.example.tm.weather.Model.SettingsPreferenceProvider;
import com.example.tm.weather.Process.HandleLineGraph;
import com.example.tm.weather.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by TM on 28/10/2016.
 */

public class DiagramFragment extends Fragment {
    HandleLineGraph handleLineGraph = new HandleLineGraph();
    SettingsPreferenceProvider settingsPreferenceProvider = new SettingsPreferenceProvider();

    public DiagramFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.diagram_fragment, null);
        settingsPreferenceProvider = new SettingsPreferenceProvider();
        String[] collectionString = settingsPreferenceProvider.getMultiSelected(getActivity());


        /**
         * TODO: get Line status List<Integer> from settingsPreference, using it as input for handle.
         * settingsPreferenceProvider
         *  handle(list, view, this.getActivity());
         **/
        HashMap<String, String> listColor = new HashMap<>();
        listColor.put("1", "#d414b7");
        listColor.put("2", "#d71f0e");
        listColor.put("3", "#4cffaa00");
        listColor.put("4", "#783300ff");


        LineGraph lineGraph = (LineGraph) view.findViewById(R.id.graph);
        ArrayList<Line> lines = handleLineGraph.handle(collectionString, getActivity());
        int i = 1;
        LineGraph li = (LineGraph) view.findViewById(R.id.graph);
        for (Line line : lines) {
            String color = listColor.get(String.valueOf(i));
            line.setColor(Color.parseColor(color));
            li.addLine(line);
            li.setLineToFill(0);
            li.setRangeY(0, 120);
            i++;
        }


        return view;
    }
}