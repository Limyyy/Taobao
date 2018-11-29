package com.gx.tianba.util.fresco;

import android.content.Context;
import android.net.Uri;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * 封装Fresco工具类
 */
public class FrescoUtil {

    public static void initFresco(final Context context){
        DiskCacheConfig cacheConfig=DiskCacheConfig.newBuilder(context)
        .setBaseDirectoryPathSupplier(new Supplier<File>() {
            @Override
            public File get() {
                return context.getCacheDir();
            }
        }).build();

        ImagePipelineConfig config=ImagePipelineConfig.newBuilder(context)
                .setMainDiskCacheConfig(cacheConfig)
                .build();
        Fresco.initialize(context,config);
    }
    /*
     * 普通加载图片
     * */
    public static void load(String s, SimpleDraweeView view){
        Uri uri=Uri.parse(s);
        view.setImageURI(uri);
    }
}
