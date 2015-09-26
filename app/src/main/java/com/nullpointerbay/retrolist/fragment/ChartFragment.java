package com.nullpointerbay.retrolist.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.nullpointerbay.retrolist.R;
import com.nullpointerbay.retrolist.component.AppComponent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChartFragment extends BaseFragment {

    @Bind(R.id.chart_billings)
    PieChart chartBillings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_chart, container, false);
        ButterKnife.bind(this, view);
        bindChart();
        return view;
    }

    private void bindChart() {

        chartBillings.setHoleRadius(30f);
        chartBillings.setDescription("");
        chartBillings.setHoleColorTransparent(true);
        chartBillings.setRotationAngle(0f);
        chartBillings.setRotationEnabled(true);
        chartBillings.animateY(1500, Easing.EasingOption.EaseInOutQuad);

        createDummyData();

        Legend legend = chartBillings.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(0f);
        legend.setYOffset(0f);
    }

    private void createDummyData() {
        List<Entry> yVals = new ArrayList<>();
        yVals.add(new Entry(30f, 0));
        yVals.add(new Entry(50f, 1));
        yVals.add(new Entry(10f, 2));
        yVals.add(new Entry(10f, 3));

        List<String> xVals = new ArrayList<>();
        xVals.add("Food");
        xVals.add("Electronics");
        xVals.add("Parties");
        xVals.add("Bills");

        PieDataSet dataSet = new PieDataSet(yVals, "Categories");
        dataSet.setSliceSpace(2f);
        dataSet.setSelectionShift(3f);

        List<Integer> colors = new ArrayList<>();

//        for (int c : ColorTemplate.VORDIPLOM_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.JOYFUL_COLORS)
//            colors.add(c);
//
//        for (int c : ColorTemplate.COLORFUL_COLORS)
//            colors.add(c);

//        for (int c : ColorTemplate.LIBERTY_COLORS)
//            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        dataSet.setColors(colors);

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(tf);
        chartBillings.setData(data);
        chartBillings.highlightValues(null);
        chartBillings.invalidate();
    }

    @Override
    protected void setupComponent(AppComponent component) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
