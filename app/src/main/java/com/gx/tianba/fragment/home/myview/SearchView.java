package com.gx.tianba.fragment.home.myview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gx.tianba.R;

public class SearchView extends LinearLayout {
    /**
     * 输入框
     */
    private EditText etInput;

    /**
     *
     * 搜索*/
    private TextView startsearch;
    /**
     * 点击图标
     */
    private ImageView homesearch;
    public SearchView(Context context) {
        this(context,null);
    }

    public SearchView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.my_view_search, this);
        etInput = view.findViewById(R.id.my_view_search_edittext);
        startsearch =view.findViewById(R.id.my_view_search_button);
        homesearch=view.findViewById(R.id.search_iamge);
    }


    //平移过来显示
    public void setShowAnimation(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(this,"translationX",590);
       animator.setDuration(1000);
       animator.start();
    }
    //平移回去隐藏
    public void setHideAnimation(){
        ObjectAnimator animator=ObjectAnimator.ofFloat(this,"translationX",0);
        animator.setDuration(1000);
        animator.start();
    }
    public ImageView GetView(){
        return homesearch;
    }
    //得到输入的内容
    public String getSearchText(){
        return etInput.getText().toString().trim();
    }
}
