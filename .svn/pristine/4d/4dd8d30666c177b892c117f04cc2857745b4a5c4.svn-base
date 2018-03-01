package fengkongweishi.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jianger
 * @Description bean转Map
 * @Date 2018/2/1 上午10:30
 **/
public class BeanToMapUtils {


    /**
     * 将一个bean转换为一个map
     * 其父类的getter和字段无法自动获取到，只能手动补齐，又或者指明字段名，也可以获取到值
     *
     * @return
     */
    public static Map<String, Object> beanToMap(Object bean) {
        Map<String, Object> result = new HashMap<>();
        if (bean == null) {
            return result;
        }
        Class<?> cls = bean.getClass();
        // 取出bean里的所有方法
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});
                result.put(field.getName(), fieldVal);
            } catch (Exception e) {
                result.put(field.getName(), null);
                continue;
            }
        }
        return result;
    }


    private static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    private static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        return "get" + fieldName.substring(0, 1).toUpperCase()
                + fieldName.substring(1);
    }


}
