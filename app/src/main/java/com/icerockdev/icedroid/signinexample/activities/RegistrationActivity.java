package com.icerockdev.icedroid.signinexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.icerockdev.icedroid.signinexample.R;
import com.icerockdev.icedroid.signinexample.utils.AuthorizationUtils;

/**
 * A login screen that offers login via email/password.
 */
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener
{
    private TextInputLayout mEmailLayout;
    private TextInputLayout mLoginLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mEmailLayout = (TextInputLayout) findViewById(R.id.email_reg_layout);
        mLoginLayout = (TextInputLayout) findViewById(R.id.login_reg_layout);
        Button registrationBtn = (Button) findViewById(R.id.registration_btn);
        registrationBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.registration_btn: {
                if (areFieldsValid()) {
                    AuthorizationUtils.setAuthorized(this);
                    onLoginCompleted();
                }
                else {
                    setError();
                }
                break;
            }
        }
    }

    //	If user is authorized we launch the main activity
    private void onLoginCompleted()
    {
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
        finish();
    }

    //	It checks the email field
    private boolean areFieldsValid()
    {
        return mEmailLayout != null &&
                mEmailLayout.getEditText() != null &&
                !TextUtils.isEmpty(mEmailLayout.getEditText().getText()) &&
                Patterns.EMAIL_ADDRESS.matcher(mEmailLayout.getEditText().getText()).matches();
    }

    private void setError()
    {
        if (mEmailLayout != null) {
            mEmailLayout.setError(getString(R.string.email_is_not_valid_error_message));
        }
    }
}

