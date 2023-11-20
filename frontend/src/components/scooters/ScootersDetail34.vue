<template>
  <div class="col">
    <div v-if="selectedScooterCopy">
      <h3>Scooter Details (ID: {{ selectedScooterCopy.id }})</h3>
      <form>
        <div class="form-group">
          <label for="tag">Tag:</label>
          <input type="text" class="form-control" id="tag" v-model="selectedScooterCopy.tag"/>
        </div>
        <div class="form-group">
          <label for="status">Status:</label>
          <select class="form-control" id="status" v-model="selectedScooterCopy.status">
            <option v-for="status in ScooterStatus" :key="status" :value="status">{{ status }}</option>
            <!-- Add more options as needed -->
          </select>
        </div>
        <div class="form-group" v-if="selectedScooterCopy && selectedScooterCopy.status !== 'INUSE' && selectedScooterCopy.gpsLocation">
          <label for="gpsLocation">GPS Location:</label>
          <input type="text" class="form-control" id="gpsLocation" v-model="selectedScooterCopy.gpsLocation.latitude"/>
          <input type="text" class="form-control" id="gpsLocation" v-model="selectedScooterCopy.gpsLocation.longitude"/>
        </div>
        <div class="form-group">
          <label for="totalMileage">Total Mileage:</label>
          <input type="number" class="form-control" id="totalMileage" v-model="selectedScooterCopy.mileage"/>
        </div>
        <div class="form-group">
          <label for="batteryCharge">Battery Charge:</label>
          <input type="number" class="form-control" id="batteryCharge" v-model="selectedScooterCopy.batteryCharge"/>
        </div>
      </form>
      <button class="btn btn-danger mt-3" type="button" @click="onDelete" :disabled="hasChanged">Delete</button>
      <button class="btn btn-danger me-3" type="button" @click="onReset" :disabled="!hasChanged">Reset</button>
      <hr class="vr me-3">
      <button class="btn btn-warning me-3" type="button" @click="onClear">Clear</button>
      <button class="btn btn-outline-warning me-3" type="button" @click="onCancel">Cancel</button>
      <hr class="vr me-3">
      <button class="btn btn-success" type="button" @click="onSave" :disabled="!hasChanged">Save</button>
    </div>
    <div v-else>
      <p>Select a scooter to view details.</p>
    </div>
  </div>
</template>

<script>
import {Scooter} from "@/models/scooter";

export default {
  name: "ScootersDetail34",
  props: [
    "selectedScooter"
  ],
  emits: [
    "delete-scooter"
  ],
  data() {
    return {
      ScooterStatus: Scooter.ScooterStatus,
      selectedScooterCopy: null,
    }
  },
  methods: {
    onDelete() {
      this.$emit("delete-scooter");
      this.$emit("scooter-unselect");
    },

    onReset() {
      if (!this.confirmedAction("Reset")) {
        return;
      }

      this.selectedScooterCopy = Scooter.copyConstructor(this.selectedScooter);
    },

    onClear() {
      if (!this.confirmedAction("Clear")) {
        return;
      }

      this.selectedScooterCopy = Scooter.copyConstructor(new Scooter(0));
    },

    onCancel() {
      if (!this.confirmedAction("Cancel")) {
        return;
      }

      this.$emit("scooter-unselect");
    },

    onSave() {
      Object.assign(this.selectedScooter, this.selectedScooterCopy);
      this.$emit("scooter-unselect");
    },

    confirmedAction(action) {
      const message = action.toLowerCase() === "leave" ? "" : ` scooter_${this.selectedScooterCopy.id} (${this.selectedScooterCopy.tag})`;

      // if NOT changed OR user confirmed the action
      return !this.hasChanged || confirm(`Are you sure you want to '${action}'${message}?\nAny unsaved changes will be lost.`);
    },
  },
  created() {
    this.selectedScooterCopy = Scooter.copyConstructor(this.selectedScooter);
  },
  watch: {
    "selectedScooter"() {
      this.selectedScooterCopy = Scooter.copyConstructor(this.selectedScooter);
    }
  },
  computed: {
    hasChanged() {
      // Check if there is any difference between the selected scooter and the cloned copy
      return !this.selectedScooter.equals(this.selectedScooterCopy);
    },
  },
}
</script>

<style scoped>

</style>
