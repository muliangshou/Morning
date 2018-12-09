package com.example.frank.morning;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class SocialFragment extends Fragment {
    RecyclerView recyclerView;
    FriendActivityListAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v;

        v = inflater.inflate(R.layout.frag_social_container, null);
        final FrameLayout containerLayout = v.findViewById(R.id.socialContainer);

        View subview;
        if (!MainActivity.isConnectedFacebook) {
            subview = inflater.inflate(R.layout.frag_social_connect, null);
            Button connectToFacebookButton = subview.findViewById(R.id.connectFBButton);
            connectToFacebookButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View socialView = inflater.inflate(R.layout.frag_social, null);
                    containerLayout.removeAllViews();
                    layout_UI(socialView);
                    containerLayout.addView(socialView);
                }
            });
            containerLayout.addView(subview);
            MainActivity.isConnectedFacebook = true;
        } else {
            View socialView = inflater.inflate(R.layout.frag_social, null);
            layout_UI(socialView);
            v = socialView;

        }



        return v;

    }


    private void layout_UI(View root) {
        List<FriendTask> newList = new ArrayList<>();
        newList.add(new FriendTask("Movie", "John", "Wed, 11/11, 12:30PM"));
        recyclerView = root.findViewById(R.id.friendTaskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FriendActivityListAdapter(getContext(), newList, this);
        recyclerView.setAdapter(adapter);
    }



}
