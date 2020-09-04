package com.collinsnkemdirim.gadsleaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.collinsnkemdirim.gadsleaderboard.model.TopLearners;

import java.util.List;

public class TopLearnersRVAdapter extends RecyclerView.Adapter<TopLearnersRVAdapter.ViewHolder> {

    private List<TopLearners> items;
    private Context context;


    public TopLearnersRVAdapter(Context context){
        this.context = context;
    }

    public void setItems(List<TopLearners> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TopLearnersRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_learners_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopLearnersRVAdapter.ViewHolder holder, int position) {
        TopLearners item = items.get(position);
        holder.nameTitle.setText(item.getName());
        holder.hoursTitle.setText(context.getString(R.string.top_learners_details, item.getHours(), item.getCountry()));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTitle, hoursTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTitle = itemView.findViewById(R.id.txtView_name);
            hoursTitle = itemView.findViewById(R.id.txtView_hours);
        }
    }
}
