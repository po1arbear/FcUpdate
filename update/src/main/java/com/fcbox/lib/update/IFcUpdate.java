package com.fcbox.lib.update;

/**
 * Author:xz
 * Date:2019/11/7 16:03
 * Desc:
 */
public interface IFcUpdate {
    void start();
    void start(DownloadListener listener);
    void start(String downloadUrl,String apkName,String apkPath);
    void stop();
}
