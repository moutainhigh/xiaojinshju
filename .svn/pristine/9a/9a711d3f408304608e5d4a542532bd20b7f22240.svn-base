package fengkongweishi.entity.user;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author huanghengkun
 * @date 2018/02/07
 */
public class UserRegister {
    @NotBlank(message = "手机号码不为空")
    @Length(min = 11, max = 11, message = "手机号码位数错误")
    private String username;
    @NotBlank(message = "密码不为空")
    @Length(min = 6, message = "密码至少6位")
    private String password1;
    @NotBlank(message = "密码不为空")
    @Length(min = 6, message = "密码至少6位")
    private String password2;
    @NotBlank(message = "短信验证码不为空")
    private String sms;

    private String nickname;

    private Integer companyId;


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword1() {
        return password1;
    }

    public void setPassword1(String password1) {
        this.password1 = password1;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
