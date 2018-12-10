package com.gx.tianba.fragment.my.mychildfragment.mywallet.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.mywallet.bean.MyWallet;
import com.gx.tianba.fragment.my.mychildfragment.mywallet.presenter.MyWalletPresenter;
import com.gx.tianba.fragment.my.mychildfragment.mywallet.view.IMyWallet;
import com.gx.tianba.fragment.my.mymainfragment.MyMainFragment;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.sp.SpUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyWalletFragment extends Fragment implements IMyWallet {
    private TextView my_wallet_price;
    private RecyclerView my_wallet_ryl;
    private MyWalletPresenter myWalletPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_wallet, container, false);
        //find view
        initView(view);
        myWalletPresenter = new MyWalletPresenter(this);
        Login.ResultBean spData = SpUtil.getSpData();
        myWalletPresenter.setPreMyWalletUrl(spData.getUserId(),spData.getSessionId(),1,5);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myWalletPresenter.onDestory();
    }

    /**
     * 返回键重定义
     */
    private void getFocus() {
        //设置焦点联系方式(正确的)
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.my_framelayout, new MyMainFragment());
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getFocus();
    }

    private void initView(View view) {
        my_wallet_price = (TextView) view.findViewById(R.id.my_wallet_price);
        my_wallet_ryl = (RecyclerView) view.findViewById(R.id.my_wallet_ryl);
    }

    @Override
    public void onSuccess(MyWallet myWallet) {
        MyWallet.ResultBean result = myWallet.getResult();
        my_wallet_price.setText(""+result.getBalance());
    }
}
