package app.com.VatsalyaCoachingAcademy;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Singup extends AppCompatActivity {
    private EditText signUpName,signUpEmail,signUpPassword;
    private ImageView signUpUserPic;
    private Button signUpLogin,signUpRegister;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        //getActionBar().setTitle("Sign Up");

        signUpName = (EditText)findViewById(R.id.etSignupName);
        signUpEmail = (EditText)findViewById(R.id.etSignupEmail);
        signUpPassword = (EditText)findViewById(R.id.etSignupPassword);
        signUpUserPic = (ImageView) findViewById(R.id.ivSignupUser);
        signUpLogin = (Button)findViewById(R.id.btnSignupLogin);
        signUpRegister = (Button)findViewById(R.id.btnSignupRegister);
        firebaseAuth = FirebaseAuth.getInstance();

        signUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Singup.this,Login.class));
            }
        });

        signUpRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    //todo:upload data to database

                    String user_email = signUpEmail.getText().toString().trim();
                    String user_password = signUpPassword.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(Singup.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Singup.this,Login.class));
                            }
                            else{
                                Toast.makeText(Singup.this, "Registration Failed!Please check all the details!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });



    }

    public Boolean validate(){
        Boolean result = false;

        String name,email,password;
        name = signUpName.getText().toString();
        email = signUpEmail.getText().toString();
        password = signUpPassword.getText().toString();

        if((name.isEmpty()) || (email.isEmpty()) || (password.isEmpty())){
            Toast.makeText(this, "Please Enter All the Details", Toast.LENGTH_SHORT).show();
        }
        else {
            result = true;
        }
        return result;
    }
}
