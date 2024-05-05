package com.example.authetication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class forgetPassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText txtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);

        mAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.txtEmail);
    }

    public void resetPassword(View view) {
        String emailAddress = txtEmail.getText().toString().trim();
        Log.d("pass here", "yes");
        if (emailAddress.isEmpty()) {
            txtEmail.setError("Por favor, insira seu e-mail.");
            return;
        }
        mAuth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(forgetPassword.this, "Email de redefinição de senha enviado com sucesso.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(forgetPassword.this, Login.class);
                        startActivity(intent);
                        
                        finish();
                    } else {
                        Toast.makeText(forgetPassword.this, "Erro ao enviar email de redefinição de senha.", Toast.LENGTH_SHORT).show();
                        Log.e("TAG", "Erro ao enviar email de redefinição de senha.", task.getException());
                    }
                });
    }
}
