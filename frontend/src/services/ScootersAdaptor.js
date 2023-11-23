import {Scooter} from "@/models/scooter";

export class ScootersAdaptor {

    static REQUEST_TYPE = {
        GET: "GET",
        POST: "POST",
        PUT: "PUT",
        DELETE: "DELETE"
    }

    _resourcesUrl;

    constructor(resourcesUrl) {
        this._resourcesUrl = resourcesUrl;
        console.log(`Created ScootersAdaptor for: ${resourcesUrl}`);
    }

    instantiateScooter(scooter) {
        if (scooter == null) {
            return {};
        }

        if (typeof scooter.gpsLocation === "string") {
            scooter.gpsLocation = {
                latitude: scooter.gpsLocation.split(",")[0],
                longitude: scooter.gpsLocation.split(",").pop()
            };
        }

        return Scooter.copyConstructor(scooter);
    }

    async fetchJson(url, options = {}) {
        let response = await fetch(url, options);
        if (response.ok) {
            return await response.json();
        }

        console.error(
            "Response:",
            "\n\t", response,
            "\n\t", !response.bodyUsed ? await response.text() : ""
        );
        return null;
    }

    async asyncFindAll() {
        console.log(`ScooterAdaptor.asyncFindAll()...`);
        const scooters = await this.fetchJson(this._resourcesUrl);
        return scooters?.map(scooter => this.instantiateScooter(scooter));
    }

    async asyncFindById(id) {
        const foundByIdScooter = await this.fetchJson(
            `${this._resourcesUrl}/${id}`,
            {
                Method: ScootersAdaptor.REQUEST_TYPE.GET
            });

        return this.instantiateScooter(foundByIdScooter);
    }

    async asyncSave(scooter) {
        // get url and update if necessary
        let url = `${this._resourcesUrl}/${scooter.id}`;
        let addOrUpdate = ScootersAdaptor.REQUEST_TYPE.PUT;
        if (scooter.id === 0) {
            addOrUpdate = ScootersAdaptor.REQUEST_TYPE.POST;
            url = url.substring(0, url.lastIndexOf("/"));
        }

        // make request to backend
        console.log(`Save (${addOrUpdate}): ${scooter.id}`);
        const savedScooter = await this.fetchJson(url, {
            method: addOrUpdate,
            body: scooter.toStringJson(),
            headers: {
                "Content-type": "application/json; charset=UTF-8"
            }
        });

        // sanitize before sending scooter back
        return this.instantiateScooter(savedScooter);
    }

    async asyncDeleteById(id) {
        console.log(`Delete: ${id}`);
        const deletedScooter = await this.fetchJson(`${this._resourcesUrl}/${id}`, {
            method: ScootersAdaptor.REQUEST_TYPE.DELETE
        });

        return this.instantiateScooter(deletedScooter);
    }
}
