package com.project.kudawala;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class MainActivity extends AppCompatActivity {
    Button login_btn;
    public static final int GOOGLE_SIGN_IN_CODE = 10005;
    GoogleSignInOptions defaultUserAccountOptions;
    GoogleSignInClient signInClient;
    FirebaseAuth firebaseAuth;
    private static final String TAG = "GOOGLEAUTH";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_btn = findViewById(R.id.continue_with_google);

        firebaseAuth = FirebaseAuth.getInstance();
        defaultUserAccountOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                .requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(this, defaultUserAccountOptions);

        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);

        if(signInAccount != null || firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, dashboard_user.class));
            finish();
        }

        login_btn.setOnClickListener(v -> {
            Intent sign = signInClient.getSignInIntent();
            startActivityForResult(sign,GOOGLE_SIGN_IN_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_SIGN_IN_CODE){
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount signInAcc = signInAccountTask.getResult(ApiException.class);
                AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAcc.getIdToken(),null);

                firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(task -> {
                    if(task.getResult().getAdditionalUserInfo().isNewUser()){
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        finish();
                    }
                    startActivity(new Intent(getApplicationContext(), dashboard_user.class));
                    finish();
                }).addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Login-failed!", Toast.LENGTH_LONG).show());

            } catch (ApiException e) {
                e.printStackTrace();
            }
        }
    }

}