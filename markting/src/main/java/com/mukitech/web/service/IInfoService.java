package com.mukitech.web.service;

import java.util.List;

import com.mukitech.web.domain.Info;


/**
 *  服务层
 * 
 * @author sky
 * @date 2018-10-25
 */
public interface IInfoService 
{
	/**
     * 查询信息
     * 
     * @param companyId ID
     * @return 信息
     */
	public Info selectInfoById(Long companyId);
	
	/**
	 * 通过电话查询对象
	 * @param companyContactPhone
	 * @return
	 */
	public List<Info> selectInfoByPhone(String companyContactPhone);
	
	
	/**
     * 查询列表
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
     * 删除信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteInfoByIds(String ids);
	
	/**
	 * 数据库中 phone number times
	 * @param phone
	 * @return
	 */
	public int selectNumOfPhone(String phone);
	
}
