package com.example.freshy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResetPasswordFragment extends Fragment {

    private Button rstbutton;
    private EditText registeremail;
    private TextView go_back;
    private FrameLayout parentFrameLayout;
    private FirebaseAuth firebaseAuth;


    public ResetPasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reset_password, container, false);
        rstbutton = view.findViewById(R.id.rst_pass_button);
        registeremail = view.findViewById(R.id.forget_pass_email);
        go_back = view.findViewById(R.id.rst_pass_go_back);


        firebaseAuth=FirebaseAuth.getInstance();

        parentFrameLayout = getActivity().findViewById(R.id.register_fram_layout);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registeremail.addTextChangedListener(new TextWatcher() {
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

        go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFragment(new SignInFragment());
            }
        });



        rstbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rstbutton.setEnabled(false);

                firebaseAuth.sendPasswordResetEmail(registeremail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                              if(task.isSuccessful())
                              {
                                  Toast.makeText(getActivity(),"Email Sent Succesfully!",Toast.LENGTH_LONG).show();
                              }
                              else
                              {
                                  String error = task.getException().getMessage();

                                  Toast.makeText(getActivity(),error,Toast.LENGTH_LONG).show();
                              }
                                rstbutton.setEnabled(true);
                            }
                        });

            }
        });

    }
    private void checkInputs()
    {
        if(TextUtils.isEmpty(registeremail.getText()))
        {
            rstbutton.setEnabled(false);
        }
        else
        {
            rstbutton.setEnabled(true);
        }

    }
    private void setFragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slideout_from_right);
        fragmentTransaction.replace(parentFrameLayout.getId(),fragment);
        fragmentTransaction.commit();
    }
}
