package com.baeflower.sol.tourlist_gallerysample.filecache;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by sol on 2015-04-07.
 */
public interface ByteProvider {
    void writeTo(OutputStream os) throws IOException;
}
