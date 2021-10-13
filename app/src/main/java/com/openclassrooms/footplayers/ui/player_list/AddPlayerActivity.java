package com.openclassrooms.footplayers.ui.player_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.openclassrooms.footplayers.R;
import com.openclassrooms.footplayers.databinding.ActivityAddPlayerBinding;
import com.openclassrooms.footplayers.databinding.ActivityPlayersDetailsBinding;
import com.openclassrooms.footplayers.di.DI;
import com.openclassrooms.footplayers.model.Player;
import com.openclassrooms.footplayers.service.PlayerApiService;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import butterknife.OnClick;

public class AddPlayerActivity extends AppCompatActivity {


    ImageView avatar;
    EditText nameInput;
    EditText phoneInput;
    EditText addressInput;
    EditText aboutMeInput;
    Button addButton;

    private PlayerApiService mApiService;
    private String mPlayerImage;
    private boolean favorite;

    // calling binding class for activity_main.xml
    // which is generated automatically.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // calling binding class for activity_add_player.xml
        // which is generated automatically.
        // inflating our xml layout in our activity
        ActivityAddPlayerBinding binding = ActivityAddPlayerBinding.inflate ( this.getLayoutInflater () );

        // getting our root layout in our view.
        View view = binding.getRoot();

        // below line is to set
        // Content view for our layout.
        setContentView(view);

        Objects.requireNonNull ( getSupportActionBar () ).setDisplayHomeAsUpEnabled(true);

        mApiService = DI.getPlayerApiService ();
        avatar = binding.avatar;
        nameInput = binding.nameLyt;//findViewById ( R.id.nameLyt );
        phoneInput = binding.phoneNumberLyt;
        addressInput = binding.addressLyt;
        aboutMeInput = binding.aboutMeLyt;
        addButton = binding.create;
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home : {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mPlayerImage = randomImage();
        Glide.with(this).load(mPlayerImage).placeholder( R.drawable.ic_account)
                .apply( RequestOptions.circleCropTransform()).into(avatar);
        nameInput.addTextChangedListener(new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                addButton.setEnabled(s.length() > 0);
            }
        });

    }

    @OnClick()
    void addPlayer() {

        Player player = new Player (
                System.currentTimeMillis(),
                nameInput.getText().toString (),
                mPlayerImage,
                addressInput.getText ().toString(),
                phoneInput.getText ().toString(),
                aboutMeInput.getText().toString(),
                favorite
        );
        mApiService.createPlayer ( player );
        finish();
    }

    /**
     * Generate a random image. Useful to mock image picker
     * @return String
     */
    String randomImage() {
        return "https://i.pravatar.cc/150?u="+ System.currentTimeMillis();
    }

    /**
     * Used to navigate to this activity
     * @param activity
     */
    public static void navigate(FragmentActivity activity) {
        Intent intent = new Intent (activity, AddPlayerActivity.class);
        ActivityCompat.startActivity(activity, intent, null);
    }
}
