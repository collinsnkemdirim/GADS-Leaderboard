package com.collinsnkemdirim.gadsleaderboard.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gadsleaderboard.R;
import com.collinsnkemdirim.gadsleaderboard.adapters.SkillIQLeadersRVAdapter;
import com.collinsnkemdirim.gadsleaderboard.model.SkillLeaders;
import com.collinsnkemdirim.gadsleaderboard.viewmodel.SkillLeadersViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SkillLeadersFragment} factory method to
 * create an instance of this fragment.
 */
public class SkillLeadersFragment extends Fragment {

    private SkillLeadersViewModel viewModel;
    private DialogFragment errorDialogFragment;


    public SkillLeadersFragment() {
        // Required empty public constructor
    }

    public static SkillLeadersFragment newInstance(){
        SkillLeadersFragment fragment = new SkillLeadersFragment();
        return fragment;
    }

    @NonNull
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SkillLeadersViewModel.class);
        errorDialogFragment = OkDialogFragment.newInstance(getString(R.string.network_error_message));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skills_lead, container, false);
        final View emptyView = view.findViewById(R.id.emptyView);

        final SwipeRefreshLayout refreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setRefreshing(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refreshingList();
            }
        });

        final RecyclerView recyclerView = view.findViewById(R.id.recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        final SkillIQLeadersRVAdapter adapter = new SkillIQLeadersRVAdapter(getContext());
        recyclerView.setAdapter(adapter);

        viewModel.getSkillLeaders().observe(getViewLifecycleOwner(), new Observer<List<SkillLeaders>>() {
            @Override
            public void onChanged(List<SkillLeaders> skillLeaders) {
                adapter.setItems(skillLeaders);
                refreshLayout.setRefreshing(false);
                if(skillLeaders.size() > 0){
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyView.setVisibility(View.GONE);
                }else{
                    emptyView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

            }
        });
        viewModel.getErrorMessage().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean error) {
                if(error){
                    refreshLayout.setRefreshing(false);
                    if(adapter.getItemCount() > 0){
                        Toast.makeText(getContext(), getString(R.string.network_error_message), Toast.LENGTH_SHORT).show();
                    }else{
                        recyclerView.setVisibility(View.GONE);
                        emptyView.setVisibility(View.VISIBLE);
                        errorDialogFragment.show(getFragmentManager(), "SkillLeadersFragment_OkDialog");
                    }
                }
            }
        });
        return  view;
    }
}