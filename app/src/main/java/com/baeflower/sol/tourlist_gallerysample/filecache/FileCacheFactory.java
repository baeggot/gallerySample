package com.baeflower.sol.tourlist_gallerysample.filecache;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.HashMap;

/**
 * Created by sol on 2015-04-07.
 */
public class FileCacheFactory {

    private static final String TAG = FileCacheFactory.class.getSimpleName();
    private static boolean initialized = false;
    private static FileCacheFactory instance = new FileCacheFactory();

    public static void initialize(Context context) {
        if (!initialized) {
            synchronized (instance) {
                if (!initialized) {
                    instance.init(context);
                    initialized = true;
                }
            }
        }
    }

    public static FileCacheFactory getInstance() {
        if (!initialized) {
            throw new IllegalStateException(
                    "Not initialized. You must call FileCacheFactory.initialize() before getInstance()");
        }
        return instance;
    }

    private HashMap<String, FileCache> cacheMap = new HashMap<String, FileCache>();
    private File cacheBaseDir;

    private FileCacheFactory() {
    }

    private void init(Context context) {
        cacheBaseDir = context.getCacheDir();
    }

    public FileCache create(String cacheName, int maxKbSizes) {
        synchronized (cacheMap) {
            FileCache cache = cacheMap.get(cacheName);
            if (cache != null) {
                try {
                    throw new FileCacheAleadyExistException(String.format(
                            "FileCache[%s] Aleady exists", cacheName));
                } catch (FileCacheAleadyExistException e) {
                    e.printStackTrace();
                }
            }
            File cacheDir = new File(cacheBaseDir, cacheName);
            cache = new FileCacheImpl(cacheDir, maxKbSizes);
            cacheMap.put(cacheName, cache);
            return cache;
        }
    }

    public FileCache get(String cacheName) {
        synchronized (cacheMap) {
            FileCache cache = cacheMap.get(cacheName);
            if (cache == null) {
                try {
                    throw new FileCacheNotFoundException(String.format(
                            "FileCache[%s] not founds.", cacheName));
                } catch (FileCacheNotFoundException e) {
                    e.printStackTrace();
                }
            }
            return cache;
        }
    }

    public boolean has(String cacheName) {
        return cacheMap.containsKey(cacheName);
    }

    private class FileCacheAleadyExistException extends RuntimeException {
        public FileCacheAleadyExistException(String format) {
            Log.d(TAG, format);
        }
    }

    private class FileCacheNotFoundException extends RuntimeException {
        public FileCacheNotFoundException(String format) {
            Log.d(TAG, format);
        }
    }
}

