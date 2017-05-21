package hu.elteik.projecttools.libmgmt.data.entity;

import hu.elteik.projecttools.libmgmt.util.UserStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bázis on 2017. 04. 15..
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @NotNull
    private String username;

    //@NotNull
    private String fullName;

    @NotNull
    private String email;

    private String phoneNum;

    private String address;

    @NotNull
    private String password; //base64

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private Integer paidAmount;

    private Integer feeAmount;

    @NotNull
    private String role;

    @ManyToMany
    @JoinColumn(name = "borrow_id")
    private Set<Borrow> borrows = new HashSet<>();

    @ManyToMany
    @JoinColumn(name = "appointment_id")
    private Set<Appointment> appointments = new HashSet<>();

    public User() {
        this.role="ROLE_USER";
        this.paidAmount=0;
        this.feeAmount=0;
        this.status=UserStatus.ACTIVE;
    }

    //called when inserting new user
    public User(String username, String fullName, String email, String phoneNum, String address, String password, String role) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
        this.password = password;
        this.status = UserStatus.ACTIVE;
        this.paidAmount = 0;
        this.feeAmount = 0;
        this.role=role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public Integer getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Integer feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Set<Borrow> getBorrows() {
        return borrows;
    }

    public void setBorrows(Set<Borrow> borrows) {
        this.borrows = borrows;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public String getRole() {
        return role;
    }
}
