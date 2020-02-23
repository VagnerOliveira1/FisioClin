package com.rightside.fisioclin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.rightside.fisioclin.fragment.MedicoVerificationDataFragment;
import com.rightside.fisioclin.fragment.UserVerificationDataFragment;
import com.rightside.fisioclin.models.Medico;
import com.rightside.fisioclin.models.Paciente;
import com.rightside.fisioclin.models.User;
import com.rightside.fisioclin.repository.FirebaseRepository;
import com.rightside.fisioclin.utils.ConstantUtils;


public class LoginPacientActivity extends AppCompatActivity {
    private int RC_SIGN_IN = 0;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pacient);

        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

            signIn();


    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);
            firebaseAuthWithGoogle(account);

        } catch (ApiException e) {

        }
    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                if (account.getEmail().equals("ti.vagner@gmail.com") || account.getEmail().equals("matheusldasilva20088@gmail.com")) {
                    checkDoutor();
                    //matheusldasilva20088@gmail.com
                } else {
                    checkPacient();
                }
            }
        });

    }

    private void checkDoutor() {
        final FirebaseUser firebaseDoctor = FirebaseAuth.getInstance().getCurrentUser();
        final DocumentReference getId = FirebaseRepository.getDB().collection("medicos").document(firebaseDoctor.getUid());
        getId.get().addOnCompleteListener(task -> {
            if(task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if(!documentSnapshot.exists()){
                    Medico medico = new Medico(firebaseDoctor.getUid(),firebaseDoctor.getDisplayName(), firebaseDoctor.getPhotoUrl().toString());
                    MedicoVerificationDataFragment.medicoVerificationDataFragment(medico).show(getSupportFragmentManager(), "medicoverification");
                } else {
                    startActivity(new Intent(LoginPacientActivity.this, MainMedicoActivity.class));
                    Toast.makeText(getApplicationContext(),ConstantUtils.LOGIN_SUCESSO,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkPacient() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final DocumentReference getId = FirebaseRepository.getDB().collection("usuarios").document(firebaseUser.getUid());
        getId.get().addOnCompleteListener(task -> {

            if (task.isSuccessful()) {
                DocumentSnapshot documentSnapshot = task.getResult();
                if (!documentSnapshot.exists()) {
                    User user = new User(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getPhotoUrl().toString(), firebaseUser.getEmail());
                    UserVerificationDataFragment.pacientVerificationDataFragment(user).show(getSupportFragmentManager(), "pacientVerification");
                } else {
                    startActivity(new Intent(this, MainPacientActivity.class));
                    Toast.makeText(getApplicationContext(),ConstantUtils.LOGIN_SUCESSO,Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}