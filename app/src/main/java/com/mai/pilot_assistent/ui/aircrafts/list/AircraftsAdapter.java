package com.mai.pilot_assistent.ui.aircrafts.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.db.model.Aircraft;

import java.util.ArrayList;
import java.util.List;

public class AircraftsAdapter extends RecyclerView.Adapter<AircraftsAdapter.AircraftHolder>  {


    AircraftsMvpPresenter<AircraftsMvpView> mPresenter;

    private List<Aircraft> aircrafts = new ArrayList<>();
    private OnItemClickListener onClickListener = null;

    public void setOnItemClickListener(OnItemClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public AircraftsAdapter(AircraftsMvpPresenter<AircraftsMvpView> presenter) {
        this.mPresenter = presenter;
    }

    public void setData(List<Aircraft> data) {
        aircrafts.clear();
        aircrafts.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AircraftHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewItem = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view_aircraft,viewGroup, false );
        return new AircraftsAdapter.AircraftHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull AircraftHolder aircraftHolder, int i) {
        aircraftHolder.binding(aircrafts.get(i));
    }

    @Override
    public int getItemCount() {
        return aircrafts.size();
    }




    class AircraftHolder extends RecyclerView.ViewHolder{
        View itemView;

        @BindView(R.id.image_aircraft)
        ImageView imageView;

        @BindView(R.id.name_aircraft)
        TextView nameTextView;

        @BindView(R.id.airport_aircraft)
        TextView airportTextView;

        @BindView(R.id.regnumber_aircraft)
        TextView regnumberTextView;

        private Context context;

        AircraftHolder(@NonNull final View itemView) {
            super(itemView);
            this.context = context;
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }

        public void binding(Aircraft aircraft){
            if (aircraft.getImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(aircraft.getImageUrl())
                        .error((R.drawable.ic_rabbit))
                        .centerCrop()
                        .into(imageView);
            }
            if(aircraft.getAirportId() != null){
                airportTextView.setText(mPresenter.loadAirportById(aircraft.getAirportId()).getNameAirport());
            }
            nameTextView.setText(aircraft.getName());
            regnumberTextView.setText(aircraft.getRegistrationName());
            itemView.setOnClickListener((view) -> onClickListener.onItemClick(aircraft));
        }

    }

    public interface OnItemClickListener {
        void onItemClick(Aircraft aircraft);
    }
}