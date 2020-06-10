package com.dianbao.domain;



public class ImagesInfo{
	
	//可以直接使用: @Length(max=50,message="用户名长度不能大于50")显示错误消息
	//columns START
	
	private java.lang.Long id;
	/***/
	private java.lang.String imagesName;
	/***/
	private java.lang.String imagesAdress;
	/***/
	private java.lang.String imagesUrl;
	/***/
	private java.lang.String imagesModuleCode;
	/***/
	private java.lang.String imagesModule;
	/***/
	private java.lang.String imagesStatus;
	
	private java.util.Date createTime;
	/***/
	private java.lang.String note;
	
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

