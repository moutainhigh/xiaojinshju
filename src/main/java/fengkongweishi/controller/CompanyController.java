package fengkongweishi.controller;

import fengkongweishi.controller.UserController.ChargeForm;
import fengkongweishi.entity.company.*;
import fengkongweishi.entity.companyremainderlog.CompanyRemainderLog;
import fengkongweishi.entity.companyremainderlog.CompanyRemainderLogRepository;
import fengkongweishi.entity.companyremainderlog.CompanyRemainderLogVO;
import fengkongweishi.entity.companyverification.CompanyVerification;
import fengkongweishi.entity.companyverification.CompanyVerificationRepository;
import fengkongweishi.entity.personreport.PersonReportRepository;
import fengkongweishi.entity.role.Role;
import fengkongweishi.entity.role.RoleRepository;
import fengkongweishi.entity.user.EmployeeVO;
import fengkongweishi.entity.user.User;
import fengkongweishi.entity.user.UserRegister;
import fengkongweishi.entity.user.UserRepository;
import fengkongweishi.enums.*;
import fengkongweishi.service.CompanyService;
import fengkongweishi.service.ParameterService;
import fengkongweishi.service.SmsService;
import fengkongweishi.service.UserService;
import fengkongweishi.util.*;
import fengkongweishi.util.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * @author huanghengkun
 * @date 2018/01/08
 */
