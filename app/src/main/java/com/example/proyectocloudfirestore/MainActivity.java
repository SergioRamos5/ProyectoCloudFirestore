package com.example.proyectocloudfirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    EditText user, password;
    Button btIniciar, btRegistrar;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener !=null)
            mAuth.removeAuthStateListener(mAuthListener);
        mAuth.signOut();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mAuth = FirebaseAuth.getInstance();
        btIniciar = findViewById(R.id.btIniciar);
        btRegistrar = findViewById(R.id.btRegistrar);
        user = findViewById(R.id.etUsuario);
        password = findViewById(R.id.etPass);

        //region Listener Autenticacion
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null)
                {
                    Toast.makeText(MainActivity.this, user.getEmail()+" LOGUEADO", Toast.LENGTH_LONG ).show();
                    mAuth = firebaseAuth;
                }
                else
                    Toast.makeText(MainActivity.this, "Usuario NULO", Toast.LENGTH_LONG ).show();
            }
        };
        //endregion

        //region Boton Iniciar
        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(user.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Authentication failed:" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                                else
                                    iniciarIntent();
                            }
                        });
            }
        });
        //endregion

        //region Boton Registrar
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.createUserWithEmailAndPassword(user.getText().toString(), password.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Toast.makeText(MainActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                                    iniciarIntent();
                                } else  Toast.makeText(MainActivity.this, "Problemas al crear usuario" + task.getException(), Toast.LENGTH_LONG).show();
                            }
                        });
            }});
        //endregion
    }

    private void iniciarIntent()
    {
        Intent intent = new Intent(this, IntentPrincipal.class);
        startActivity(intent);
    }
}
