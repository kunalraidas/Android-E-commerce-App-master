package com.example.freshy;


import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.freshy.RegisterActivity.onResetpassfrag;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignInFragment extends Fragment {


    public SignInFragment() {
        // Required empty public constructor
    }

    private TextView dontHaveAnAccount;
    private FrameLayout parentFrameLayout;

    private EditText email;
    private EditText password;

    private TextView closebut;
    private Button loginbut;

    private FirebaseAuth firebaseAuth;

    private ProgressBar progressBar;
    private TextView forgotpas;

    private String emailpattern= "[a-zA-z0-9._-]+@[a-z]+.[a-z]+";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_in,container,false);
        dontHaveAnAccount = view.findViewById(R.id.move_to_signup);
        parentFrameLayout = getActivity().findViewById(R.id.register_fram_layout);
        email =  view.findViewById(R.id.login_email);
        password = view.findViewById(R.id.login_password);
        closebut = view.findViewById(R.id.close_btn);
        loginbut = view.findViewById(R.id.login_button);
        progressBar = view.findViewById(R.id.signin_progressBar);
        forgotpas = view.findViewById(R.id.forgot_pass);
        firebaseAuth = FirebaseAuth.getInstance();
        return view;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        dontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignUpFragment());
            }
        });

        closebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainIntent();
            }
        });

        forgotpas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onResetpassfrag = true;
                setFragment(new ResetPasswordFragment());
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                checkInputs();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkEmailandPassword();
            }
        });

    }


    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slideout_from_left);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }

    private void checkInputs()
    {
        if(!TextUtils.isEmpty(email.getText()))
        {
            if(!TextUtils.isEmpty(password.getText()))
            {
                loginbut.setEnabled(true);

            }
            else
            {
                loginbut.setEnabled(false);
            }
        }
        else
        {
            loginbut.setEnabled(false);
        }
    }

    private void checkEmailandPassword()
    {
        if(email.getText().toString().matches(emailpattern))
        {
            if(password.length() >= 8)
            {
                progressBar.setVisibility(View.VISIBLE);
                loginbut.setEnabled(false);
                firebaseAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    mainIntent();
                                }
                                else
                                {
                                    loginbut.setEnabled(true);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    String error= task.getException().getMessage();
                                    Toast.makeText(getActivity(),error,Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
            else
            {
                Toast.makeText(getActivity(),"Incorrect email or Password",Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getActivity(),"Incorrect email or Password",Toast.LENGTH_SHORT).show();
        }
    }
    private void mainIntent()
    {
        Intent mainIntent = new Intent(getActivity(),HomeActivity.class);
        startActivity(mainIntent);
        getActivity().finish();
    }


}
