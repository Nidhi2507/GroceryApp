package com.dioco.groceyapp.customecomponent;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import com.dioco.groceyapp.R;
import com.dioco.groceyapp.util.FontCache;


public class CustomButton extends AppCompatButton {

    private Typeface fontType;

    /**
     * Set the font according to style
     *
     * @param context context
     * @param attrs   style attribute like bold, italic
     */


    public CustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (isInEditMode())
            return;
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView);
        String fontName = ta.getString(R.styleable.CustomTextView_font_name);
        if (fontName != null) {
            final Typeface font = FontCache.getInstance().getFont(context, "fonts/" + fontName);
            setTypeface(font);
        }
        ta.recycle();
    }


}