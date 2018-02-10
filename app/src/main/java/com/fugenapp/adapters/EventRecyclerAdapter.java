package com.fugenapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fugenapp.R;
import com.fugenapp.database.model.Event;

import java.util.ArrayList;

public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.ViewHolder> {

    private ArrayList<Event> data;

    public EventRecyclerAdapter(ArrayList<Event> data) {
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
        holder.eventName.setText(event.name);
        holder.eventDate.setText(event.date);
        holder.eventDesc.setText(event.desc);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new BlurPopupWindow.Builder(FugenApp.getAppContext())
                        .setContentView()*/
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
