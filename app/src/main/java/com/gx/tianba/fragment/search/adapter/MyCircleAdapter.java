package com.gx.tianba.fragment.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.gx.tianba.R;
import com.gx.tianba.fragment.search.bean.MyCircle;
import com.gx.tianba.util.fresco.FrescoUtil;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyCircleAdapter extends RecyclerView.Adapter<MyCircleAdapter.MyCircleViewHolder> {

    private List<MyCircle.ResultBean> result;
    private Context context;
    private OnItemHandClickListner onItemHandClickListner;
    public MyCircleAdapter(List<MyCircle.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public MyCircleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_circle_item, null);
        return new MyCircleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCircleViewHolder myCircleViewHolder, int i) {
        final MyCircle.ResultBean resultBean = result.get(i);
        FrescoUtil.load(resultBean.getHeadPic(),myCircleViewHolder.my_circle_icon);
        myCircleViewHolder.my_circle_name.setText(resultBean.getNickName());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = new Date(resultBean.getCreateTime());
        String format1 = format.format(date);
        myCircleViewHolder.my_circle_time.setText(format1);

        myCircleViewHolder.my_circle_content.setText(resultBean.getContent());

        //小手图标(为2则未点赞，为1则已经点过赞)
        int whetherGreat = resultBean.getWhetherGreat();
        if (whetherGreat==2) {
            myCircleViewHolder.my_circle_childimage_hand.setImageResource(R.mipmap.common_btn_prise_n_xxhdpi);
        }
        else {
            myCircleViewHolder.my_circle_childimage_hand.setImageResource(R.mipmap.common_btn_prise_s_xxhdpi);
        }

        //分隔字符
        String image = resultBean.getImage();
        int i1 = image.indexOf(",");
        if (i1==-1) {
            String[] images={image};
            MyCircleImageAdapter myCircleImageAdapter = new MyCircleImageAdapter(images, context);
            myCircleViewHolder.my_circle_childimage_ryl.setLayoutManager(new GridLayoutManager(context,3));
            myCircleViewHolder.my_circle_childimage_ryl.setAdapter(myCircleImageAdapter);
        }
        else {
            String[] strings = image.split(",");
            MyCircleImageAdapter myCircleImageAdapter = new MyCircleImageAdapter(strings, context);
            myCircleViewHolder.my_circle_childimage_ryl.setLayoutManager(new GridLayoutManager(context,3));
            myCircleViewHolder.my_circle_childimage_ryl.setAdapter(myCircleImageAdapter);
        }
        myCircleViewHolder.my_circle_childimage_greatenum.setText(""+resultBean.getGreatNum());

        //点赞接口回调
        myCircleViewHolder.my_circle_childimage_hand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemHandClickListner.click(resultBean.getId(),resultBean.getWhetherGreat());
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public List<MyCircle.ResultBean> getResult() {
        return result;
    }

    public void setResult(List<MyCircle.ResultBean> result) {
        this.result = result;
    }

    public class MyCircleViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView my_circle_icon;
       TextView my_circle_name,my_circle_time,my_circle_content,my_circle_childimage_greatenum;
        RecyclerView my_circle_childimage_ryl;
        ImageView my_circle_childimage_hand;
        public MyCircleViewHolder(@NonNull View itemView) {
            super(itemView);
             my_circle_icon=itemView.findViewById(R.id.my_circle_icon);
            my_circle_name = itemView.findViewById(R.id.my_circle_name);
            my_circle_time=itemView.findViewById(R.id.my_circle_time);
            my_circle_content=itemView.findViewById(R.id.my_circle_content);
            my_circle_childimage_ryl=itemView.findViewById(R.id.my_circle_childimage_ryl);
            my_circle_childimage_hand=itemView.findViewById(R.id.my_circle_childimage_hand);
            my_circle_childimage_greatenum=itemView.findViewById(R.id.my_circle_childimage_greatenum);
        }
    }

    public void setOnItemHandClickListner(OnItemHandClickListner onItemHandClickListner1){
        this.onItemHandClickListner=onItemHandClickListner1;
    }
    public interface OnItemHandClickListner{
        void click(int id,int whetherGreatId);
    }
}
