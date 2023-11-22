package com.projeto.projetoandroidspring.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.textfield.TextInputLayout;
import com.projeto.projetoandroidspring.R;
import com.projeto.projetoandroidspring.utils.Action0;
import com.projeto.projetoandroidspring.utils.Utils;

import androidx.core.content.ContextCompat;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomEditText extends FrameLayout {
    public static final String CUSTOM_EDITTEXT_TAG = "CUSTOM_EDITTEXT_TAG";
    private final EditText editText;
    private final TextView prefixTextView, suffixTextView;
    private ImageButton iconButton;
    private Action0 onIconClickedListener;
    private Action0 onDispatchKeyEventListener;
    private TextWatcher textWatcher;
    private Context context;
    public View view;

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        clearFocus(null);
    }

    public CustomEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;

        final int defaultColor = getResources().getColor(android.R.color.transparent);
        final int mainColor = getResources().getColor(R.color.red);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.custom_edittext, this, true);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText, 0, 0);
        editText = view.findViewWithTag(CUSTOM_EDITTEXT_TAG);
        int disabledColor = a.getColor(R.styleable.CustomEditText_disabledColor, defaultColor);
        int textColor = getResources().getColor(R.color.black);
        String hint = a.getString(R.styleable.CustomEditText_hint);
        boolean hintEnabled = a.getBoolean(R.styleable.CustomEditText_hintEnabled,true);
        String digits = a.getString(R.styleable.CustomEditText_digits);
        String prefix = a.getString(R.styleable.CustomEditText_prefix);
        String suffix = a.getString(R.styleable.CustomEditText_suffix);
        float textSize = a.getDimension(R.styleable.CustomEditText_textSize, 28);
        int drawable = a.getResourceId(R.styleable.CustomEditText_iconSrc, -1);
        int imeOptions = a.getInt(R.styleable.CustomEditText_android_imeOptions, 0);
        int inputType = a.getInt(R.styleable.CustomEditText_android_inputType, EditorInfo.TYPE_CLASS_TEXT);
        boolean validateTypeParam = a.getBoolean(R.styleable.CustomEditText_typeParam, false);
        a.recycle();

        View line1 = view.findViewById(R.id.line1);
        View line2 = view.findViewById(R.id.line2);
        View line3 = view.findViewById(R.id.line3);

        if ((inputType == ((InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) )) {
            inputType =  (InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        }

        suffixTextView = view.findViewById(R.id.textView_suffix);
        if (Utils.isEmpty(prefix)) {
            suffixTextView.setVisibility(GONE);
        } else {
            suffixTextView.setVisibility(VISIBLE);
            suffixTextView.setTextColor(mainColor);
            suffixTextView.setText(suffix);
        }

        prefixTextView = view.findViewById(R.id.textView_prefix);
        if (Utils.isEmpty(prefix)) {
            prefixTextView.setVisibility(GONE);
        } else {
            prefixTextView.setVisibility(VISIBLE);
            prefixTextView.setTextColor(mainColor);
            prefixTextView.setText(prefix);
        }

        editText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        editText.setImeOptions(imeOptions);
        editText.setInputType(inputType);
        editText.setTextColor(textColor);

        if (!Utils.isEmpty(digits)) editText.setKeyListener(DigitsKeyListener.getInstance(digits));
        ((TextInputLayout) view.findViewById(R.id.textInputLayout_search)).setHint(hint);
        setHintEnabled(hintEnabled);


        if (disabledColor != defaultColor ) {
            line1.setBackgroundColor(disabledColor);
            line2.setBackgroundColor(disabledColor);
            line3.setBackgroundColor(disabledColor);
            editText.setOnEditorActionListener((textView, i, keyEvent) -> false);
            editText.setOnFocusChangeListener((view12, status) -> {
                if (!status) {
                    line1.setBackgroundColor(disabledColor);
                    line2.setBackgroundColor(disabledColor);
                    line3.setBackgroundColor(disabledColor);
                } else {
                    line1.setBackgroundColor(mainColor);
                    line2.setBackgroundColor(mainColor);
                    line3.setBackgroundColor(mainColor);
                }
            });
        } else {
            line1.setBackgroundColor(mainColor);
            line2.setBackgroundColor(mainColor);
            line3.setBackgroundColor(mainColor);
        }

        setCursorColor(editText, mainColor);

        editText.setVisibility(VISIBLE);
        iconButton = view.findViewById(R.id.imageButton_customEditText);
        if (drawable > 0) {
            iconButton.setImageResource(drawable);
            iconButton.setVisibility(VISIBLE);
        } else {
            iconButton.setVisibility(GONE);
        }
        iconButton.setOnClickListener(view1 -> {
            if (onIconClickedListener != null && editText.isEnabled()) onIconClickedListener.call();
        });

        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                if(onDispatchKeyEventListener != null){
                    onDispatchKeyEventListener.call();
                }
            }
        });
    }

    public void clearFocus(final View view) {
        editText.clearFocus();
        if (view != null) Utils.hideKeyboardFrom(getContext(), view);
    }

    public void setAdapter(ArrayAdapter<String> adapter) {
        ((AutoCompleteTextView) editText).setAdapter(adapter);
    }

    public void disable() {
        editText.setEnabled(false);
        editText.setFocusable(false);
    }

    public void setText(String text) {

        if(text != null && 2 == editText.getInputType() && textWatcher == null) {
            String replacement = Matcher.quoteReplacement("");
            String searchString = Pattern.quote(".");
            String str = text.replaceAll(searchString, replacement);
            editText.setText(str);
        } else {
            editText.setText(text);
        }
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setOnIconClickedListener(Action0 onIconClickedListener) {
        this.onIconClickedListener = onIconClickedListener;
    }

    public void addTextChangedListener(TextWatcher listener) {
        textWatcher = listener;
        editText.addTextChangedListener(listener);
    }

    public void dispose() {
        if (textWatcher != null) editText.removeTextChangedListener(textWatcher);
    }

    public void setOnEditorActionListener(TextView.OnEditorActionListener listener) {
        editText.setOnEditorActionListener(listener);
    }

    public void setSuffix(String suffix) {
        suffixTextView.setText(suffix);

        suffixTextView.setVisibility(Utils.isEmpty(suffix) ? GONE : VISIBLE);
    }

    public void setPrefix(String prefix) {
        prefixTextView.setText(prefix);

        prefixTextView.setVisibility(Utils.isEmpty(prefix) ? GONE : VISIBLE);
    }

    public void setOnClickListener(Action0 l) {
        final OnClickListener onClickListener = v -> {
            if (editText.isEnabled()) l.call();
        };
        super.setOnClickListener(onClickListener);
        editText.setOnClickListener(onClickListener);

        editText.setShowSoftInputOnFocus(false);
        editText.setCursorVisible(false);
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) l.call();
        });
    }

    public void setSelection(int index) {
        editText.setSelection(index);
    }

    public static void setCursorColor(EditText view, @ColorInt int color) {
        try {
            // Get the cursor resource id
            @SuppressLint("SoonBlockedPrivateApi")
            Field field = TextView.class.getDeclaredField("mCursorDrawableRes");
            field.setAccessible(true);
            int drawableResId = field.getInt(view);

            // Get the editor
            field = TextView.class.getDeclaredField("mEditor");
            field.setAccessible(true);
            Object editor = field.get(view);

            // Get the drawable and set a color filter
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), drawableResId);
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            Drawable[] drawables = {drawable, drawable};

            // Set the drawables
            field = editor.getClass().getDeclaredField("mCursorDrawable");
            field.setAccessible(true);
            field.set(editor, drawables);
        } catch (Exception ignored) {
        }
    }

    public void setHint(String hint) {
        ((TextInputLayout) view.findViewById(R.id.textInputLayout_search)).setHint(hint);
    }

    public void setHintEnabled(Boolean enabled) {
        ((TextInputLayout) view.findViewById(R.id.textInputLayout_search)).setHintEnabled(enabled);
    }

    public String getHint() {
        final CharSequence hint = ((TextInputLayout) view.findViewById(R.id.textInputLayout_search)).getHint();
        return hint == null ? "" : hint.toString();
    }

    public ImageButton getIconButton(){
        return iconButton;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setMargins(int left, int top, int right, int bottom){
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(left, top, right, bottom);
        view.findViewById(R.id.everything).setLayoutParams(layoutParams);
    }

    public void setEnabled(boolean enabled) {
        editText.setEnabled(enabled);
    }

    public void setOnDispatchKeyEventListener(Action0 listener){
        onDispatchKeyEventListener = listener;
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if(onDispatchKeyEventListener != null){
            onDispatchKeyEventListener.call();
        }
        return super.dispatchKeyEventPreIme(event);
    }
}
