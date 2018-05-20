package com.jce.framework.web.system.pojo.base;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jce.framework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 部门机构表
* @author  bobo
 */
@Entity
@Table(name = "t_s_depart")
public class TSDepart extends IdEntity implements java.io.Serializable {
	private TSDepart TSPDepart;//上级部门
	@Excel(name = "部门名称")
	private String departname;//部门名称
	@Excel(name = "部门描述")
	private String description;//部门描述
	@Excel(name = "机构编码")
    private String orgCode;//机构编码
	@Excel(name = "机构类型编码")
    private String orgType;//机构编码
	@Excel(name = "电话")
	private String mobile;//电话
	@Excel(name = "传真")
	private String fax;//传真
	@Excel(name = "地址")
	private String address;//地址
	
	private String logo ;//LOGO
	private String coverImg ;//封面照片
	private String consumerHotline;//客服电话
	private String leader;//负责人
	private String leaderTelephone;//负责人电话
	private String grade;//评价
	private String remarks;//备注
	private String status;//状态
	private String orgClassify;//机构分类

	private List<TSDepart> TSDeparts = new ArrayList<TSDepart>();//下属部门

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentdepartid")
	public TSDepart getTSPDepart() {
		return this.TSPDepart;
	}

	public void setTSPDepart(TSDepart TSPDepart) {
		this.TSPDepart = TSPDepart;
	}

	@Column(name = "departname", nullable = false, length = 100)
	public String getDepartname() {
		return this.departname;
	}

	public void setDepartname(String departname) {
		this.departname = departname;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TSPDepart")
	public List<TSDepart> getTSDeparts() {
		return TSDeparts;
	}

	public void setTSDeparts(List<TSDepart> tSDeparts) {
		TSDeparts = tSDeparts;
	}

    @Column(name = "org_code", length = 64)
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Column(name = "org_type", length = 1)
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

	@Column(name = "mobile", length = 32)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "fax", length = 32)
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name = "logo", length = 200)
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Column(name = "cover_img", length = 200)
	public String getCoverImg() {
		return coverImg;
	}

	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	@Column(name = "consumer_hotline", length = 200)
	public String getConsumerHotline() {
		return consumerHotline;
	}

	public void setConsumerHotline(String consumerHotline) {
		this.consumerHotline = consumerHotline;
	}
	@Column(name = "leader", length = 200)
	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}
	@Column(name = "leader_telephone", length = 200)
	public String getLeaderTelephone() {
		return leaderTelephone;
	}

	public void setLeaderTelephone(String leaderTelephone) {
		this.leaderTelephone = leaderTelephone;
	}
	@Column(name = "grade", length = 200)
	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Column(name = "remarks", length = 500)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Column(name = "status", length = 1)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "org_classify", length = 100)
	public String getOrgClassify() {
		return orgClassify;
	}

	public void setOrgClassify(String orgClassify) {
		this.orgClassify = orgClassify;
	}
	
	
}