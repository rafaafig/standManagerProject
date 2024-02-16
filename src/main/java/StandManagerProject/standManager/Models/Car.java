package StandManagerProject.standManager.Models;

import StandManagerProject.standManager.Enums.CarEnums;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;


@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String brand;

    private String model;

    @Nullable
    private int licensePlate;

    @Enumerated(EnumType.STRING)
    private CarEnums.Traction traction;

    @Enumerated(EnumType.STRING)
    private CarEnums.Country country;

    @Enumerated(EnumType.STRING)
    private CarEnums.Seats seats;

    @Enumerated(EnumType.STRING)
    private CarEnums.Fuel fuel;

    @Enumerated(EnumType.STRING)
    private CarEnums.Type type;

    @Enumerated(EnumType.STRING)
    private CarEnums.Doors doors;

    @Enumerated(EnumType.STRING)
    private CarEnums.Status status;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private Seller seller;


    public Car() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(int licensePlate) {
        this.licensePlate = licensePlate;
    }

    public CarEnums.Traction getTraction() {
        return traction;
    }

    public void setTraction(CarEnums.Traction traction) {
        this.traction = traction;
    }

    public CarEnums.Country getCountry() {
        return country;
    }

    public void setCountry(CarEnums.Country country) {
        this.country = country;
    }

    public CarEnums.Seats getSeats() {
        return seats;
    }

    public void setSeats(CarEnums.Seats seats) {
        this.seats = seats;
    }

    public CarEnums.Fuel getFuel() {
        return fuel;
    }

    public void setFuel(CarEnums.Fuel fuel) {
        this.fuel = fuel;
    }

    public CarEnums.Type getType() {
        return type;
    }

    public void setType(CarEnums.Type type) {
        this.type = type;
    }

    public CarEnums.Doors getDoors() {
        return doors;
    }

    public void setDoors(CarEnums.Doors doors) {
        this.doors = doors;
    }

    public CarEnums.Status getStatus() {
        return status;
    }

    public void setStatus(CarEnums.Status status) {
        this.status = status;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

}
