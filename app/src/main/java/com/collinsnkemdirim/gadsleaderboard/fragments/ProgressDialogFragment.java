package com.collinsnkemdirim.gadsleaderboard.fragments;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gadsleaderboard.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgressDialogFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgressDialogFragment extends DialogFragment {


    private static final String ARG_MESSAGE = "message";


    public ProgressDialogFragment() {
        // Required empty public constructor
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, "");
        setArguments(args);
        setCancelable(false);

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param message
     * @return A new instance of fragment ProgressDialogFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgressDialogFragment newInstance(String message) {
        ProgressDialogFragment dialogFragment = new ProgressDialogFragment();
        dialogFragment.getArguments().putString(ARG_MESSAGE, message);
        return dialogFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.progress_dialog, container, false);
        TextView txtMessage = view.findViewById(R.id.message_txtView);
        txtMessage.setText(getArguments().getString(ARG_MESSAGE));
        return view;
    }


}