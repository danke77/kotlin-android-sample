package com.danke.contacts.medium.widget

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import com.danke.contacts.R

/**
 * @author danke (https://github.com/danke77)
 * *
 * @date 16/7/9
 */
class CommonNumberText(context: Context, attrs: AttributeSet) : AutoResizeTextView(context, attrs) {
    init {

        //Typeface.createFromAsset doesn't work in the layout editor. Skipping...
        //Note: you won't be able to see your custom font in Eclipse's layout editor.
        //This is why I put the isInEditMode() check. But if you run your app, the custom font will work like a charm.
        if (!isInEditMode) {
            val styledAttrs = context.obtainStyledAttributes(attrs, R.styleable.NumberTextTextView)
            val fontName = styledAttrs.getString(R.styleable.NumberTextTextView_typeface)
            styledAttrs.recycle()

            if (fontName != null) {
                val typeface = Typeface.createFromAsset(context.assets, "Roboto/" + fontName)
                setTypeface(typeface)
            }
        }
    }
}
