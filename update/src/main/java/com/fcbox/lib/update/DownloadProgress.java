package com.fcbox.lib.update;

/**
 * Author:xz
 * Date:2019/11/7 17:17
 * Desc:
 */
public class DownloadProgress {

    DownloadProgress(int totalSize, int downloadSize) {
        this.totalSize = totalSize;
        this.downloadSize = downloadSize;
    }


    public int downloadSize;
    public int totalSize;

    public int getProgress() {
        if (totalSize != 0) {
            return (int) (downloadSize * 100.0 / totalSize);
        } else {
            return 100;
        }
    }

}
