package felipegonzalez.com.ciervogo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

// My Facebook ID: 10210814575779573
public class ProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();

        String birthdayDate = intent.getStringExtra("user.birthdayDate");



        FacebookSdk.sdkInitialize(getApplicationContext());
        if(isLoggedIn()){ //Esta logueado en facebook
            Profile profile = Profile.getCurrentProfile(); //Obtener profile usuario de facebook

            //Obtener Profile Picture Facebook y mandarla al layout
            ProfilePictureView profilePictureView;
            profilePictureView = (ProfilePictureView) findViewById(R.id.imageProfileFacebook);
            profilePictureView.setProfileId(profile.getId());


            String name = profile.getName();
            TextView myTextView= (TextView) findViewById(R.id.nameProfile);
            myTextView.setText(name);


        }
        else{ //No esta logueado
            Intent intent2 = new Intent(this, LoginActivity.class);
            startActivity(intent2);

        }

    }

    public boolean isLoggedIn() { //Esta logueado en facebook ?
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }

    public static Bitmap getFacebookProfilePicture(String userID) throws IOException {
        URL imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
        Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
        return bitmap;

    }



}
