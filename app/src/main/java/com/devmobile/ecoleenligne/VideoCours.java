package com.devmobile.ecoleenligne;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX;

public class VideoCours extends Fragment  {

    public VideoCours getVideo() {
        return video;
    }

    public void setVideo(VideoCours video) {
        this.video = video;
    }

    private VideoCours video;
    private ImageView retour;
    YouTubePlayerSupportFragmentX mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitialiazedListner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_cours, container, false);
        ((dashboard)getActivity()).selectedFromRetour(2);
        final YouTubePlayer[] player = new YouTubePlayer[1];
        //YouTubePlayerSupportFragment youTubePlayerFragment = (YouTubePlayerSupportFragment) getActivity().getSupportFragmentManager()
          //      .findFragmentById(R.id.youtube_fragment);
        retour=view.findViewById(R.id.retour_video);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });


        mOnInitialiazedListner = new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo("0GQy5hlvPkY");
                youTubePlayer.pause();
                player[0] =youTubePlayer;
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
              player[0].play();
            }
        };
        final YouTubePlayerSupportFragmentX youTubePlayerFragment = YouTubePlayerSupportFragmentX.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        youTubePlayerFragment.initialize("AIzaSyC-HoxrKRHU4CC9YITRTkRk7qj89t-dXZg",mOnInitialiazedListner);
        transaction.replace(R.id.youtube_fragment, youTubePlayerFragment).commit();


        return view;
    }

}
