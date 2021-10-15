package com.openclassrooms.footplayers.ui.player_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.footplayers.R;
import com.openclassrooms.footplayers.databinding.ActivityAddPlayerBinding;
import com.openclassrooms.footplayers.di.DI;
import com.openclassrooms.footplayers.model.Player;
import com.openclassrooms.footplayers.service.PlayerApiService;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

public class AddPlayerActivity extends AppCompatActivity {

    private PlayerApiService mApiService;
    private String mPlayerImage;

    private ActivityAddPlayerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApiService = DI.getPlayerApiService();

        initView();

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        setPlayer();

        binding.createButton.setOnClickListener(v -> addPlayer());
    }

    private void initView() {
        binding = ActivityAddPlayerBinding.inflate(this.getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }

    private void setPlayer() {
        mPlayerImage = randomImage();
        Glide.with(this).load(mPlayerImage).placeholder(R.drawable.ic_account)
                .apply(RequestOptions.circleCropTransform()).into(binding.avatar);
        binding.nameLyt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.createButton.setEnabled(s.length() > 0);
            }
        });
    }

    private void addPlayer() {
        Player player = new Player(
                System.currentTimeMillis(),
                binding.nameLyt.getText().toString(),
                mPlayerImage,
                binding.addressLyt.getText().toString(),
                binding.phoneNumberLyt.getText().toString(),
                binding.aboutMeEdit.getText().toString()
        );
        mApiService.createPlayer(player);
        finish();
    }

    /**
     * Generate a random image. Useful to mock image picker
     *
     * @return String
     */
    String randomImage() {
        return "https://i.pravatar.cc/150?u=" + System.currentTimeMillis();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
