package com.zhliang.springboot.elasticsearch.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @类描述： 创建索引模板，用于解析为JSON 格式
 * @创建人：zhiang
 * @创建时间：2020/12/23 09:34
 * @version：V1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdxVo {

    /**
     * idxName : idx_location
     * idxSql : {"dynamic":false,"properties":{"location_id":{"type":"long"},"flag":{"type":"text","index":true},"local_code":{"type":"text","index":true},"local_name":{"type":"text","index":true,"analyzer":"ik_max_word"},"lv":{"type":"long"},"sup_local_code":{"type":"text","index":true},"url":{"type":"text","index":true}}}
     */
    private String idxName;
    private IdxSql idxSql;

    public static class IdxSql {
        /**
         * dynamic : false
         * properties : {"location_id":{"type":"long"},"flag":{"type":"text","index":true},"local_code":{"type":"text","index":true},"local_name":{"type":"text","index":true,"analyzer":"ik_max_word"},"lv":{"type":"long"},"sup_local_code":{"type":"text","index":true},"url":{"type":"text","index":true}}
         */

        private boolean dynamic=false;


        private Map<String, Map<String, Object>> properties;

        public boolean isDynamic() {
            return dynamic;
        }

        public void setDynamic(boolean dynamic) {
            this.dynamic = dynamic;
        }

        public Map<String, Map<String, Object>> getProperties() {
            return properties;
        }

        public void setProperties(Map<String, Map<String, Object>> properties) {
            this.properties = properties;
        }
    }
}
