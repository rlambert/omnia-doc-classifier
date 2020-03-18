/**
 * -----------------------------------------
 *      StringTemplateTransformImpl Class
 *      by Ross W. Lambert
 *      Copyright (c) 2019
 *      Digital Provisioners
 *      All Rights Reserved
 *       - Used with permission of author
 * -----------------------------------------
 */
package com.omnia.docclassifier.transforms;


import com.omnia.docclassifier.utils.StringTemplateUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringTemplateTransformImpl extends DataTransformer {

    private static StringTemplateUtils _templateUtils = new StringTemplateUtils();


    @Override
    public void init(List<String> templateList) throws IOException {
        _templateUtils.buildTemplateCache(templateList);
    }

    @Override
    public Map<String, Object> transform(Map<String, Object> dataIn) {
        return transform(dataIn, "Slack");
    }

    @Override
    public Map<String, Object> transform(Map<String, Object> dataIn, String xformName) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", _templateUtils.transform(dataIn, xformName));  // Slack is default for this xform
        return result;
    }

    @Override
    public void close() {
    }
}
