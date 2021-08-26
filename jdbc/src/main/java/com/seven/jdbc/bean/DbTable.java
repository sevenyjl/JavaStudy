package com.seven.jdbc.bean;

import cn.hutool.core.map.MapUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class DbTable {
    private String tableCat;
    private String tableName;
    private String tableType;
    private String remarks;
    private List<Field> fields;

    public static List<DbTable> map2Bean(List<Map> list) {
        if (list == null) return null;
        return list.stream().map(DbTable::map2Bean).collect(Collectors.toList());

    }

    public static DbTable map2Bean(Map map) {
        if (map == null) return null;
        return new DbTable()
                .setTableCat(MapUtil.getStr(map, "TABLE_CAT"))
                .setTableName(MapUtil.getStr(map, "TABLE_NAME"))
                .setTableType(MapUtil.getStr(map, "TABLE_TYPE"))
                .setRemarks(MapUtil.getStr(map, "REMARKS"));
    }
}
