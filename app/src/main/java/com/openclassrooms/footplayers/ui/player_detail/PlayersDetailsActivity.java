package com.openclassrooms.footplayers.ui.player_detail;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.footplayers.R;
import com.openclassrooms.footplayers.di.DI;
import com.openclassrooms.footplayers.model.Player;
import com.openclassrooms.footplayers.service.PlayerApiService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayersDetailsActivity extends AppCompatActivity {

    @BindView(R.id.image_details)
    public ImageView imageDetails;
    @BindView(R.id.name_in_image)
    public TextView nameImage;
    @BindView(R.id.name_in_card)
    public TextView nameCard;
    @BindView(R.id.localisation_txt)
    public TextView localisation;
    @BindView(R.id.phone_number)
    public TextView phoneNumber;
    @BindView(R.id.about_me_details)
    public TextView aboutMe;
    @BindView(R.id.web_address)
    public TextView facebookTextView;

    @BindView(R.id.FAB_detail)
    public FloatingActionButton mFab;
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    private Player mPlayer;
    private PlayerApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_players_details);
        ButterKnife.bind(this);

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
        mFab.setColorFilter((mPlayer.isFavorite()) ? Color.YELLOW : Color.GRAY);
    }

}
