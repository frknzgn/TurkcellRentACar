package com.turkcell.rentacar.entites.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ordered_additional_services")
@PrimaryKeyJoinColumn(name = "customer_id")
public class OrderedAdditionalService {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_additional_service_id", nullable = false)
    private int id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "ordered_additional_service_bill")
    private double orderedAdditionalServiceBill;

    @ManyToOne()
    @JoinColumn(name = "additional_service_id", nullable = false)
    private AdditionalService additionalService;

    @ManyToOne()
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;
    
    @OneToOne(mappedBy = "orderedAdditionalService")
	private Payment payment;
    
   

}
