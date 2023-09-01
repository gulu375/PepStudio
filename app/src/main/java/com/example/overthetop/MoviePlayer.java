package com.example.overthetop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.exoplayer2.MediaItem;
//import com.google.android.exoplayer2.SimpleExoPlayer;
//import com.google.android.exoplayer2.source.MediaSource;
//import com.google.android.exoplayer2.source.dash.DashMediaSource;
//import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
//import com.google.android.exoplayer2.ui.PlayerView;
//import com.google.android.exoplayer2.ui.TrackSelectionDialogBuilder;
//import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

import java.util.concurrent.TimeUnit;

public class MoviePlayer extends AppCompatActivity {
    TextView duration, heading, running;
    VideoView videoView;
    SeekBar play_line;
    ImageButton play, forward, backward;



//  Exoplayer var
//    SimpleExoPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_movie_player);
        Intent list = getIntent();
        Movies object = (Movies) list.getExtras().getSerializable("collection");


        duration = findViewById(R.id.duration);
        running = findViewById(R.id.running);
        heading = findViewById(R.id.heading);
        play_line = findViewById(R.id.playline);
        heading.setText(object.getMovie_Name());
        play = findViewById(R.id.play);
        forward = findViewById(R.id.forward);
        backward = findViewById(R.id.backward);
        videoView = findViewById(R.id.movie_player);
        Uri uri = Uri.parse(object.VideoLink);
        videoView.setVideoURI(uri);
        /*
         * When the VideoView is loaded with content
         * it sets the duration with seekbar and starts the video.
         */
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                play_line.setMax(videoView.getDuration());
                videoView.start();
            }
        });
        /*
         * Whenever the play button is clicked
         * it pauses and resumes accordingly.
         */
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!videoView.isPlaying()) {
                    videoView.start();
                    play.setImageDrawable(getDrawable(R.drawable.baseline_pause_circle_outline_24));
                } else {
                    videoView.pause();
                    play.setImageDrawable(getDrawable(R.drawable.baseline_play_circle_outline_24));
                }

            }
        });
        /*
         * On Click 10 seconds forwarded
         */
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(videoView.getCurrentPosition() + 10000);
            }
        });
        /*
         * On Click 10 seconds backward
         */
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.seekTo(videoView.getCurrentPosition() - 10000);
            }
        });
        /*
         * On Click the control layer visibility changes
         */
        findViewById(R.id.movieplayer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (findViewById(R.id.frontlayer).getVisibility() == View.GONE) {
                    findViewById(R.id.frontlayer).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.frontlayer).setVisibility(View.GONE);
                }
            }
        });
        initializeseekbar();
        sethadler();

    }

    /*
     * It initializes the seekbar with current running time.
     */
    private void initializeseekbar() {
        play_line.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (play_line.getId() == R.id.playline) {
                    if (b) {
                        videoView.seekTo(i);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    @SuppressLint("DefaultLocale")
    private String timerString(long millis){
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }
    /*
     * Whenever the seekbar's progress manually controlled
     * get sync with the video.
     *
     * Current running time and duration get sync.
     */
    private void sethadler() {
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                if (videoView.getDuration() > 0) {
                    play_line.setProgress(videoView.getCurrentPosition());
                    duration.setText(timerString(videoView.getDuration()));
                    running.setText(timerString(videoView.getCurrentPosition()));
                }
                handler.postDelayed(this, 0);
            }
        };
        handler.postDelayed(runnable, 0);
    }
}