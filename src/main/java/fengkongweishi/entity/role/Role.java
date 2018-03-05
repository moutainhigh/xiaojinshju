package fengkongweishi.entity.role;

import fengkongweishi.entity.base.BaseEntity;

import javax.persistence.Entity;

@Entity
public class Role extends BaseEntity {
   
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum defaultRole {
        /**
         * Admin role.
         */
        ADMIN("ROLE_ADMIN"),
        /**
         * Manager role.
         */
        MANAGER("ROLE_MANAGER"),
        /**
         * Leader role.
         */
        LEADER("ROLE_LEADER"),
        /**
         * Employee role.
         */
        EMPLOYEE("ROLE_EMPLOYEE"),
        /**
         * User role.
         */
        USER("ROLE_USER");
        private String name;

        defaultRole(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

}
