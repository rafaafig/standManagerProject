package StandManagerProject.standManager.Models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int phoneNumber;

    private String email;


    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Car> carsBought = new ArrayList<>();


    public Buyer() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Car> getCarsBought() {
        return carsBought;
    }

    public void setCarsBought(List<Car> carsBought) {
        this.carsBought = carsBought;
    }
}
