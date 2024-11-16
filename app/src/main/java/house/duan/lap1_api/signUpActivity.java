package house.duan.lap1_api;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class signUpActivity extends AppCompatActivity {
    Button btnRegister;
    EditText edEmail, edpassWord;
    String email, passwork;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);
        btnRegister = findViewById(R.id.btn_Register);
        edEmail = findViewById(R.id.ed_Email2);
        edpassWord = findViewById(R.id.ed_password2);
        auth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = edEmail.getText().toString();
                passwork = edpassWord.getText().toString();
                auth.createUserWithEmailAndPassword(email, passwork).addOnCompleteListener(signUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(signUpActivity.this, "Tao moi thanh cong", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(signUpActivity.this, "Tao moi that bai", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}