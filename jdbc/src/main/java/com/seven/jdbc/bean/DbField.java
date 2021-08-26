package com.seven.jdbc.bean;

import cn.hutool.core.map.MapUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
public class DbField {
    private String tableName;
    private String isNullable;
    private String columnName;
    private String remarks;
    private String typeName;
    private String isAutoIncrement;
    private Boolean isUnique = false;
    private Boolean isKey = false;

    public static List<DbField> map2Bean(List<Map> list) {
        return list.stream().map(DbField::map2Bean).collect(Collectors.toList());

    }

    public static DbField map2Bean(Map map) {
        return new DbField()
                .setTableName(MapUtil.getStr(map, "TABLE_NAME"))
                .setIsNullable(MapUtil.getStr(map, "IS_NULLABLE"))
                .setColumnName(MapUtil.getStr(map, "COLUMN_NAME"))
                .setRemarks(MapUtil.getStr(map, "REMARKS"))
                .setTypeName(MapUtil.getStr(map, "TYPE_NAME"))
                .setIsAutoIncrement(MapUtil.getStr(map, "IS_AUTOINCREMENT"))
                ;
    }
}
