package com.a40333.bharrin4.triangle_care;

/**
 * Created by User on 4/9/2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextPhone;
    private EditText editTextConfirmPassword;
    private EditText editTextTriangleCode;

    private String first;
    private String last;
    private String confirm;
    private String email;
    private String password;
    private String phone;
    private String facility;

    private ProgressDialog progressDialog;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.su_email);
        editTextPassword = (EditText) findViewById(R.id.su_password);
        editTextConfirmPassword = (EditText) findViewById(R.id.su_conf_password);
        editTextFirstName = (EditText) findViewById(R.id.su_first);
        editTextLastName = (EditText) findViewById(R.id.su_last);
        editTextPhone = (EditText) findViewById(R.id.su_mobile);
        editTextTriangleCode = (EditText) findViewById(R.id.su_code);
        buttonSignup = (Button) findViewById(R.id.sign_up_button);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
    }

    private void writeNewUser(String userId, String first_name, String last_name, String email, String password, String phone, String facility) {
        User user = new User(first_name, last_name, email, password, phone, facility);

        mDatabase.child("users").child(userId).setValue(user);
    }

    private void registerUser(){

        //getting email and password from edit texts
        email = editTextEmail.getText().toString().trim();
        password  = editTextPassword.getText().toString().trim();
        first  = editTextFirstName.getText().toString().trim();
        last  = editTextLastName.getText().toString().trim();
        confirm  = editTextConfirmPassword.getText().toString().trim();
        phone = editTextPhone.getText().toString().trim();
        facility = editTextTriangleCode.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
             }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(first)){
            Toast.makeText(this,"Please enter first name",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(last)){
            Toast.makeText(this,"Please enter last name",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(confirm)){
            Toast.makeText(this,"Please enter confirmation password",Toast.LENGTH_LONG).show();
            return;
        }

        //if all fields are full
        //displaying a progress dialog
        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        System.out.println("task.isSuccessful()");

                        //checking if success
                        if(task.isSuccessful()){
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            final String userId =  user.getUid();
                            System.out.println("userID= " + userId);

                            writeNewUser(userId, first, last, email, password, phone, facility);

                            finish();

                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }else{
                            //display some message here
                            Toast.makeText(SignUpActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if(view == buttonSignup){
            registerUser();
        }


    }
}
