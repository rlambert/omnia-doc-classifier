/**
 * -----------------------------------------
 *      DataTransformer Base Class
 *      by Ross W. Lambert
 *      Copyright (c) 2019
 *      Digital Provisioners
 *      All Rights Reserved
 *       - Used with permission of author
 * -----------------------------------------
 */
package com.omnia.docclassifier.transforms;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public abstract class DataTransformer {


    public abstract void init(List<String> templates) throws IOException;

    public abstract Map<String, Object> transform(Map<String, Object> dataIn);

    public abstract Map<String, Object> transform(Map<String, Object> dataIn, String templateName);

    public abstract void close();
}
