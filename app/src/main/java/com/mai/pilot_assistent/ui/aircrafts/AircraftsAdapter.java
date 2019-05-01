package com.mai.pilot_assistent.ui.aircrafts;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.mai.pilot_assistent.R;
import com.mai.pilot_assistent.data.db.model.Aircraft;
import com.mai.pilot_assistent.ui.base.BaseViewHolder;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class AircraftsAdapter extends RecyclerView.Adapter<BaseViewHolder>{

    private Callback mCallback;
    private List<Aircraft> aircraftList;

    public AircraftsAdapter(List<Aircraft> aircraftList) {
        this.aircraftList = aircraftList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view_aircraft, parent, false));

    }

    @Override
    public int getItemCount() {
        if (aircraftList != null && aircraftList.size() > 0) {
            return aircraftList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<Aircraft> repoList) {
        aircraftList.addAll(repoList);
        notifyDataSetChanged();
    }

    public interface Callback {
        void onEmptyViewRetrySwipe();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.image_aircraft)
        CircularImageView aircraftImageView;

        @BindView(R.id.name_aircraft)
        TextView aircraftTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            aircraftImageView.setImageDrawable(null);
            aircraftTextView.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);

            final Aircraft aircraft = aircraftList.get(position);

            if (aircraft.getImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(aircraft.getImageUrl())
                        .asBitmap()
                        .centerCrop()
                        .into(aircraftImageView);
            }

            if (aircraft.getName() != null) {
                aircraftTextView.setText(aircraft.getName());
            }

            itemView.setOnClickListener(v -> {
                     //TODO написать обработчик по элементу списка
            });
        }
    }

}
