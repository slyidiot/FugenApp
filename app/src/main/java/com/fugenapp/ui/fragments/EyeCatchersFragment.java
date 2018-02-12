package com.fugenapp.ui.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fugenapp.FugenApp;
import com.fugenapp.R;
import com.fugenapp.adapters.EventRecyclerAdapter;
import com.fugenapp.database.FugenAppDatabase;
import com.fugenapp.database.model.Event;

import java.util.ArrayList;

public class EyeCatchersFragment extends Fragment {
    private View convertView;

    private RecyclerView recyclerView;

    private ArrayList<Event> events;
    private EventRecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment_events, container, false);
        new EyeCatchersFragment.LoadData().execute();
        return convertView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = convertView.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
    }

    private void displayData() {
        adapter = new EventRecyclerAdapter(getActivity(), events);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    private class LoadData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            events = new FugenAppDatabase(getActivity()).getFilteredEvents(FugenApp.EYE_CATCHERS);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            displayData();
        }
    }
}
