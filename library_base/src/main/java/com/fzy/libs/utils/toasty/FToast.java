package com.fzy.libs.utils.toasty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fzy.libs.R;
import com.fzy.libs.base.BaseApplication;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

/**
 * This file is part of FToast.
 * <p>
 * FToast is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * FToast is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with FToast.  If not, see <http://www.gnu.org/licenses/>.
 */

@SuppressLint("InflateParams")
public class FToast {

    private static final Typeface LOADED_TOAST_TYPEFACE = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private static Typeface currentTypeface = LOADED_TOAST_TYPEFACE;

    private static final int textSizeDef = 14; // in SP
    private static final boolean tintIconDef = true;
    private static final boolean allowQueueDef = false;

    private static int textSize = textSizeDef; // in SP

    private static boolean tintIcon = tintIconDef;
    private static boolean allowQueue = allowQueueDef;

    private static Toast lastToast = null;

    public static final int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static final int LENGTH_LONG = Toast.LENGTH_LONG;

    private FToast() {
        // avoiding instantiation
    }

    private static Context context;
    static {
        context = BaseApplication.getApplication().getApplicationContext();
    }
    @CheckResult
    public static Toast normal(@StringRes int message) {
        return normal(context.getString(message), Toast.LENGTH_SHORT, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message) {
        return normal(message, Toast.LENGTH_SHORT, null, false);
    }

    @CheckResult
    public static Toast normal(@StringRes int message, Drawable icon) {
        return normal(context.getString(message), Toast.LENGTH_SHORT, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message, Drawable icon) {
        return normal(message, Toast.LENGTH_SHORT, icon, true);
    }

    @CheckResult
    public static Toast normal(@StringRes int message, int duration) {
        return normal(context.getString(message), duration, null, false);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message, int duration) {
        return normal(message, duration, null, false);
    }

    @CheckResult
    public static Toast normal(@StringRes int message, int duration,
                               Drawable icon) {
        return normal(context.getString(message), duration, icon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message, int duration,
                               Drawable icon) {
        return normal(message, duration, icon, true);
    }

    @CheckResult
    public static Toast normal(@StringRes int message, int duration,
                               Drawable icon, boolean withIcon) {
        return custom(context.getString(message), icon, ToastyUtils.getColor(context, R.color.toast_normalColor),
                ToastyUtils.getColor(context, R.color.toast_defaultTextColor), duration, withIcon, true);
    }

    @CheckResult
    public static Toast normal(@NonNull CharSequence message, int duration,
                               Drawable icon, boolean withIcon) {
        return custom(message, icon, ToastyUtils.getColor(context, R.color.toast_normalColor),
                ToastyUtils.getColor(context, R.color.toast_defaultTextColor), duration, withIcon, true);
    }

    @CheckResult
    public static Toast warning(@StringRes int message) {
        return warning(context.getString(message), Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast warning(@NonNull CharSequence message) {
        return warning(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast warning(@StringRes int message, int duration) {
        return warning(context.getString(message), duration, true);
    }

    @CheckResult
    public static Toast warning(@NonNull CharSequence message, int duration) {
        return warning(message, duration, true);
    }

    @CheckResult
    public static Toast warning(@StringRes int message, int duration, boolean withIcon) {
        return custom(context.getString(message), ToastyUtils.getDrawable(context, R.drawable.ic_toast_error_outline_white_24dp),
                ToastyUtils.getColor(context, R.color.toast_warningColor), ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, true);
    }

    @CheckResult
    public static Toast warning(@NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(message, ToastyUtils.getDrawable(context, R.drawable.ic_toast_error_outline_white_24dp),
                ToastyUtils.getColor(context, R.color.toast_warningColor), ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, true);
    }

    @CheckResult
    public static Toast info(@StringRes int message) {
        return info(context.getString(message), Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast info(@NonNull CharSequence message) {
        return info(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast info(@StringRes int message, int duration) {
        return info(context.getString(message), duration, true);
    }

    @CheckResult
    public static Toast info(@NonNull CharSequence message, int duration) {
        return info(message, duration, true);
    }

    @CheckResult
    public static Toast info(@StringRes int message, int duration, boolean withIcon) {
        return custom(context.getString(message), ToastyUtils.getDrawable(context, R.drawable.ic_toast_info_outline_white_24dp),
                ToastyUtils.getColor(context, R.color.toast_infoColor), ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, true);
    }

    @CheckResult
    public static Toast info(@NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(message, ToastyUtils.getDrawable(context, R.drawable.ic_toast_info_outline_white_24dp),
                ToastyUtils.getColor(context, R.color.toast_infoColor), ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, true);
    }

    @CheckResult
    public static Toast success(@StringRes int message) {
        return success(context.getString(message), Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast success(@NonNull CharSequence message) {
        return success(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast success(@StringRes int message, int duration) {
        return success(context.getString(message), duration, true);
    }

    @CheckResult
    public static Toast success(@NonNull CharSequence message, int duration) {
        return success(message, duration, true);
    }

    @CheckResult
    public static Toast success(@StringRes int message, int duration, boolean withIcon) {
        return custom(context.getString(message), ToastyUtils.getDrawable(context, R.drawable.ic_toast_check_white_24dp),
                ToastyUtils.getColor(context, R.color.toast_successColor), ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, true);
    }

    @CheckResult
    public static Toast success(@NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(message, ToastyUtils.getDrawable(context, R.drawable.ic_toast_check_white_24dp),
                ToastyUtils.getColor(context, R.color.toast_successColor), ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, true);
    }

    @CheckResult
    public static Toast error(@StringRes int message) {
        return error(context.getString(message), Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast error(@NonNull CharSequence message) {
        return error(message, Toast.LENGTH_SHORT, true);
    }

    @CheckResult
    public static Toast error(@StringRes int message, int duration) {
        return error(context.getString(message), duration, true);
    }

    @CheckResult
    public static Toast error(@NonNull CharSequence message, int duration) {
        return error(message, duration, true);
    }

    @CheckResult
    public static Toast error(@StringRes int message, int duration, boolean withIcon) {
        return custom(context.getString(message), ToastyUtils.getDrawable(context, R.drawable.ic_toast_clear_white_24dp),
                ToastyUtils.getColor(context, R.color.toast_errorColor), ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, true);
    }

    @CheckResult
    public static Toast error(@NonNull CharSequence message, int duration, boolean withIcon) {
        return custom(message, ToastyUtils.getDrawable(context, R.drawable.ic_toast_clear_white_24dp),
                ToastyUtils.getColor(context, R.color.toast_errorColor), ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, true);
    }

    @CheckResult
    public static Toast custom(@StringRes int message, Drawable icon,
                               int duration, boolean withIcon) {
        return custom(context.getString(message), icon, -1, ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, false);
    }

    @CheckResult
    public static Toast custom(@NonNull CharSequence message, Drawable icon,
                               int duration, boolean withIcon) {
        return custom(message, icon, -1, ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, false);
    }

    @CheckResult
    public static Toast custom(@StringRes int message, @DrawableRes int iconRes,
                               @ColorInt int tintColor, int duration,
                               boolean withIcon, boolean shouldTint) {
        return custom(context.getString(message), ToastyUtils.getDrawable(context, iconRes),
                tintColor, ToastyUtils.getColor(context, R.color.toast_defaultTextColor), duration, withIcon, shouldTint);
    }

    @CheckResult
    public static Toast custom(@NonNull CharSequence message, @DrawableRes int iconRes,
                               @ColorInt int tintColor, int duration,
                               boolean withIcon, boolean shouldTint) {
        return custom(message, ToastyUtils.getDrawable(context, iconRes),
                tintColor, ToastyUtils.getColor(context, R.color.toast_defaultTextColor), duration, withIcon, shouldTint);
    }

    @CheckResult
    public static Toast custom(@StringRes int message, Drawable icon,
                               @ColorInt int tintColor, int duration,
                               boolean withIcon, boolean shouldTint) {
        return custom(context.getString(message), icon, tintColor, ToastyUtils.getColor(context, R.color.toast_defaultTextColor),
                duration, withIcon, shouldTint);
    }

    @CheckResult
    public static Toast custom(@StringRes int message, Drawable icon,
                               @ColorInt int tintColor, @ColorInt int textColor, int duration,
                               boolean withIcon, boolean shouldTint) {
        return custom(context.getString(message), icon, tintColor, textColor,
                duration, withIcon, shouldTint);
    }

    /**
     * @param message Toast文字内容
     * @param icon icon图片
     * @param tintColor 背景色
     * @param textColor 文字颜色
     * @param duration 显示时间
     * @param withIcon 是否显示icon
     * @param shouldTint
     * @return
     */
    @SuppressLint("ShowToast")
    @CheckResult
    public static Toast custom(@NonNull CharSequence message, Drawable icon,
                               @ColorInt int tintColor, @ColorInt int textColor, int duration,
                               boolean withIcon, boolean shouldTint) {
        final Toast currentToast = Toast.makeText(context, "", duration);
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.toast_layout, null);
        final ImageView toastIcon = toastLayout.findViewById(R.id.toast_icon);
        final TextView toastTextView = toastLayout.findViewById(R.id.toast_text);
        Drawable drawableFrame;

        if (shouldTint)
            drawableFrame = ToastyUtils.tint9PatchDrawableFrame(context, tintColor);
        else
            drawableFrame = ToastyUtils.getDrawable(context, R.drawable.toast_frame);
        ToastyUtils.setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon == null)
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            ToastyUtils.setBackground(toastIcon, tintIcon ? ToastyUtils.tintIcon(icon, textColor) : icon);
        } else {
            toastIcon.setVisibility(View.GONE);
        }

        toastTextView.setText(message);
        toastTextView.setTextColor(textColor);
        toastTextView.setTypeface(currentTypeface);
        toastTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);

        currentToast.setView(toastLayout);

        if (!allowQueue){
            if (lastToast != null) {
                lastToast.cancel();
            }
            lastToast = currentToast;
        }
        return currentToast;
    }

    public static class Config {
        private Typeface typeface = FToast.currentTypeface;
        private int textSize = FToast.textSize;

        private boolean tintIcon = FToast.tintIcon;
        //是否允许排队，true:先后一个个展示   false：前一个需要展示2秒，但在1s的时候有新的Toast将会覆盖前一个
        private boolean allowQueue = true;

        private Config() {
            // avoiding instantiation
        }

        @CheckResult
        public static Config getInstance() {
            return new Config();
        }

        public static void reset() {
            FToast.currentTypeface = LOADED_TOAST_TYPEFACE;
            FToast.textSize = textSizeDef;
            FToast.tintIcon = tintIconDef;
            FToast.allowQueue = allowQueueDef;
        }

        @CheckResult
        public Config setToastTypeface(@NonNull Typeface typeface) {
            this.typeface = typeface;
            return this;
        }

        @CheckResult
        public Config setTextSize(int sizeInSp) {
            this.textSize = sizeInSp;
            return this;
        }

        @CheckResult
        public Config tintIcon(boolean tintIcon) {
            this.tintIcon = tintIcon;
            return this;
        }

        @CheckResult
        public Config allowQueue(boolean allowQueue) {
            this.allowQueue = allowQueue;
            return this;
        }

        public void apply() {
            FToast.currentTypeface = typeface;
            FToast.textSize = textSize;
            FToast.tintIcon = tintIcon;
            FToast.allowQueue = allowQueue;
        }
    }
}
