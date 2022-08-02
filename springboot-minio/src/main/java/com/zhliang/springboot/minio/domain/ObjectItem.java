package com.zhliang.springboot.minio.domain;

import lombok.Data;

/**
 * ClassName: ObjectItem
 * Function:
 * Date: 2022年07月01 18:13:51
 *
 * @author 张良 E-mail:zhangliang01@jingyougroup.com
 * @version V1.0.0
 */
@Data
public class ObjectItem {

    private long size;
    private String objectName;

}
