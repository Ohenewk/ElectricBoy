import { createApp } from "vue";
// import App31 from "./AppComponent31.vue";
// import App33 from "./AppComponent33.vue";
import App37 from "./AppComponent37.vue";
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap";
import "./main.css"
import VueQrcodeReader from "vue3-qrcode-reader";
import {router} from "@/router";


createApp(App37).use(router, VueQrcodeReader).mount('#app');