@RestController
@RequestMapping(value = "/company")
public class CompanyController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    CompanyRemainderLogRepository companyRemainderLogRepository;
    @Autowired
    ParameterService parameterService;
    @Autowired
    CompanyService companyService;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    CompanyVerificationRepository companyVerificationRepository;
    @Autowired
    SmsService smsService;
    @Autowired
    UserService userService;
    @Autowired
    PersonReportRepository personReportRepository;

    /**
     * 获取公司名字、超级管理员的联系方式、开通版本、账户余额等相关信息
     */
    @RequestMapping(value = "/myInfo")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('EMPLOYEE') or hasRole('LEADER')")
    public ResponseBody info(@PageableDefault(size = 15) Pageable page) {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        } else if (currentUser.getCompany() == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        Company company = companyRepository.findOne(currentUser.getCompany().getId());
        CompanyVO companyVO = new CompanyVO(company);
        return new ResponseBody(companyVO);
    }

    /**
     * 公司团队列表
     */
    @RequestMapping(value="/teamList",method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER')")
    public ResponseBody teamListAndSearch(@RequestBody(required = false)TeamForSearch searchBean, Pageable page){
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        } else if (currentUser.getCompany() == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        Company company = companyRepository.findOne(currentUser.getCompany().getId());
        List<TeamVO> teamList = new ArrayList<>();
        Page<Company> companyPage = companyService.teamListAndSearch(searchBean,page,company);
        Map<String,Object> returnMap = new HashMap<>();
        for (Company company1:companyPage.getContent()) {
            TeamVO teamVO = new TeamVO(company1);
            teamList.add(teamVO);
        }
        returnMap.put("teamList",teamList);
        returnMap.put("number",companyPage.getNumber());
        returnMap.put("size",companyPage.getSize());
        returnMap.put("totalElements",companyPage.getTotalElements());
        returnMap.put("totalPages",companyPage.getTotalPages());
        return new ResponseBody(returnMap);
    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value="/addTeam",method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody addTeam(String companyName,String userName,String sms){
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = companyRepository.findOne(currentUser.getCompany().getId());
        if (company == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        User manager = userRepository.findByUsername(userName);
        if("ROLE_ADMIN".equals(manager.getRole().getName())){
            throw new FailResponse(ExceptionEnum.ADMIN_CANNOT_JOININ_COMPANY);
        }
        if("ROLE_MANAGER".equals(manager.getRole().getName())){
            throw new FailResponse(ExceptionEnum.NEW_COMPANY_MANAGER_ERROR);
        }
        if(!smsService.checkSms(userName,sms)){
            throw new FailResponse(ExceptionEnum.SMS_ERROR);
        }
        companyService.addTeam(company,manager,companyName);
        return new ResponseBody("");

    }

    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value="/registerAddTeam",method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody registerAddTeam(@RequestBody @Valid UserRegister userForm, BindingResult result,String companyName){
        if (result.hasErrors()) {
            ValidUtils.getFirstErrorInfo(result);
        }
        User manager = userService.register(userForm);
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = companyRepository.findOne(currentUser.getCompany().getId());
        companyService.addTeam(company,manager,companyName);
        return new ResponseBody("");
    }

    @RequestMapping(value = "/registerSms")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody registerSms(HttpServletRequest httpServletRequest, String username) {
        if (username == null) {
            throw new FailResponse(6699, "没有手机号");
        }
        smsService.sendSms(httpServletRequest.getRemoteAddr(), username);
        return new ResponseBody();
    }

    /**
     * 公司注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @Transactional(rollbackOn = Exception.class)
    public void register(HttpServletResponse response, @RequestBody @Valid CompanyRegister companyForm, BindingResult result) throws IOException {
        if (result.hasErrors()) {
            ResponseBody errorInfo = ValidUtils.getFirstErrorInfo(result);
            throw new FailResponse(1200, errorInfo.getMessage());
        }
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company userCompany = currentUser.getUser().getCompany();
        companyVerifyStatusCheck(userCompany);

        Company company = new Company();
        company.setCompanyName(companyForm.getCompanyName());
        company.setStatus(CompanyStatusEnum.VERIFYING);
        company.setCreateTime(new Date());
        company.setProvince(companyForm.getProvince());
        company.setCity(companyForm.getCity());
        company.setAddress(companyForm.getAddress());
        company.setLicenseType(companyForm.getLicenseType());
        company.setLicenseNumber(companyForm.getLicenseNumber());
        company.setLicensePicURL(companyForm.getLicensePicURL());
        company.setOpenEditions(companyForm.getOpenEditions());
        company.setOpenWeChatSubscription(companyForm.getOpenWeChatSubscription());
        company.setAppCode(parameterService.getMD5(new Date().toString().getBytes(Charset.forName("utf-8"))));
        companyRepository.save(company);

        CompanyVerification verification = new CompanyVerification();
        User user = userRepository.findOne(currentUser.getId());
        verification.setCompany(company);
        verification.setApplyTime(new Date());
        verification.setApplyType(ApplyTypeEnum.AUTHENTICATION);
        verification.setApplyUser(user);
        Set<SystemEditionEnum> openEditions = companyForm.getOpenEditions();
        List<String> editionStr = new ArrayList<>();
        openEditions.stream().sorted().forEachOrdered(item -> editionStr.add(item.getMessage()));
        verification.setApplyInfo("初次提交认证,申请开通版本：" + String.join(",", editionStr));
        companyVerificationRepository.save(verification);

        user.setCompany(company);
        user.setJoinCompanyTime(new Date());
        userRepository.save(user);
        response.sendRedirect("/user/myInfo");
    }

    /**
     * 查询版本升级申请
     */
    @RequestMapping(value = "/editionUpgreade",method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody editionUpgreade(String edition){
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        User user = userRepository.findOne(currentUser.getUser().getId());
        Company company = companyRepository.findOne(currentUser.getCompany().getId());
        companyService.editionupgreade(company,user,edition);
        return new ResponseBody("");
    }


    // ------------------------------------------------------------

    @RequestMapping(value = "/user/info/{id}")
    @PreAuthorize("hasRole('MANAGER') and principal.company.id.equals(returnObject.company.id)")
    public ResponseBody userInfo(@PathVariable Integer id) {
        return new ResponseBody(userRepository.findOne(id));
    }

    @RequestMapping(value = "/user/list")
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody listEmployee(Pageable page) {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = currentUser.getCompany();
        return new ResponseBody(userRepository.findByCompany(company, page));
    }

    /**
     * 输入手机号，若存在用户，回显用户姓名
     */
    @RequestMapping(value = "/user/show", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER')")
    public ResponseBody showUser(String username) {
        User findOne = userRepository.findByUsername(username);
        Map<String, Object> returnMap = new HashMap<>();
        ResponseBody responseBody;
        if (findOne != null) {
            returnMap.put("id", findOne.getId());
            returnMap.put("username", findOne.getUsername());
            returnMap.put("nickname", findOne.getNickname());
            responseBody = new ResponseBody(returnMap);
        } else {
            responseBody = new ResponseBody("error", "100", "该用户不存在，需要先注册", null);
        }
        return responseBody;
    }

    /**
     * 添加团队用户
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER')")
    public ResponseBody addUser(String username,String sms) {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        User findOne = userRepository.findByUsername(username);
        if (findOne.getCompany() != null && findOne.getCompany().getId().equals(currentUser.getCompany().getId())) {
            throw new FailResponse(ExceptionEnum.COMPANY_ALREADY_EXIST_EMPLOYEE);
        }
        if("ROLE_ADMIN".equals(findOne.getRole().getName())){
            throw new FailResponse(ExceptionEnum.ADMIN_CANNOT_JOININ_COMPANY);
        }
        if (!smsService.checkSms(username, sms)) {
            throw new FailResponse(ExceptionEnum.SMS_ERROR);
        }
        Company company = currentUser.getCompany();
        findOne.setCompany(company);
        findOne.setJoinCompanyTime(new Date());
        Role role = new Role();
        role.setId(4);
        role.setName("ROLE_EMPLOYEE");
        findOne.setRole(role);
        userRepository.save(findOne);
        return new ResponseBody();

    }

    /**
     * 添加团队成员，若该用户未注册，则先注册再添加
     */
    @RequestMapping(value = "/user/registerAdd", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER')")
    public ResponseBody registerAdd(@RequestBody @Valid UserRegister userForm, BindingResult result) {
        if (result.hasErrors()) {
            ValidUtils.getFirstErrorInfo(result);
        }
        User user = userService.register(userForm);
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = currentUser.getCompany();
        user.setJoinCompanyTime(new Date());
        user.setCompany(company);
        Role role = new Role();
        role.setId(4);
        role.setName("ROLE_EMPLOYEE");
        user.setRole(role);
        userRepository.save(user);
        return new ResponseBody();
    }


    @RequestMapping(value = "/user/insertOrUpdate", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody insertOrUpdateUser(@RequestBody User user) {
        if (user.getId() != null) {
            User findOne = userRepository.findOne(user.getId());
            Common.UserDetailsImpl currentUser = Common.getPrincipal();
            if (currentUser == null) {
                throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
            }
            if (!findOne.getCompany().getId().equals(currentUser.getCompany().getId())) {
                throw new FailResponse(1545, "权限不允许");
            }
        } else {
            if (user.getId() == null || user.getCreateTime() == null) {
                throw new FailResponse(1388, "接口不允许新建");
            }
        }
        try {
            return new ResponseBody(userRepository.save(user));
        } catch (Exception e) {
            throw new FailResponse(5133, "手机号已存在");
        }
    }


    // --------------------------------------------------------------------

    @RequestMapping(value = "/deposit", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER')")
    public String deposit() {
        return "payment";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody deposit(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
                                @Valid @RequestBody ChargeForm form, BindingResult result) {

        if (result.hasErrors()) {
            return ValidUtils.getFirstErrorInfo(result);
        }

        if (form.getPlatform().equals(PlatformEnum.ALIPAY)) {
            String returnForm = companyService.taobaoDeposit(form.getAmount());
            HttpUtils.writeResponseWithUtf8(httpResponse, returnForm);
        }
        return null;
    }

    /**
     * 回显公司信息
     */
    @RequestMapping(value = "/getCompanyInfo", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('LEADER') or hasRole('EMPLOYEE')")
    public ResponseBody getCompanyInfo() {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = currentUser.getCompany();
        if (company == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        CompanyVO companyVO = new CompanyVO(company);
        return new ResponseBody(companyVO);
    }

    @RequestMapping(value = "/showMobile", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody showMobile() {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        String username = currentUser.getUsername();
        return new ResponseBody(username);
    }

    /**
     * 移交团队，显示团队成员列表
     */
    @RequestMapping(value = "/teamMoving/employeesList", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody teamMemberList() {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Integer id= currentUser.getUser().getId();
        Company company = currentUser.getCompany();
        List<User> teamMemberList = userRepository.findByCompanyAndIdNot(company,id);
        List<Map<String, Object>> returnList = new ArrayList<>();
        for (User user : teamMemberList) {
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("id", user.getId());
            String userAndNickName = user.getNickname() + "  " + user.getUsername();
            returnMap.put("detail", userAndNickName);
            returnList.add(returnMap);

        }
        return new ResponseBody(returnList);
    }

    /**
     * 移交团队
     */
    @Transactional(rollbackOn = Exception.class)
    @RequestMapping(value = "/teamMoving", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody teamMoving(Integer id, String sms) {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        String username = currentUser.getUsername();
        if (!smsService.checkSms(username, sms)) {
            throw new FailResponse(ExceptionEnum.SMS_ERROR);
        }
        Company company = companyRepository.findOne(currentUser.getCompany().getId());
        User user = userRepository.findOne(id);
        if (!company.equals(user.getCompany())) {
            throw new FailResponse(ExceptionEnum.COMPANY_TEAMMOVING_USERROLE_ERROR);
        }
        User manager = currentUser.getUser();
        Role managerRole = manager.getRole();
        Role employeeRole = new Role();
        employeeRole.setId(4);
        employeeRole.setName("ROLE_EMPLOYEE");
        manager.setRole(employeeRole);
        user.setRole(managerRole);
        company.setManager(user);
        companyRepository.save(company);
        userRepository.save(manager);
        userRepository.save(user);
        return new ResponseBody();
    }

    /**
     * 显示团队成员
     */
    @RequestMapping(value = "/employeesList", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('EMPLOYEE') or hasRole('LEADER')")
    public ResponseBody emplyeesListAndSearch(String name,String mobile,@PageableDefault(sort = {"role"}, direction = Sort.Direction.ASC) Pageable page) {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = companyRepository.findOne(currentUser.getCompany().getId());
        if (company == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        Map<String, Object> returnMap = new HashMap<>();
        Page<User> employeePage = userService.emplyeesListAndSearch(company,name,mobile,page);
        List<EmployeeVO> employeeList = new ArrayList<>();
        for (User employee : employeePage.getContent()) {
            EmployeeVO employeeVO = new EmployeeVO(employee, personReportRepository);
            employeeList.add(employeeVO);
        }
        returnMap.put("employeeList", employeeList);
        returnMap.put("number", employeePage.getNumber());
        returnMap.put("totalPages", employeePage.getTotalPages());
        returnMap.put("totalElements", employeePage.getTotalElements());
        returnMap.put("size", employeePage.getSize());

        return new ResponseBody(returnMap);
    }

    /**
     * 成员剔除
     */
    @RequestMapping(value = "/employeesList/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER')")
    @Transactional(rollbackOn = Exception.class)
    public ResponseBody deleteEmployee(@PathVariable Integer id) {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        if (id.equals(currentUser.getUser().getId())) {
            throw new FailResponse(ExceptionEnum.MANAGER_CANNOT_DELETE_HIMSELF);
        }
        Company company = companyRepository.findOne(currentUser.getCompany().getId());
        if (company == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        User user = userRepository.findOne(id);
        Set<User> employees = company.getEmployees();
        employees.remove(user);
        Role role = new Role();
        role.setId(5);
        role.setName("ROLE_USER");
        user.setCompany(null);
        user.setRole(role);
        userRepository.save(user);
        companyRepository.save(company);
        return new ResponseBody();
    }

    /**
     * 查询页面的开通版本的显示
     */
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseBody queryEdition() {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = currentUser.getCompany();
        if (company == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        company = companyRepository.findOne(currentUser.getCompany().getId());
        List<Map<String, Object>> returnList = new ArrayList<>();
        List<CompanyVerification> companyVerifications = companyVerificationRepository.findByCompanyAndApplyType(company,ApplyTypeEnum.EDITIONUPGREADE);
        Set<SystemEditionEnum> openEditions = company.getOpenEditions();
        for (SystemEditionEnum systemEditionEnum : SystemEditionEnum.values()) {
            Map<String, Object> returnMap = new HashMap<>();
            if(openEditions.contains(systemEditionEnum)) {
                returnMap.put("color", "#000000");
                returnMap.put("Edition", systemEditionEnum.getMessage());
                returnMap.put("status", "查询");
                returnList.add(returnMap);
            } else if (companyVerifications.stream().anyMatch(item-> ApplyTypeEnum.EDITIONUPGREADE.equals(item.getApplyType())&&item.getVerifyTime() == null && item.getApplyInfo().contains(systemEditionEnum.toString()))) {
                returnMap.put("color", "#FF4500");
                returnMap.put("Edition", systemEditionEnum.getMessage());
                returnMap.put("status", "审核中");
                returnList.add(returnMap);
            } else {
                returnMap.put("color","#60AEFC");
                returnMap.put("Edition", systemEditionEnum.getMessage());
                returnMap.put("status", "申请");
                returnList.add(returnMap);
            }
        }
        return new ResponseBody(returnList);
    }

    /**
     * 消费记录
     */
    @RequestMapping(value = "/getRemainderLog", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER') or hasRole('LEADER')")
    public ResponseBody getRemainderLog(Pageable page) {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = companyRepository.findOne(currentUser.getCompany().getId());
        if (company == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        Page<CompanyRemainderLog> companyRemainderLogPage = companyRemainderLogRepository.findByCompanyAndStatusOrderByIdDesc(company,ChargeStatusEnum.TRADE_SUCCESS,page);
        Map<String, Object> returnMap = new HashMap<>();
        List<CompanyRemainderLogVO> companyRemainderLogVOS = new ArrayList<>();
        for (CompanyRemainderLog companyRemainderLog : companyRemainderLogPage.getContent()) {
            CompanyRemainderLogVO companyRemainderLogVO = new CompanyRemainderLogVO(companyRemainderLog);
            companyRemainderLogVOS.add(companyRemainderLogVO);
        }
        returnMap.put("companyRemainderLogs", companyRemainderLogVOS);
        returnMap.put("number", companyRemainderLogPage.getNumber());
        returnMap.put("totalPages", companyRemainderLogPage.getTotalPages());
        returnMap.put("totalElements", companyRemainderLogPage.getTotalElements());
        returnMap.put("size", companyRemainderLogPage.getSize());
        return new ResponseBody(returnMap);

    }

    /**
     * 重新提交认证
     */
    @RequestMapping(value = "/updateCompanyInfo", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    public ResponseBody updateCompanyInfo(@RequestBody @Valid CompanyRegister companyForm, BindingResult result) {
        if (result.hasErrors()) {
            return ValidUtils.getFirstErrorInfo(result);
        }
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company userCompany = currentUser.getUser().getCompany();
        companyVerifyStatusCheck(userCompany);

        userCompany.setCompanyName(companyForm.getCompanyName());
        userCompany.setStatus(CompanyStatusEnum.VERIFYING);
        userCompany.setCreateTime(new Date());
        userCompany.setProvince(companyForm.getProvince());
        userCompany.setCity(companyForm.getCity());
        userCompany.setAddress(companyForm.getAddress());
        userCompany.setLicenseType(companyForm.getLicenseType());
        userCompany.setLicenseNumber(companyForm.getLicenseNumber());
        userCompany.setLicensePicURL(companyForm.getLicensePicURL());
        userCompany.setOpenEditions(companyForm.getOpenEditions());
        userCompany.setOpenWeChatSubscription(companyForm.getOpenWeChatSubscription());
        userCompany = companyRepository.save(userCompany);

        CompanyVerification verification = new CompanyVerification();
        User user = userRepository.findOne(currentUser.getId());
        verification.setCompany(userCompany);
        verification.setApplyTime(new Date());
        verification.setApplyType(ApplyTypeEnum.AUTHENTICATION);
        verification.setApplyUser(user);
        Set<SystemEditionEnum> openEditions = companyForm.getOpenEditions();
        List<String> editionStr = new ArrayList<>();
        openEditions.stream().sorted().forEachOrdered(item -> editionStr.add(item.getMessage()));
        verification.setApplyInfo("重新提交认证,申请开通版本：" + String.join(",", editionStr));
        companyVerificationRepository.save(verification);
//        CompanyVO companyVO = new CompanyVO(userCompany);
        return new ResponseBody();

    }


    @RequestMapping(value = "/remainderLog/list", method = RequestMethod.GET)
    @PreAuthorize("hasRole('MANAGER')")
    public ResponseBody remainderLogList(Pageable page) {
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = currentUser.getCompany();
        Page<CompanyRemainderLog> findByCompany = companyRemainderLogRepository.findByCompanyAndStatusNotOrderByIdDesc(company, ChargeStatusEnum.WAIT_BUYER_PAY, page);
        return new ResponseBody(findByCompany);
    }

    private void companyVerifyStatusCheck(Company userCompany) {
        if (userCompany != null) {
            if (CompanyStatusEnum.ENABLED.equals(userCompany.getStatus())) {
                throw new FailResponse(ExceptionEnum.COMPANY_REGISTER_HAVE_A_VERIFIED_COMPANY);
            } else if (CompanyStatusEnum.VERIFYING.equals(userCompany.getStatus())) {
                throw new FailResponse(ExceptionEnum.COMPANY_REGISTER_HAVE_A_VERIFYING_COMPANY);
            }
        }
    }

}
