package com.example.android.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class science extends Fragment {
    String api="3e231e44e1b1442ba9817748913dc30f";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String counrty="in";
    private RecyclerView recyclerviewofscience;
    private String category="science";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.sciencefragment,null);



        recyclerviewofscience=v.findViewById(R.id.recyclerviewofscience);
        modelClassArrayList =new ArrayList<>();
        recyclerviewofscience.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new Adapter(getContext(),modelClassArrayList);
        recyclerviewofscience.setAdapter(adapter);

        findNews();
        return v;
    }

    private void findNews() {

        ApiUtilities.getApiInteface().getCategoryNews(counrty,category,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                if(response.isSuccessful()){
                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });

    }
}
