package com.gx.tianba.fragment.options;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gx.tianba.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionsFragment extends Fragment {

    private ImageView option_back;
    private SimpleDraweeView option_image;
    private TextView option_name;
    private TextView option_sex;
    private TextView option_details;
    private TextView option_uppassword;
    private TextView option_clear_cache;
    private TextView option_soft_gradle;
    private TextView option_idea;
    private TextView option_about_weidu;
    private LinearLayout option_service_tel;
    private TextView cancle_hit_service_tel;
    private TextView hit_service_tel;
    private PopupWindow popupWindow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_options, container, false);
        //find view
        initView(view);
        //拨打客服电话弹框
        option_service_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = View.inflate(getActivity(), R.layout.service_tel_alert, null);
                popupWindow = new PopupWindow(inflate,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                hit_service_tel = inflate.findViewById(R.id.hit_service_tel);
                cancle_hit_service_tel = inflate.findViewById(R.id.cancle_hit_service_tel);
                popupWindow.setOutsideTouchable(false);
                /*popupWindow.setTouchable(true);*/
                //底部弹出
                popupWindow.showAtLocation(v,Gravity.BOTTOM,0,0);
                //点击取消打电话则取消
                cancle_hit_service_tel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
            }
        });
        //单击打电话则打电话

        return view;
    }

    private void initView(View view) {
        option_back =  view.findViewById(R.id.option_back);
        option_image =  view.findViewById(R.id.option_image);
        option_name = view.findViewById(R.id.option_name);
        option_sex =  view.findViewById(R.id.option_sex);
        option_details =  view.findViewById(R.id.option_details);
        option_uppassword =  view.findViewById(R.id.option_uppassword);
        option_clear_cache = view.findViewById(R.id.option_clear_cache);
        option_soft_gradle =view.findViewById(R.id.option_soft_gradle);
        option_idea = view.findViewById(R.id.option_idea);
        option_about_weidu =  view.findViewById(R.id.option_about_weidu);
        option_service_tel =  view.findViewById(R.id.option_service_tel);
    }
}
