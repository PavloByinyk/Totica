package com.example.gottgried.totica.login;

import android.support.annotation.StringRes;

import com.example.gottgried.totica.BasePresenter;
import com.example.gottgried.totica.BaseView;

/**
 * Created by Gottgried on 03.10.2017.
 */

public interface LoginContract {

    interface View extends BaseView<LoginContract.Presenter> {
        void makeToast(@StringRes int stringId);

        void makeToast(String message);

        String getEmail();

        String getPassword();

        void startProfileActivity();

        void startCreateAccountActivity();

        void setPresenter(LoginContract.Presenter presenter);

        void showProgressIndicator(boolean show);
    }

    interface Presenter extends BasePresenter {
        void onLogInClick();

        void onCreateClick();

    }
}
