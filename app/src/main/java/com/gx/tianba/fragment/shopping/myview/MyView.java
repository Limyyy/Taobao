package com.gx.tianba.fragment.shopping.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gx.tianba.R;

public class MyView extends LinearLayout {
    private TextView num;
    private TextView jia;
    private TextView jian;
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context,  AttributeSet attrs) {
        this(context,attrs,0);

    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.jiajian, this);
        jia = findViewById(R.id.jia);
        jian=findViewById(R.id.jian);
        num=findViewById(R.id.num);
    }
    public void setNum(int str) {
        num.setText(""+str);
    }

    public int getNum() {
        return Integer.parseInt(num.getText().toString());
    }

    //接口回调
    public void jiaOnclickListener(OnClickListener onclickListener) {
        jia.setOnClickListener(onclickListener);
    }

    //接口回调
    public void jianOnclickListener(OnClickListener onclickListener) {
        jian.setOnClickListener(onclickListener);
    }
}
