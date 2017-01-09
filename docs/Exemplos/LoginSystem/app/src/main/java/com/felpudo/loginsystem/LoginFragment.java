package com.felpudo.loginsystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class LoginFragment extends Fragment {

    private CallbackManager _CallbackManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Initialize the SDK before executing any other operations

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());

        // Creates an instance of CallbackManager and registers it

        _CallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(_CallbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onCancel() {
                Snackbar.make(getView(), "Login was canceled!", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Snackbar.make(getView(), "An error has occurred!", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(LoginResult loginResult) {

                GraphRequest request = GraphRequest.newMeRequest(
                        AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {

                                try {
                                    String message = String.format(
                                            "First Name: %s \nLast Name: %s \nE-mail: %s \nBirthday: %s",
                                            object.getString("first_name"),
                                            object.getString("last_name"),
                                            object.getString("email"),
                                            object.getString("birthday"));

                                    android.support.v7.app.AlertDialog.Builder alertDialog =
                                            new android.support.v7.app.AlertDialog.Builder(getActivity());

                                    alertDialog
                                            .setTitle("Facebook")
                                            .setMessage(message)
                                            .setNegativeButton("LogOut", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    LoginManager.getInstance().logOut();
                                                }
                                            })
                                            .setCancelable(false)
                                            .show();
                                }
                                catch (JSONException e) {
                                    Log.d("JSONException", e.getMessage());
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,email,birthday");
                request.setParameters(parameters);
                request.executeAsync();
            }
        });

        // Getting views

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button facebookButton = (Button)view.findViewById(R.id.facebookButton);
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> permissions = new ArrayList<>();
                permissions.add("public_profile");
                permissions.add("email");
                permissions.add("user_birthday");

                LoginManager.getInstance().logInWithReadPermissions(
                        getActivity(), permissions);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        _CallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
