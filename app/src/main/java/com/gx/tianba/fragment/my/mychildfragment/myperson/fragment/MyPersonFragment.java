package com.gx.tianba.fragment.my.mychildfragment.myperson.fragment;


import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.up.ImageUtil;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.up.bean.MyPersonUpImage;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.up.presenter.MyPersonUpImagePre;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.up.view.IMyPersonUpImage;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatename.fragment.MyPersonUpdateNameFragment;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatepwd.fragment.MyPresonUpdatePwdFragment;
import com.gx.tianba.fragment.my.mymainfragment.MyMainFragment;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.fresco.FrescoUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPersonFragment extends Fragment implements IMyPersonUpImage {

    private SimpleDraweeView chile_my_person_image;
    private TextView chile_my_person_name;
    private TextView chile_my_person_password;
    private RelativeLayout chile_my_person_image_up;
    private RelativeLayout chile_my_person_name_update;
    private RelativeLayout chile_my_person_password_update;
    private View viewAlert;
    private TextView photo,camera;
    private AlertDialog dialog;
    private File file;
    private MyPersonUpImagePre myPersonUpImagePre;
    private int userId;
    private String sessionId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_person, container, false);
        initView(view);
        //弹框
        viewAlert= View.inflate(getActivity(), R.layout.my_person_up_image_alert, null);
        photo = viewAlert.findViewById(R.id.my_person_up_image_alert_photo);
        camera = viewAlert.findViewById(R.id.my_person_up_image_alert_camera);
        //得到数据
        Login.ResultBean bean = SpUtil.getSpData();
        //设置数据
        //图片
        FrescoUtil.load(bean.getHeadPic(), chile_my_person_image);
        //名字
        chile_my_person_name.setText(bean.getNickName());
        //密码
        chile_my_person_password.setText(bean.getPassword());
        //创建弹框
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(viewAlert);
        dialog = builder.create();
        //点击昵称修改昵称
        chile_my_person_name_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.my_framelayout, new MyPersonUpdateNameFragment(),"UpdateName");
                fragmentTransaction.addToBackStack("UpdateName");
                fragmentTransaction.commit();
            }
        });

        //点击修改密码
        chile_my_person_password_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.my_framelayout, new MyPresonUpdatePwdFragment(),"UpdatePwd");
                fragmentTransaction.addToBackStack("UpdatePwd");
                fragmentTransaction.commit();
            }
        });
        //点击更换头像
        chile_my_person_image_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹框
                dialog.show()   ;
                WindowManager m = getActivity().getWindowManager();
                Display d = m.getDefaultDisplay();  //为获取屏幕宽、高
                android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
                p.width = (int) (d.getWidth());    //宽度设置为屏幕的0.5
                dialog.getWindow().setAttributes(p);
            }
        });
        //照相
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //相册
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,0);
            }
        });
        myPersonUpImagePre = new MyPersonUpImagePre(this);
        Login.ResultBean spData = SpUtil.getSpData();
        userId = spData.getUserId();
        sessionId = spData.getSessionId();
        return view;
    }

    //find view
    private void initView(View view) {
        chile_my_person_image = (SimpleDraweeView) view.findViewById(R.id.chile_my_person_image);
        chile_my_person_name = (TextView) view.findViewById(R.id.chile_my_person_name);
        chile_my_person_password = (TextView) view.findViewById(R.id.chile_my_person_password);
        chile_my_person_image_up = (RelativeLayout) view.findViewById(R.id.chile_my_person_image_up);
        chile_my_person_name_update = (RelativeLayout) view.findViewById(R.id.chile_my_person_name_update);
        chile_my_person_password_update = (RelativeLayout) view.findViewById(R.id.chile_my_person_password_update);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                //打开手机的相册
                Uri uri = data.getData();
                //得到图片的绝对路径
                String path1 = ImageUtil.getPath(getActivity(), uri);
                //上传头像
                myPersonUpImagePre.setPreMyPersonUpImageUrl(userId,sessionId,file);
                break;
        }
    }

    @Override
    public void onSuccess(MyPersonUpImage myPersonUpImage) {
        //更新本页我的头像
        FrescoUtil.load(myPersonUpImage.getHeadPath(),chile_my_person_image);
        //保存到数据库中我的头像地址
        SpUtil.updateHeadPic(myPersonUpImage.getHeadPath());
        ToastUtil.Toast(myPersonUpImage.getMessage());
    }

    @Override
    public void onFailer(String msg) {
        ToastUtil.Toast(msg);
    }

}
