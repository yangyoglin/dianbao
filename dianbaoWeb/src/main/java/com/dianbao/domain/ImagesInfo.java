package com.dianbao.domain;

import com.dianbao.util.BaseEntity;

/** 负责官网图片管理
 * @author YangYongLin
 *
 */
public class ImagesInfo extends BaseEntity {
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
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
	private java.lang.String imagesModule;
	/** 图片状态 */
	private java.lang.String imagesStatus;
	/** 上传时间 */
	private java.util.Date createTime;
	/** 备注 */
	private java.lang.String note;
	/** 图片排序 */
	private java.lang.Integer imagesSort;
	//columns END


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
	public void setImagesModule(java.lang.String value) {
		this.imagesModule = value;
	}
	
	public java.lang.String getImagesModule() {
		return this.imagesModule;
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


}

