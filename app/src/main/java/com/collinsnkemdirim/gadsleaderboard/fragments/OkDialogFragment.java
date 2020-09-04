package com.collinsnkemdirim.gadsleaderboard.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class OkDialogFragment extends DialogFragment {

    public static final String ARGS_MESSAGE = "message";

    public OkDialogFragment() {
        Bundle args = new Bundle();
        args.putString(ARGS_MESSAGE, "");
        setArguments(args);
    }

    public static OkDialogFragment newInstance(String message){
        OkDialogFragment dialogFragment = new OkDialogFragment();
        dialogFragment.getArguments().putString(ARGS_MESSAGE, message);
        return dialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setMessage(getArguments().getString(ARGS_MESSAGE))
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
