package com.example.saini.mvp.util;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.widget.EditText;

import com.example.saini.mvp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Common Utility to Validate any type of user input
 */
public class Validation {

    public static boolean isValidEmail(Context context, EditText email, TextInputLayout textInputLayout) {
        return isValidEmailCheck(context, email, textInputLayout, context.getString(R.string.err_enter_email));
    }

    public static boolean checkEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidEmailCheck(Context context, EditText email, TextInputLayout textInputLayout, String msg) {
        if (isNullorEmpty(email, textInputLayout, msg)) {
            return false;
        }
        if (!checkEmail(email.getText().toString())) {
            setError(email, textInputLayout, context.getString(R.string.err_msg_email));
            return false;
        }
        if (!isContainSpace(email, textInputLayout, context.getString(R.string.err_msg_space))) {

            return false;
        }
        return true;
    }


    public static boolean isValidMobile(Context context, String countryCode, EditText mobileNumber, TextInputLayout textInputLayout) {
        boolean isValid = false;
        if (!isNullorEmpty(mobileNumber, textInputLayout, context.getString(R.string.mobile_number_empty))
                && isContainSpace(mobileNumber, textInputLayout, context.getString(R.string.mobile_number_contain_space))
                && !checkPattren(mobileNumber, textInputLayout, context.getString(R.string.err_msg_mobile_not_valid)))
            return true;
        else
            return isValid;
    }


    /**
     * check for email and password
     */

    public static boolean isPasswordValid(Activity activity, EditText mpassword, TextInputLayout textInputLayout, String msg) {

        if (isNullorEmpty(mpassword, textInputLayout, msg)) {
            return false;
        } else if (!checkPasswordLength(activity, mpassword, textInputLayout)) {
            return false;
        } else if (!isContainSpace(mpassword, textInputLayout, activity.getString(R.string.err_msg_space))) {
            return false;
        }
        return true;
    }

    public static boolean checkPasswordLength(Context context, EditText mEditOldPassword, TextInputLayout textOldPassword) {
        if (mEditOldPassword.length() < 6) {
            setError(mEditOldPassword, textOldPassword, context.getString(R.string.password_length_less));
            return false;
        } else if (mEditOldPassword.length() > 15) {
            setError(mEditOldPassword, textOldPassword, context.getString(R.string.password_length_more));
            return false;
        }
        return true;
    }

    public static boolean isChangePassword(Activity activity, EditText mEditOldPassword, TextInputLayout textOldPassword, EditText mEditNewPassword, TextInputLayout textNewPassword, EditText mEditConfirmPassword, TextInputLayout textConfirmPassword) {

        if (!isPasswordValid(activity, mEditOldPassword, textOldPassword, activity.getString(R.string.enter_old_password))) {
            return false;
        }
        if (!isPasswordValid(activity, mEditNewPassword, textNewPassword, activity.getString(R.string.enter_new_password))) {
            return false;
        }
        if (!isPasswordValid(activity, mEditConfirmPassword, textConfirmPassword, activity.getString(R.string.err_msg_confirmpassword))) {
            return false;
        }
        if (mEditOldPassword.getText().toString().equalsIgnoreCase(mEditNewPassword.getText().toString())) {
            setError(mEditNewPassword, textNewPassword, activity.getString(R.string.err_msg_password_match));
            return false;
        }
        if (!mEditConfirmPassword.getText().toString().equalsIgnoreCase(mEditNewPassword.getText().toString())) {
            setError(mEditConfirmPassword, textConfirmPassword, activity.getString(R.string.err_msg_confirmpassword_not_match));
            return false;
        }

        return true;

    }


    /**
     * To check whether a given edittext is null or empty
     *
     * @param editText
     * @return status of edittext
     */
    public static boolean isNullorEmpty(EditText editText, TextInputLayout textInputLayout, String errorMsg) {
        if (editText == null || editText.length() < 1) {
            setError(editText, textInputLayout, errorMsg);
            return true;
        } else
            return false;
    }

    public static boolean isContainSpace(EditText editText, TextInputLayout textInputLayout, String errorMsg) {
        if (editText != null && editText.getText().toString().contains(" ")) {
            setError(editText, textInputLayout, errorMsg);
            return false;
        } else
            return true;
    }


    public static void setError(EditText editText, TextInputLayout inputLayout, String errorMessage) {
        try {
            if (editText.getText().toString().length() > 0)
                editText.requestFocus(editText.getText().toString().length());
            else
                editText.requestFocus();
            inputLayout.setError(errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean checkPattren(EditText editText, TextInputLayout textInputLayout, String errorMsg) {
        boolean isPattern = false;
        String edit_text_name = editText.getText().toString();

        Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");

        if (regex.matcher(edit_text_name).find()) {
            Log.d("Utility", "SPECIAL CHARS FOUND");
            setError(editText, textInputLayout, errorMsg);
            isPattern = true;
        }
        return isPattern;
    }


    /**
     * Validate password with regular expression
     *
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public static boolean validateAlphaNumericPassword(final String password) {
        /*String PASSWORD_PATTERN =
				"((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";*/
        String PASSWORD_PATTERN =
                "((?=.*\\d)(?=.*[A-Z])(?=.*[!@#$%&*()_+=|:;<>?{}\\[\\]~-]).{6,15})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();

    }


}
