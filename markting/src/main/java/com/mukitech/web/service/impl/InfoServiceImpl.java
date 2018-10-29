package com.mukitech.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mukitech.web.domain.Info;
import com.mukitech.web.mapper.InfoMapper;
import com.mukitech.web.service.IInfoService;
import com.mukitech.web.util.Convert;

/**
 * 服务层实现
 * 
 * @author sky
 * @date 2018-10-25
 */
@Service
public class InfoServiceImpl implements IInfoService {
	@Autowired
	private InfoMapper infoMapper;

	/**
	 * 查询信息
	 * 
	 * @param companyId
	 *            ID
	 * @return 信息
	 */
	@Override
	public Info selectInfoById(Long companyId) {
		return infoMapper.selectInfoById(companyId);
	}

	/**
	 * 查询列表
	 * 
	 * @param info
	 *            信息
	 * @return 集合
	 */
	@Override
	public List<Info> selectInfoList(Info info) {
		return infoMapper.selectInfoList(info);
	}

	/**
	 * 数据库中 phone number times
	 * 
	 * @param phone
	 * @return 数据库中有多少个
	 */
	public int selectNumOfPhone(String phone) {
		return infoMapper.numOfPhone(phone);
	}

	/**
	 * 新增
	 * 
	 * @param info
	 *            信息
	 * @return 结果
	 */
	@Override
	public int insertInfo(Info info) {
		String phone = info.getCompanyContactPhone();
		
		int num = selectNumOfPhone(phone);
		String isDu = null;
		
		if (num == 0) {
			isDu = "0";
			info.setIsDuplicate(isDu);
		} else {
			isDu = "1";
			info.setIsDuplicate(isDu);
			List<Info> list = selectInfoByPhone(phone);
			
			for (int j = 0; j < list.size(); j++) {
				Info oInfo = list.get(j);
				oInfo.setIsDuplicate(isDu);
				infoMapper.updateInfo(oInfo);
			}
		}
		return infoMapper.insertInfo(info);
	}

	/**
	 * 修改
	 * 
	 * @param info
	 *            信息
	 * @return 结果
	 */
	@Override
	public int updateInfo(Info info) {
		Long cid = info.getCompanyId();
		String oPhone = selectInfoById(cid).getCompanyContactPhone();
		System.out.println("old phone numbet"+oPhone);
		String nphone = info.getCompanyContactPhone();
		System.out.println("new phone numbet"+nphone);
		
		infoMapper.updateInfo(info);
		//判断新得手机号
		int nnum = selectNumOfPhone(nphone);
		if(nnum == 1) {
			//以后维护需要判断 update 成功没有
			info.setIsDuplicate("0");
			int onump = selectNumOfPhone(oPhone);
			if (onump == 1) {
				Info infod =selectInfoByPhone(oPhone).get(0);
				infod.setIsDuplicate("0");
				infoMapper.updateInfo(infod);
			}
		}
		if (nnum > 1) {
			info.setIsDuplicate("1");
			infoMapper.updateInfo(info);
			
			List<Info> list = selectInfoByPhone(nphone);
			for (int i = 0; i < list.size(); i++) {
				Info orinfo = list.get(i);
				orinfo.setIsDuplicate("1");
				infoMapper.updateInfo(orinfo);
			}
		}
		return infoMapper.updateInfo(info);
	}

	/**
	 * 删除对象
	 * 
	 * @param ids
	 *            需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteInfoByIds(String ids) {
		Long id = Long.parseLong(ids);
		String phone = selectInfoById(id).getCompanyContactPhone();
		int suc = infoMapper.deleteInfoByIds(Convert.toStrArray(ids));
		int num = selectNumOfPhone(phone);
		if (num ==1) {
			Info inf = selectInfoByPhone(phone).get(0);
			inf.setIsDuplicate("0");
			infoMapper.updateInfo(inf);
		}
		return suc;
	}

	/**
	 * 通过电话查询所有对象
	 * 
	 * @param companyContactPhone
	 * @return
	 */
	@Override
	public List<Info> selectInfoByPhone(String companyContactPhone) {
		return infoMapper.selectInfoListByPhone(companyContactPhone);
	}

}
