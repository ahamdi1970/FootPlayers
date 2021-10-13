package com.openclassrooms.footplayers.ui.player_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.openclassrooms.footplayers.databinding.ActivityPlayersDetailsBinding;
import com.openclassrooms.footplayers.di.DI;
import com.openclassrooms.footplayers.model.Player;
import com.openclassrooms.footplayers.service.PlayerApiService;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

public class PlayersDetailsActivity extends AppCompatActivity {

    public ImageView imageDetails;

    public TextView nameImage;

    public TextView nameCard;

    public TextView localisation;

    public TextView phoneNumber;

    public TextView aboutMe;

    public TextView facebookTextView;


    public AppCompatButton mFab;

    public Toolbar mToolbar;

    private Player mPlayer;
    private PlayerApiService mApiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        @NonNull ActivityPlayersDetailsBinding binding = ActivityPlayersDetailsBinding.inflate ( this.getLayoutInflater () );
        setContentView(binding.getRoot ());

        imageDetails = binding.imageDetails;
        nameImage = binding.nameInImage;
        nameCard = binding.nameInCard;
        localisation = binding.localisationTxt;
        phoneNumber = binding.phoneNumber;
        aboutMe = binding.aboutMe;
        facebookTextView = binding.webAddress;
        mFab = binding.FABDetail;
        mToolbar = binding.toolbar;

        mApiService = DI.getPlayerApiService ();

        setToolbarAttribute();

        setListeners();

        setGraphicElement();

        fabColor();
    }

    public void setGraphicElement () {
        mPlayer = getIntent().getExtras().getParcelable("Player item");

        nameImage.setText(mPlayer.getName());
        nameCard.setText(mPlayer.getName());
        localisation.setText(mPlayer.getAddress());
        phoneNumber.setText(mPlayer.getPhoneNumber());
        aboutMe.setText(mPlayer.getAboutMe());
        facebookTextView.setText("www.facebook.fr/"+mPlayer.getName());
        Glide.with(this).load(mPlayer.getAvatarUrl()).into(imageDetails);
    }

    private void setToolbarAttribute () {

        // Toolbar Transparent
        mToolbar.setBackgroundColor( Color.TRANSPARENT);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setListeners() {
             mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFavoriteState();
            }
        });
    }

    private void changeFavoriteState() {
        mApiService.isFavorite(mPlayer);
        mPlayer.setIsFavorite(!mPlayer.isFavorite());
        fabColor();
    }

    private void fabColor() {
        mFab.setTextColor ((mPlayer.isFavorite()) ? Color.YELLOW : Color.GRAY);
    }

}
