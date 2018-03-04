package com.apollo.base.view.widgets;


import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.squareup.picasso.Picasso;

public class SquareImageView extends AppCompatImageView {

    public String urlSource;

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, heightMeasureSpec); // This is the key that will make the height equivalent to its width
    }

    public void setUrlSource(String urlSource) {
        this.urlSource = urlSource;

        Picasso.with(getContext())
                .load(urlSource)
                .fit()
                .centerCrop()
                .into(this);

    }

}
