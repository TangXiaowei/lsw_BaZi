package lsw.lunar_calendar.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.SpannableString;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.HashMap;

import lsw.library.ColorHelper;
import lsw.lunar_calendar.R;

/**
 * Created by swli on 8/3/2015.
 */
public class EraDayTextView extends TextView {

    static HashMap<String,SpannableString> hashMap = new HashMap<String, SpannableString>();

    private boolean isDisable;
    private ColorHelper colorHelper;
    public EraDayTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        colorHelper = ColorHelper.getInstance(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.EraDayTextView);

        String c = typedArray.getString(R.styleable.EraDayTextView_CelestialText);
        String t = typedArray.getString(R.styleable.EraDayTextView_TerrestrialText);
        isDisable = typedArray.getBoolean(R.styleable.EraDayTextView_IsDisable, false);

        setColorText(c, t, isDisable, false);

        typedArray.recycle();
    }

    private SpannableString getCSapnnable(String c)
    {
        if(!hashMap.containsKey(c)) {
            SpannableString cString = colorHelper.getColorCelestialStem( c);
            hashMap.put(c,cString);
        }
        return hashMap.get(c);
    }

    private SpannableString getTSapnnable(String t)
    {
        if(!hashMap.containsKey(t)) {
            SpannableString tString = colorHelper.getColorTerrestrial( t);
            hashMap.put(t,tString);
        }

        return hashMap.get(t);
    }

    public void setColorText(String c, String t, boolean isNotDisable, boolean isSolarTerm)
    {
        setText("");
        if(isNotDisable) {
            this.append(getCSapnnable(c));
            this.append(getTSapnnable(t));
        }
        else {
            if(isSolarTerm)
            {
                this.append(ColorHelper.getTextByColor(c, ColorHelper.getSolarTermColor()));
                this.append(ColorHelper.getTextByColor(t, ColorHelper.getSolarTermColor()));
            }
            else {
                this.append(ColorHelper.getTextByColor(c, Color.LTGRAY));
                this.append(ColorHelper.getTextByColor(t, Color.LTGRAY));
            }
        }
    }

}
