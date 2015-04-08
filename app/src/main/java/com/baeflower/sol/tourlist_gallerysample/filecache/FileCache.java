package com.baeflower.sol.tourlist_gallerysample.filecache;

import com.baeflower.sol.tourlist_gallerysample.model.FileEntry;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by sol on 2015-04-07.
 */
public interface FileCache {
    public FileEntry get(String key);

    public void put(String key, ByteProvider provider) throws IOException;

    public void put(String key, InputStream is) throws IOException;

    public void put(String key, File sourceFile, boolean move) throws IOException;

    public void remove(String key);

    public void clear();
}
