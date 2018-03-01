/*
package apifengkong.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jinrongfengkong.entity.base.BaseEntity;

@Entity
public class PersonWebNote  extends BaseEntity {

	@ManyToOne
	@JsonIgnore
	private PersonWeb personWeb;
	
	@Transient
	private Integer personWebId;
	
	
	private String level;
	
	@Lob
	private String content;
	
	@ManyToOne
	private User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;



	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public PersonWeb getPersonWeb() {
		return personWeb;
	}

	public void setPersonWeb(PersonWeb personWeb) {
		this.personWeb = personWeb;
	}

	public Integer getPersonWebId() {
		return personWebId;
	}

	public void setPersonWebId(Integer personWebId) {
		this.personWebId = personWebId;
	}

	public String getLevel() {
		return level;
	}


	public void setLevel(String level) {
		this.level = level;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	
	
}
*/
