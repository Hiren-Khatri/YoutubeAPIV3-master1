package app.com.VatsalyaCoachingAcademy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText loginEmail,loginPassword;
    private ImageView loginUserPic;
    private Button loginBtn,loginRegister;
    private TextView tvLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //getSupportActionBar().setTitle("Login");

        loginEmail = (EditText)findViewById(R.id.etLoginEmail);
        loginPassword = (EditText)findViewById(R.id.etLoginPassword);
        loginBtn = (Button)findViewById(R.id.btnLogin);
        loginRegister = (Button)findViewById(R.id.btnLoginRegister);
        loginUserPic = (ImageView)findViewById(R.id.ivLoginUser);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            finish();
            startActivity(new Intent(this,MainActivity.class));

        }
        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Singup.class));
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(loginEmail.getText().toString(),loginPassword.getText().toString());
            }
        });
    }

    public void validate(String userEmail,String userPassword){
        if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this, "Please enter registered  Email!", Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this, "Please enter Password!", Toast.LENGTH_SHORT).show();

        }
        else {
            progressDialog.setMessage("Validating Details...Please Wait...");
            progressDialog.show();
            firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        Toast.makeText(Login.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, MainActivity.class));

                    } else {
                        Toast.makeText(Login.this, "Login Failed!Please check all the details", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.forgotPasswordMenu:{
                startActivity(new Intent(this,PasswordActivity.class));
                break;
            }
        }        return super.onOptionsItemSelected(item);
    }
}
