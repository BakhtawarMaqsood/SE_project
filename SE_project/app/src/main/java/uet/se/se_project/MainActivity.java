package uet.se.se_project;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    //widgets//
    TextView register;

    Button login;

    TextInputEditText currentemail;
    TextInputEditText currentpassword;
    TextView errormsg;
    private static final String TAG = "MainActivity";
    //Database//
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference signupColl = db.collection("Sign_up_Data");

    //Extras//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!amIConnected(MainActivity.this)) {
            Log.d(TAG, "not connected");
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("No Internet Connection");
            builder.setMessage("You must have an Internet Connection");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();
                }
            });
            builder.show();
        }
        Log.d(TAG, "connected");
        //finding ids//
        register = (TextView) findViewById(R.id.register_L);

        currentemail = (TextInputEditText) findViewById(R.id.e_mail_L);
        currentpassword = (TextInputEditText) findViewById(R.id.password_L);
        errormsg = (TextView) findViewById(R.id.error_L);

        login = (Button) findViewById(R.id.login);

        //onclicks
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!amIConnected(MainActivity.this)) {
                    Log.d(TAG, "not connected");
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("No Internet Connection");
                    builder.setMessage("You must have an Internet Connection");

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finish();
                        }
                    });
                    builder.show();
                }
                if (!ValidateEmail() | !ValidatePassword()) {
                    return;
                }
                signupColl.addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                        String currentemailString = currentemail.getText().toString().trim();
                        String currentPasswordString = currentpassword.getText().toString().trim();

                        if(currentemailString.equals("admin@gmail.com") && currentPasswordString.equals("admin12")){
                            startActivity(new Intent(MainActivity.this, Admin_Homepage.class));
                        }

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            SignupData signupData = documentSnapshot.toObject(SignupData.class);
                           // String name = signupData.getUserName();
                            String pass = signupData.getPassword();
                            String email = signupData.getEmail();
                            //CurrentUser.username = name;
                            CurrentUser.email = email;
                            CurrentUser.password = pass;
                            if (email.equals(currentemailString) && pass.equals(currentPasswordString)) {
                                //Bundle bundle = new Bundle();
                                //bundle.putString("NAME", name);
                                Intent intent = new Intent(MainActivity.this, Dashboard.class);
                                //intent.putExtras(bundle);
                                //CurrentUser.loginStatus = true;
                                startActivity(intent);
                                return;
                            }

                        }
                        errormsg.setText("email or password does not match");

                    }
                });
            }
        });
    }

    private boolean ValidateEmail() {
        String inputEmail = currentemail.getText().toString().trim();
        if (inputEmail.isEmpty()) {
            currentemail.setError("email fiels can't be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            currentemail.setError("INVALID EMAIL");
            return false;
        } else {
            currentemail.setError(null);
            return true;
        }
    }

    private boolean ValidatePassword() {
        String inputPassword = currentpassword.getText().toString().trim();
        if (inputPassword.isEmpty()) {
            currentpassword.setError("field can't be empty");
            return false;
        } else {
            currentpassword.setError(null);
            return true;
        }
    }

    private Boolean amIConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else
                return false;
        }
        else
            return false;

    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
}
