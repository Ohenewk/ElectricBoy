<template>
  <div>
    <h1>Scooters Overview</h1>
    <div class="row">
      <div class="col-md-4">
        <h3>Scooter Tags</h3>
        <ul class="list-group">
          <li
              class="list-group-item list-group-item-action"
              v-for="scooter in scooterList"
              :key="scooter.tag"
              @click="selectScooter(scooter)"
              :class="{ 'active': scooter === selectedScooter }"
          >
            {{ scooter.tag }}
          </li>
        </ul>
        <button class="btn btn-primary mt-3" @click="onNewScooter">New Scooter</button>
      </div>
      <div class="col-md-8">
        <ScootersDetail32 v-if="this.selectedScooter" :selected-scooter="this.selectedScooter"
                          @delete-scooter="onDeleteScooter"></ScootersDetail32>
        <div class="col" v-else>
          <h4>Please select a scooter</h4>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import {Scooter} from "@/models/scooter";
import ScootersDetail32 from "@/components/scooters/ScootersDetail32";

export default {
  name: "ScootersOverview32",
  components: {
    ScootersDetail32
  },
  data() {
    return {
      scooterList: [], // Initialize an empty list for scooters
      nextScooterId: 30000, // Starting scooter ID
      selectedScooter: null, // Property to keep track of the selected scooter
    };
  },
  methods: {
    onNewScooter() {
      const scooter = Scooter.createSampleScooter(this.nextScooterId);
      this.scooterList.push(scooter);
      this.selectScooter(scooter); // Automatically select the newly added scooter
      this.nextScooterId += Math.floor(Math.random() * 3) + 1; // Randomly increment the ID
    },
    selectScooter(scooter) {
      if (this.selectedScooter === scooter) {
        this.selectedScooter = null; // Deselect if already selected
      } else {
        this.selectedScooter = scooter; // Select the clicked scooter
      }
    },
    onDeleteScooter() {
      this.scooterList = this.scooterList.filter(scooter => scooter.id !== this.selectedScooter.id);
      this.selectedScooter = null;
    }
  },
  created() {
    // Create initial scooters
    for (let i = 0; i < 8; i++) {
      const scooter = Scooter.createSampleScooter(this.nextScooterId);
      this.scooterList.push(scooter);
      this.nextScooterId += Math.floor(Math.random() * 3) + 1;
    }
  },
}
</script>

<style scoped>
/* Style to highlight the selected tag */
.list-group-item.active {
  background-color: #007bff;
  color: #fff;
  cursor: pointer;
}

.list-group-item-action {
  background-color: #ffffff; /* Custom background color */
  color: #333; /* Custom text color */
  border: 1px solid #ccc; /* Custom border */
}

.list-group-item-action:hover {
  background-color: #007bff; /* Custom background color on hover */
  color: #ffffff; /* Custom text color on hover */
}
</style>
