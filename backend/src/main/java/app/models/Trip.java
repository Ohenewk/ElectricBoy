package app.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@NamedQueries({
        @NamedQuery(name = "Trip.find_by_scooterId_and_period",
                query = """
                        SELECT trip FROM Trip trip
                        WHERE (trip.scooter.id = ?1 AND ?2 BETWEEN trip.startDT AND trip.endDT)
                        """),
        @NamedQuery(name = "Trip.find_by_scooterId_between_periods",
                query = """
                        SELECT trip FROM Trip trip
                                WHERE (
                                    trip.scooter.id = ?1 
                                    AND trip.startDT BETWEEN ?2 AND ?3
                                    AND trip.endDT BETWEEN ?2 AND ?3
                                    )
                        """)
})
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @DateTimeFormat
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime startDT;

    @DateTimeFormat
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

    public Trip(LocalDateTime endDT) {
        this.endDT = endDT;
    }

    public static Trip createSampleTripForScooter(Scooter scooter) {
        // TODO: implement further
        final LocalDateTime endDT = LocalDateTime.now().plusDays(10);
        System.out.println(endDT);
        return new Trip(endDT);
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
