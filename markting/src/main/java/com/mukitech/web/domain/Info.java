package com.mukitech.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.mukitech.web.util.BaseEntity;

import java.util.Date;

/**
 * @date 2018-10-25
 */
public class Info extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 公司id */
	private Long companyId;
	/** 公司名 */
	private String companyName;
	/** 公司负责人 */
	private String companyContactName;
	/** 公司行业 */
	private String companyType;
	/** 公司地址 */
	private String companyAddress;
	/** 公司联系电话 */
	private String companyContactPhone;
	/** 创建时间 */
	private Date createdDate;
	/** 最后更新时间 */
	private Date updatedDate;
	/** 0:无重复 1:重复 */
	private String isDuplicate;
	/** timestamp+00_serail_number 工具生成 */
	private String serialNumber;

	public void setCompanyId(Long companyId) 
	{
		this.companyId = companyId;
	}

	public Long getCompanyId() 
	{
		return companyId;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
	}
	public void setCompanyContactName(String companyContactName) 
	{
		this.companyContactName = companyContactName;
	}

	public String getCompanyContactName() 
	{
		return companyContactName;
	}
	public void setCompanyType(String companyType) 
	{
		this.companyType = companyType;
	}

	public String getCompanyType() 
	{
		return companyType;
	}
	public void setCompanyAddress(String companyAddress) 
	{
		this.companyAddress = companyAddress;
	}

	public String getCompanyAddress() 
	{
		return companyAddress;
	}
	public void setCompanyContactPhone(String companyContactPhone) 
	{
		this.companyContactPhone = companyContactPhone;
	}

	public String getCompanyContactPhone() 
	{
		return companyContactPhone;
	}
	public void setCreatedDate(Date createdDate) 
	{
		this.createdDate = createdDate;
	}

	public Date getCreatedDate() 
	{
		return createdDate;
	}
	public void setUpdatedDate(Date updatedDate) 
	{
		this.updatedDate = updatedDate;
	}

	public Date getUpdatedDate() 
	{
		return updatedDate;
	}
	public void setIsDuplicate(String isDuplicate) 
	{
		this.isDuplicate = isDuplicate;
	}

	public String getIsDuplicate() 
	{
		return isDuplicate;
	}
	public void setSerialNumber(String serialNumber) 
	{
		this.serialNumber = serialNumber;
	}

	public String getSerialNumber() 
	{
		return serialNumber;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("companyContactName", getCompanyContactName())
            .append("companyType", getCompanyType())
            .append("companyAddress", getCompanyAddress())
            .append("companyContactPhone", getCompanyContactPhone())
            .append("createdDate", getCreatedDate())
            .append("updatedDate", getUpdatedDate())
            .append("isDuplicate", getIsDuplicate())
            .append("serialNumber", getSerialNumber())
            .toString();
    }
}
