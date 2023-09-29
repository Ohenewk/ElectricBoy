<template>
  <div class="h-100 d-flex justify-content-between align-items-center">
    <div class="col-auto col-md-3 bg-dark p-5">
      <div class="d-flex flex-column align-items-center align-items-md-center text-white min-vh-100">

          <h3>Pick a brand:</h3>
          <div class="btn-group">
            <button class="btn btn-secondary btn-md dropdown-toggle" id="_button" type="button" data-bs-toggle="dropdown"
                    aria-expanded="false">
              Select a brand...
            </button>
            <ul class="dropdown-menu">
              <li v-for="brand in brands" :key="brand.name" @click="doSomethingWithBrand(brand)">
                <a class="dropdown-item" href="#">{{ brand.name }}</a>
              </li>
            </ul>
          </div>
        </div>

        <div class="m-2">
          <h3>Availability</h3>
          <ul class="">
            <li v-for="place in places" :key="place.count" @click="doSomethingWithPlace(place)">
              <a class="" href="#">{{ place.name }}&nbsp;-&nbsp;{{ place.count }}</a>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <hr class="vr">
    <div class="col-auto col-md-3 bg-dark p-5 flex-grow-1 bd-highlight">
      <div class="d-flex flex-column align-items-center align-items-md-start text-white min-vh-100">
        <h1>Welcome!</h1>
        <p>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc tristique tortor sit amet augue cursus, at
          tincidunt
          nibh efficitur. Nam consequat, elit sit amet pretium lobortis, mi nisi elementum mi, id finibus ex nisl ut
          sapien.
          Donec risus sapien, euismod eu sem nec, finibus gravida turpis. Nunc sit amet augue at arcu posuere euismod eu
          quis lectus. Donec quis tellus accumsan, euismod massa eu, placerat massa. Fusce ac erat sit amet erat
          vestibulum
          pulvinar. Integer tincidunt dapibus dolor, et commodo velit posuere quis. Cras id tellus viverra, tincidunt
          tortor
          vulputate, tincidunt nisl. Aenean venenatis bibendum auctor. Phasellus vehicula elit vitae quam rhoncus, sed
          pellentesque turpis viverra.
        </p>

        <h3>Gallery:</h3>
        <p>(Randomly generated)</p>

        <div v-if="!isLoadingImages" class="images">
          <img v-for="image in images" :key="image.id" :src="image.src" :width="image.width" :alt="image.alt"
               loading="lazy"/>
        </div>
        <div v-else>
          <p>Loading images... ({{ images.length }}/{{ amountOfRandomlyGeneratedImages }})</p>
        </div>
      </div>
    </div>
    <hr class="vr">
    <div class="col-auto col-md-3 bg-dark p-5">
      <div class="d-flex flex-column align-items-center align-items-md-start text-white min-vh-100">
        <h3>Title</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam hendrerit mi lectus, a aliquam purus varius in. Maecenas pulvinar massa non arcu finibus imperdiet. Maecenas quis accumsan nisi. Nunc faucibus, urna id vehicula malesuada, elit erat porttitor turpis, at placerat mauris tellus in erat. Cras rhoncus enim a justo rutrum porta et vitae justo. Pellentesque lorem est, aliquet ut tristique at, sodales vel odio. Quisque magna ante, suscipit non lacus eu, finibus tincidunt elit. In rhoncus vitae felis at efficitur. Vivamus ullamcorper eu ante vitae placerat. Etiam dolor mauris, efficitur sit amet velit at, sodales tincidunt dolor. Integer sit amet nisl condimentum, tincidunt dolor eu, iaculis nisi.</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "WelcomeComponent"
}
</script>

<style scoped>
.images > img {
  margin: 1em;
}

hr {
  color: white;
}

</style>
