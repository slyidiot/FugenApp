package com.fugenapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fugenapp.R;
import com.fugenapp.database.model.Event;
import com.fugenapp.interfaces.OnEventSelectedListener;
import com.fugenapp.ui.view.EventDetailPopup;
import com.kyleduo.blurpopupwindow.library.BlurPopupWindow;

import java.util.ArrayList;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {

    private ArrayList<Event> data;
    private Context context;

    public EventRecyclerAdapter(Context context, ArrayList<Event> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Event event = data.get(position);
        final int resID = context.getResources().getIdentifier(event.image, "drawable", context.getPackageName());
        holder.eventName.setText(event.name);
        holder.eventDate.setText(event.date);
        holder.eventDesc.setText(event.desc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((OnEventSelectedListener) context).onEventSelected(resID);
                new EventDetailPopup.Builder(context, event)
                        .setScaleRatio(0.2f)
                        .setBlurRadius(0)
                        .setTintColor(0x30000000)
                        .setOnDismissListener(new BlurPopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss(BlurPopupWindow popupWindow) {
                                ((OnEventSelectedListener) context).onEventDeselected();
                            }
                        })
                        .build()
                        .show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView eventName;
        private TextView eventDate;
        private TextView eventDesc;
        private ImageView status;

        ViewHolder(View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name);
            eventDate = itemView.findViewById(R.id.event_date);
            eventDesc = itemView.findViewById(R.id.event_desc);
            status = itemView.findViewById(R.id.event_status);
        }
    }
}
