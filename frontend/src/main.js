import { createApp } from "vue";
// import App31 from "./AppComponent31.vue";
import App33 from "./AppComponent33.vue";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap";
import "./main.css"
import { router } from "@/router";

createApp(App33).use(router).mount('#app');
