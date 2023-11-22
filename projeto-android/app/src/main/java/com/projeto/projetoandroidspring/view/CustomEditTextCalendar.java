package com.projeto.projetoandroidspring.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.projeto.projetoandroidspring.R;
import com.projeto.projetoandroidspring.utils.Action0;
import com.projeto.projetoandroidspring.utils.DateUtils;
import com.projeto.projetoandroidspring.utils.GenericMask;
import com.projeto.projetoandroidspring.utils.Utils;

import java.util.Calendar;
import java.util.Date;

public class CustomEditTextCalendar extends CustomEditText {

    private static DatePickerDialog picker;

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        clearFocus(null);
    }

    public CustomEditTextCalendar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(actionCalendar(this, getContext()));

        this.getIconButton().setImageResource(R.drawable.calendar);
        this.getIconButton().setVisibility(VISIBLE);

        this.addTextChangedListener(GenericMask.insert(this, GenericMask.DATE_MASK));

    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        return super.dispatchKeyEventPreIme(event);
    }

    public Action0 actionCalendar(CustomEditText editText, Context context) {

        return new Action0() {
            @Override
            public void call() {
                int day, month, year;
                if(DateUtils.isValid(editText.getText(), DateUtils.DEFAULT_PATTERN_DATE) && editText.getText().length() == 10){
                    Date date = DateUtils.getParseDate(editText.getText());
                    day = DateUtils.getDayOfDate(date);
                    month = DateUtils.getMonthOfYear(date);
                    year = DateUtils.getYear(date);
                } else {
                    final Calendar cldr = Calendar.getInstance();
                    day = cldr.get(Calendar.DAY_OF_MONTH);;
                    month = cldr.get(Calendar.MONTH);
                    year = cldr.get(Calendar.YEAR);
                }

                picker = new DatePickerDialog(context, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Date dateSelected = DateUtils.getDate(year, month, dayOfMonth);
                        editText.setText(DateUtils.getFormatDate(dateSelected));
                    }

                }, year, month, day);
                if(!Utils.isEmpty(editText.getEditText())){
                    picker.setButton(DatePickerDialog.BUTTON_NEUTRAL, "LIMPAR", new DatePickerDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editText.setText("");
                        }
                    });
                }
                picker.show();

                picker.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLACK);
                picker.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                if(picker.getButton(DatePickerDialog.BUTTON_NEUTRAL) != null) {
                    picker.getButton(DatePickerDialog.BUTTON_NEUTRAL).setTextColor(Color.BLACK);
                }}
        };
    }
}
