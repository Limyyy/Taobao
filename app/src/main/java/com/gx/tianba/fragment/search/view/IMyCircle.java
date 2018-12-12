package com.gx.tianba.fragment.search.view;

import com.gx.tianba.fragment.search.bean.MyCircle;

public interface IMyCircle {
    void onSuccess(MyCircle myCircle);
    void onFailer(String msg);
    void onGreatSuccess(MyCircle myCircle);
    void onNoGreatSuccess(MyCircle myCircle);
}
