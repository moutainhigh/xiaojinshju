package fengkongweishi.aop;

import fengkongweishi.entity.authorizationlog.AuthorizationLog;
import fengkongweishi.entity.authorizationlog.AuthorizationLogRepository;
import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.company.CompanyRepository;
import fengkongweishi.enums.ExceptionEnum;
import fengkongweishi.enums.TerminalEnum;
import fengkongweishi.util.FailResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限控制切面
 *
 * @author huanghengkun
 * @date 2018/01/16
 */
@Aspect
@Component
public class AuthorizationAspect {

    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AuthorizationLogRepository authorizationLogRepository;

    @Before("execution(public * fengkongweishi.controller.ReportController.*(..))")
    public void authorization(JoinPoint joinPoint) {
        System.out.println("--------authorization begin--------");
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = requestAttributes.getRequest();
        String ip = httpServletRequest.getRemoteAddr();
        String appCode = httpServletRequest.getHeader("appCode");
        String terminalStr = httpServletRequest.getHeader("terminal");
        String longitude = httpServletRequest.getHeader("longitude");
        String latitude = httpServletRequest.getHeader("latitude");
        System.out.println("appCode:" + appCode + ",terminal:" + terminalStr + ",longitude:" + longitude + ",latitude:" + latitude);
        if (StringUtils.isEmpty(terminalStr)) {
            throw new FailResponse(ExceptionEnum.AUTHORIZATION_TERMINAL_EMPTY);
        }
        TerminalEnum terminal;
        try {
            terminal = TerminalEnum.valueOf(terminalStr);
        } catch (IllegalArgumentException e) {
            throw new FailResponse(ExceptionEnum.AUTHORIZATION_TERMINAL_ERROR);
        }
        AuthorizationLog log = new AuthorizationLog(appCode, terminal, longitude, latitude, ip);
        if (StringUtils.isEmpty(appCode)) {
            authorizationLogRepository.save(log);
            throw new FailResponse(ExceptionEnum.AUTHORIZATION_APPCODE_EMPTY);
        }
        Company company = companyRepository.findByAppCode(appCode);
        if (company == null) {
            authorizationLogRepository.save(log);
            throw new FailResponse(ExceptionEnum.AUTHORIZATION_APPCODE_ERROR);
        }
        log.setCompany(company);
        authorizationLogRepository.save(log);
        System.out.println("--------authorization  end--------");
    }

}
