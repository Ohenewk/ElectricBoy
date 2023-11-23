<template>
  <HeaderComponent></HeaderComponent>
  <NavBarComponent></NavBarComponent>

  <router-view></router-view>
</template>

<script>
import WelcomeComponent from "@/components/WelcomeComponent";
import HeaderComponent from "@/components/HeaderComponent";
import NavBarComponent from "@/components/NavBarComponent";
import CONFIG from "@/app-config"
import {ScootersAdaptor} from "@/services/ScootersAdaptor";

export default {
  name: "AppComponent37",
  components: {
    WelcomeComponent,
    NavBarComponent,
    HeaderComponent
  },
  provide() {
    return {
      scootersService: new ScootersAdaptor(`${CONFIG.BACKEND_URL}/scooters`),
    }
  }
}
</script>

<style scoped>

</style>
