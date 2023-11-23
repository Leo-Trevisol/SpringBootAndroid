package com.projeto.projetoandroidspring.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.projeto.projetoandroidspring.R;

import java.util.Collection;

public class Utils {

    private static final String INITIAL_NUMERIC_PASSWORDS = "NumericPasswords";

    private static DatePickerDialog picker;

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

    public static boolean isNumericPasswords(Context context) {
        String numericPasswords = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE).getString(INITIAL_NUMERIC_PASSWORDS, null);
        return numericPasswords != null && Boolean.TRUE.toString().equals(numericPasswords);
    }

    public static boolean isValidEmail(String email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


}
