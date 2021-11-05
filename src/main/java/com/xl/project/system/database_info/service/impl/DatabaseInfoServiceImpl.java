package com.xl.project.system.database_info.service.impl;

import java.util.List;

import com.xl.common.utils.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xl.project.system.database_info.mapper.DatabaseInfoMapper;
import com.xl.project.system.database_info.domain.DatabaseInfo;
import com.xl.project.system.database_info.service.IDatabaseInfoService;


/**
 * listService业务层处理
 * 
 * @author xl
 * @date 2021-06-22
 */
@Service
public class DatabaseInfoServiceImpl implements IDatabaseInfoService 
{
    @Autowired
    private DatabaseInfoMapper databaseInfoMapper;

    /**
     * 查询list
     * 
     * @param databaseIp listID
     * @return list
     */
    @Override
    public DatabaseInfo selectDatabaseInfoById(String databaseIp)
    {
        return databaseInfoMapper.selectDatabaseInfoById(databaseIp);
    }

    /**
     * 查询list列表
     * 
     * @param databaseInfo list
     * @return list
     */
    @Override
    public List<DatabaseInfo> selectDatabaseInfoList(DatabaseInfo databaseInfo)
    {
        return databaseInfoMapper.selectDatabaseInfoList(databaseInfo);
    }

    /**
     * 新增list
     * 
     * @param databaseInfo list
     * @return 结果
     */
    @Override
    public int insertDatabaseInfo(DatabaseInfo databaseInfo)
    {
        return databaseInfoMapper.insertDatabaseInfo(databaseInfo);
    }

    /**
     * 修改list
     * 
     * @param databaseInfo list
     * @return 结果
     */
    @Override
    public int updateDatabaseInfo(DatabaseInfo databaseInfo)
    {
        return databaseInfoMapper.updateDatabaseInfo(databaseInfo);
    }

    /**
     * 删除list对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDatabaseInfoByIds(String ids)
    {
        return databaseInfoMapper.deleteDatabaseInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除list信息
     * 
     * @param databaseIp listID
     * @return 结果
     */
    @Override
    public int deleteDatabaseInfoById(String databaseIp)
    {
        return databaseInfoMapper.deleteDatabaseInfoById(databaseIp);
    }
}
