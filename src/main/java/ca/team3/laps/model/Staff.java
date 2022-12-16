package ca.team3.laps.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id", nullable = false)
    private int stfId;

    private String username;

    private String password;

    @Column(name = "role_id", nullable = false)
    private int roleId;

    @Column(name = "job_title", nullable = false)
    private String title;

    private String firstname;

    private String lastname;

    private boolean status;

    private String email;

    @Column(name = "one_time_password", nullable = false)
    private String otp;

    @Column(name = "otp_requested_time", nullable = false)
    private Float otpReqTime;

    @Column(name = "anual_leave_entitlemnt", nullable = false)
    private int anuLeave;

    @Column(name = "med_leave_entitlement", nullable = false)
    private int mediLeave;

    @Column(name = "comp_leave_entitlement", nullable = false)
    private int compLeave;

    @JsonIgnore
    @OneToMany(mappedBy = "staff")
    private List<Leave> leave;
    
    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Staff manager;

    @JsonIgnore
    @OneToMany(mappedBy = "manager")
    private Set<Staff> subordinates;

    
}
