package com.example.tianqitong.data;

import android.content.Context;
import android.widget.ImageView;

import com.example.tianqitong.R;

import java.util.ArrayList;


/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class GuildeImg {
    public static ArrayList<ImageView> getImgs(Context context){
        int[] imgs = new int[]{
                R.mipmap.guide01,
                R.mipmap.guide02,
                R.mipmap.guide03,
                R.mipmap.guide04,
        };

        ImageView iv;
        ArrayList<ImageView> list = new ArrayList<ImageView>();
        for (int i = 0; i < imgs.length; i++) {
            iv = new ImageView(context);
            iv.setBackgroundResource(imgs[i]);
            list.add(iv);
        }
        return list;
    }
}
