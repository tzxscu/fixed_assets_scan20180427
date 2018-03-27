package com.mingle.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.mingle.shapeloading.R;

/**
 * Created by zzz40500 on 15/6/15.
 */
public class ShapeLoadingDialog extends Dialog{

    private LoadingView mLoadingView;

    private Builder mBuilder;



    public ShapeLoadingDialog(Builder builder) {
        super(builder.mContext, R.style.custom_dialog);
        mBuilder = builder;
        setCancelable(mBuilder.mCancelable);
        setCanceledOnTouchOutside(mBuilder.mCanceledOnTouchOutside);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dialog);

        mLoadingView = (LoadingView) findViewById(R.id.loadView);

        mLoadingView.setDelay(mBuilder.mDelay);
        mLoadingView.setLoadingText(mBuilder.mLoadText);

        setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                mBuilder.masyncTask.cancel(true);
               // Toast.makeText(mBuilder.mContext,"网络不好啦，超时。。。",Toast.LENGTH_SHORT).show();
                mLoadingView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void show() {
        super.show();
        mLoadingView.setVisibility(View.VISIBLE);
    }

    public Builder getBuilder() {
        return mBuilder;
    }

    public static class Builder{

        private Context mContext;

        private AsyncTask masyncTask;

        private int mDelay = 130;

        private CharSequence mLoadText;

        private boolean mCancelable = true;

        private boolean mCanceledOnTouchOutside = false;

        public Builder(Context context, AsyncTask asyncTask) {
            mContext = context;
            masyncTask = asyncTask;
        }

        public Builder delay(int delay) {
            mDelay = delay;
            return this;
        }

        public Builder loadText(CharSequence loadText) {
            mLoadText = loadText;
            return this;
        }

        public Builder loadText(int resId) {
            mLoadText = mContext.getString(resId);
            return this;
        }

        public Builder cancelable(boolean cancelable) {
            mCancelable = cancelable;
            mCanceledOnTouchOutside = cancelable;
            return this;
        }

        public Builder canceledOnTouchOutside(boolean canceledOnTouchOutside) {
            mCanceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public ShapeLoadingDialog build(){
            return new ShapeLoadingDialog(this);
        }

        public ShapeLoadingDialog show(){
            ShapeLoadingDialog dialog = build();
            dialog.show();
            return dialog;
        }
    }
}
