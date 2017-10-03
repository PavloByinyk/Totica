package com.example.gottgried.totica;

import android.support.annotation.StringRes;

/**
 * Created by Gottgried on 03.10.2017.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void makeToast(@StringRes int stringId);

    void makeToast(String msg);
}
