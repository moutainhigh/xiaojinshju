package fengkongweishi.controller;

import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.company.CompanyRepository;
import fengkongweishi.entity.company.CompanyVO;
import fengkongweishi.entity.companyverification.CompanyVerificationRepository;
import fengkongweishi.entity.personreport.PersonReport;
import fengkongweishi.entity.personreport.PersonReportRepository;
import fengkongweishi.entity.personreport.ReportForSearch;
import fengkongweishi.entity.personreport.ReportVO;
import fengkongweishi.entity.user.User;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.service.ReportManageService;
import fengkongweishi.util.Common;
import fengkongweishi.util.FailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import fengkongweishi.util.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * @author duyiting
 * @date 2018/03/13
 */

@RestController
@RequestMapping("/reportManage")
public class ReportManageController {

    @Autowired
    ReportManageService reportManageService;

    @Autowired
    PersonReportRepository personReportRepository;

    @Autowired
    CompanyRepository companyRepository;

    @RequestMapping(value="/reportList", method = RequestMethod.POST)
    @PreAuthorize("hasRole('MANAGER') or hasRole('EMPLOYEE') or hasRole('LEADER')")
    public ResponseBody reportListAndSearch(@RequestBody(required = false) ReportForSearch searchBean,@PageableDefault(sort={"customerSearchLog.createTime"},direction = Sort.Direction.DESC) Pageable pageable){
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = currentUser.getCompany();
        if (company == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        Company parent = company.getParent();
        if(parent != null){
            throw new FailResponse(ExceptionEnum.NOT_HAVE_POWER);
        }
        Page<PersonReport> reportPage =reportManageService.multiConditionSearch(searchBean,pageable,company);
        Map<String,Object> returnMap = new HashMap<>();
        List<ReportVO> reportVoList = new ArrayList<>();
        for (PersonReport report:reportPage.getContent()) {
            ReportVO reportVo = new ReportVO(report);
            reportVoList.add(reportVo);
        }
        returnMap.put("reportList", reportVoList);
        returnMap.put("number", reportPage.getNumber());
        returnMap.put("totalPages", reportPage.getTotalPages());
        returnMap.put("totalElements", reportPage.getTotalElements());
        returnMap.put("size", reportPage.getSize());
        return new ResponseBody(returnMap);

    }

    @RequestMapping(value = "/createdByNameList",method = RequestMethod.GET)
    public ResponseBody createdByNameList(){
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Integer companyId = currentUser.getCompany().getId();
        Company company = companyRepository.findOne(companyId);
        if (company == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }

        Set<Map<String,Object>> employeeList = new HashSet<>();
        for (User user : company.getEmployees()) {
            Map<String,Object> userInfo = new HashMap<>();
            userInfo.put("id",user.getId());
            userInfo.put("nickname",user.getNickname());
            employeeList.add(userInfo);
        }
        Set<Company> children = company.getChildren();
        for (Company company1:children) {
            Set<User> employees = company1.getEmployees();
            for (User user:employees) {
                Map<String,Object> userMap = new HashMap<>();
                userMap.put("id",user.getId());
                userMap.put("nickname",user.getNickname());
                employeeList.add(userMap);
            }
        }
        return new ResponseBody(employeeList);
    }

    @RequestMapping(value = "/companyNameList",method = RequestMethod.GET)
    public ResponseBody companyNameList(){
        Common.UserDetailsImpl currentUser = Common.getPrincipal();
        if (currentUser == null) {
            throw new FailResponse(ExceptionEnum.NOT_LOGGED_IN);
        }
        Company company = currentUser.getCompany();
        if (company == null) {
            throw new FailResponse(ExceptionEnum.HAVE_NOT_COMPANY);
        }
        List<Company> companyList = companyRepository.findByParent(company);
        List<Map<String,Object>> returnList = new ArrayList<>();
        Map<String,Object> companyMap = new HashMap<>();
        companyMap.put("id",company.getId());
        companyMap.put("name",company.getCompanyName());
        returnList.add(companyMap);
        for (Company company1:companyList) {
            Map<String,Object> companyInfo = new HashMap<>();
            companyInfo.put("id",company1.getId());
            companyInfo.put("name",company1.getCompanyName());
            returnList.add(companyInfo);
        }
        return new ResponseBody(returnList);
    }







}
