package com.cafe.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cafe.enums.ItemType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Delicacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;
    private String description;
    private Double price;
    private Boolean availability;		// 1 for true, 0 for false
   // @Enumerated(EnumType.STRING)    	// Store enums as strings
    @Enumerated(EnumType.ORDINAL)    	// to store enums in integer
    private ItemType type;
}

