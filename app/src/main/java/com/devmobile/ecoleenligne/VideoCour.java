package com.devmobile.ecoleenligne;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoCour extends Fragment  {

    public VideoCour getVideo() {
        return video;
    }

    public void setVideo(VideoCour video) {
        this.video = video;
    }

    private VideoCour video;
    Button play;
    YouTubePlayerView mYouTubePlayerView;
    YouTubePlayer.OnInitializedListener mOnInitialiazedListner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.video_cour, container, false);
        play = view.findViewById(R.id.bt_playVideo);
        mYouTubePlayerView = view.findViewById(R.id.youtube_fragment);
        //YouTubePlayerSupportFragment youTubePlayerFragment = (YouTubePlayerSupportFragment) getActivity().getSupportFragmentManager()
          //      .findFragmentById(R.id.youtube_fragment);

    /*  YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.add(R.id.video, youTubePlayerFragment).commit();
    */
        mOnInitialiazedListner = new YouTubePlayer.OnInitializedListener(){

            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //youTubePlayer.loadVideo("0GQy5hlvPkY");
                youTubePlayer.loadPlaylist("E8q7jiPAnwQ");

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mYouTubePlayerView.initialize("AIzaSyC-HoxrKRHU4CC9YITRTkRk7qj89t-dXZg",mOnInitialiazedListner);

            }
        });

        return view;
    }
}
