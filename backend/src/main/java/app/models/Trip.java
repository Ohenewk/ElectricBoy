package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime startDT;
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime endDT;

    private String startLocation, endLocation;

    private double mileage;
    private double cost;

    @ManyToOne
    @JsonBackReference
    private Scooter scooter;

    public Trip() {
        // empty constructor mandatory
    }

    public static Trip createSampleTripForScooter(Scooter scooter) {
        // TODO:
        return new Trip();
    }

    public boolean associateScooter(Scooter scooter) {
        if (this.scooter == null) {
            this.scooter = scooter;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", startDT=" + startDT +
                ", endDT=" + endDT +
                ", startLocation='" + startLocation + '\'' +
                ", endLocation='" + endLocation + '\'' +
                ", mileage=" + mileage +
                ", cost=" + cost +
                ", scooter=" + scooter +
                '}';
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------------------
    public long getId() {
        return id;
    }

    public LocalDateTime getStartDT() {
        return startDT;
    }

    public LocalDateTime getEndDT() {
        return endDT;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public double getMileage() {
        return mileage;
    }

    public double getCost() {
        return cost;
    }

    public Scooter getScooter() {
        return scooter;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Setters
    // -----------------------------------------------------------------------------------------------------------------
    public void setId(long id) {
        this.id = id;
    }

    public void setStartDT(LocalDateTime startDT) {
        this.startDT = startDT;
    }

    public void setEndDT(LocalDateTime endDT) {
        this.endDT = endDT;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setScooter(Scooter scooter) {
        this.scooter = scooter;
    }
}
