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
            <option value="AVAILABLE">Available</option>
            <option value="INUSE">In Use</option>
            <!-- Add more options as needed -->
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
      <button class="btn btn-danger mt-3" type="button" @click="onDelete">Delete</button>
    </div>
    <div v-else>
      <p>Select a scooter to view details.</p>
    </div>
  </div>
</template>

<script>

export default {
  name: "ScootersDetail32",
  props: [
    "selectedScooter"
  ],
  emits: [
    "delete-scooter"
  ],
  methods: {
    onDelete() {
      this.$emit("delete-scooter");
    }
  },
}
</script>

<style scoped>

</style>
