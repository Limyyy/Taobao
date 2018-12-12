package com.gx.tianba.fragment.my.mychildfragment.mygroup.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.mygroup.bean.MyGroup;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MyGroupAdapter extends RecyclerView.Adapter<MyGroupAdapter.MyGroupViewHolder> {
    private OnItemClickListner onItemClickListner;
    private List<MyGroup.ResultBean> result;
    private Context context;
    private ArrayList<String> imagess=new ArrayList<>();

    public MyGroupAdapter(List<MyGroup.ResultBean> result, Context context) {
        if (result!=null){
            this.result = result;
        }
        this.context = context;
    }

    @NonNull
    @Override
    public MyGroupViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_group_item, null);
        return new MyGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGroupViewHolder myGroupViewHolder, int i) {
        imagess=new ArrayList<>();
        final MyGroup.ResultBean resultBean = result.get(i);
        //内容
        String content = resultBean.getContent();
        //图片
        String image = resultBean.getImage();
        if (image!=null){
            int i1 = image.indexOf(",");
            if (i1==-1) {
                imagess.add(image);
            }
            else{
                String[] split = image.split(",");
                for (int i2 = 0; i2 < split.length; i2++) {
                    imagess.add(split[i2]);
                }
            }
        }
        //时间
        long createTime = resultBean.getCreateTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format1 = format.format(createTime);
        //点赞
        int greatNum = resultBean.getGreatNum();
        //是否选中
        boolean checked = resultBean.isChecked();
        if (checked) {
            myGroupViewHolder.my_group_item_checkbox.setButtonDrawable(R.drawable.ic_check_circle_black_24dp);
        }
        else {
            myGroupViewHolder.my_group_item_checkbox.setButtonDrawable(R.drawable.ic_radio_button_unchecked_black_24dp);
        }
        //显示多选框
        boolean showCheckBox = resultBean.isShowCheckBox();
        if (showCheckBox){
            myGroupViewHolder.my_group_item_checkbox.setVisibility(View.VISIBLE);
        }
        else {
            myGroupViewHolder.my_group_item_checkbox.setVisibility(View.GONE);
        }
        myGroupViewHolder.my_group_item_content.setText(content);
        myGroupViewHolder.my_group_item_createtime.setText(format1);
        myGroupViewHolder.my_group_item_greatenum.setText(""+greatNum);
        myGroupViewHolder.my_group_item_ryl.setLayoutManager(new GridLayoutManager(context,3));
        final MyGroupChildAdapter myGroupChildAdapter = new MyGroupChildAdapter(imagess, context);
        myGroupViewHolder.my_group_item_ryl.setAdapter(myGroupChildAdapter);

        //点击选中
        myGroupViewHolder.my_group_item_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = resultBean.getId();
                onItemClickListner.click(id);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (result!=null){
            return result.size();
        }
        else {
            return 0;
        }
    }

    public List<MyGroup.ResultBean> getResult() {
        return result;
    }

    public class MyGroupViewHolder extends RecyclerView.ViewHolder {
        TextView my_group_item_content,my_group_item_createtime,my_group_item_greatenum;
        RecyclerView my_group_item_ryl;
        ImageView my_group_item_hand;
        CheckBox my_group_item_checkbox;
        public MyGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            my_group_item_content=itemView.findViewById(R.id.my_group_item_content);
            my_group_item_createtime=itemView.findViewById(R.id.my_group_item_createtime);
            my_group_item_greatenum=itemView.findViewById(R.id.my_group_item_greatenum);
            my_group_item_ryl=itemView.findViewById(R.id.my_group_item_ryl);
            my_group_item_hand=itemView.findViewById(R.id.my_group_item_hand);
            my_group_item_checkbox=itemView.findViewById(R.id.my_group_item_checkbox);
        }
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner1){
        this.onItemClickListner=onItemClickListner1;
    }
    public interface OnItemClickListner{
        void click(int id);
    }
}
