package com.mai.pilot_assistent.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    protected abstract void clear();

    public int getCurrentPosition() {
        return mCurrentPosition;
    }
}