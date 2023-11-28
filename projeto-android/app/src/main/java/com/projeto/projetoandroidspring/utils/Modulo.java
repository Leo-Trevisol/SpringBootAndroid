package com.projeto.projetoandroidspring.utils;

import android.view.View;

public class Modulo {
    private final String title;
    private int resourceId;
    private View.OnClickListener onClickListener;
    private boolean enabled;

    public Modulo(int resourceId, String title, View.OnClickListener listener) {
        this(resourceId, true, title, listener);
    }

    public Modulo(int resourceId, boolean enabled, String title, View.OnClickListener listener) {
        onClickListener = listener;
        this.resourceId = resourceId;
        this.enabled = enabled;
        this.title = title;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public int getResourceId() {
        return resourceId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getTitle() {
        return title;
    }
}
