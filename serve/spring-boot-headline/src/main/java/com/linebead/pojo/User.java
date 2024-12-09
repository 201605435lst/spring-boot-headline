package com.linebead.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;

/**
 * @TableName news_user
 */
//@TableName(value ="news_user")
/* table-prefix: news_ # 设置表的前缀  因为统一配置了，所以不需要配置*/
@Data
public class User implements Serializable {

    @TableId
    private Integer uid;

    private String username;

    private String userPwd;

    private String nickName;
    @Version
    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}