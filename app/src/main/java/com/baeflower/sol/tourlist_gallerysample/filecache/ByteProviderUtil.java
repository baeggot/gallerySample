package com.baeflower.sol.tourlist_gallerysample.filecache;

import com.baeflower.sol.tourlist_gallerysample.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by sol on 2015-04-07.
 */
public abstract class ByteProviderUtil {

    public static ByteProvider create(final InputStream is) {
        return new ByteProvider() {
            @Override
            public void writeTo(OutputStream os) throws IOException {
                IOUtils.copy(is, os);
            }
        };
    }

    public static ByteProvider create(final File file) {
        return new ByteProvider() {
            @Override
            public void writeTo(OutputStream os) throws IOException {
                IOUtils.copy(file, os);
            }
        };
    }

    public static ByteProvider create(final String str) {
        return new ByteProvider() {
            @Override
            public void writeTo(OutputStream os) throws IOException {
                IOUtils.copy(str, os);
            }
        };
    }
}
