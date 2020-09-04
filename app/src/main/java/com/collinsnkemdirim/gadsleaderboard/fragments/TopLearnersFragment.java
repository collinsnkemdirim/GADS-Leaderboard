package com.collinsnkemdirim.gadsleaderboard.fragments;

import android.os.Bundle;

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


import com.collinsnkemdirim.gadsleaderboard.adapters.TopLearnersRVAdapter;
import com.collinsnkemdirim.gadsleaderboard.model.TopLearners;

import com.collinsnkemdirim.gadsleaderboard.viewmodel.TopLearnersViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TopLearnersFragment} factory method to
 * create an instance of this fragment.
 */
public class TopLearnersFragment extends Fragment {



    private TopLearnersViewModel viewModel;
    private DialogFragment errorDialogFragment;


    public TopLearnersFragment() {
        // Required empty public constructor
    }

    public static TopLearnersFragment newInstance(){
        TopLearnersFragment fragment = new TopLearnersFragment();
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(TopLearnersViewModel.class);
        errorDialogFragment = OkDialogFragment.newInstance(getString(R.string.network_error_message));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top_learners, container, false);
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
        final TopLearnersRVAdapter adapter = new TopLearnersRVAdapter(getContext());
        recyclerView.setAdapter(adapter);

        viewModel.getTopLearners().observe(getViewLifecycleOwner(), new Observer<List<TopLearners>>() {
            @Override
            public void onChanged(List<TopLearners> topLearners) {
                adapter.setItems(topLearners);
                refreshLayout.setRefreshing(false);
                if(topLearners.size() > 0){
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
                        errorDialogFragment.show(getFragmentManager(), "TopLearnersFragment_OkDialog");
                    }
                }
            }
        });
        return  view;

    }
}