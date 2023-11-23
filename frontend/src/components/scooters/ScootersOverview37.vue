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
        <router-view v-if="this.selectedScooter"
                     @save-scooter="onSaveScooter"
                     @delete-scooter="onDeleteScooter"
                     @unselect-scooter="unselectScooter"
                     @refresh="onRefresh"
        ></router-view>
        <div class="col" v-else>
          <h4>Please select a scooter</h4>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import ScootersDetail37 from "@/components/scooters/ScootersDetail37";
import {Scooter} from "@/models/scooter";

export default {
  name: "ScootersOverview37",
  inject: ["scootersService"],
  components: {ScootersDetail37},
  data() {
    return {
      scooters: [],
      selectedScooter: null
    }
  },
  async created() {
    this.scooters = await this.scootersService.asyncFindAll();
    this.selectedScooter = this.findSelectedScooterFromRouteParam(this.$route);
  },
  methods: {
    // SCOOTER SELECTION
    findSelectedScooterFromRouteParam(route) {
      const urlId = route.params.id;
      if (!urlId || isNaN(urlId)) {
        return null;
      }

      return this.scooters.find(scooter => scooter.id === parseInt(urlId));
    },

    onScooterSelect(scooter) {
      let overviewBaseUrl = `${this.$route.matched[0].path}/`;
      if (scooter && scooter !== this.selectedScooter) {
        // select
        overviewBaseUrl += scooter.id.toString();
        this.selectedScooter = scooter;
      }

      this.$router.push(overviewBaseUrl);
    },

    unselectScooter() {
      this.onScooterSelect(this.selectedScooter);
    },

    // REST
    async onNewScooter() {
      const newScooter = Scooter.createSampleScooter(0);
      const savedScooter = await this.scootersService.asyncSave(newScooter);

      if (savedScooter !== {}) {
        // check if our scooter isn't messed up
        this.onScooterSelect(savedScooter);
        this.scooters.push(savedScooter);
      }
    },

    async onSaveScooter(selectedScooterCopy) {
      // so scooter isn't null
      this.selectedScooter = Scooter.copyConstructor(selectedScooterCopy);
      const savedScooter = await this.scootersService.asyncSave(selectedScooterCopy);

      // check if our scooter isn't messed up
      (savedScooter !== {}) ? this.onScooterSelect(this.selectedScooter) : this.unselectScooter();
    },

    async onDeleteScooter() {
      const deletedScooter = await this.scootersService.asyncDeleteById(this.selectedScooter.id);
      if (deletedScooter) {
        // find scooter and remove it from array
        this.scooters = this.scooters.filter(scooter => scooter.id !== deletedScooter.id);
      }
    },
    async onRefresh() {
      await this.scootersService.asyncFindAll();
      this.scooters = await this.scootersService.asyncFindAll();
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
