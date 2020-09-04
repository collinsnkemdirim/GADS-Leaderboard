package com.collinsnkemdirim.gadsleaderboard.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.example.gadsleaderboard.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubmitFormDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SubmitFormDialogFragment extends DialogFragment {

    private boolean success;

    public SubmitFormDialogFragment() {
    }

    public static SubmitFormDialogFragment newInstance() {
        SubmitFormDialogFragment dialogFragment= new SubmitFormDialogFragment();
        return dialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView view = (TextView) inflater.inflate(R.layout.submit_result_dialog, container, false);
        // Set transparent background and no title
        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }
        if (success) {
            view.setText(R.string.submission_successful);
            view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_check_circle, 0, 0);
        } else {
            view.setText(R.string.submission_not_successful);
            view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_error_triangle, 0, 0);
        }
        return view;
    }

    public boolean getSuccess() {
        return success;

    }

    public void setSuccess(boolean success) {
        this.success = success;
        TextView view = (TextView) getView();
        if (view != null) {
            if (success) {
                view.setText(R.string.submission_successful);
                view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_check_circle, 0, 0);
            } else {
                view.setText(R.string.submission_not_successful);
                view.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_error_triangle, 0, 0);
            }
        }
    }
}