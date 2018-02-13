/*
 * MIT License
 *
 * Copyright (c) 2018 Vaisakh J Nair
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.fugenapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
                        .setBlurRadius(0)
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

    public void filterList(ArrayList<Event> newData) {
        this.data = newData;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView eventName;
        private TextView eventDate;
        private TextView eventDesc;

        ViewHolder(View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name);
            eventDate = itemView.findViewById(R.id.event_date);
            eventDesc = itemView.findViewById(R.id.event_desc);
        }
    }
}
