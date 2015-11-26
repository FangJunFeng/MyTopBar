package com.alandy.mytopbar;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by AlandyFeng on 2015/11/24.
 * 自定义TopBar的属性
 */
public class TopBar extends RelativeLayout {
    private Button mLeftButton;
    private Button mRightButton;
    private TextView mTvTitle;

    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;

    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;

    private TopBarClickListener listener;



    //点击事件监听器接口
    public interface TopBarClickListener{
        public void leftClick();
        public void rightClick();
    }

    //设置监听器
    public void setOnTopBarClickListener(TopBarClickListener listener){
        this.listener = listener;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 0);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar_myTitle);

        //避免浪费资源和缓存出错
        ta.recycle();

        //创建控件
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTvTitle = new TextView(context);

        //设置控件属性
        mLeftButton.setText(mLeftText);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setTextColor(mLeftTextColor);

        mRightButton.setText(mRightText);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setTextColor(mRightTextColor);

        mTvTitle.setText(mTitle);
        mTvTitle.setTextColor(mTitleTextColor);
        mTvTitle.setTextSize(mTitleTextSize);
        mTvTitle.setGravity(Gravity.CENTER);

        setBackgroundColor(0xff1234);

        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mRightParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mTitleParams.addRule(RelativeLayout.CENTER_IN_PARENT);

        addView(mTvTitle, mTitleParams);

        //设置标题的监听器
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.leftClick();
            }
        });

        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.rightClick();
            }
        });
    }
}
