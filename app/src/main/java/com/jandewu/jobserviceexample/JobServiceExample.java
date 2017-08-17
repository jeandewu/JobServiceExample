package com.jandewu.jobserviceexample;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by RENT on 2017-08-11.
 */

public class JobServiceExample extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Toast.makeText(this, "onStartJob", Toast.LENGTH_SHORT).show();
        Log.d(JobServiceExample.class.getSimpleName(), "onStartJob");
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }


}
