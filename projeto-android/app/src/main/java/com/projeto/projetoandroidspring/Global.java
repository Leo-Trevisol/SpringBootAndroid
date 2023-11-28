package com.projeto.projetoandroidspring;

import android.graphics.Color;

public class Global {

    private static Global instance;

    private static final String DEFAULT_COLOR = "#E44D4D"; //red

    public static Global getInstance() {
        if (instance == null) {
            instance = new Global();
        }
        return instance;
    }

    public int getDefaultColor() {
            return Color.parseColor(DEFAULT_COLOR);
        }
    }
