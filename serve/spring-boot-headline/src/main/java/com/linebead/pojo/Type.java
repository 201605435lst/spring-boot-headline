package com.linebead.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName news_type
 */
//@TableName(value ="news_type")
/* table-prefix: news_ # 设置表的前缀  因为统一配置了，所以不需要配置*/
@Data
public class Type implements Serializable {
    @TableId
    private Integer tid;

    private String tname;
    @Version
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}