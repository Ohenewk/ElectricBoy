<template>
  <HeaderComponent></HeaderComponent>
  <NavBarComponent></NavBarComponent>

  <router-view></router-view>
</template>

<script>
import WelcomeComponent from "@/components/WelcomeComponent";
import HeaderComponent from "@/components/HeaderComponent";
import NavBarComponent from "@/components/NavBarComponent";
import ScootersOverview32 from "@/components/scooters/ScootersOverview32";


export default {
  name: "AppComponent33",
  components: {
    WelcomeComponent,
    NavBarComponent,
    HeaderComponent,
    ScootersOverview32,
  }
}
</script>

<style scoped>

</style>
