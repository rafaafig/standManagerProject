package StandManagerProject.standManager.Models;

import StandManagerProject.standManager.Enums.CarEnums;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.sql.results.graph.basic.BasicResultGraphNode;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Model model;

    private String licensePlate;

    private double kilometers;

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


    @ManyToOne
    private Seller seller;

    private Long buyPrice;

    private Long sellPrice;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Long getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(Long buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getKilometers() {
        return kilometers;
    }

    public void setKilometers(double kilometers) {
        this.kilometers = kilometers;
    }
}
