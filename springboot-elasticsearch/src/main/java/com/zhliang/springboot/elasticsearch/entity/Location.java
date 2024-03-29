package com.zhliang.springboot.elasticsearch.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @类描述：
 * @创建人：zhiang
 * @创建时间：2020/12/23 09:58
 * @version：V1.0
 */
@Builder(toBuilder=true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location extends BaseEntity<Long> {

    private Long id;

    private String flag;

    private String localCode;

    private String localName;

    private Integer lv;

    private String supLocalCode;

    private String url;

    public Location(Long id, String localName, String supLocalCode, String url, Integer lv) {
        this.id=id;
        this.localName = localName;
        this.supLocalCode = supLocalCode;
        this.url = url;
        this.lv=lv;
    }

    public Location(String localCode, String localName, String supLocalCode, String url, Integer lv) {
        super();
        this.localCode = localCode;
        this.localName = localName;
        this.supLocalCode = supLocalCode;
        this.url = url;
        this.lv=lv;
    }

    public Location(String localCode, String localName, String supLocalCode, String url, Integer lv,String flag) {
        super();
        this.localCode = localCode;
        this.localName = localName;
        this.supLocalCode = supLocalCode;
        this.url = url;
        this.lv=lv;
        this.flag=flag;
    }

}
