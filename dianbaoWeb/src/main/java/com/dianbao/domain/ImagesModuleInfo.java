package com.dianbao.domain;

import com.dianbao.util.BaseEntity;

/**图片所属模块管理
 * @author YangYongLin
 *
 */
public class ImagesModuleInfo extends BaseEntity {

	
	private java.lang.Integer id;
	/** 模块编号 */
	private java.lang.String moduleCode;
	/** 模块名称 */
	private java.lang.String moduleName;
	/** 模块上级编号 */
	private java.lang.String moduleParentCode;
	/** 模块上级名称 */
	private java.lang.String moduleParentName;
	//columns END


	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	
	public java.lang.Integer getId() {
		return this.id;
	}
	public void setModuleCode(java.lang.String value) {
		this.moduleCode = value;
	}
	
	public java.lang.String getModuleCode() {
		return this.moduleCode;
	}
	public void setModuleName(java.lang.String value) {
		this.moduleName = value;
	}
	
	public java.lang.String getModuleName() {
		return this.moduleName;
	}
	public void setModuleParentCode(java.lang.String value) {
		this.moduleParentCode = value;
	}
	
	public java.lang.String getModuleParentCode() {
		return this.moduleParentCode;
	}
	public void setModuleParentName(java.lang.String value) {
		this.moduleParentName = value;
	}
	
	public java.lang.String getModuleParentName() {
		return this.moduleParentName;
	}

}

