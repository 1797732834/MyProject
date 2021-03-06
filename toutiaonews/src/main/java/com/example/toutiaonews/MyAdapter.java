package com.example.toutiaonews;

import android.content.Context;
import android.graphics.Bitmap.CompressFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 * Created by wangye on 2017/8/28.
 */

public class MyAdapter extends BaseAdapter{
  Context c;
  News news;
  DisplayImageOptions options;
  public MyAdapter(Context c,News news){
    this.c = c;
    this.news = news;
    options = new DisplayImageOptions.Builder()
        .showImageForEmptyUri(R.mipmap.ic_launcher)     //url爲空會显示该图片，自己放在drawable里面的
        .showImageOnFail(R.mipmap.ic_launcher)             //加载图片出现问题，会显示该图片
        .cacheInMemory()                                  //缓存用
        .cacheOnDisc()                                   //缓存用
        .displayer(new RoundedBitmapDisplayer(10))       //图片圆角显示，值为整数
        .build();
    ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
        c)
        .memoryCacheExtraOptions(480, 800)// 缓存在内存的图片的宽和高度
        .memoryCache(new WeakMemoryCache())
        .memoryCacheSize(2 * 1024 * 1024) //缓存到内存的最大数据
        .discCacheSize(50 * 1024 * 1024).  //缓存到文件的最大数据
        discCacheFileCount(1000)            //文件数量
        .defaultDisplayImageOptions(options).  //上面的options对象，一些属性配置
        build();
    ImageLoader.getInstance().init(config); //初始化
  }

  @Override
  public int getCount() {
    return news.getResult().getData().size();
  }

  @Override
  public Object getItem(int i) {
    return news.getResult().getData().get(i);
  }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override//组件复用   ViewHolder
  public View getView(final int i, View view, ViewGroup viewGroup) {
    final ViewHolder holder;
    if(view == null){
      view = View.inflate(c,R.layout.adapter,null);
      holder = new ViewHolder();
      holder.imageView = view.findViewById(R.id.imageView);
      holder.txTitle = view.findViewById(R.id.tx_title);
      holder.txDate = view.findViewById(R.id.tx_date);
      holder.txAuthor = view.findViewById(R.id.tx_author);
      view.setTag(holder);
    }else{
      holder = (ViewHolder) view.getTag();
    }
//    new Thread(){
//      public void run(){
//        try {
//          URL url = new URL(news.getResult().getData().get(i).getThumbnail_pic_s());
//          HttpURLConnection con = (HttpURLConnection) url.openConnection();
//          con.setRequestMethod("GET");
//          con.setReadTimeout(10000);
//          con.setConnectTimeout(10000);
//          con.connect();
//          if(con.getResponseCode() == 200){
//            InputStream inputStream = con.getInputStream();
//            ByteArrayOutputStream bs = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int length;
//            while((length = inputStream.read(buffer))!=-1){
//              bs.write(buffer,0,length);
//              bs.flush();
//            }
//            //将字节信息转换成图片
//            Bitmap bitmap = BitmapFactory.decodeByteArray(bs.toByteArray(),0,bs.toByteArray().length);
//            holder.imageView.setImageBitmap(bitmap);
//          }
//        } catch (Exception e) {
//          e.printStackTrace();
//        }
//      }
//    }.start();
    ImageLoader.getInstance().displayImage(
        news.getResult().getData().get(i).getThumbnail_pic_s(),
        holder.imageView,options);
    holder.txTitle.setText(news.getResult().getData().get(i).getTitle());
    holder.txDate.setText(news.getResult().getData().get(i).getDate());
    holder.txAuthor.setText(news.getResult().getData().get(i).getAuthor_name());
    return view;
  }
  class ViewHolder{
    ImageView imageView;
    TextView txTitle,txDate,txAuthor;
  }
}
