package com.projeto.projetoandroidspring.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class GenericMask {

    public static String mask;

    public static String CNPJ_MASK = "##.###.###/####-##";
    public static String CPF_MASK = "###.###.###-##";
    public static String RG_MASK = "##########";
    public static String DATE_MASK = "##/##/####";
    public static String CEP_MASK = "#####-###";
    public static String CELULAR_MASK = "(##) #####-####";
    public static String CELULAR_MASK_DDD = "(###) #####-####";
    public static String DINHEIRO_REAL_MASK =  "0,00";
    public static String CMC7_MASK = "########  ##########  #############";
    public static String BANCO_MASK = "###";
    public static String AGENCIA_MASK = "####";
    public static String NUM_CHEQUE_MASK = "######";



    public static String unmask(String s) {
        return s.replaceAll("[^0-9]*", "");
    }

    public static String removeZeroIsLeft(String s) {
        return s.replaceFirst("^0+(?!$)", "");
    }

    public static String mask(String s, String mask) {
        final StringBuilder finalString = new StringBuilder(s);
        for (int pos = 0; pos < mask.length(); pos++) {
            final char sub = mask.charAt(pos);
            if (sub != '#') {
                finalString.insert(pos, sub);
            }
        }

        return finalString.toString();
    }

    public static TextWatcher insert(final EditText editText, final String mask) {
        return insert(editText, mask, null);
    }

    public static TextWatcher insert(final EditText editText, String defaultMask, Func1<Integer, String> customMask) {
        return new TextWatcher() {
            boolean isUpdating;
            String old = "";

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = GenericMask.unmask(s.toString());
                String mascara = "";

                if (isUpdating) {
                    old = str;
                    isUpdating = false;
                    return;
                }

                if (defaultMask.equals(DINHEIRO_REAL_MASK)) {
                    str = removeZeroIsLeft(str);

                    if (str.length() == 0) {
                        mask = "0,00";
                    }else if (str.length() == 1) {
                        mask = "0,0#";
                    }else if (str.length() == 2) {
                        mask = "0,##";
                    }else if (str.length() == 3) {
                        mask = "#,##";
                    } else if (str.length() == 4) {
                        mask = "##,##";
                    } else if (str.length() == 5) {
                        mask = "###,##";
                    } else if (str.length() == 6) {
                        mask = "#.###,##";
                    } else if (str.length() == 7) {
                        mask = "##.###,##";
                    } else if (str.length() == 8) {
                        mask = "###.###,##";
                    } else if (str.length() == 9) {
                        mask = "#.###.###,##";
                    } else if (str.length() == 10) {
                        mask = "##.###.###,##";
                    } else if (str.length() == 11) {
                        mask = "###.###.###,##";
                    } else if (str.length() <= 12) {
                        mask = "#.###.###.###,##";
                    }

                }else {
                    if (customMask != null) mask = customMask.call(str.length());
                    else mask = defaultMask;
                }

                int i = 0;
                for (char m : mask.toCharArray()) {
                    if ((m != '#' && str.length() >= old.length()) || (m != '#' && str.length() <= old.length() && str.length() != i)) {
                        mascara += m;
                        continue;
                    }

                    try {
                        mascara += str.charAt(i);
                    } catch (Exception e) {
                        break;
                    }
                    i++;
                }

                isUpdating = true;
                editText.setText(mascara);
                editText.setSelection(mascara.length());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void afterTextChanged(Editable s) { }
        };
    }
}

