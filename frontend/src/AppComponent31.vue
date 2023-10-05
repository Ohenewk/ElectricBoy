<template>
  <HeaderComponent></HeaderComponent>
  <NavBarComponent></NavBarComponent>
<!--  <WelcomeComponent></WelcomeComponent>-->
  <ScootersOverview31></ScootersOverview31>
</template>

<script>
// import WelcomeComponent from "@/components/WelcomeComponent";
import HeaderComponent from "@/components/HeaderComponent";
import NavBarComponent from "@/components/NavBarComponent";
import ScootersOverview31 from "@/components/ScootersOverview31";

export default {
  name: "AppComponent31",
  components: {
    ScootersOverview31,
    NavBarComponent,
    HeaderComponent,
    // WelcomeComponent
  }
}
</script>

<style scoped>

</style>
