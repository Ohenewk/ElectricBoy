<template>
  <div>
    <h1>Scooters Overview</h1>
    <div class="row">
      <div class="col-md-4">
        <h3>Scooter Tags</h3>
        <ul class="list-group">
          <li
              class="list-group-item list-group-item-action"
              v-for="scooter in scooters"
              :key="scooter.tag"
              @click="onScooterSelect(scooter)"
              :class="{ 'active': scooter === selectedScooter }"
          >
            {{ scooter.tag }}
          </li>
        </ul>
        <button class="btn btn-primary mt-3" @click="onNewScooter">New Scooter</button>
      </div>
      <div class="col-md-8">
        <router-view v-if="this.selectedScooter" :selected-scooter="this.selectedScooter"
                     @delete-scooter="onDeleteScooter"></router-view>
      </div>
    </div>
  </div>
</template>

<script>
import ScootersDetail32 from "@/components/scooters/ScootersDetail32";
import {Scooter} from "@/models/scooter";

export default {
  name: "ScootersOverview33",
  components: {
    ScootersDetail32,
  },
  data() {
    return {
      scooters: [],
      selectedScooter: null,
      lastScooterId: null,
    }
  },
  created() {
    this.generateScooters(5, 3);
    this.selectedScooter = this.findSelectedScooterFromRouteParam(this.$route);
  },
  methods: {
    findSelectedScooterFromRouteParam(route) {
      const urlId = route.params.id;
      if (!urlId || isNaN(urlId)) {
        return null;
      }

      return this.scooters.find(scooter => scooter.id === parseInt(urlId));
    },

    generateScooters(amount, idIncrement) {
      let id = 33000;
      for (let numOfScooters = 0; numOfScooters < amount; numOfScooters++) {
        this.scooters.push(Scooter.createSampleScooter(id));
        id += idIncrement;
      }

      // set last scooter id when for when we need to generate a new one
      this.lastScooterId = id - idIncrement;
    },

    onScooterSelect(scooter) {
      if (scooter && scooter !== this.selectedScooter) {
        // select
        const updatedUrlWithSelectedScooter = `${this.$route.matched[0].path}/${scooter.id}`;
        this.$router.push(updatedUrlWithSelectedScooter);
      } else if (this.selectedScooter) {
        // deselect
        scooter = null;

        const overviewBaseUrl = `${this.$route.matched[0].path}/`;
        this.$router.push(overviewBaseUrl);
      }

      this.selectedScooter = scooter;
    },

    onNewScooter() {
      const newScooter = Scooter.createSampleScooter(this.lastScooterId + 3);
      this.lastScooterId = newScooter.id;
      this.selectedScooter = newScooter;

      this.scooters.push(newScooter);
      this.onScooterSelect(newScooter);
    },

    onDeleteScooter() {
      this.scooters = this.scooters.filter(scooter => scooter.id !== this.selectedScooter.id);
      this.selectedScooter = null;
    }
  },
  watch: {
    "$route"() {
      this.selectedScooter = this.findSelectedScooterFromRouteParam(this.$route);
    }
  }
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
