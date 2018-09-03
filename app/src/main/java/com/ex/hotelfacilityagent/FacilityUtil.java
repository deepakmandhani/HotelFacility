package com.ex.hotelfacilityagent;

import android.content.Context;
import android.graphics.drawable.Drawable;

import agent.ex.com.hotelfacilityagent.R;

public class FacilityUtil {

    public static Drawable getFacilityOptionDrawable(String option, Context context) {

        Drawable drawable = null;
        switch (option) {
            case "apartment":
                drawable = context.getDrawable(R.mipmap.apartment);
                break;

            case "condo":
                drawable = context.getDrawable(R.mipmap.condo);
                break;

            case "boat":
                drawable = context.getDrawable(R.mipmap.boat);
                break;

            case "land":
                drawable = context.getDrawable(R.mipmap.land);
                break;

            case "rooms":
                drawable = context.getDrawable(R.mipmap.rooms);
                break;

            case "no-room":
                drawable = context.getDrawable(R.mipmap.noroom);
                break;

            case "swimming":
                drawable = context.getDrawable(R.mipmap.swimming);
                break;

            case "garden":
                drawable = context.getDrawable(R.mipmap.garden);
                break;

            case "garage":
                drawable = context.getDrawable(R.mipmap.garage);
                break;
        }
        return drawable;
    }
}
