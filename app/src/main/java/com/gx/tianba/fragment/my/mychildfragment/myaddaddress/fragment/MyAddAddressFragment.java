package com.gx.tianba.fragment.my.mychildfragment.myaddaddress.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.bean.MyAddAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.presenter.MyAddAddressPre;
import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.view.IMyAddAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.MyAddressFragment;
import com.gx.tianba.fragment.my.mymainfragment.MyMainFragment;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;
import com.lljjcoder.citypickerview.widget.CityPicker;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAddAddressFragment extends Fragment implements IMyAddAddress {

    private EditText my_add_address_name;
    private EditText my_add_address_phone;
    private TextView my_add_address_address;
    private EditText my_add_address_address_detail;
    private EditText my_add_address_postcode;
    private TextView my_add_address_save;
    private CityPicker cityPicker;
    private MyAddAddressPre myAddAddressPre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_add_address, container, false);
        initView(view);
        //初始化选择城市控件
        cityPicker = new CityPicker.Builder(getActivity())
                .textSize(20)//滚轮文字的大小
                .title("城市选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#0CB6CA")
                .titleTextColor("#000000")
                .backgroundPop(0xa0000000)
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .province("xx省")
                .city("xx市")
                .district("xx区")
                .textColor(Color.parseColor("#000000"))//滚轮文字的颜色
                .provinceCyclic(true)//省份滚轮是否循环显示
                .cityCyclic(false)//城市滚轮是否循环显示
                .districtCyclic(false)//地区（县）滚轮是否循环显示
                .visibleItemsCount(7)//滚轮显示的item个数
                .itemPadding(10)//滚轮item间距
                .onlyShowProvinceAndCity(false)
                .build();
        //弹出选择城市并赋值
        my_add_address_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPicker.show();
                cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                    @Override
                    public void onSelected(String... strings) {
                        //省份
                        String province=strings[0];
                        //城市
                        String city=strings[1];
                        //区县
                        String district=strings[2];
                        //邮编
                        String code=strings[3];
                        //给地址和邮编赋值
                        my_add_address_address.setText(province+" "+city+" "+district);
                        my_add_address_postcode.setText(code);
                    }

                    @Override
                    public void onCancel() {
                        my_add_address_address.setText("");
                        my_add_address_postcode.setText("");
                    }
                });
            }
        });
        myAddAddressPre = new MyAddAddressPre(this);

        //点击提交保存添加新地址
        my_add_address_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        return view;
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
                    fragmentTransaction.replace(R.id.my_framelayout, new MyAddressFragment());
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
        my_add_address_name = (EditText) view.findViewById(R.id.my_add_address_name);
        my_add_address_phone = (EditText) view.findViewById(R.id.my_add_address_phone);
        my_add_address_address = (TextView) view.findViewById(R.id.my_add_address_address);
        my_add_address_address_detail = (EditText) view.findViewById(R.id.my_add_address_address_detail);
        my_add_address_postcode = (EditText) view.findViewById(R.id.my_add_address_postcode);
        my_add_address_save = (TextView) view.findViewById(R.id.my_add_address_save);
    }

    private void submit() {
        // validate
        String name = my_add_address_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(), "收件人不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = my_add_address_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getActivity(), "电话不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String postcode = my_add_address_postcode.getText().toString().trim();
        if (TextUtils.isEmpty(postcode)) {
            Toast.makeText(getActivity(), "邮编不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String address_detail = my_add_address_address_detail.getText().toString().trim();
        if (TextUtils.isEmpty(address_detail)) {
            Toast.makeText(getActivity(), "详细地址必须写6个字不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Login.ResultBean spData = SpUtil.getSpData();
        String address = my_add_address_address.getText().toString().trim();
        String addressdetail = my_add_address_address_detail.getText().toString().trim();
        myAddAddressPre.setPreMyAddAddressUrl(spData.getUserId(),spData.getSessionId(),name,phone,address+" "+addressdetail,postcode);

    }

    @Override
    public void onSuccess(MyAddAddress myAddAddress) {
        String status = myAddAddress.getStatus();
        if (status.equals("0000")){
            ToastUtil.Toast(myAddAddress.getMessage());
            FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.my_framelayout, new MyAddressFragment());
            fragmentTransaction.commit();
        }
        else {
            ToastUtil.Toast(myAddAddress.getMessage());
            my_add_address_name.setText("");
            my_add_address_phone.setText("");
             my_add_address_address.setText("");
             my_add_address_address_detail.setText("");
             my_add_address_postcode.setText("");
        }
    }
}
