package com.example.gottgried.totica;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Gottgried on 03.10.2017.
 */

public class FragmentTemplate extends Fragment implements Contract.View{
    private Contract.Presenter presenter;


    public FragmentTemplate() {
        // Required empty public constructor
    }

    @Override
    public void setPresenter(Contract.Presenter presenter){
        this.presenter = presenter;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_page, container, false);


        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(presenter == null){
            presenter = new Presenter();
        }
        presenter.subscribe();
    }

    @Override
    public void onDestroy() {
        presenter.unsubsribe();
        super.onDestroy();
    }
}
