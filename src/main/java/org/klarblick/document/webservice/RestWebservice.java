package org.klarblick.document.webservice;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.klarblick.document.webservice.resource.DocumentResource;
import org.klarblick.document.webservice.resource.WatermarkResource;

/**
 */
@ApplicationPath("/")
public class RestWebservice extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register root resource
        classes.add(DocumentResource.class);
        classes.add(WatermarkResource.class);
        return classes;
    }
}