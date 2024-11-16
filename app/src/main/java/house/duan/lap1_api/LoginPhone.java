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
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginPhone extends AppCompatActivity {
    EditText edphoneNumber, edOTP;
    Button btnRegidterphone, btnOTP;
    private FirebaseAuth auth;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private String verificationID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_phone);
        edphoneNumber = findViewById(R.id.ed_phone);
        edOTP = findViewById(R.id.ed_otp);
        btnRegidterphone = findViewById(R.id.btn_login_otp);
        btnOTP = findViewById(R.id.btn_otp);

        auth = FirebaseAuth.getInstance();
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                edOTP.setText(phoneAuthCredential.getSmsCode());
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

            }

            @Override
            public void onCodeSent(@NonNull String code, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                verificationID = code;
            }
        };

        btnOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOTP("+84" + edphoneNumber.getText().toString());
            }
        });

        btnRegidterphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifyOTP(edOTP.getText().toString());
            }
        });
    }
    private void SingInWitnPhoneAuthCredential(PhoneAuthCredential credential){
        auth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginPhone.this, "Dang nhap thanh cong", Toast.LENGTH_SHORT).show();
                    FirebaseUser firebaseUser = task.getResult().getUser();
                }else {
                    Toast.makeText(LoginPhone.this, "Dang nhap khong thanh cong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void verifyOTP(String code){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, code);
        SingInWitnPhoneAuthCredential(credential);
    }
    private void getOTP(String phoneNumbre){
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumbre)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}