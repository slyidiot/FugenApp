package com.fugenapp.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.fugenapp.FugenApp;
import com.fugenapp.R;
import com.fugenapp.database.model.Event;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;

import jp.wasabeef.blurry.Blurry;

public class EventDetailPopup extends BlurPopupWindow {

    private static Event event;
    private ImageView image;
    private TextView name;
    private TextView date;
    private TextView time;
    private TextView desc;
    private TextView contact;

    public EventDetailPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    public View getContentView() {
        return super.getContentView();
    }

    @Override
    protected View createContentView(ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popup_item, parent, false);

        LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        view.setLayoutParams(lp);
        view.setVisibility(INVISIBLE);

        image = view.findViewById(R.id.popup_event_image);
        name = view.findViewById(R.id.popup_event_name);
        date = view.findViewById(R.id.popup_event_date);
        time = view.findViewById(R.id.popup_event_time);
        desc = view.findViewById(R.id.popup_event_desc);
        contact = view.findViewById(R.id.popup_event_contact);

        name.setText(event.name);
        date.setText(event.date);
        time.setText(event.time);
        desc.setText(event.desc);
        contact.setText("Contact: " + event.contact);

        int resID = getResources().getIdentifier(event.image, "drawable", FugenApp.getAppContext().getPackageName());
        image.setImageResource(resID);
        Bitmap image = BitmapFactory.decodeResource(getResources(), resID);
        Blurry.with(getContext()).radius(5).sampling(5).from(image).into((ImageView) view.findViewById(R.id.event_poster));
        //((ImageView)findViewById(R.id.event_poster)).setImageBitmap(fastblur(image,0.2f, 10));
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