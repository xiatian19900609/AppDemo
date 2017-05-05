package com.xinfu.demo3;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用Jackson 来解析Json
 * 需要注意转化解析异常导致解析结果为null,一般情况下为Bean构造异常
 *
 * @JsonIgnoreProperties(value={"hibernateLazyInitializer"}) 注释
 * http://godfox.iteye.com/blog/646887
 * http://my.oschina.net/u/568577/blog/386107
 * http://blog.csdn.net/gavincook/article/details/46574661
 * http://shmuelrosansky.com/jackson/android/2015/07/20/jackson-android/
 * http://www.cnblogs.com/lee0oo0/articles/2658772.html
 * Created by Tony on 7/25/16.
 */
public class JsonUtil {

    public static JsonUtil instance = null;

    public static ObjectMapper mapper = new ObjectMapper();

    public static JsonUtil getInstance() {
        if (instance == null)
            instance = new JsonUtil();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return instance;
    }

    public <T> T convertString2Bean(String content, Class<T> valueType) {
        try {
            return mapper.readValue(content, valueType);
        } catch (Exception e) {
//            ConfigUtil.showLog("JsonUtil", "=====convertString2Bean=====parse exception ======" + e.getLocalizedMessage());
            if (content != null && content.contains("+")) {
                content = content.replaceAll("\\+", "");
                try {
                    return mapper.readValue(content, valueType);
                } catch (Exception e1) {
//                    ConfigUtil.showLog("JsonUtil", "=====convertString2Bean=====+++++++ ======" + e.getLocalizedMessage());
                    return null;
                }
            }
            return null;
        }
    }

    public <T> T convertJsonString2Type(String content, TypeReference type) {
        try {
            return mapper.readValue(content, type);
        } catch (Exception e) {
//            ConfigUtil.showLog("JsonUtil", "=====convertJsonString2Type=====parse exception ======" + e.getLocalizedMessage());
            return null;
        }
    }


    public String converData2String(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
//            ConfigUtil.showLog("JsonUtil", "====converData2String======parse exception ======" + e.getLocalizedMessage());
            return null;
        }
    }


    /**
     * json串转ArrayList
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> convertJson2Array(String json, Class<T> clazz) {

        JavaType javaType = getCollectionType(ArrayList.class, clazz);
        try {
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取类型
     *
     * @param collectionClass 集合：如  ArrayList.class
     * @param elementClasses  集合中的元素 如： ArrayList<User>  中的User.class
     * @return
     */
    private JavaType getCollectionType(Class<?> collectionClass, Class<?> elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
