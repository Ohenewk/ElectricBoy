import {createRouter, createWebHashHistory} from "vue-router";
import WelcomeComponent from "@/components/WelcomeComponent";
import ScootersOverview31 from "@/components/scooters/ScootersOverview31";
import ScootersOverview32 from "@/components/scooters/ScootersOverview32";
import ScootersOverview33 from "@/components/scooters/ScootersOverview33";
import ScootersDetail32 from "@/components/scooters/ScootersDetail32";
import UnknownRoute from "@/components/UnknownRoute";

const routes = [
    {path: '/', redirect: '/home'}, // Redirect from '/' to '/home'
    {path: '/home', name: 'Home', component: WelcomeComponent},
    {path: '/scooters/overview31', name: 'Overview31', component: ScootersOverview31},
    {path: '/scooters/overview32', name: 'Overview32', component: ScootersOverview32},
    {
        path: '/scooters/overview33',
        name: 'Overview33',
        component: ScootersOverview33,
        children: [
            // Add a child route ':id'
            {path: ':id', component: ScootersDetail32},
        ],
    },
    {path: '/:pathMatch(.*)*', component: UnknownRoute}, // Catch-all route for unknown routes
];

export const router = createRouter({
    history: createWebHashHistory(),
    routes
});
