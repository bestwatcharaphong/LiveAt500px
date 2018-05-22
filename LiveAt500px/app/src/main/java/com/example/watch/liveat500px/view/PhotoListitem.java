package com.example.watch.liveat500px.view;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.watch.liveat500px.R;


/**
 * Created by watch on 5/1/2018.
 */

public class PhotoListitem extends BaseCustomViewGroup {
    private TextView tvName;
    private TextView tvDescription;
    private ImageView ivImag;
    public PhotoListitem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public PhotoListitem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initWithAttrs(attrs,0,0);
    }

    public PhotoListitem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initWithAttrs(attrs,defStyleAttr,0);
    }

    public PhotoListitem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initWithAttrs(attrs,defStyleAttr,defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_photo, this);
    }

    private void initInstances() {
        tvName = findViewById(R.id.tvName);
        tvDescription = findViewById(R.id.tvDescription);
        ivImag = findViewById(R.id.ivImg);


    }

    private void initWithAttrs(AttributeSet attrs, int defStyleAttr, int defStyleRes) {
         /*
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StyleableName,
                defStyleAttr, defStyleRes);

        try {

        } finally {
            a.recycle();
        }
        */
    }

    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
        // Save Instance State(s) here to the 'savedState.getBundle()'
        // for example,
        // savedState.getBundle().putString("key", value);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        // Restore State from bundle here
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);// width in px
        int height = width * 2 / 3;
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                MeasureSpec.EXACTLY
        );
        //Child View
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        //Self
        setMeasuredDimension(width,height);
    }
    public void setNameText(String text){
        tvName.setText(text);
    }
    public void setDescriptionText(String text){
        tvDescription.setText(text);
    }
    public void setImageUrl(String url){
        Glide.with(getContext())
                .load(url)
                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImag);
    }
}
