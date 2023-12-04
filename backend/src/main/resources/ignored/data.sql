-- scooter
INSERT INTO `scooter` (tag, status, gps_location, mileage, battery_charge)
VALUES ('mqwkdeic', 'INUSE', '52.37403,4.88969', 2877, 61);

INSERT INTO `scooter` (tag, status, gps_location, mileage, battery_charge)
VALUES ('abvsjefh', 'IDLE', '52.37403,4.88969', 6237, 28);

INSERT INTO `scooter` (tag, status, gps_location, mileage, battery_charge)
VALUES ('hshagdbs', 'IDLE', '52.37403,4.88969', 2363, 87);

-- trip
INSERT INTO `trip` (startDT, start_location, end_location, mileage, cost, scooter_id)
VALUES (current_timestamp, '52.37403,4.88969', null, 5.0, 2.5, 1);
