package org.sunt.basicmodel;

import org.apache.commons.codec.digest.MurmurHash3;

import java.util.zip.CRC32;

/**
 * 全局唯一ID生成工具类，boss说过这个东西非常重要
 * 元数据相关的ID都以些方法生成即可不用每次都从表中读取ID
 */
public interface Important {

    int METADATA_HASH_SEED = 0xFACE181;

    static long databaseId(String datasourceIdentifier, String database) {
        return hash(String.join("/", datasourceIdentifier, database));
    }

    static long tableId(String datasourceIdentifier, String database, String table) {
        return hash(String.join("/", datasourceIdentifier, database, table));
    }

    static long columnId(String datasourceIdentifier, String database, String table, String column) {
        return hash(String.join("/", datasourceIdentifier, database, table, column));
    }

    static long hashId(String contextPath) {
        return hash(contextPath);
    }

    static long hash(String str) {
        return Math.abs(crc64(str));
    }

    static long crc32(String str) {
        CRC32 CRC_32 = new CRC32();
        CRC_32.update(str.toUpperCase().getBytes());
        return CRC_32.getValue();
    }

    static long murmurHash64(String str) {
        return MurmurHash3.hash64(str.toUpperCase().getBytes());
    }

    static long crc64(String str) {
        CRC64 CRC_64 = new CRC64();
        byte[] bytes = str.toUpperCase().getBytes();
        CRC_64.update(bytes, bytes.length);
        return CRC_64.getValue();
    }

}
