package cn.com.uitl;

import java.util.UUID;

/**
 * 生成32位UUID
 * @author haku
 *
 */
public class UUIDGenerator {
    
    // 生成UUID
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }
    
}