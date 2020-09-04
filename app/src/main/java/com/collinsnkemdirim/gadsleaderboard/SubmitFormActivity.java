package com.collinsnkemdirim.gadsleaderboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.collinsnkemdirim.gadsleaderboard.fragments.ConfirmDialogFragment;
import com.collinsnkemdirim.gadsleaderboard.fragments.OkDialogFragment;
import com.collinsnkemdirim.gadsleaderboard.fragments.ProgressDialogFragment;
import com.collinsnkemdirim.gadsleaderboard.fragments.SubmitFormDialogFragment;
import com.collinsnkemdirim.gadsleaderboard.model.SubmitForm;
import com.collinsnkemdirim.gadsleaderboard.viewmodel.SubmitFormViewModel;
import com.example.gadsleaderboard.R;

public class SubmitFormActivity extends AppCompatActivity implements ConfirmDialogFragment.OnClickListener {

    private SubmitForm submitForm = new SubmitForm();
    private SubmitFormViewModel viewModel;
    private ProgressDialogFragment progressDialogFragment;
    private SubmitFormDialogFragment submitFormDialogFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_form);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        OkDialogFragment okDialogFragment = OkDialogFragment.newInstance(getString(R.string.fill_fields));
        ConfirmDialogFragment confirmDialogFragment = ConfirmDialogFragment.newInstance(getString(R.string.are_you_sure_confirmation));
        progressDialogFragment = ProgressDialogFragment.newInstance(getString(R.string.submitting));
        submitFormDialogFragment = submitFormDialogFragment.newInstance();

        viewModel = new ViewModelProvider(this).get(SubmitFormViewModel.class);
        viewModel.getStatus().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer status) {
                if (status != SubmitFormViewModel.STATUS_DEFAULT) {
                    progressDialogFragment.dismiss();
                    submitFormDialogFragment.setSuccess(status == SubmitFormViewModel.STATUS_OK);
                    submitFormDialogFragment.show(getSupportFragmentManager(), "SubmitActivity_SubmitResultDialog");
                }
            }
        });

        findViewById(R.id.back_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                    }
                });

        TextView txtFirstName = findViewById(R.id.firstName_edTxt);
        TextView txtLastName = findViewById(R.id.lastName_edTxt);
        TextView txtEmail = findViewById(R.id.email_edtTxt);
        TextView txtProjectUrl = findViewById(R.id.projectUrl_edTxt);

        Button submitButton = findViewById(R.id.submit_form_btn);
                submitButton
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            submitForm.setFirstName(txtFirstName.getText().toString().trim());
                            submitForm.setLastName(txtLastName.getText().toString().trim());
                            submitForm.setEmail(txtEmail.getText().toString().trim());
                            submitForm.setProjectUrl(txtProjectUrl.getText().toString().trim());
                        boolean filledForm = submitForm.getFirstName().length() > 0 && submitForm.getLastName().length() > 0 &&
                                submitForm.getEmail().length() > 0 && submitForm.getProjectUrl().length() > 0;

                        if (!filledForm) {
                            okDialogFragment.show(getSupportFragmentManager(), "SubmitActivity_OkDialog");
                        }else{
                            confirmDialogFragment.show(getSupportFragmentManager(), "SubmitActivity_ConfirmDialog");
                        }


                    }

                });

        }


    @Override
    public void onConfirm(ConfirmDialogFragment confirmDialogFragment) {
        progressDialogFragment.show(getSupportFragmentManager(), "SubmitActivity_ProgressDialog");
        viewModel.submitForm(submitForm);
    }

    @Override
    public void onDismiss(ConfirmDialogFragment confirmDialogFragment) {
        //no implementation here
    }










}