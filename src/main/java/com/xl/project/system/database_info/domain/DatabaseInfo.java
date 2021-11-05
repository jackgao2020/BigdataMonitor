package com.xl.project.system.database_info.domain;

import com.xl.framework.aspectj.lang.annotation.Excel;
import com.xl.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * list对象 Database_Info
 * 
 * @author xl
 * @date 2021-06-22
 */
public class DatabaseInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据库 */
    @Excel(name = "数据库")
    private String databaseIp;

    /** 账号 */
    @Excel(name = "账号")
    private String count;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    public void setDatabaseIp(String databaseIp) 
    {
        this.databaseIp = databaseIp;
    }

    public String getDatabaseIp() 
    {
        return databaseIp;
    }
    public void setCount(String count) 
    {
        this.count = count;
    }

    public String getCount() 
    {
        return count;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("databaseIp", getDatabaseIp())
            .append("count", getCount())
            .append("password", getPassword())
            .append("remark", getRemark())
            .toString();
    }
}
