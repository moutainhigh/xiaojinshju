package fengkongweishi.entity.evaluate;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author liuzhenfeng
 * @date 2018/1/31
 */
public class EvaluationVO {

	private Integer id;

	private String comment;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date evaluateDate;

	private String nickname;

	public EvaluationVO(Evaluation evaluation) {
		super();
		this.comment = evaluation.getComment();
		this.evaluateDate = evaluation.getEvaluateDate();
		if (evaluation.getByUser() != null) {
			this.nickname = evaluation.getByUser().getNickname();
		}
		this.id = evaluation.getId();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getEvaluateDate() {
		return evaluateDate;
	}

	public void setEvaluateDate(Date evaluateDate) {
		this.evaluateDate = evaluateDate;
	}

	public String getNickname() {
		return nickname;
	}

	public void setUsername(String nickname) {
		this.nickname = nickname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}
