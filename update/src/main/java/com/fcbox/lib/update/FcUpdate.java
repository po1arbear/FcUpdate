package com.fcbox.lib.update;


import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;

/**
 * Author:xz
 * Date:2019/11/7 16:06
 * Desc:
 */
public class FcUpdate implements IFcUpdate {
    private String mDownloadUrl;
    private String mApkName;
    private String mApkPath;
    private String mMd5;
    private CacheStrategy mCahceStrategy = CacheStrategy.DISK;
    private DownloadListener mDownloadListener;

    public static final String TAG ="FcUpdate";

    /**
     * 存储策略，MEMORY内存，DIST硬盘
     */
    enum CacheStrategy{
        MEMERY,
        DISK
    }

    private static final class HolderClass {
        private static final FcUpdate INSTANCE = new FcUpdate();
    }

    public static FcUpdate getInstance(Context context) {
        FileDownloader.setup(context);
        return HolderClass.INSTANCE;
    }

    @Override
    public void start() {
        start(mDownloadListener);
    }

    @Override
    public void start(DownloadListener listener) {
        this.mDownloadListener = listener;
        start(mDownloadUrl, mApkName, mApkPath);
    }

    @Override
    public void start(String downloadUrl, String apkName, String apkPath) {
        if (canStart()) {
            startInternal(downloadUrl, apkName, apkPath);
        }
    }


    @Override
    public void stop() {

    }

    public FcUpdate url(String url) {
        this.mDownloadUrl = url;
        return this;
    }

    public FcUpdate apkName(String apkName) {
        this.mApkName = apkName;
        return this;
    }

    public FcUpdate apkPath(String apkPath) {
        this.mApkPath = apkPath;
        return this;
    }

    public FcUpdate md5(String md5) {
        this.mMd5 = md5;
        return this;
    }

    public FcUpdate cacheStrategy(CacheStrategy strategy){
        this.mCahceStrategy = strategy;
        return this;
    }


    private boolean canStart() {
        if(TextUtils.isEmpty(mDownloadUrl)){
            Log.w(TAG,"download url is empty");
            return false;
        }

        if(TextUtils.isEmpty(mApkPath)){
            Log.w(TAG,"apk path is empty");
            return false;
        }

        if(TextUtils.isEmpty(mApkName)){
            Log.w(TAG,"apk name is empty");
            return false;
        }


        return true;
    }


    private void startInternal(String downloadUrl, String apkName, String mApkPath) {
        // apk路径
        String path = mApkPath+ File.separator+apkName;
        FileDownloader.getImpl().create(downloadUrl).
                setPath(path)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        mDownloadListener.onProgress(new DownloadProgress(totalBytes,soFarBytes));
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {

                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {

                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {

                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {

                    }
                });
    }


}
