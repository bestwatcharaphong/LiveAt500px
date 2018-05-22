package com.example.watch.liveat500px.view;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.example.watch.liveat500px.R;

/**
 * Created by watch on 5/1/2018.
 */

public class CustomViewGroupTemplate extends FrameLayout {
    public CustomViewGroupTemplate(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomViewGroupTemplate(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initWithAttrs(attrs,0,0);
    }

    public CustomViewGroupTemplate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initWithAttrs(attrs,defStyleAttr,0);
    }

    public CustomViewGroupTemplate(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initWithAttrs(attrs,defStyleAttr,defStyleRes);
    }

    private void initInflate() {
        inflate(getContext(), R.layout.list_item_photo, this);
    }

    private void initInstances() {

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
}
