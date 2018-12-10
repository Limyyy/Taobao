package com.gx.tianba.fragment.home.adapter;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.home.PicsoImageLoader;
import com.gx.tianba.fragment.home.adapter.homemainviewpager.HomeMainViewPagerAdapter;
import com.gx.tianba.fragment.home.adapter.homemainviewpager.RotationPageTransformer;
import com.gx.tianba.fragment.home.bean.Home;
import com.youth.banner.Banner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeMainAdapter extends RecyclerView.Adapter{

    private Context context;
    private List<com.gx.tianba.fragment.home.bean.Banner.ResultBean> bannerresult;
    private Home.ResultBean result;
    private final int TYPE_ONE=0;
    private final int TYPE_TWO=1;
    private final int TYPE_THREE=2;
    private final int TYPE_FOUR=3;
    private MenuClickListner menuClickListner;
    private ArrayList<String> banners;
    private Handler handler;

    public HomeMainAdapter(MenuClickListner menuClickListner1,Context context, Home.ResultBean result,List<com.gx.tianba.fragment.home.bean.Banner.ResultBean> bannerresult) {
        this.context = context;
        this.menuClickListner=menuClickListner1;
        this.result = result;
        this.bannerresult=bannerresult;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //banner
        if (getItemViewType(i)==TYPE_ONE){
            View view = LayoutInflater.from(context).inflate(R.layout.home_banner_adapter, null);
            return new BannerViewHolder(view);
        }
        //热销新品
        else if (getItemViewType(i)==TYPE_TWO){
            View view = View.inflate(context, R.layout.home_rxxp_adapter, null);
            return new RxxpViewHolder(view);
        }
        //魔力时尚
        else if (getItemViewType(i)==TYPE_THREE){
            View view = View.inflate(context, R.layout.home_mlss_adapter, null);
            return new MlssViewHolder(view);
        }
        //品质生活
        else {
            View view = View.inflate(context, R.layout.home_pzsh_adapter, null);
            return new PzshViewHolder(view);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //banner
        if (getItemViewType(i)==TYPE_ONE){
            final BannerViewHolder bannerViewHolder= (BannerViewHolder) viewHolder;
            banners = new ArrayList<>();
            for (int i1 = 0; i1 < bannerresult.size(); i1++) {
                banners.add(bannerresult.get(i1).getImageUrl());
            }
            bannerViewHolder.viewPager.setAdapter(new HomeMainViewPagerAdapter(banners,context));
            bannerViewHolder.viewPager.setOffscreenPageLimit(banners.size());
            bannerViewHolder.viewPager.setPageMargin(-160);//控制两幅图之间的间距,尽量以屏幕的宽度来确定
            bannerViewHolder.viewPager.setPageTransformer(true,new RotationPageTransformer());
            HomeMainViewPagerAdapter homeMainViewPagerAdapter = new HomeMainViewPagerAdapter(banners,context);
            bannerViewHolder.viewPager.setAdapter(homeMainViewPagerAdapter);
            //viewPager左右两边滑动无效的处理
            bannerViewHolder.linearLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return bannerViewHolder.viewPager.dispatchTouchEvent(motionEvent);
                }
            });
            handler.sendEmptyMessageDelayed(0,2000);
        }
        //热销新品
        else if (getItemViewType(i)==TYPE_TWO){
            RxxpViewHolder rxxpViewHolder= (RxxpViewHolder) viewHolder;
            List<Home.ResultBean.RxxpBean> rxxp = result.getRxxp();
            Home.ResultBean.RxxpBean rxxpBean = rxxp.get(0);
            //得到里面的数据
            String name = rxxpBean.getName();
            List<Home.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxpBean.getCommodityList();
            //设置数据和适配器
            rxxpViewHolder.rxxptext.setText(name);
            rxxpViewHolder.rxxpryl.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,true));
            RxxpAdapter rxxpAdapter = new RxxpAdapter(commodityList,context);
            rxxpViewHolder.rxxpryl.setAdapter(rxxpAdapter);
            DividerItemDecoration dividerItemDecoratio=new DividerItemDecoration(context,1);
            dividerItemDecoratio.setDrawable(ContextCompat.getDrawable(context,R.drawable.ryl_sha));
            rxxpViewHolder.rxxpryl.addItemDecoration(dividerItemDecoratio);

        }
        //魔力时尚
        else if (getItemViewType(i)==TYPE_THREE){
            MlssViewHolder mlssViewHolder= (MlssViewHolder) viewHolder;
            List<Home.ResultBean.MlssBean> mlss = result.getMlss();
            Home.ResultBean.MlssBean mlssBean = mlss.get(0);
            //得到里面的数据
            String name = mlssBean.getName();
            List<Home.ResultBean.MlssBean.CommodityListBeanXX> commodityList = mlssBean.getCommodityList();
            //设置数据和适配器
            mlssViewHolder.mlsstext.setText(name);
            mlssViewHolder.mlssryl.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,true));
            MlssAdapter mlssAdapter = new MlssAdapter(commodityList,context);
            mlssViewHolder.mlssryl.setAdapter(mlssAdapter);
        }
        //品质生活
        else {
            PzshViewHolder pzshViewHolder= (PzshViewHolder) viewHolder;
            List<Home.ResultBean.PzshBean> pzsh = result.getPzsh();
            Home.ResultBean.PzshBean pzshBean = pzsh.get(0);
            //得到里面的数据
            String name = pzshBean.getName();
            List<Home.ResultBean.PzshBean.CommodityListBeanX> commodityList = pzshBean.getCommodityList();
            //设置数据和适配器
            pzshViewHolder.pzshtext.setText(name);
            pzshViewHolder.pzshryl.setLayoutManager(new GridLayoutManager(context,2));
            PzshAdapter pzshAdapter = new PzshAdapter(commodityList,context);
            pzshViewHolder.pzshryl.setAdapter(pzshAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return  TYPE_ONE;
        }
        else if (position==1){
            return  TYPE_TWO;
        }
        else if (position==2){
            return  TYPE_THREE;
        }
        else {
            return  TYPE_FOUR;
        }
    }
    //banner
    public class BannerViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        ViewPager viewPager;
        @SuppressLint({"HandlerLeak", "ClickableViewAccessibility"})
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPager=itemView.findViewById(R.id.home_main_viewpager);
            linearLayout=itemView.findViewById(R.id.ll_gallery_outer);

            handler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what==0){
                        int currentItem = viewPager.getCurrentItem();
                        viewPager.setCurrentItem(currentItem+1);
                        handler.sendEmptyMessageDelayed(0,2000);
                    }
                }
            };

            viewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            handler.removeMessages(0);
                            break;
                        case MotionEvent.ACTION_MOVE:
                            handler.removeMessages(0);
                            break;
                        case MotionEvent.ACTION_UP:
                            handler.sendEmptyMessageDelayed(0,2000);
                            break;
                    }
                    return false;
                }
            });
        }
    }
    //热销新品
    public class RxxpViewHolder extends RecyclerView.ViewHolder{
        TextView rxxptext;
        ImageView rxxpimagemenu;
        RecyclerView rxxpryl;
        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            rxxptext=itemView.findViewById(R.id.rxxp_text);
            rxxpimagemenu=itemView.findViewById(R.id.rxxp_menu_image);
            rxxpryl=itemView.findViewById(R.id.rxxp_ryl);
            //点击通过接口回调到这个适配器的fragment告诉它点击的哪个界面
            rxxpimagemenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuClickListner.RxxpMenuClickListner(1,result);
                }
            });
        }

    }
    //魔力时尚
    public class MlssViewHolder extends RecyclerView.ViewHolder{
        TextView mlsstext;
        ImageView mlssimagemenu;
        RecyclerView mlssryl;
        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            mlsstext=itemView.findViewById(R.id.mlss_text);
            mlssimagemenu=itemView.findViewById(R.id.mlss_menu_image);
            mlssryl=itemView.findViewById(R.id.mlss_ryl);
            //点击通过接口回调到这个适配器的fragment告诉它点击的哪个界面
            mlssimagemenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuClickListner.MoliMenuClickListner(2,result);
                }
            });
        }
    }
    //品质生活
    public class PzshViewHolder extends RecyclerView.ViewHolder{
        TextView pzshtext;
        ImageView pzshimagemenu;
        RecyclerView pzshryl;
        public PzshViewHolder(@NonNull View itemView) {
            super(itemView);
            pzshtext=itemView.findViewById(R.id.pzsh_text);
            pzshimagemenu=itemView.findViewById(R.id.pzsh_menu_image);
            pzshryl=itemView.findViewById(R.id.pzsh_ryl);

            //点击通过接口回调到这个适配器的fragment告诉它点击的哪个界面
            pzshimagemenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    menuClickListner.PzshMenuClickListner(3,result);
                }
            });
        }
    }
    //设置跳转后fragment要展示的数据类型
    public interface MenuClickListner{
        void RxxpMenuClickListner(int type,Home.ResultBean result);
        void MoliMenuClickListner(int type,Home.ResultBean result);
        void PzshMenuClickListner(int type,Home.ResultBean result);
    }

}
