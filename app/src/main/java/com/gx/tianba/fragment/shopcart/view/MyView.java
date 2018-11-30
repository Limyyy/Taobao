package com.gx.tianba.fragment.shopcart.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gx.tianba.R;

public class MyView extends LinearLayout {

    private TextView goodsnums;
    private ImageView jia;
    private ImageView jian;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.myview, this, true);
        jian = view.findViewById(R.id.jian);
        jia = view.findViewById(R.id.jia);
        goodsnums = view.findViewById(R.id.goods_nums);
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void setAddOnclickListener(OnClickListener onclickListener) {
        jia.setOnClickListener(onclickListener);
    }


    public void setJianOnclickListener(OnClickListener onclickListener) {
        jian.setOnClickListener(onclickListener);
    }


    public int getNum(){
        String trim = goodsnums.getText().toString().trim();
        return Integer.parseInt(trim);
    }
    public void setNum(int num){
        goodsnums.setText(""+num);
    }

}
