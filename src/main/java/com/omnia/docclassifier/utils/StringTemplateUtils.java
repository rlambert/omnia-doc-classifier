/**
 * -----------------------------------------
 *      StringTemplateUtils Class
 *      by Ross W. Lambert
 *      Copyright (c) 2019
 *      Digital Provisioners
 *      All Rights Reserved
 *       - Used with permission of author
 * -----------------------------------------
 */

package com.omnia.docclassifier.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.stringtemplate.v4.ST;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Data
public class StringTemplateUtils {

    @Slf4j
    public static class TemplateCache {
        private String templateName;
        private String templateFile;
        private String templateText;
        private ST parsedTemplate;

        /**
         * reads and caches template... this name provides clearer semantics
         * @throws IOException
         */
        public void cacheTemplate() throws IOException {
            // fetch template content if we haven't already
            if (StringUtils.isNullOrEmpty(this.templateText)) {
                try {
                    this.templateText = StringUtils.readResource(this, this.templateFile);
                    ST st = new ST(templateText, '~', '~');  // note delimiters
                    this.parsedTemplate = st;
                }
                catch (IOException ex) {
                    String msg = String.format("Could not load template %s at %s, ", ex.getMessage());
                    TemplateCache.log.error(LogUtils.formatError(ex));
                    throw new IOException(msg);
                }
            }
        }

        /**
         * constructor
         * @param templateName - String
         * @param templateFile - String
         */
        public TemplateCache(String templateName, String templateFile) {
            this.templateName = templateName;
            this.templateFile = templateFile;
        }

        /**
         * read each template and cache the compiled template instance
         * @param templateMap - Map&lt;String,String&gt;
         * @return Map&lt;String,TemplateCache&gt;
         * @throws IOException - on file read error
         */
        public static Map<String, TemplateCache> buildTemplateCache(Map<String, String> templateMap) throws IOException {
            Map<String, TemplateCache> tcMap = new HashMap<>();
            for (Map.Entry entry : templateMap.entrySet()) {
                TemplateCache tc = new TemplateCache(entry.getKey().toString(), entry.getValue().toString());
                tc.cacheTemplate();
                tcMap.put(tc.templateName, tc);
            }
            return tcMap;
        }

    }

    // cache of preparsed templates
    private Map<String, TemplateCache> templateCacheMap = new HashMap<>();

    /**
     * constructor
     * @param templateMap - Map&lt;String, String&gt;
     */
    public StringTemplateUtils(Map<String, String> templateMap) {
        try {
            this.templateCacheMap = TemplateCache.buildTemplateCache(templateMap);
        }
        catch (IOException ex) {
            // we swallow this because we know it was logged at the point it happened
            // instead we throw a runtime error
            throw new IllegalArgumentException();
        }
    }

    /**
     * no-arg constructor
     */
    public StringTemplateUtils() { }

    /**
     * parses all templates required for this instance
     * @param templateList - List&lt;String&gt; key = name, value = file name
     * @param templatePath - String, folder in which templates are stored
     * @throws IOException
     */
    public void buildTemplateCache(List<String> templateList, String templatePath) throws IOException {

        // initialize templates if needed
        if (this.getTemplateCacheMap().size() == 0) {
            if (StringUtils.isNullOrEmpty(templatePath)) {
                templatePath = "templates";
            }
            Map<String, String> tmap = new HashMap<>();
            for (String taskId : templateList) {
                String templateFile = String.format("%s/%s", templatePath, taskId);
                tmap.put(taskId, templateFile);
            }
            // this *should* give us one static cache per JVM, or at least per class-loader
            this.setTemplateCacheMap(StringTemplateUtils.TemplateCache.buildTemplateCache(tmap));
        }
    }

    /**
     * overload that doesn't require the path (will default to "templates")
     * @param templateList
     * @throws IOException
     */
    public void buildTemplateCache(List<String> templateList) throws IOException {
        buildTemplateCache(templateList, null);
    }

    /**
     * Transforms the data by inserting it into the template with the given
     * name. The compiled template is cloned and then thrown away so we don't
     * have to bother to empty it out.
     * @param dataIn - Map<String, Object>
     * @param templateName - String
     * @return String
     */
    public String transform(Map<String, Object> dataIn, String templateName) {
        TemplateCache templateCache = this.getTemplateCacheMap().get(templateName.toLowerCase());
        ST stCopy = new ST(templateCache.parsedTemplate);
        addMap(stCopy, dataIn);
        return stCopy.render();
    }

    /**
     * This method is deceptively simple: It is filling in the "holes"
     * of the template, but does so by matching the key name in the map
     * to the named values in the template. Because it is StringTemplate,
     * you can put an object into the map and the template can access
     * the object's fields with dot notation (e.g. user.name).
     * @param tmpl - ST, a StringTemplate
     * @param map - Map&lt;String, Object&gt;
     * @return String, the output of the rendered template
     */
    public static void addMap(ST tmpl, Map<String, Object> map) {
        Object valueObj;
        for(Map.Entry<String, Object> set : map.entrySet()) {
            valueObj = set.getValue();
            if (valueObj instanceof Map) {
                addMap(tmpl, (Map<String, Object>) valueObj);
            }
            else {
                tmpl.add(set.getKey(), set.getValue());
            }
        }
    }
}
