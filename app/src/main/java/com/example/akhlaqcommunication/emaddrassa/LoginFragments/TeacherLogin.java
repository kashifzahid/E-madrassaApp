package com.example.akhlaqcommunication.emaddrassa.LoginFragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.akhlaqcommunication.emaddrassa.R;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.Dashboard;
import com.example.akhlaqcommunication.emaddrassa.Volley.Urls;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyPostCallBack;
import com.example.akhlaqcommunication.emaddrassa.Volley.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static com.android.volley.VolleyLog.TAG;


public class TeacherLogin extends Fragment {

    Button signin;
    EditText user,pass;
    public TeacherLogin() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_teacher_login, container, false);
        signin = view.findViewById(R.id.teachersignin);
        user=view.findViewById(R.id.user_name);
        pass=view.findViewById(R.id.pass);

        signin.setOnClickListener(myClickListner);
        return view;
    }
    private View.OnClickListener myClickListner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.teachersignin:
                    JSONObject data=new JSONObject();
                    try {
                        data.put("screen","TeacherLogin");
                        String users=user.getText().toString();
                        String pa=pass.getText().toString();
                        data.put("name",users);
                        data.put("pass",pa);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    VolleyRequest.PostRequest(getActivity(), Urls.login,data, new VolleyPostCallBack() {
                        @Override
                        public void OnSuccess(JSONObject jsonObject)  {
                            try {
                                String status=jsonObject.getString("result");
                                Log.e(TAG, "OnSuccess: "+status );
                                if(status.equals("0")){
                                    Toast.makeText(getActivity(), "Login not valid", Toast.LENGTH_SHORT).show();
                                    user.setText("");
                                    pass.setText("");
                                }else{
                                    SharedPreferenceEdit sharedPreferenceEdit=new SharedPreferenceEdit(getActivity());
                                    sharedPreferenceEdit.AddDriverId(status);
                                    Intent intent=new Intent(getContext(), Dashboard.class);
                                    startActivity(intent);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        @Override
                        public void OnFailure(String err) {

                        }
                    });

            }
        }
    };
}