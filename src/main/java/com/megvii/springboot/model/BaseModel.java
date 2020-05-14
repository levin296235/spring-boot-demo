package com.megvii.springboot.model;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {

    protected Integer state;//状态

    protected String creator;//创建人

    protected String createTime;//创建时间

    private String lastLoginTime;

    protected String modifiedBy;//修改人

    protected String changeTime;//修改时间

    protected Boolean deleted=false;//删除标志
}
