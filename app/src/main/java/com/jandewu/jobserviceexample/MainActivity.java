package com.jandewu.jobserviceexample;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "dddd" ;
    JobScheduler jobScheduler;
    private Chronometer chronometer;
    private String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.start_job_button);
        Button stopButton = (Button) findViewById(R.id.cancel_all_job_button);
        chronometer = (Chronometer) findViewById(R.id.chronometer);
        jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
    }

    public void startJob(View view) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
        ComponentName componentName = new ComponentName(this, JobServiceExample.class);
        JobInfo.Builder builder = new JobInfo.Builder(997, componentName).setRequiresCharging(true);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            builder.setMinimumLatency(5000);
        }
        else builder.setPeriodic(1000);

        JobInfo jobInfo = builder.build();
        jobScheduler.schedule(jobInfo);
    }


    public void stopJob(View view) {
        chronometer.stop();
     //   jobScheduler.cancelAll();

        List<JobInfo> allJobs = jobScheduler.getAllPendingJobs();
        for (JobInfo jobInfo:allJobs){

            Log.d(TAG, String.format("Cancel %d",jobInfo.getId()));
            jobScheduler.cancel(jobInfo.getId());
        }

    }


}
