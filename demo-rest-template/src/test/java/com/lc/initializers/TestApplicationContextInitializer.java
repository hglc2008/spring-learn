package com.lc.initializers;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.support.GenericWebApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author liuci
 * @date 2017/12/12
 * @desc http://www.jianshu.com/p/1c798a6c56be
 */
public class TestApplicationContextInitializer implements ApplicationContextInitializer<GenericWebApplicationContext>{

    @Override
    public void initialize(GenericWebApplicationContext genericWebApplicationContext) {
        Properties props = getTestProperties();

        MutablePropertySources sources = genericWebApplicationContext.getEnvironment().getPropertySources();
        if (props!= null && !props.isEmpty()){
            Map<String,Object> map = new HashMap<String,Object>((Map)props);
            sources.addLast(new MapPropertySource("defaultProperties",map));
        }
    }

    private Properties getTestProperties() {
        Properties prop =  new  Properties();
        InputStream in = this.getClass().getResourceAsStream( "/application.properties" );
        try  {
            prop.load(in);
            System.out.println(prop.getProperty( "test.url" ).trim());
        }  catch  (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
