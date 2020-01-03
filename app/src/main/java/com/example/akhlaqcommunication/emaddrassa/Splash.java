 package com.example.akhlaqcommunication.emaddrassa;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.akhlaqcommunication.emaddrassa.ParentConsole.ParentDashboard;
import com.example.akhlaqcommunication.emaddrassa.ParentConsole.ParentDashboard2;
import com.example.akhlaqcommunication.emaddrassa.Shared.SharedPreferenceEdit;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.Dashboard;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.TeacherDashboard;
import com.example.akhlaqcommunication.emaddrassa.TeachersConsole.TeacherLogin;
import com.example.akhlaqcommunication.emaddrassa.Utils.LanguageUtil;

import java.util.Locale;

 public class Splash extends AppCompatActivity {

    private TextView txtProgress;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        txtProgress = findViewById(R.id.txtProgress);
        progressBar = findViewById(R.id.progressBar);
        //Locale locale = Locale.getDefault();
        Locale locale=Locale.getDefault();
        Locale.setDefault(new Locale("ur"));

        Log.e("tag", "onCreate: jj "+Locale.getDefault().getLanguage());
        LanguageUtil.changeLanguageType(this,new Locale(Locale.getDefault().getLanguage()));


        locale=LanguageUtil.getLanguageType(this);
        Log.e("jfn", "onCreate: "+locale.getLanguage() );
                startActivity(new Intent(Splash.this,TeacherDashboard.class));
        finish();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (progressStatus < 100) {
//                    // Update the progress status
//                    progressStatus += 1;
//                    // Try to sleep the thread for 20 milliseconds
//                    try {
//                        Thread.sleep(40);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    // Update the progress bar
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            progressBar.setProgress(progressStatus);
//                            // Show the progress on TextView
//                            txtProgress.setText(progressStatus + "%");
//                            // If task execution completed
//                            if (progressStatus == 100) {
//                                // Set a message of completion
//                                SharedPreferenceEdit sharedPreferenceEdit=new SharedPreferenceEdit(Splash.this);
//                                String status=sharedPreferenceEdit.GetLoginStatus();
//                                if(status.equals("login")){
//                                    String type=sharedPreferenceEdit.GetLoginType();
//                                    if(type.equals("parent")){
//                                        Intent intent = new Intent(getApplicationContext(), ParentDashboard2.class);
//                                        startActivity(intent);
//
//                                    }else if(type.equals("teacher")){
//
//
//                                    Intent intent = new Intent(getApplicationContext(), TeacherDashboard.class);
//                                    startActivity(intent);
//                                    }
//                                }else{
//                                    Intent intent = new Intent(getApplicationContext(), TeacherLogin.class);
//                                    startActivity(intent);
//                                }
//
//                            }
//                        }
//                    });
//                }
//            }
//        }).start(); // Start the operation


    }
}
