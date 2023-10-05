<template>
  <header class="navbar navbar-expand bg-warning p-5">
    <div class="container-fluid">
      <div class="navbar-brand">
        <img class="" src="../assets/images/Map_Icon.png" alt="logo" loading="lazy"/>
      </div>

      <figure class="text-center">
        <h1 class="text-primary">Electric Scooter Rental</h1>
        <figcaption class="blockquote-footer">
          By:
          <a href="https://www.example.com" target="_blank"><cite title="Rio">Rio</cite></a>
          &
          <a href="https://www.example.com" target="_blank"><cite title="Kingsley">Kingsley</cite></a>
        </figcaption>
        <hr>
        <p class="text-primary">Today's date: <span class="text-primary-emphasis fst-italic">{{ currentDate }}</span></p>
      </figure>

      <div class="navbar-brand">
        <img class="" src="../assets/images/Electric_Scooter.png" alt="logo" loading="lazy"/>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: "HeaderComponent",
  data () {
    return {
      currentDate: ""
    }
  },
  mounted() {
    this.updateHeaderDate();
  },
  methods: {
    updateHeaderDate() {
      const currentDate = new Date();
      const options = { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' };
      this.currentDate = currentDate.toLocaleDateString('en-US', options);
    }
  }
}
</script>

<style scoped>
div > * {
  color: white;
}

header {
  background-image: url("../assets/images/Electric_Scooter_Background.png");
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
}

h1 {
  font-size: 5vw;
}

h2 {
  font-size: 4vw;
}

h3 {
  font-size: 3vw;
}

h4 {
  font-size: 2vw;
}

img {
  height: auto;
  width: auto;
  max-width: 20vw;
  max-height: 24vh;
}

hr {
  color: black;
}
</style>
