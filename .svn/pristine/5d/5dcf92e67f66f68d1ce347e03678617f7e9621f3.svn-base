package fengkongweishi.util;

import fengkongweishi.entity.company.Company;
import fengkongweishi.entity.role.Role;
import fengkongweishi.entity.user.User;
import fengkongweishi.enums.Color;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Common {

    public static UserDetailsImpl getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        if (authentication.getPrincipal() instanceof UserDetailsImpl) {
            return (UserDetailsImpl) authentication.getPrincipal();
        } else {
            return null;
        }
    }

    public static long youfenMoneyMin(String s) {
        String[] split = s.split("～");
        String moneyMin = split[0].substring(0, split[0].indexOf("w"));
        Double value = Double.parseDouble(moneyMin);
        return (long) (value * 10000);
    }

    public static long youfenMoneyMax(String s) {
        String[] split = s.split("～");
        String moneyMax = split[1].substring(0, split[1].indexOf("w"));
        Double value = Double.parseDouble(moneyMax);
        return (long) (value * 10000);
    }

    public static class UserDetailsImpl implements UserDetails {

        private User user;

        public UserDetailsImpl(User user) {
            this.user = user;
        }

        public User getUser() {
            return this.user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> auths = new ArrayList<>();
            Role role = this.user.getRole();
            auths.add(new SimpleGrantedAuthority(role.getName()));
            return auths;
        }

        public Integer getId() {
            return this.user.getId();
        }

        public Company getCompany() {
            return this.user.getCompany();
        }

        public Role getRole() {
            return this.user.getRole();
        }

        @Override
        public String getPassword() {
            // TODO Auto-generated method stub
            return this.user.password();
        }

        @Override
        public String getUsername() {
            // TODO Auto-generated method stub
            return this.user.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            // TODO Auto-generated method stub
            return true;
        }

        @Override
        public boolean isEnabled() {
            // TODO Auto-generated method stub
            return this.user.isEnabled();
        }

    }


    public static class Response {
        Integer status;
        String msg;
        Color color;
        Object result;
        Object Description;

        public Object getDescription() {
            return Description;
        }

        public void setDescription(Object description) {
            Description = description;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public Object getResult() {
            return result;
        }

        public void setResult(Object result) {
            this.result = result;
        }

        @Override
        public String toString() {
            return "Response [status=" + status + ", msg=" + msg + ", color=" + color + ", result=" + result + "]";
        }

    }

    public static class CodeMessageColor {
        private String message;
        private Object value;
        private Color color;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public CodeMessageColor(String message, Object value, Color color) {
            super();
            this.message = message;
            this.value = value;
            this.color = color;
        }

    }

}
