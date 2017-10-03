package com.example.gottgried.totica.profilePage;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gottgried.totica.BasePresenter;
import com.example.gottgried.totica.R;
import com.example.gottgried.totica.login.LoginActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilePageFragment extends Fragment implements ProfilePageContract.View{
    private ProfilePageContract.Presenter presenter;

    private TextView userBio, userInterests, userName, userEmail;
    private ImageView thumbnail;
    private FloatingActionButton editDetails;
    private ImageButton settings, logout;
    private ProgressBar avatarProgress, bioProgress, interestsProgress;

    public ProfilePageFragment() {
        // Required empty public constructor
    }

    public static ProfilePageFragment newInstance(){
        ProfilePageFragment fragment = new ProfilePageFragment();
        return fragment;
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


        userName = (TextView)v.findViewById(R.id.lbl_page_user_name);
        userEmail = (TextView)v.findViewById(R.id.lbl_page_user_email);

        avatarProgress = (ProgressBar)v.findViewById(R.id.pro_profile_page_thumbnail);
        bioProgress = (ProgressBar)v.findViewById(R.id.pro_profile_page_bio);
        interestsProgress = (ProgressBar)v.findViewById(R.id.pro_profile_page_interests);

        userBio = (TextView)v.findViewById(R.id.lbl_page_user_bio);
        userInterests = (TextView)v.findViewById(R.id.lbl_page_user_interests);

        editDetails = (FloatingActionButton) v.findViewById(R.id.fab_edit_profile_details);

        editDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onEditProfileClick();
            }
        });



        thumbnail = (ImageView) v.findViewById(R.id.imb_page_thumbnail);
        thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onThumbnailClick();
            }
        });

        settings = (ImageButton)v.findViewById(R.id.imb_page_user_settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAccountSettingsClick();
            }
        });
        logout = (ImageButton)v.findViewById(R.id.imb_page_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onLogoutClick();
            }
        });



        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(presenter == null){
            presenter = new ProfilePagePresenter();
        }
        presenter.subscribe();
    }

    @Override
    public void onDestroy() {
        presenter.unsubscribe();
        super.onDestroy();
    }

    @Override
    public void setPresenter(ProfilePageContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void makeToast(@StringRes int stringId) {
        Toast.makeText(getActivity(), getString(stringId), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setName(String name) {
        userName.setText(name);
    }

    @Override
    public void setEmail(String email) {
        userEmail.setText(email);
    }

    @Override
    public void setBio(String bio) {
        userBio.setText(bio);
    }

    @Override
    public void setInterests(String interests) {
        userInterests.setText(interests);
    }

    @Override
    public void setProfilePhotoURL(String profilePhotoURL) {
        Picasso.with(getActivity())
                .load(Uri.parse(profilePhotoURL))
                .noFade()
                .into(thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onThumbnailLoaded();
                    }

                    @Override
                    public void onError() {
                        setDefaultProfilePhoto();
                    }
                });
        //CircleImageView requires noFade() to be set
    }

    @Override
    public void setDefaultProfilePhoto() {
        Picasso.with(getActivity())
                .load(R.drawable.default_profile_pic)
                .noFade()
                .into(thumbnail, new Callback() {
                    @Override
                    public void onSuccess() {
                        presenter.onThumbnailLoaded();
                    }

                    @Override
                    public void onError() {
                        makeToast("Error load image");
                    }
                });
    }

    @Override
    public void showLogoutSnackbar() {
        Snackbar.make(getView(),getString(R.string.action_log_out),Snackbar.LENGTH_SHORT)
                .setAction(R.string.action_log_out, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.onLogoutConfirmed();
                    }
                })
                .show();
    }

    @Override
    public void startLoginActivity() {
        Intent i = new Intent(getActivity(), LoginActivity.class);
        startActivity(i);
    }

    @Override
    public void setThumbnailLoadingIndicator(boolean show) {

    }

    @Override
    public void setDetailLoadingIndicators(boolean show) {

    }
}
