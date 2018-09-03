package com.ex.hotelfacilityagent.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ex.hotelfacilityagent.FacilityUtil;
import com.ex.hotelfacilityagent.model.Facility;
import com.ex.hotelfacilityagent.model.Option;
import com.ex.hotelfacilityagent.mvp.presenter.FacilityPresenter;

import java.util.List;

import agent.ex.com.hotelfacilityagent.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class FacilityListRecyclerViewAdapter extends Adapter<FacilityListRecyclerViewAdapter.FacilityItemViewHolder> {

    private Context context;
    private List<Facility> facilityList;
    private FacilityPresenter facilityPresenter;

    public FacilityListRecyclerViewAdapter(Context context, List<Facility> facilityList, FacilityPresenter facilityPresenter) {
        this.context = context;
        this.facilityList = facilityList;
        this.facilityPresenter = facilityPresenter;
    }

    @Override
    public FacilityItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_facility_recycler, parent, false);
        return new FacilityItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FacilityItemViewHolder holder, int position) {
        Facility facility = facilityList.get(position);

        holder.facilityName.setText(facility.getName());
        holder.facilityOptions.setTag(facility.getFacilityId());

        for (Option option:facility.getOptions()
             ) {
            RadioButton radioButton = new RadioButton(context);
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setText(option.getName());
            Drawable drawable = FacilityUtil.getFacilityOptionDrawable(option.getIcon(), context);
            radioButton.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
            radioButton.setTag(option.getId());
            holder.facilityOptions.addView(radioButton);
            holder.facilityOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton checkedRadioButton = radioGroup.findViewById(i);
                    facilityPresenter.addOrUpdateSelectedFacilityToList((String) radioGroup.getTag(), String.valueOf(checkedRadioButton.getTag()));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return facilityList != null?facilityList.size():0;
    }

    public static class FacilityItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.facility_name_tv)
        TextView facilityName;

        @BindView(R.id.facility_group_rg)
        RadioGroup facilityOptions;

        public FacilityItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
