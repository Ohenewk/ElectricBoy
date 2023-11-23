<template>
  <nav class="navbar navbar-expand-lg sticky-lg-top bg-primary-subtle" :class="{'fixed-top': navbarAtPageTop}"
       data-bs-theme="dark">
    <div class="container-fluid">
      <div class="navbar-collapse" id="navbarText">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <router-link class="nav-link" aria-current="page" to="/home">Home</router-link>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Scooters
            </a>
            <ul class="dropdown-menu">
              <li class="dropend">
                <a class="dropdown-item dropdown-toggle" data-bs-toggle="dropdown" >Overviews</a>
                <ul class="dropdown-menu">
                  <li>
                    <router-link class="dropdown-item" to="/scooters/Overview31">Scooters Overview (31)</router-link>
                  </li>
                  <li>
                    <router-link class="dropdown-item" to="/scooters/overview32">Scooters Overview (32)</router-link>
                  </li>
                  <li>
                    <router-link class="dropdown-item" to="/scooters/overview33">Scooters Overview (33)</router-link>
                  </li>
                  <li>
                    <router-link class="dropdown-item" to="/scooters/overview34">Scooters Overview (34)</router-link>
                  </li>
                  <li>
                    <router-link class="dropdown-item" to="/scooters/overview37">Scooters Overview (37)</router-link>
                  </li>
                </ul>
              </li>
              <li>
                <hr class="dropdown-divider">
              </li>
              <router-link class="dropdown-item" to="/scooters/details">Scooters details (?)</router-link>
              <li>
                <hr class="dropdown-divider">
              </li>
              <router-link class="dropdown-item" to="/scooters/available">Available Scooters</router-link>
              <router-link class="dropdown-item" to="/scooters/abandoned">Abandoned Scooters</router-link>
            </ul>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/my-trips">My Trips</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/my-account">My Account</router-link>
          </li>
        </ul>
        <ul class="navbar-nav mb-lg-auto">
          <li class="nav-item">
            <router-link class="nav-link" to="/sign-up">
              <button type="button" class="btn btn-danger">
                Sign Up
              </button>
            </router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" to="/sign-in">
              <button type="button" class="btn btn-outline-success">
                Log In
              </button>
            </router-link>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "NavBarComponent"
}
</script>

<style scoped>
/* Add your custom highlighted style here */
.nav-item.active {
  background-color: #007bff; /* Highlighted background color */
  color: #fff; /* Text color on highlight */
}
.dropdown:hover > .dropdown-menu {
  display: block;
  margin-top: 0;
  cursor: pointer;
}
</style>
