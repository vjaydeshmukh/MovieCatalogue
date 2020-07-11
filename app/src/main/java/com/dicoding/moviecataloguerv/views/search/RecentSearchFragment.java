package com.dicoding.moviecataloguerv.views.search;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.moviecataloguerv.R;
import com.dicoding.moviecataloguerv.adapter.SearchAdapter;
import com.dicoding.moviecataloguerv.data.source.model.Search;
import com.dicoding.moviecataloguerv.viewmodel.RecentSearchViewModel;
import com.dicoding.moviecataloguerv.viewmodel.ViewModelFactory;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecentSearchFragment extends Fragment {

    private SearchAdapter adapter;
    private RecentSearchViewModel viewModel;

    public RecentSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.rv_recent_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SearchAdapter(new ArrayList<>(), onItemClicked);
        recyclerView.setAdapter(adapter);
        viewModel = obtainViewModel(requireActivity());
        viewModel.getSearchQuery().observe(getViewLifecycleOwner(), searchList -> {
            if (searchList != null) {
                adapter.refillSearch(searchList);
            }
        });
    }

    @NonNull
    private static RecentSearchViewModel obtainViewModel(FragmentActivity activity) {
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());
        return new ViewModelProvider(activity, factory).get(RecentSearchViewModel.class);
    }

    private SearchAdapter.OnItemClicked onItemClicked = new SearchAdapter.OnItemClicked() {
        @Override
        public void onDeleteClick(Search search) {
            viewModel.deleteSearch(search);
        }

        @Override
        public void onSearchClick(Search search) {
            Intent intent = new Intent(getContext(), SearchActivity.class);
            intent.putExtra(SearchActivity.SEARCH_QUERY, search.getQuery());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };
}