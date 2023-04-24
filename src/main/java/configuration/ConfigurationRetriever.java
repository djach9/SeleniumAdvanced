package configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class ConfigurationRetriever {

    private static Configuration configuration;
    private static User user;
    private static ProductData product;

    public static Configuration getConfig() {
        if (configuration == null) {
            try {
                configuration = getObjectMapper().readValue(getConfigFile("configuration.yml"), Configuration.class);
                return configuration;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return configuration;
    }

    public static ProductData getProductData() {
        if (product == null) {
            try {
                product = getObjectMapper().readValue(getConfigFile("productData.yml"), ProductData.class);
                return product;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return product;
    }

    public static User getUserData() {
        if (user == null) {
            try {
                user = getObjectMapper().readValue(getConfigFile("userData.yml"), User.class);
                return user;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    private static ObjectMapper getObjectMapper() {
        return new ObjectMapper(new YAMLFactory());
    }

    private static File getConfigFile(String filePath) {
        File configFile = null;
        try {
            URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource(filePath);
            if (resourceUrl != null) {
                configFile = new File(resourceUrl.getFile());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Objects.requireNonNull(configFile, "Configuration file cannot be null");
        return configFile;
    }

}