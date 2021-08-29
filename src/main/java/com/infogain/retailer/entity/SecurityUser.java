package com.infogain.retailer.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Setter
@Getter
@Table(name="SECURITY_USER")
public class SecurityUser {

    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="USERNAME")
    private String userName;

    @Column(name="ROLE")
    private String role;

    @Column(name="PASSWORD")
    private String password;
}
