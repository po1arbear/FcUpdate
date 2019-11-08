package com.fcbox.lib.update;

/**
 * Author:xz
 * Date:2019/11/7 16:04
 * Desc:
 */
public interface DownloadListener {
    void onCompleted();

    void onProgress(DownloadProgress progress);

    void onError();

}
