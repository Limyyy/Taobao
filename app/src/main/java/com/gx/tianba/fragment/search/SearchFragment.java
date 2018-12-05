package com.gx.tianba.fragment.search;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private RecyclerView search_rly;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);

        return view;
    }

    private void initView(View view) {
        search_rly = (RecyclerView) view.findViewById(R.id.search_rly);
    }
}
