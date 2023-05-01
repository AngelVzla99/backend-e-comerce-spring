package com.example.springboot.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "payment_method")
public class PaymentMethodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "payment_user_id")
    private String paymentUserId;

    @Column(name = "payment_id")
    private String paymentId;

    @Column(name = "payed_at")
    private Date payedAt;

    @Column(name = "expire_at")
    private Date expireAt;

    @Column(name = "isConfirmed", nullable = false)
    private Boolean isConfirmed;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "currency", nullable = false)
    private String currency;
}