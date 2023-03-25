package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    int noOfHours;
    @ManyToOne
            @JoinColumn
    User user;
    @ManyToOne
    @JoinColumn
    Spot spot;
@OneToOne(mappedBy = "reservation",cascade = CascadeType.ALL)
    Payment payment;

    public Reservation() {
    }

    public Reservation(int id, int noOfHours, User user, Spot spot, Payment payment) {
        this.id = id;
        this.noOfHours = noOfHours;
        this.user = user;
        this.spot = spot;
        this.payment = payment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoOfHours() {
        return noOfHours;
    }

    public void setNoOfHours(int noOfHours) {
        this.noOfHours = noOfHours;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
