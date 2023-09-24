package com.akash.employeemanagementsystem.auth_entity;

import com.akash.employeemanagementsystem.entity.Employee;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "employee_user_table")
public class EmployeeUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "emp_id",columnDefinition = "varchar(36)")
    private String employeeId;

    @Column(name = "email",columnDefinition = "unique not null varchar(20)")
    private String email;

    @Column(name = "password",columnDefinition = "not null varchar(60)")
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;


    @OneToOne(mappedBy = "employeeUser",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Employee employee;

    @OneToOne(mappedBy = "employeeUser",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private TokenEntity token;

    @Override
    public String toString() {
        return "EmployeeUser{" +
                "employeeId='" + employeeId + '\'' +
                ", email='" + email + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAllAuthorities();
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
