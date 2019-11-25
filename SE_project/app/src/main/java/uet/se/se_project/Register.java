package uet.se.se_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Register extends AppCompatActivity {

    //widgets//
    TextView login,error;

    TextInputEditText name;
    TextInputEditText pass;
    TextInputEditText email;
    TextInputEditText reg_no;

    Button register;

    RelativeLayout layout;

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference signUpColl = db.collection("Sign_up_Data");

    //Extras//
    public int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //finding id's//
        login = (TextView) findViewById(R.id.login_R);
        error = (TextView) findViewById(R.id.error_R);

        name = (TextInputEditText) findViewById(R.id.name_R);
        pass = (TextInputEditText) findViewById(R.id.password_R);
        email = (TextInputEditText) findViewById(R.id.e_mail_R);
        reg_no = (TextInputEditText) findViewById(R.id.reg_no_R);

        register = (Button) findViewById(R.id.register);
        layout = (RelativeLayout) findViewById(R.id.layout_R);

        //ForRegister//


        //OnClicks//

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!amIConnected(Register.this)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Register.this);
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
                count = 0;
                final String username = name.getText().toString().trim();
                String password = pass.getText().toString().trim();
                final String e_mail = email.getText().toString().trim();
                String regno = reg_no.getText().toString().trim();
                if(!ValidateEmail() | !ValidatePassword() | !ValidateUserName() | !ValidateRegNo())
                {
                    return;
                }
                final SignupData signupData = new SignupData(username, e_mail, regno, password);
                signUpColl.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
                {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots)
                    {

                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                        {
                            SignupData signupData = documentSnapshot.toObject(SignupData.class);
                            if(e_mail.equals(signupData.getEmail()))
                            {
                                Toast.makeText(Register.this, "Email Matches", Toast.LENGTH_LONG).show();
                                count = 1;
                            }
                        }
                        if(count == 0)
                        {
                            signUpColl.document(e_mail).set(signupData).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Snackbar.make(layout, "registered Successfully", Snackbar.LENGTH_LONG).show();
                                }
                            });
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            error.setText("You already have an Account");
                        }

                    }
                });
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean ValidateEmail(){
        String inputEmail = email.getText().toString().trim();
        if(inputEmail.isEmpty()) {
            email.setError("email fiels can't be empty");
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()){
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }
    private boolean ValidateUserName(){
        String inputName = name.getText().toString().trim();
        if(inputName.isEmpty()) {
            name.setError("field can't be empty");
            return false;
        }
        else{
            name.setError(null);
            return true;
        }
    }
    private boolean ValidateRegNo(){
        String inputRegNo = reg_no.getText().toString().trim();
        if(inputRegNo.isEmpty()) {
            reg_no.setError("field can't be empty");
            return false;
        }
        else{
            reg_no.setError(null);
            return true;
        }
    }
    private boolean ValidatePassword(){
        String inputPassword = pass.getText().toString().trim();
        if(inputPassword.isEmpty()) {
            pass.setError("field can't be empty");
            return false;
        }
        else{
            pass.setError(null);
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
}
