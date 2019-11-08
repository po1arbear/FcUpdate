package com.fcbox.lib.updatelib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fcbox.lib.update.DownloadListener;
import com.fcbox.lib.update.DownloadProgress;
import com.fcbox.lib.update.FcUpdate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "dsa";
        String apkName = "dsa";
        String apkPath = "apkPath";
        FcUpdate.getInstance(this).url(url).apkName(apkName).apkPath(apkPath)
                .start(new DownloadListener() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onProgress(DownloadProgress progress) {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
