package com.collinsnkemdirim.gadsleaderboard.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.gadsleaderboard.R;

public class ConfirmDialogFragment extends DialogFragment {


    private static final String ARG_MESSAGE = "message";
    public static final int RESULT_OK = 1;

    public ConfirmDialogFragment() {
        Bundle defaultArgs = new Bundle();
        defaultArgs.putString(ARG_MESSAGE, "");
        setArguments(defaultArgs);
    }

    public static ConfirmDialogFragment newInstance(String message) {
        ConfirmDialogFragment dialog = new ConfirmDialogFragment();
        dialog.getArguments().putString(ARG_MESSAGE, message);
        return dialog;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.confirm_dialog, container, false);
        TextView txtMessage = view.findViewById(R.id.txtMessage);
        txtMessage.setText(getArguments().getString(ConfirmDialogFragment.ARG_MESSAGE));
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        view.findViewById(R.id.yes_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnClickListener listener = (OnClickListener) getActivity();
                        if (listener != null){
                            listener.onConfirm(ConfirmDialogFragment.this);

                        }
                        dismiss();
                    }
                });
        view.findViewById(R.id.btnClose)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnClickListener listener = (OnClickListener) getActivity();
                        if (listener != null)
                            listener.onDismiss(ConfirmDialogFragment.this);

                        dismiss();
                    }
                });

        return view;
    }

    public static interface OnClickListener {
        void onConfirm(ConfirmDialogFragment confirmDialogFragment);

        void onDismiss(ConfirmDialogFragment confirmDialogFragment);
    }

}