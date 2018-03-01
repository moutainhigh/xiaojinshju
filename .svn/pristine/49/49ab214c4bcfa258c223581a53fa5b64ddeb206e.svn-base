package fengkongweishi.enums;

/**
 * 异常枚举类
 *
 * @author huanghengkun
 * @date 2018/01/08
 */
public enum ExceptionEnum {
    NOT_LOGGED_IN(1001, "未登录"),
    HAVE_NOT_COMPANY(1002, "当前用户未绑定企业"),
    LOGIN_USERNAME_PASSWORD_EMPTY(1003, "手机号或密码不能为空"),
    LOGIN_USERNAME_PASSWORD_WRONG(1004, "手机号或密码错误"),
    SMS_ERROR(5188,"短信验证码错误，请重新输入验证码"),

    AUTHORIZATION_APPCODE_EMPTY(1010, "appCode不能为空"),
    AUTHORIZATION_TERMINAL_EMPTY(1011, "terminal不能为空"),
    AUTHORIZATION_TERMINAL_ERROR(1012, "terminal错误"),
    AUTHORIZATION_APPCODE_ERROR(1013, "appCode验证不通过"),

    FILEUPLOAD_LASTFILE_ERROR(1101, "上次文件出错"),
    FILEUPLOAD_FILE_FORMAT_ERROR(1102, "不支持的文件格式"),
    FILEUPLOAD_PICTURE_FORMAT_ERROR(1103, "未知的图片格式"),
    FILEUPLOAD_PICTURE_UPLOAD_ERROR(1104, "图片文件上传出错"),
    FILEUPLOAD_UNKNOWN_ERROR(1105, ""),
    FILEUPLOAD_COMMONFILE_UPLOAD_ERROR(1106, "普通文件上传出错"),
    FILEUPLOAD_ALIYUN_FILE_ERROR(1107, "阿里云转换文件格式出错"),
    FILEUPLOAD_REMOTE_SERVER_ERROR(1108, "读取远程服务器出错"),
    FILEUPLOAD_URL_FILE_ERROR(1109, "从url地址取文件出错"),
    FILEUPLOAD_URL_FORMAT_ERROR(1110, "url格式出错"),
    FILEUPLOAD_ALIYUN_PICTURE_ERROR(1111, "阿里云图片格式转换出错"),
    FILEUPLOAD_IO_ERROR(1112, "io错误"),

    COMPANY_REGISTER_HAVE_A_VERIFIED_COMPANY(1201, "已有一个已认证公司"),
    COMPANY_REGISTER_HAVE_A_VERIFYING_COMPANY(1202, "公司认证中,请耐心等待"),

    COMPANY_VERIFYING_RESULT_REASON_ERROR(1301, "请填写拒绝原因"),
    COMPANY_STATUE_ERRROR(1302, "待审核的公司状态并非VERIFYING，请检查"),
    COMPANY_ID_ERROR(1303, "Id不正确,找不到该公司"),

    COMPANY_ALREADY_EXIST_EMPLOYEE(1401, "该用户已经是本公司员工"),
    COMPANY_TEAMMOVING_USERROLE_ERROR(1402, "该用户不是本公司员工"),
    MANAGER_CANNOT_DELETE_HIMSELF(1403, "管理员不能将自己从团队中剔除"),
    NOT_HAVE_POWER(1404, "当前用户不具备该权限"),
    USER_NOT_EXIST(1405, "该用户不存在"),
    ADMIN_CANNOT_JOININ_COMPANY(1406,"该用户已经属于其他公司，不能添加"),
    NEW_COMPANY_MANAGER_ERROR(1407,"该用户不能成为新团队的负责人，请选择其他用户"),

    REPORT_COMPANY_HAVE_NOT_ENOUGH_REMAINDER(1501, "公司账户余额不足"),
    REPORT_COMPANY_HAVE_NOT_OPEN_EDTION(1502, "尚未开通该版本"),
    REPORT_COMPANY_AND_APPCODE_INCONSISTENCIES(1503, "当前用户公司与密钥公司不一致"),
    REPORT_AUTHMOBILE_IDCARD_ERROR(1504, "18位身份证格式不正确"),
    REPORT_AUTHMOBILE_NAME_ERROR(1505, "2-5位中文姓名"),
    REPORT_AUTHMOBILE_MOBILE_ERROR(1506, "手机号格式错误"),

    TEAM_ALREADY_EXIST(1601, "本公司已存在一个同名团队"),

    LEVEL_PARAM_ERROR(1701, "评级参数格式错误"),

    IMAGE_IO_ERROR(1801, "图片识别失败"),

    REPORT_TASKID_ERROR(1901, "魔蝎taskId不能为空"),
    REPORT_COURTJUDGMENT_DETAIL_DOCID_ERROR(1902, "docId错误"),
    REPORT_COURTJUDGMENT_DETAIL_QUERIED(1903, "该裁判文书已查询过详情,请勿重复查询"),
    REPORT_COURTJUDGMENT_DETAIL_HANDLE_ERROR(1904, "裁判文书详情获取失败");

    private int code;
    private String message;

    ExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
