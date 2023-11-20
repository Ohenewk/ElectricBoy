package app.models;

import app.models.enums.Status;
import app.utils.RandomDataHelper;

public class Scooter {
    private long id;
    private String tag;
    private Status status;
    private String gpsLocation;
    private int mileage;
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
}
