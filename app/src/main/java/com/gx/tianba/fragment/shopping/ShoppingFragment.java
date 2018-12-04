package com.gx.tianba.fragment.shopping;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {


     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_shopping, container, false);
         return view;
    }

}
