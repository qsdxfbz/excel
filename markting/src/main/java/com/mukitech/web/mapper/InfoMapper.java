package com.mukitech.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mukitech.web.domain.Info;	

/**
 *  数据层
 * 
 * @author sky
 * @date 2018-10-25
 */
@Mapper
public interface InfoMapper 
{
	/**
     * 信息
     * 
     * @param companyId ID
     * @return 信息
     */
	public Info selectInfoById(Long companyId);
	
	/**
     * 列表
     * 
     * @param info 信息
     * @return 集合
     */
	public List<Info> selectInfoList(Info info);
	
	/**
     * 新增
     * 
     * @param info 信息
     * @return 结果
     */
	public int insertInfo(Info info);
	
	/**
     * 修改
     * 
     * @param info 信息
     * @return 结果
     */
	public int updateInfo(Info info);
	
	/**
     * 删除
     * 
     * @param companyId ID
     * @return 结果
     */
	public int deleteInfoById(Long companyId);
	
	/**
     * 批量删除
     * 
     * @param companyIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteInfoByIds(String[] companyIds);
	
	/**
	 * 校验 公司电话 是否 唯一
	 * 
	 * @param companyContactPhone
	 * @return
	 */
	public List<Info> checkPhoneUnique(String companyContactPhone);
	
	/**
	 * 电话在数据库中 次数
	 * @param companyContactPhone
	 * @return
	 */
	public int numOfPhone(String companyContactPhone);
	
	/**
	 * 校验公司名称是否唯一
	 * @param companyName
	 * @return
	 */
	public int checkCompNameUnique(String companyName);
	
	/**
	 * 通过电话查询所有对象
	 * @param companyContactPhone
	 * @return
	 */
	public List<Info> selectInfoListByPhone(String companyContactPhone);
	
}