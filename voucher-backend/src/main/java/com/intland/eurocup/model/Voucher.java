package com.intland.eurocup.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.intland.eurocup.common.model.Territory;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Voucher entity.
 */
@Entity
@Table(name = "voucher")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
@Getter @Setter @ToString
public class Voucher {
	@Transient
	final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String code;

    @NotBlank
    @Column(unique = true)
    private String email;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Territory territory;
    
    @Column(name = "created", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;
    
    @Column(name = "lot_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private LotStatus lotStatus;
    
    public String getCreationDate() {
    	return dateFormat.format(createdAt);  
    }
}
