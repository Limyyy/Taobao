package com.gx.tianba.fragment.list;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.list.listchild.alllist.fragment.AllListFragment;
import com.gx.tianba.fragment.list.listchild.completed.CompletedFragment;
import com.gx.tianba.fragment.list.listchild.waitevaluate.WaitEvalueteFragment;
import com.gx.tianba.fragment.list.listchild.waitpay.WaitPayFragment;
import com.gx.tianba.fragment.list.listchild.waitreceive.WaitReceiveFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private TextView list_all_list;
    private TextView list_wait_for_pay;
    private TextView list_wait_for_receive;
    private TextView list_wait_for_evaluate;
    private TextView list_completed;
    private FrameLayout list_framelayout;
    private AllListFragment allListFragment;
    private WaitPayFragment waitPayFragment;
    private WaitReceiveFragment waitReceiveFragment;
    private WaitEvalueteFragment waitEvalueteFragment;
    private CompletedFragment completedFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initView(view);
        allListFragment = new AllListFragment();
        waitPayFragment = new WaitPayFragment();
        waitReceiveFragment = new WaitReceiveFragment();
        waitEvalueteFragment = new WaitEvalueteFragment();
        completedFragment = new CompletedFragment();

        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.list_framelayout,allListFragment);
        fragmentTransaction.add(R.id.list_framelayout,waitPayFragment);
        fragmentTransaction.add(R.id.list_framelayout,waitReceiveFragment);
        fragmentTransaction.add(R.id.list_framelayout,waitEvalueteFragment);
        fragmentTransaction.add(R.id.list_framelayout,completedFragment);
        fragmentTransaction.hide(waitPayFragment);
        fragmentTransaction.hide(waitReceiveFragment);
        fragmentTransaction.hide(waitEvalueteFragment);
        fragmentTransaction.hide(completedFragment);
        fragmentTransaction.show(allListFragment);
        fragmentTransaction.commit();

        //点击切换fragment
        list_all_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.hide(waitPayFragment);
                fragmentTransaction.hide(waitReceiveFragment);
                fragmentTransaction.hide(waitEvalueteFragment);
                fragmentTransaction.hide(completedFragment);
                fragmentTransaction.show(allListFragment);
                fragmentTransaction.commit();
            }
        });

        list_wait_for_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.show(waitPayFragment);
                fragmentTransaction.hide(waitReceiveFragment);
                fragmentTransaction.hide(waitEvalueteFragment);
                fragmentTransaction.hide(completedFragment);
                fragmentTransaction.hide(allListFragment);
                fragmentTransaction.commit();
            }
        });

        list_wait_for_receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.hide(waitPayFragment);
                fragmentTransaction.show(waitReceiveFragment);
                fragmentTransaction.hide(waitEvalueteFragment);
                fragmentTransaction.hide(completedFragment);
                fragmentTransaction.hide(allListFragment);
                fragmentTransaction.commit();
            }
        });

        list_wait_for_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.hide(waitPayFragment);
                fragmentTransaction.hide(waitReceiveFragment);
                fragmentTransaction.show(waitEvalueteFragment);
                fragmentTransaction.hide(completedFragment);
                fragmentTransaction.hide(allListFragment);
                fragmentTransaction.commit();
            }
        });

        list_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.hide(waitPayFragment);
                fragmentTransaction.hide(waitReceiveFragment);
                fragmentTransaction.hide(waitEvalueteFragment);
                fragmentTransaction.show(completedFragment);
                fragmentTransaction.hide(allListFragment);
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    private void initView(View view) {
        list_all_list = (TextView) view.findViewById(R.id.list_all_list);
        list_wait_for_pay = (TextView) view.findViewById(R.id.list_wait_for_pay);
        list_wait_for_receive = (TextView) view.findViewById(R.id.list_wait_for_receive);
        list_wait_for_evaluate = (TextView) view.findViewById(R.id.list_wait_for_evaluate);
        list_completed = (TextView) view.findViewById(R.id.list_completed);
        list_framelayout = (FrameLayout) view.findViewById(R.id.list_framelayout);
    }
}
