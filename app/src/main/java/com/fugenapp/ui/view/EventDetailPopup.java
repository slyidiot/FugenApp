package com.fugenapp.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.fugenapp.R;
import com.fugenapp.database.model.Event;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;

public class EventDetailPopup extends BlurPopupWindow {

    private static Event event;

    public EventDetailPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected View createContentView(ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popup_item, parent, false);
        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        view.setLayoutParams(lp);
        view.setVisibility(INVISIBLE);

        ((TextView) view.findViewById(R.id.blah)).setText(event.name);
        return view;
    }

    @Override
    protected void onShow() {
        super.onShow();
        getContentView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                getContentView().setVisibility(VISIBLE);
            }
        });

    }

    public static class Builder extends BlurPopupWindow.Builder<EventDetailPopup> {
        public Builder(Context context, Event e) {
            super(context);
            this.setBlurRadius(0).setTintColor(0x70000000);
            event = e;
        }

        @Override
        protected EventDetailPopup createPopupWindow() {
            return new EventDetailPopup(mContext);
        }
    }
}
