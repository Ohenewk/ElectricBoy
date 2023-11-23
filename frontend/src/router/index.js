import {createRouter, createWebHashHistory} from "vue-router";
import WelcomeComponent from "@/components/WelcomeComponent";
import ScootersOverview31 from "@/components/scooters/ScootersOverview31";
import ScootersOverview32 from "@/components/scooters/ScootersOverview32";
import ScootersOverview33 from "@/components/scooters/ScootersOverview33";
import ScootersOverview34 from "@/components/scooters/ScootersOverview34";
import ScootersOverview37 from "@/components/scooters/ScootersOverview37";
import ScootersDetail32 from "@/components/scooters/ScootersDetail32";
import ScootersDetail34 from "@/components/scooters/ScootersDetail34";
import ScootersDetail37 from "@/components/scooters/ScootersDetail37";
import UnknownRoute from "@/components/UnknownRoute";




const routes = [
    {path: '/', redirect: '/home'}, // Redirect from '/' to '/home'
    {path: '/home', name: 'Home', component: WelcomeComponent},
    {path: '/scooters/overview31', name: 'Overview31', component: ScootersOverview31},
    {path: '/scooters/overview32', name: 'Overview32', component: ScootersOverview32},
    {path: '/scooters/overview33', name: 'Overview33', component: ScootersOverview33, children: [
            {path: ':id', component: ScootersDetail32},
        ],
    },
    {path: '/scooters/overview34', name: 'Overview34', component: ScootersOverview34, children: [
            {path: ':id', component: ScootersDetail34}
        ],
    },
    {path: '/scooters/overview37', name: 'Overview37', component: ScootersOverview37, children: [
            {path: ':id', component: ScootersDetail37}
        ],
    },
    {path: '/:pathMatch(.*)*', component: UnknownRoute}, // Catch-all route for unknown routes
];

export const router = createRouter({
    history: createWebHashHistory(),
    routes
});
