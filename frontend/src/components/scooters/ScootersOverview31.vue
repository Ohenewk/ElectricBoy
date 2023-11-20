<template>
  <div>
    <h1>Scooter List</h1>
    <table class="table table-striped table-bordered">
      <thead class="thead-dark">
      <tr>
        <th>ID</th>
        <th>Tag</th>
        <th>Status</th>
        <th>GPS Location</th>
        <th>Total Mileage</th>
        <th>Battery Charge</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="scooter in scooterList" :key="scooter.id">
        <td>{{ scooter.id }}</td>
        <td>{{ scooter.tag }}</td>
        <td>{{ scooter.status }}</td>
        <td v-if="scooter.status !== 'INUSE'">{{ getGpsLocationString(scooter.gpsLocation) }}</td>
        <td v-else>-</td>
        <td>{{ scooter.mileage }}</td>
        <td>{{ scooter.batteryCharge }}%</td>
      </tr>
      </tbody>
    </table>
    <button class="btn btn-primary new-scooter-button" @click="onNewScooter">New Scooter</button>
  </div>
</template>

<script>
import {Scooter} from "@/models/scooter";

export default {
  name: "ScootersOverview31",
  data () {
    return {
      scooterList: [],
      nextScooterId: 30000,
    };
  },
  methods: {
    getGpsLocationString(gpsLocation) {
      return gpsLocation ? `${gpsLocation.latitude}, ${gpsLocation.longitude}` : '-';
      // return gpsLocation ? gpsLocation.latitude + ', ' + gpsLocation.longitude : '-';
    },
    onNewScooter() {
      const scooter = Scooter.createSampleScooter(this.nextScooterId);
      this.scooterList.push(scooter);

      // Calculate a random increment (about 3) for the next scooter ID
      const randomIncrement = Math.floor(Math.random() * 3 + 1);
      this.nextScooterId += randomIncrement;
    },
  },
  created() {
    for (let i = 0; i < 8; i++) {
      const scooter = Scooter.createSampleScooter(this.nextScooterId);
      this.scooterList.push(scooter);

      const randomIncrement = Math.floor(Math.random() * 3 + 1);
      this.nextScooterId += randomIncrement;
    }
  },
}
</script>

<style scoped>

</style>
