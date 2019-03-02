package com.fzy.libs.databinding_adapter;

import android.text.Spanned;
import android.widget.TextView;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;

/**
 * Author: openXu
 * Time: 2019/2/25 17:26
 * class: TextViewBindingAdapter
 * Description: DataBinding框架很开明，它承诺：在寻找合适的BindingAdapter时，会优先使用用户定义的BindingAdapter
 */
public class TextViewBindingAdapter {

    @BindingAdapter("text")
    public static void setText(TextView view, LiveData data) {
//        final CharSequence oldText = view.getText();
//        view.setText(text+"  新适配");
    }

    private static boolean haveContentsChanged(CharSequence str1, CharSequence str2) {
        if ((str1 == null) != (str2 == null)) {
            return true;
        } else if (str1 == null) {
            return false;
        }
        final int length = str1.length();
        if (length != str2.length()) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return true;
            }
        }
        return false;
    }
}
