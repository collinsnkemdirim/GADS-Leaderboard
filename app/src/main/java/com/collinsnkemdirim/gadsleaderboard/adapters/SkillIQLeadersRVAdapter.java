package com.collinsnkemdirim.gadsleaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.collinsnkemdirim.gadsleaderboard.model.SkillLeaders;

import java.util.List;

public class SkillIQLeadersRVAdapter extends RecyclerView.Adapter<SkillIQLeadersRVAdapter.ViewHolder> {

    private List<SkillLeaders> items;
    private Context context;

    public SkillIQLeadersRVAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public SkillIQLeadersRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.skills_lead_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void setItems(List<SkillLeaders> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQLeadersRVAdapter.ViewHolder holder, int position) {
        SkillLeaders item = items.get(position);
        holder.nameTitle.setText(item.getName());
        holder.scoreTitle.setText(context.getString(R.string.skills_leader_details, item.getScore(), item.getCountry()));

    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTitle, scoreTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTitle = itemView.findViewById(R.id.name_txtView);
            scoreTitle = itemView.findViewById(R.id.score_txtView);
        }
    }
}
