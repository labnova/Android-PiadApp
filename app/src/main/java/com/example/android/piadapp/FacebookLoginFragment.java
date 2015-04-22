package com.example.android.piadapp;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.piadapp.data.Piada;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;


public class FacebookLoginFragment extends Fragment implements View.OnClickListener {

    //TextView accesso;
    LoginButton loginButton;
    Button condividi; //per condividere su facebook
    CallbackManager callbackManager;
    ProfileTracker pt;
    ShareButton shareButton;
    ShareDialog shareDialog;
    Piada piada;
    // Callback registration
   private FacebookCallback<LoginResult> mCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();

          /*  if(profile != null) {
                accesso.setText("Benvenuto" + profile.getName());
            }
*/

            Toast.makeText(getActivity(), "Collegato a Facebook!", Toast.LENGTH_SHORT).show();

            shareButton.setVisibility(View.VISIBLE);

            ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                    .setContentTitle("Nuova Piada Condivisa!")
                    .setContentDescription("Ecco la mia nuova piada: ")
                    .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                    .build();


           /* shareDialog = new ShareDialog(getActivity());


            if(shareDialog.canShow(ShareLinkContent.class)) {
                ShareLinkContent shareLinkContent = new ShareLinkContent.Builder()
                        .setContentTitle("Nuova Piada Condivisa!")
                        .setContentDescription("Ecco la mia nuova piada: ")
                        .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                        .build();
                shareDialog.show(shareLinkContent);

            }*/

            shareButton.setShareContent(shareLinkContent);


        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException e) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());




        Bundle b = getArguments();
        if (b != null) {
            piada = new Piada(b);
        }

        if (piada.getNome() == "") {
            piada.setNome("Nuova Piada");
        }



    }

    @Override
    public void onStop() {
        super.onStop();
      //  pt.stopTracking();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.facebook_login_fragment, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);

        loginButton.setFragment(this);
        loginButton.setOnClickListener(this);

        shareButton = (ShareButton) view.findViewById(R.id.share_button);
        shareButton.setVisibility(View.GONE);
        shareButton.setOnClickListener(this);



     }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        LoginManager.getInstance().logInWithPublishPermissions(
                getActivity(), Arrays.asList("publish_actions"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                callbackManager = CallbackManager.Factory.create();
                loginButton.setReadPermissions("user_friends");
                loginButton.registerCallback(callbackManager, mCallback);


       /* //per risolvere il bug Profile di Facebook
        pt = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile profile, Profile newProfile) {
                if(newProfile != null) {
                    accesso.setText("Benvenuto" + profile.getName());
                }
            }
        };

        pt.startTracking();*/


                break;
            default: return;

        }
    }
}
