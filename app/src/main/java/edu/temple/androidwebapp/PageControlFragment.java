package edu.temple.androidwebapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PageControlFragment extends Fragment {

    PageControlListener controlListener;
    Button forward;
    Button back;
    Button okButton;
    EditText urlText;

    public interface PageControlListener{
        void onPush(CharSequence input);
        void onForward();
        void onBackward();
    }
    public PageControlFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_page_control, container, false);
        forward = v.findViewById(R.id.forward);
        back = v.findViewById(R.id.backward);
        okButton = v.findViewById(R.id.ok_button);
        urlText = v.findViewById(R.id.editTextURL);
        /*Go button to go to specified URL*/
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CharSequence input = urlText.getText();
                controlListener.onPush(input);
            }
        });
        /*Navigate to the next clicked page*/
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlListener.onForward();
            }
        });

        /*Navigate back to previous page*/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlListener.onBackward();
            }
        });
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PageControlListener){
            controlListener = (PageControlListener) context;
        }else{
            throw new RuntimeException("Please implements PageControlListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        controlListener = null;
    }
}