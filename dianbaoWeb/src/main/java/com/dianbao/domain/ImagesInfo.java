package com.dianbao.domain;

import com.dianbao.util.BaseEntity;

/** 负责官网图片管理
 * @author YangYongLin
 *
 */
public class ImagesInfo extends BaseEntity {
	
	//columns START
	
	private java.lang.Long id;
	/** 图片名称 */
	private java.lang.String imagesName;
	/** 图片地址 */
	private java.lang.String imagesAdress;
	/** 图片链接 */
	private java.lang.String imagesUrl;
	/** 图片所属模块编号 */
	private java.lang.String imagesModuleCode;
	/** 图片所属模块名称 */
	private java.lang.String imagesModuleName;
	/** 图片状态 */
	private java.lang.String imagesStatus;
	/** 上传时间 */
	private java.util.Date createTime;
	/** 备注 */
	private java.lang.String note;
	/** 图片排序 */
	private java.lang.Integer imagesSort;
	//columns END

    /** 查询开始时间 */
    private String beginDate;

    /** 查询结束时间 */
    private String endDate;
    
    /** 虚拟字段：创建时间 */
    private String createTimeZ;


	public void setId(java.lang.Long value) {
		this.id = value;
	}
	
	public java.lang.Long getId() {
		return this.id;
	}
	public void setImagesName(java.lang.String value) {
		this.imagesName = value;
	}
	
	public java.lang.String getImagesName() {
		return this.imagesName;
	}
	public void setImagesAdress(java.lang.String value) {
		this.imagesAdress = value;
	}
	
	public java.lang.String getImagesAdress() {
		return this.imagesAdress;
	}
	public void setImagesUrl(java.lang.String value) {
		this.imagesUrl = value;
	}
	
	public java.lang.String getImagesUrl() {
		return this.imagesUrl;
	}
	public void setImagesModuleCode(java.lang.String value) {
		this.imagesModuleCode = value;
	}
	
	public java.lang.String getImagesModuleCode() {
		return this.imagesModuleCode;
	}
	public void setImagesModuleName(java.lang.String value) {
		this.imagesModuleName = value;
	}
	
	public java.lang.String getImagesModuleName() {
		return this.imagesModuleName;
	}
	public void setImagesStatus(java.lang.String value) {
		this.imagesStatus = value;
	}
	
	public java.lang.String getImagesStatus() {
		return this.imagesStatus;
	}

	
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	public void setNote(java.lang.String value) {
		this.note = value;
	}
	
	public java.lang.String getNote() {
		return this.note;
	}
	public void setImagesSort(java.lang.Integer value) {
		this.imagesSort = value;
	}
	
	public java.lang.Integer getImagesSort() {
		return this.imagesSort;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreateTimeZ() {
		return createTimeZ;
	}

	public void setCreateTimeZ(String createTimeZ) {
		this.createTimeZ = createTimeZ;
	}


}

