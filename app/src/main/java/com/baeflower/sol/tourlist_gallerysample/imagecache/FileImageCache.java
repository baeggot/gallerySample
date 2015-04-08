
package com.baeflower.sol.tourlist_gallerysample.imagecache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.baeflower.sol.tourlist_gallerysample.filecache.ByteProvider;
import com.baeflower.sol.tourlist_gallerysample.filecache.FileCache;
import com.baeflower.sol.tourlist_gallerysample.filecache.FileCacheFactory;
import com.baeflower.sol.tourlist_gallerysample.model.FileEntry;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by sol on 2015-04-07.
 */
public class FileImageCache implements ImageCache {
    private static final String TAG = "FileImageCache";

    private FileCache fileCache;

    public FileImageCache(String cacheName) {
        fileCache = FileCacheFactory.getInstance().get(cacheName);
    }

    @Override
    public void addBitmap(String key, final Bitmap bitmap) {
        try {
            fileCache.put(key, new ByteProvider() {
                @Override
                public void writeTo(OutputStream os) {bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
                }
            });
        } catch (IOException e) {
            Log.e(TAG, "fail to bitmap to fileCache", e);
        }
    }

    @Override
    public void addBitmap(String key, File bitmapFile) {
        try {
            fileCache.put(key, bitmapFile, true);
        } catch (IOException e) {
            Log.e(TAG, String.format("fail to bitmap file[%s] to fileCache",
                    bitmapFile.getAbsolutePath()), e);
        }
    }

    @Override
    public Bitmap getBitmap(String key) {
        FileEntry cachedFile = fileCache.get(key);
        if (cachedFile == null) {
            return null;
        }
        return BitmapFactory.decodeFile(cachedFile.getFile().getAbsolutePath());
    }

    @Override
    public void clear() {
        fileCache.clear();
    }
}
