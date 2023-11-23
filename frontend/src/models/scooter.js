export class Scooter {
    static ScooterStatus = [
        "IDLE",
        "INUSE",
        "MAINTENANCE"
    ];

    constructor(id, tag, status, gpsLocation, mileage, batteryCharge) {
        this.id = id;
        this.tag = tag;
        this.status = status;
        this.gpsLocation = gpsLocation;
        this.mileage = mileage;
        this.batteryCharge = batteryCharge;
    }

    equals(other) {
        if (other === null || other === undefined) {
            return false;
        }
        return (
            this.id === other.id &&
            this.tag === other.tag &&
            this.status === other.status &&
            this.mileage === other.mileage &&
            this.batteryCharge === other.batteryCharge &&
            JSON.stringify(this.gpsLocation) === JSON.stringify(other.gpsLocation)
        );
    }

    static createSampleScooter(pId = 0) {
        const randomTag = Math.random().toString(36).substring(2, 10);
        const randomStatus = this.ScooterStatus[Math.floor(Math.random() * this.ScooterStatus.length)];
        const randomLatitude = 52.370216 + (Math.random() * 0.1); // Random latitude in Amsterdam area
        const randomLongitude = 4.895168 + (Math.random() * 0.1); // Random longitude in Amsterdam area
        const gpsLocation = { latitude: randomLatitude, longitude: randomLongitude };
        const randomTotalMileage = Math.floor(Math.random() * 10000); // Random mileage
        const randomBatteryCharge = Math.floor(Math.random() * 96) + 5; // Random battery charge between 5% and 100%

        return new Scooter(pId, randomTag, randomStatus, gpsLocation, randomTotalMileage, randomBatteryCharge);
    }

    static copyConstructor(scooter) {
        if (scooter === null || scooter === undefined) {
            return null;
        }

        return Object.assign(new Scooter(0), scooter);
    }

    toRawJson() {
        return {
            id: this.id,
            tag: this.tag,
            status: this.status,
            gpsLocation: this.gpsLocation,
            mileage: this.mileage,
            batteryCharge: this.batteryCharge
        };
    }

    toSafeJson() {
        let safe = this.toRawJson();
        safe.gpsLocation = `${this.gpsLocation.latitude},${this.gpsLocation.longitude}`;
        return safe;
    }

    toStringJson() {
        return JSON.stringify(this.toSafeJson());
    }
}

