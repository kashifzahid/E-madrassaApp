package com.example.akhlaqcommunication.emaddrassa.LoginFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.akhlaqcommunication.emaddrassa.ParentConsole.ParentDashboard;
import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.Dashboard;

public class ParentLogin extends Fragment {

    Button signin;
    public ParentLogin() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_parent_login, container, false);
        signin = view.findViewById(R.id.teachersignin);
        signin.setOnClickListener(myClickListner);
        return view;
    }
    private View.OnClickListener myClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.teachersignin:
                    Intent intent=new Intent(getContext(), ParentDashboard.class);
                    startActivity(intent);
            }
        }
    };

}
