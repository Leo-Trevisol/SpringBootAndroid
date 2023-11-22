package com.projeto.projetoandroidspring.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Collection;

public class Utils {

    public static boolean isEmpty(EditText edit) {
        if (edit.getText() == null || edit.getText().toString().trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(TextView edit) {
        if (edit.getText() == null || edit.getText().toString().trim().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isEmpty(String txt) {
        return txt == null || txt.trim().isEmpty();
    }

    public static boolean isEmpty(Collection c) {
        return c == null || c.isEmpty();
    }

    public static void hideKeyboardFrom(Context context, View view) {
        if (context != null && view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
