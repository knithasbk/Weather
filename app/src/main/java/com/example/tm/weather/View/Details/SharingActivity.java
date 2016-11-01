package com.example.tm.weather.View.Details;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.tm.weather.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;

import java.util.Arrays;
import java.util.List;

/**
 * Created by TM on 01/11/2016.
 */

public class SharingActivity extends Activity {
    private AccessTokenTracker accessTokenTracker;
    private LoginManager loginManager;
    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            Profile profile = Profile.getCurrentProfile();
            nextActivity(profile);
        }

        @Override
        public void onCancel() {
        }

        @Override
        public void onError(FacebookException e) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("TEST_TRACE", "Start onCreate() at SharingActivity class");
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        /**
         * The method that will be called with the access token changes.
         * @param oldAccessToken The access token before the change.
         * @param currentAccessToken The new access token.
         */

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

            }
        };
        /**
         *  Will be execute some action when current profile be changed
         *  @param oldProfile
         *  @param newProfile
         *  Output: void
         * */
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                nextActivity(newProfile);
            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();


        List<String> permission = Arrays.asList("publish_actions");
        loginManager = LoginManager.getInstance();
        loginManager.logInWithPublishPermissions(this, permission);
        Toast.makeText(getApplicationContext(), "Logging in...", Toast.LENGTH_SHORT).show();
        Log.i("TEST_TRACE", "End onCreate() at SharingActivity class");


    }


    public void nextActivity(Profile profile) {
        if (profile != null) {
            Intent intent = new Intent(this, ContentActivity.class);
            intent.putExtra("name", profile.getFirstName());
            intent.putExtra("surname", profile.getLastName());
            intent.putExtra("imageUrl", profile.getProfilePictureUri(200, 200).toString());
            startActivity(intent);
        }
    }

    ShareDialog shareDialog;

    public void shareFacebook() {
        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);

        ShareButton shareButton = (ShareButton) findViewById(R.id.details_layout_action_share);
        ShareLinkContent linkContent = new ShareLinkContent.Builder()
                .setContentTitle("Hello Facebook")
                .setContentDescription(
                        "The 'Hello Facebook' sample  showcases simple Facebook integration")
                .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
                .build();
        shareButton.setShareContent(linkContent);

    }

    public void shareImage() {
        System.out.println("Share Image method ");

    }
}

