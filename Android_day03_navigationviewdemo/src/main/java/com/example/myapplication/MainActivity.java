package com.example.myapplication;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private NavigationView mNavigationView;
    private ImageButton mImageButton;
    private TextView mTextView;
    private View mHeaderView;
    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView= (TextView) findViewById(R.id.textView6);
        mDrawerLayout= (DrawerLayout) findViewById(R.id.drawerLayout);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        //mNavigationView= (NavigationView) findViewById(R.id.nav_view);
        //mHeaderView= mNavigationView.getHeaderView(0);
        //mImageButton= (ImageButton) mHeaderView.findViewById(R.id.img_btn);
       // mTextView= (TextView) mHeaderView.findViewById(R.id.txt);
//        mImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"我是头部按钮",Toast.LENGTH_SHORT).show();
//            }
//        });


//        if (mNavigationView!=null){
//            mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    int id=item.getItemId();
//                    switch (id){
//                        case R.id.nav_home:
//                            Toast.makeText(MainActivity.this,"home",Toast.LENGTH_SHORT).show();
//                            break;
//                        case R.id.nav_messages:
//                            Toast.makeText(MainActivity.this,"messages",Toast.LENGTH_SHORT).show();
//                            break;
//                        case R.id.nav_friends:
//                            Toast.makeText(MainActivity.this,"friends",Toast.LENGTH_SHORT).show();
//                            break;
//                        case R.id.nav_discussion:
//                            Toast.makeText(MainActivity.this,"discussion",Toast.LENGTH_SHORT).show();
//                            break;
//                        case R.id.sub_item1:
//                            Toast.makeText(MainActivity.this,"Sub item1",Toast.LENGTH_SHORT).show();
//                            break;
//                        case R.id.sub_item2:
//                            Toast.makeText(MainActivity.this,"Sub item2",Toast.LENGTH_SHORT).show();
//                            break;
//                    }
//                    //切换相应 Fragment 等操作
//                    item.setChecked(true);
//                    mDrawerLayout.closeDrawers();
//                    return true;
//                }
//            });
//        }
    }
}
