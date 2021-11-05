package com.xl.project.system.database_info.mapper;

import java.util.List;
import com.xl.project.system.database_info.domain.DatabaseInfo;

/**
 * listMapper接口
 * 
 * @author xl
 * @date 2021-06-22
 */
public interface DatabaseInfoMapper 
{
    /**
     * 查询list
     * 
     * @param databaseIp listID
     * @return list
     */
    public DatabaseInfo selectDatabaseInfoById(String databaseIp);

    /**
     * 查询list列表
     * 
     * @param databaseInfo list
     * @return list集合
     */
    public List<DatabaseInfo> selectDatabaseInfoList(DatabaseInfo databaseInfo);

    /**
     * 新增list
     * 
     * @param databaseInfo list
     * @return 结果
     */
    public int insertDatabaseInfo(DatabaseInfo databaseInfo);

    /**
     * 修改list
     * 
     * @param databaseInfo list
     * @return 结果
     */
    public int updateDatabaseInfo(DatabaseInfo databaseInfo);

    /**
     * 删除list
     * 
     * @param databaseIp listID
     * @return 结果
     */
    public int deleteDatabaseInfoById(String databaseIp);

    /**
     * 批量删除list
     * 
     * @param databaseIps 需要删除的数据ID
     * @return 结果
     */
    public int deleteDatabaseInfoByIds(String[] databaseIps);
}
