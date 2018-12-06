package com.gx.tianba.fragment.home.homechildfragment;


import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.gx.tianba.R;
import com.gx.tianba.fragment.home.bean.Home;
import com.gx.tianba.fragment.home.homechildfragment.adapter.HomeChildAdapter;
import com.gx.tianba.fragment.home.homechildfragment.bean.HomeChildBean;
import com.gx.tianba.fragment.home.homechildfragment.presenter.HomeChildPresenter;
import com.gx.tianba.fragment.home.homechildfragment.view.IHomeChildView;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.net.HttpUrl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.List;

/**
 * 根据商品列表归属标签查询商品信息
 */
public class HomeChildFragment extends Fragment implements IHomeChildView {

    private ShowHomeMainFragment showHomeMainFragment;
    private ImageView home_child_Menu;
    private EditText home_child_edit;
    private TextView home_child_sousuo;
    private TextView home_child_name;
    private RecyclerView home_child_ryl;
    private HomeChildPresenter homeChildPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_child_home, container, false);
        initView(view);
        EventBus.getDefault().register(this);

        //点击通过接口回调返回
        home_child_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHomeMainFragment.SetShow();
            }
        });
        //连接P层
        homeChildPresenter = new HomeChildPresenter(this);
        return view;
    }

    //热销新品的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setVieRxxpwData(List<Home.ResultBean.RxxpBean> rxxp){
        Log.d("-----",rxxp+"");
        //标题
        home_child_name.setText(rxxp.get(0).getName());
        //背景
        Drawable drawable=getActivity().getResources().getDrawable(R.mipmap.rxxp_bitmap);
        home_child_name.setBackground(drawable);
        //文字颜色
        home_child_name.setTextColor(Color.parseColor("#ff7f57"));
        //发送请求
        homeChildPresenter.setPreUrlGet(HttpUrl.HOMECHILD,1002,1,5);
    }
    //魔力时尚的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setViewMlssData(List<Home.ResultBean.MlssBean> mlss){
        Log.d("-----",mlss+"");
        //标题
        home_child_name.setText(mlss.get(0).getName());
        //背景
        Drawable drawable=getActivity().getResources().getDrawable(R.mipmap.mlss_bitmap);
        home_child_name.setBackground(drawable);
        //文字颜色
        home_child_name.setTextColor(Color.parseColor("#608ed9"));
        //发送请求
        homeChildPresenter.setPreUrlGet(HttpUrl.HOMECHILD,1003,1,5);
    }
    //品质生活的数据
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setViewPzshData(List<Home.ResultBean.PzshBean> pzsh ){
        //标题
        home_child_name.setText(pzsh.get(0).getName());
        //背景
        Drawable drawable=getActivity().getResources().getDrawable(R.mipmap.pzsh_bitmap);
        home_child_name.setBackground(drawable);
        //文字颜色
        home_child_name.setTextColor(Color.parseColor("#ff7f8d"));
        //发送请求
        homeChildPresenter.setPreUrlGet(HttpUrl.HOMECHILD,1004,1,5);
    }


    //find  view
    private void initView(View view) {
        home_child_Menu = (ImageView) view.findViewById(R.id.home_child_Menu);
        home_child_edit = (EditText) view.findViewById(R.id.home_child_edit);
        home_child_sousuo = (TextView) view.findViewById(R.id.home_child_sousuo);
        home_child_name = (TextView) view.findViewById(R.id.home_child_name);
        home_child_ryl = (RecyclerView) view.findViewById(R.id.home_child_ryl);
    }

    //搜索方法
    private void submit() {
        // validate
        String edit = home_child_edit.getText().toString().trim();
        if (TextUtils.isEmpty(edit)) {
            Toast.makeText(getActivity(), "请输入你要搜索的商品", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    //EventBus反注册
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    //接口回调到最大的Fragment来切换fragment
    public void SetShowHomeMainFragment(ShowHomeMainFragment showHomeMainFragment1){
        this.showHomeMainFragment=showHomeMainFragment1;
    }

    //请求成功显示数据
    @Override
    public void OnSuccess(HomeChildBean homeChildBean) {
        //设置适配器
        //设置RecyclerView的布局
        home_child_ryl.setLayoutManager(new GridLayoutManager(getActivity(),2));
        HomeChildAdapter homeChildAdapter = new HomeChildAdapter(getActivity(), homeChildBean.getResult());
        home_child_ryl.setAdapter(homeChildAdapter);
    }

    //请求失败吐司
    @Override
    public void OnFailer(String msg) {
        ToastUtil.Toast(msg);
    }

    public interface ShowHomeMainFragment{
        void SetShow();
    }
}
