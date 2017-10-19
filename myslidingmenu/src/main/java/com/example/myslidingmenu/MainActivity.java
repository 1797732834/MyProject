package com.example.myslidingmenu;

import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.Toast;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity {
  SlidingMenu slidingMenu;
  View textView;
  Button bt;
  private static Interpolator interp = new Interpolator() {
    @Override
    public float getInterpolation(float t) {
      t -= 1.0f;
      return t * t * t + 1.0f;
    }
  };
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    init();

  }
  void init(){
    slidingMenu = new SlidingMenu(this);
    //设置侧滑菜单出现的方向（跟滑动方向相反）
    slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
    //设置主页滑动有效范围
    slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    //设置侧滑页滑动有限范围 抢夺侧滑页的点击事件（焦点）
//    slidingMenu.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);
    //设置菜单页的宽度
    //动态获取手机屏幕的宽度
    WindowManager windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    //获取显示对象
    Display display = windowManager.getDefaultDisplay();
    //创建属性对象
    DisplayMetrics displayMetrics = new DisplayMetrics();
    //属性对象传给给显示对象 获取显示对象的属性
    display.getMetrics(displayMetrics);
    slidingMenu.setBehindWidth(displayMetrics.widthPixels/3*2);
//    slidingMenu.setBehindOffsetRes(R.dimen.slidingMenu_offset);
//    设置阴影的图片资源
    slidingMenu.setShadowDrawable(R.color.colorPrimary);
//    设置阴影图片的宽度
    slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
//    设置滑动时菜单的是否淡入淡出
    slidingMenu.setFadeEnabled(true);
//    设置淡入淡出的比例
    slidingMenu.setFadeDegree(1.0f);
//    设置滑动时拖拽效果
    slidingMenu.setBehindScrollScale(1);

    slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
    slidingMenu.setMenu(R.layout.sliding);
    //添加第二个侧滑页
    slidingMenu.setSecondaryMenu(R.layout.right);
    slidingMenu.showContent();

    slidingMenu.setBehindCanvasTransformer(new SlidingMenu.CanvasTransformer() {
      @Override
      public void transformCanvas(Canvas canvas, float percentOpen) {
//                canvas.scale(percentOpen,1,0,0);//折叠动画
        //缩放动画
//                float scale = (float) (percentOpen*0.25 + 0.75);
//                canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);
        //上升动画
        canvas.translate(0, canvas.getHeight()*(1-interp.getInterpolation(percentOpen)));
      }
    });
    //弹出侧滑页
//    slidingMenu.showMenu();

    textView = findViewById(R.id.textView);
    textView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        //开关侧滑页
        slidingMenu.toggle();
      }
    });
    bt = (Button) findViewById(R.id.button);
    bt.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        //开关侧滑页
        slidingMenu.toggle();
      }
    });
  }

}
