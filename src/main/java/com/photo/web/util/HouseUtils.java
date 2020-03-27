package com.photo.web.util;



import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**

 * @Create 2020-01-11 4:29
 */
public class HouseUtils {

    private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyyMMddhhmmss");

    /**
     * 验证集合是否有效
     * @param c		待验证集合
     * @return		验证结果（true：有效，false：无效）
     * @author 封捷
     */
    public static <E> boolean collectionEffectiveCheck(Collection<E> c) {
        return (c != null) && (c.size() > 0);
    }

    /**
     * 验证字符串是否有效
     * @param source	待验证字符串
     * @return			验证结果（true：有效，false：无效）
     * @author 封捷
     */
    public static boolean strEffectiveCheck(String source) {
        return (source != null) && (source.length() > 0);
    }


    public static String getSharingFileConnectedActicleId(){
        String dateString = dateTimeFormatter.format(new Date());
        return UUID.randomUUID().toString().replace("-", "").substring(0, 6) + dateString;
    }

    public static void main(String[] args) {
        System.out.println(getSharingFileConnectedActicleId().length());
    }
}