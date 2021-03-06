package com.besoft.cafelite.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_menu")
public class Menu implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_id")
	private Long menuId;
	
	@Column(name = "menu_code")
	private String menuCode;
	
	@Column(name = "menu_name")
	@NotBlank(message = "{menu.menuName.notBlank}")
	private String menuName;
	
	@Column(name = "printout_name")
	private String printoutName;
	
	@Column(name = "menu_price")
	private Double menuPrice;
	
	@Column(name = "deleted", nullable = false)
	private boolean deleted;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "login_id")
	@JsonIgnore
	private LoginHistory loginHistory;
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "menu_id")
	private List<MenuMaterial> materials = new ArrayList<>();
}
