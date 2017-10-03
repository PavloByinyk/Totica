package com.example.gottgried.totica.profilePage;

import com.example.gottgried.totica.BasePresenter;
import com.example.gottgried.totica.BaseView;

/**
 * Created by Gottgried on 03.10.2017.
 */

public interface ProfilePageContract {

    interface View extends BaseView<ProfilePageContract.Presenter>{
        @Override
        void setPresenter(ProfilePageContract.Presenter presenter);

        void setName (String name);

        void setEmail (String email);

        void setBio (String bio);

        void setInterests (String interests);

        void setProfilePhotoURL (String profilePhotoURL);

        void setDefaultProfilePhoto ();

        //void startPhotoGalleryActivity();

        //void startProfileDetailActivity();

        //void startProfileSettingsActivity();

        void showLogoutSnackbar ();

        void startLoginActivity();

        void setThumbnailLoadingIndicator(boolean show);

        void setDetailLoadingIndicators(boolean show);
    }

    interface Presenter extends BasePresenter{

        void onThumbnailClick();

        void onEditProfileClick();

        void onLogoutClick();

        void onLogoutConfirmed();

        void onAccountSettingsClick();

        void onThumbnailLoaded();

    }
}
