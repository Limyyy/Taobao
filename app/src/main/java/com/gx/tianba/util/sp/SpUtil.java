package com.gx.tianba.util.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.gx.tianba.App;
import com.gx.tianba.login.bean.Login;

import static android.content.Context.MODE_PRIVATE;

/**
 * 封装Sp工具类
 */
public class SpUtil {
    static Context context=App.TAG;
    private static SharedPreferences sp;
    private static Login.ResultBean result;
    public static  void initSp(){
        sp = context.getSharedPreferences("login", MODE_PRIVATE);
        result=new Login.ResultBean();
    }
    public static void putSpData(Login login,boolean islogin,String name,String password){
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean("islogin", true);
        edit.putString("name", name);
        edit.putString("password", password);

        //Login里面的数据
        edit.putInt("userId", login.getResult().getUserId());
        edit.putString("sessionId", login.getResult().getSessionId());
        edit.putString("headPic", login.getResult().getHeadPic());
        edit.putString("nickName", login.getResult().getNickName());
        edit.putInt("sex",login.getResult().getSex());
        edit.putString("phone",login.getResult().getPhone());
        //提交保存
        edit.commit();
    }
    //得到数据
    public static Login.ResultBean getSpData(){
        int userId = sp.getInt("userId",-1);
        String sessionId = sp.getString("sessionId", "");
        String headPic = sp.getString("headPic", "");
        String nickName = sp.getString("nickName","");
        int sex = sp.getInt("sex", -1);
        String phone = sp.getString("phone", "");
        //保存数据
        result.setUserId(userId);
        result.setSessionId(sessionId);
        result.setHeadPic(headPic);
        result.setNickName(nickName);
        result.setSex(sex);
        result.setPhone(phone);
        return result;
    }
}
