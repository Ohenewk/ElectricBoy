<template>
  <HeaderComponent></HeaderComponent>
  <NavBarComponent></NavBarComponent>
  <WelcomeComponent></WelcomeComponent>
</template>

<script>
import WelcomeComponent from "@/components/WelcomeComponent";
import HeaderComponent from "@/components/HeaderComponent";
import NavBarComponent from "@/components/NavBarComponent";

export default {
  name: "AppComponent31",
  components: {
    NavBarComponent,
    HeaderComponent,
    WelcomeComponent
  }
}
</script>

<style scoped>

</style>
