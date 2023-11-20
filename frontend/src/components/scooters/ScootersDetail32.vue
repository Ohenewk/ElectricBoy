<template>
  <div>
    <div v-if="selectedScooter">
      <h3>Scooter Details (ID: {{ selectedScooter.id }})</h3>
      <form>
        <div class="form-group">
          <label for="tag">Tag:</label>
          <input type="text" class="form-control" id="tag" v-model="selectedScooter.tag"/>
        </div>
        <div class="form-group">
          <label for="status">Status:</label>
          <select class="form-control" id="status" v-model="selectedScooter.status">
            <option v-for="status in ScooterStatus" :key="status" :value="status">{{ status }}</option>
          </select>
        </div>
        <div class="form-group" v-if="selectedScooter.status !== 'INUSE'">
          <label for="gpsLocation">GPS Location:</label>
          <input type="text" class="form-control" id="gpsLocation" v-model="selectedScooter.gpsLocation.latitude"/>
          <input type="text" class="form-control" id="gpsLocation" v-model="selectedScooter.gpsLocation.longitude"/>
        </div>
        <div class="form-group">
          <label for="totalMileage">Total Mileage:</label>
          <input type="number" class="form-control" id="totalMileage" v-model="selectedScooter.mileage"/>
        </div>
        <div class="form-group">
          <label for="batteryCharge">Battery Charge:</label>
          <input type="number" class="form-control" id="batteryCharge" v-model="selectedScooter.batteryCharge"/>
        </div>
      </form>
      <button class="btn btn-danger mt-3" type="button" @click="$emit('delete-scooter')">Delete</button>
    </div>
    <div v-else>
      <p>Select a scooter to view details.</p>
    </div>
  </div>
</template>

<script>
// import {Scooter} from "@/models/scooter";

import {Scooter} from "@/models/scooter";

export default {
  name: "ScootersDetail32",
  props: [
    "selectedScooter"
  ],
  emits: [
    "delete-scooter"
  ],
  data() {
    return {
      ScooterStatus: Scooter.ScooterStatus,
    }
  }
}
</script>

<style scoped>

</style>
