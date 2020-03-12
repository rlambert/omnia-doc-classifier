/*
 -----------------------------------------
   VersionController
   Copyright (c) 2018
   Digital Provisioners
   All Right Reserved
 -----------------------------------------
 */

package com.omnia.docclassifier.web.controllers;

import com.omnia.docclassifier.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Properties;


@RestController
@Component
@CrossOrigin
@RequestMapping(value = {"${app.apiRestBase}/version", "/version"}, produces = "application/json")
public class VersionController extends ApiBase {

    private String _appName;
    private Environment _environment;

    @Autowired
    public VersionController(AppConfig config, Environment env) {
        super(config);
        _appName = config.getName();
        _environment = env;
    }

    private String getVersion() throws IOException {
        String profileTxt = "default";

        // extract all active profiles
        String[] profiles = _environment.getActiveProfiles();
        if ((profiles != null) && (profiles.length > 0)) {
            StringBuilder sb = new StringBuilder();
            for (String profile : profiles) {
                sb.append(profile);
                sb.append(", ");
            }
            sb.setLength(sb.length() - 2);
            profileTxt = sb.toString();
        }
        Properties props = new Properties();
        props.put("active.profiles", profileTxt);
        props.put("appname", _appName);
        props.put("version", this.getAppConfig().getVersion());
        String json = this.serialize(props);
        return json;
    }

    @GetMapping(value = {"/", ""}, produces = "application/json")
    public String version1() throws IOException {
        return getVersion();
    }
}
