package app.models;

import app.models.enums.Status;
import app.utils.RandomDataHelper;
import com.fasterxml.jackson.annotation.JsonView;

public class Scooter {
    @JsonView(ViewClasses.Shallow.class)
    private long id;
    @JsonView(ViewClasses.Shallow.class)
    private String tag;
    @JsonView(ViewClasses.Shallow.class)
    private Status status;
    private String gpsLocation;
    private int mileage;
    @JsonView(ViewClasses.Shallow.class)
    private int batteryCharge;

    public Scooter(long id, String tag, Status status, String gpsLocation, int mileage, int batteryCharge) {
        this.id = id;
        this.tag = tag;
        this.status = status;
        this.gpsLocation = gpsLocation;
        this.mileage = mileage;
        this.batteryCharge = batteryCharge;
    }

    public Scooter(long id, String tag) {
        this(
                id,
                tag,
                Status.values()[RandomDataHelper.getRandom().nextInt(Status.values().length)],
                RandomDataHelper.gpsLocation(),
                RandomDataHelper.getRandom().nextInt(1000, 10000),
                RandomDataHelper.getRandom().nextInt(5, 100)
        );
    }

    public Scooter(String tag) {
        this(RandomDataHelper.getRandom().nextInt(35000, 36000), tag);
    }

    public Scooter(long id) {
        this(id, "tag");
    }

    public Scooter() {
        this(RandomDataHelper.getRandom().nextInt(35000, 36000));
    }

    public static Scooter createSampleScooter(long id) {
        return new Scooter(
                id,
                RandomDataHelper.string(8),
                Status.values()[RandomDataHelper.getRandom().nextInt(Status.values().length)],
                RandomDataHelper.gpsLocation(),
                RandomDataHelper.getRandom().nextInt(1000, 10000),
                RandomDataHelper.getRandom().nextInt(5, 100)
        );
    }

    @Override
    public String toString() {
        return "Scooter{" +
                "id=" + id +
                ", tag='" + tag + '\'' +
                ", status=" + status +
                ", gpsLocation='" + gpsLocation + '\'' +
                ", mileage=" + mileage +
                ", batteryCharge=" + batteryCharge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Scooter scooter)) return false;
        return id == scooter.id;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Getters
    // -----------------------------------------------------------------------------------------------------------------
    public long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public Status getStatus() {
        return status;
    }

    public String getGpsLocation() {
        return gpsLocation;
    }

    public int getMileage() {
        return mileage;
    }

    public int getBatteryCharge() {
        return batteryCharge;
    }

    // -----------------------------------------------------------------------------------------------------------------
    // Setters
    // -----------------------------------------------------------------------------------------------------------------


    public void setId(long id) {
        this.id = id;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setGpsLocation(String gpsLocation) {
        this.gpsLocation = gpsLocation;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public void setBatteryCharge(int batteryCharge) {
        this.batteryCharge = batteryCharge;
    }
}
