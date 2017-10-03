package com.example.gottgried.totica;

import com.example.gottgried.totica.login.LoginContract;
import com.example.gottgried.totica.login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by Gottgried on 03.10.2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginContract.View view;

    private LoginPresenter loginPresenter;

    @Before
    public void SetUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        loginPresenter = new LoginPresenter();

    }

    @Test
    public void whenUserLoginSuccess(){
        Mockito.verify(view).startProfileActivity();
    }

    @Test
    public void whenUserLoginFail(){
        Mockito.verify(view).makeToast(Mockito.anyString());
    }
}
