package com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.fragment;


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
import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.MyAddressFragment;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.bean.MyAddressUpdate;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.presenter.MyAddressUpdatePre;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.view.IMyAddressUpdate;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;
import com.lljjcoder.citypickerview.widget.CityPicker;


public class MyAddressUpdateFragment extends Fragment implements IMyAddressUpdate {

    private EditText my_address_update_name;
    private EditText my_address_update_phone;
    private TextView my_address_update_address;
    private EditText my_address_update_address_detail;
    private EditText my_address_update_postcode;
    private TextView my_address_update_save;
    private static MyAddress.ResultBean resultBean=new MyAddress.ResultBean();
    private MyAddressUpdatePre myAddressUpdatePre;
    private CityPicker cityPicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_address_update, container, false);
        initView(view);
        my_address_update_name.setText(resultBean.getRealName());
        my_address_update_phone.setText(resultBean.getPhone());
        my_address_update_address.setText(resultBean.getAddress());
        my_address_update_address_detail.setText(resultBean.getAddress());
        my_address_update_postcode.setText(resultBean.getZipCode());
        myAddressUpdatePre = new MyAddressUpdatePre(this);
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
        my_address_update_address.setOnClickListener(new View.OnClickListener() {
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
                        my_address_update_address.setText(province+" "+city+" "+district);
                        my_address_update_postcode.setText(code);
                        my_address_update_address_detail.setText("");
                    }

                    @Override
                    public void onCancel() {
                    }
                });
            }
        });

        //修改
        my_address_update_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        return view;
    }

    private void initView(View view) {
        my_address_update_name = (EditText) view.findViewById(R.id.my_address_update_name);
        my_address_update_phone = (EditText) view.findViewById(R.id.my_address_update_phone);
        my_address_update_address = (TextView) view.findViewById(R.id.my_address_update_address);
        my_address_update_address_detail = (EditText) view.findViewById(R.id.my_address_update_address_detail);
        my_address_update_postcode = (EditText) view.findViewById(R.id.my_address_update_postcode);
        my_address_update_save = (TextView) view.findViewById(R.id.my_address_update_save);
    }
    private void submit() {
        // validate
        String name = my_address_update_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(getActivity(), "收货人姓名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String phone = my_address_update_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(getActivity(), "电话不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String detail = my_address_update_address_detail.getText().toString().trim();
        if (TextUtils.isEmpty(detail)) {
            Toast.makeText(getActivity(), "最少写5个字", Toast.LENGTH_SHORT).show();
            return;
        }

        String postcode = my_address_update_postcode.getText().toString().trim();
        if (TextUtils.isEmpty(postcode)) {
            Toast.makeText(getActivity(), "邮政编码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Login.ResultBean spData = SpUtil.getSpData();
        String trim = my_address_update_address.getText().toString().trim();
        myAddressUpdatePre.setPreMyAddressUpdate(spData.getUserId(),spData.getSessionId(),name,phone,trim+" "+detail,postcode);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //使用静态方法传值
    public static void getInstace(MyAddress.ResultBean resultBean1) {
        resultBean=resultBean1;
    }


    @Override
    public void onSuccess(MyAddressUpdate myAddressUpdate) {
        ToastUtil.Toast(myAddressUpdate.getMessage());
    }

    @Override
    public void onFailer(String msg) {
        ToastUtil.Toast(msg);
    }
}
