package StandManagerProject.standManager.Models;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Stand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long standID;
    private String address;
    private long capacity;

    @OneToMany(mappedBy = "stand", cascade = CascadeType.ALL)
    private List<Car> carsInInventory;


    public Long getStandID() {
        return standID;
    }

    public void setStandID(Long standID) {
        this.standID = standID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public List<Car> getCarsInInventory() {
        return carsInInventory;
    }

    public void setCarsInInventory(List<Car> carsInInventory) {
        this.carsInInventory = carsInInventory;
    }
}
