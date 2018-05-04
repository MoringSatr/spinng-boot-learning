package top.liubowen.learning07.common;

import java.io.IOException;
import java.util.Properties;

/**
 * @author liubowen
 * @date 2018/5/4 11:08
 * @description 常量信息信息
 */
public class Constants {

    public static String RESOURCE_URL;

    public static String SERVER_URL;

    public static String SERVER_SOCKET_URL;

    public static int SERVER_ID;

    public static String SERVER_NAME;

    public static void load() {
        try {
            loadPlatformProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadPlatformProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(Constants.class.getClassLoader().getResourceAsStream("constant_file.properties"));
        String platform_path = properties.getProperty("platform_path");
        properties.clear();
        properties.load(Constants.class.getClassLoader().getResourceAsStream(platform_path));

        RESOURCE_URL = properties.getProperty("resource_url");
        SERVER_URL = properties.getProperty("server_url");
        SERVER_SOCKET_URL = properties.getProperty("server_socket_url");
        SERVER_ID = Integer.parseInt(properties.getProperty("server_id"));
        SERVER_NAME = properties.getProperty("server_name");
    }

}
