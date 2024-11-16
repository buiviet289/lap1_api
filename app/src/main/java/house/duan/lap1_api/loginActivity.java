package house.duan.lap1_api;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class loginActivity extends AppCompatActivity {
    TextView login_sign_up;
    Button btnLogin;
    EditText edEmail, edPassword;
    String email;
    String password;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        // chuyen man -> dang ky
        login_sign_up =  findViewById(R.id.btn_sign_up);
        login_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this, signUpActivity.class);
                startActivity(intent);
            }
        });

        edEmail = findViewById(R.id.ed_Email);
        edPassword = findViewById(R.id.ed_password);
        auth = FirebaseAuth.getInstance();

        // login
        btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edEmail.getText().toString();
                password = edPassword.getText().toString();
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(loginActivity.this, "Login thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(loginActivity.this, "Login Không thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });



    }
}
