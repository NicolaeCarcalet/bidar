<template>
<div>
    <dashboard-structure></dashboard-structure>
    <!--Container Main start-->
    <div class="height-100 bg-light">
        <div class="container text-center" style="margin-top: 64px">
            <h2>Explorer</h2>

            <div class="row justify-content-center">
                <div class="col-12 col-md-10 col-lg-8">
                    <form class="card card-sm">
                        <div class="card-body row no-gutters align-items-center">
                            <div class="col">
                                <input class="form-control form-control-lg form-control-borderless" type="search" name="search" id="search" placeholder="Explore dbpedia..." v-model="searchTerm" />
                            </div>
                            <div class="col-auto">
                                <select class="form-select" v-model="limit">
                                    <option value="10" selected>10</option>
                                    <option value="100">100</option>
                                    <option value="200">200</option>
                                    <option value="500">500</option>
                                </select>
                            </div>
                            <div class="col-auto">
                                <button class="btn btn-lg btn-primary" type="submit" v-on:click="search">Search</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <h4 class="h4">Search results {{ resources.length }}</h4>
                <div class="card" v-for="(resource, index) in resources">
                    <div class="card-body">
                        <h5 class="card-title">{{ resource.resourceLabel }}</h5>
                        <p class="card-text">
                            {{ resource.resourceAbstract }}
                        </p>
                        <a target="_blank" :href='resource.resourceLink' class="btn btn-primary">{{ resource.resourceLink }}</a>
                    </div>
                </div>
            </div>

            <hr />
            <div class="row">
                <p>Recommendations {{ recommendations.length }}</p>
                <div class="card" v-for="(resource, index) in recommendations">
                    <div class="card-body">
                        <h5 class="card-title">{{ resource.resourceLabel }}</h5>
                        <p class="card-text">
                            {{ resource.resourceAbstract }}
                        </p>
                        <a target="_blank" :href='resource.resourceLink' class="btn btn-primary">{{ resource.resourceLink }}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import DashboardStructure from "./DashboardStructure";
export default {
    name: "Explorer",
    components: {
        DashboardStructure
    },
    data() {
        return {
            searchTerm: "",
            userProfile: {},
            resources: [],
            recommendations: [],
            limit: "",
        };
    },
    methods: {
        getInitialData() {
            let userId = localStorage.getItem("userId");
            this.$http.get(this.$profile + "/profile/" + userId).then((response) => {
                this.userProfile = response.data;
                console.log(this.userProfile);
                for (let skill of this.userProfile.skills) {
                    this.getResources(skill.skillData);
                }
                for (let interest of this.userProfile.interests) {
                    this.getResources(interest.interestData);
                }
            });
        },

        getResources(resource) {
            console.log(`Retrieving skills resources for ${resource}`);
            let payload = {
                queryObject: `dbr:${resource}`,
                countryCode: this.userProfile.countryCode,
                limit: 5,
            };
            this.$http.post(this.$main + "/resources", payload).then((response) => {
                let data = response.data;
                console.log(data);
                for (let userResource of data) {
                    this.resources.push({
                        resourceLink: userResource.resourceMetadata.s,
                        resourceLabel: userResource.resourceMetadata.label,
                        resourceAbstract: userResource.resourceMetadata.abstract,
                    });
                }
            });
        },

        search() {
            let payload = {
                queryObject: `dbr:${this.capitalizeFirstLetter(this.searchTerm)}`,
                countryCode: this.userProfile.countryCode,
                limit: this.limit,
            };
            this.$http.post(this.$main + "/resources", payload).then((response) => {
                let data = response.data;
                console.log(data);
                this.resources = data.map((resource) => {
                    return {
                        resourceLink: resource.resourceMetadata.s,
                        resourceLabel: resource.resourceMetadata.label,
                        resourceAbstract: resource.resourceMetadata.abstract,
                    };
                });
                this.getRecommendations(data);
            });
        },

        capitalizeFirstLetter(string) {
            return string.charAt(0).toUpperCase() + string.slice(1);
        },

        getRecommendations(resources) {
            let payload = {
                currentResources: resources,
                countryCode: this.userProfile.countryCode,
            };
            this.$http
                .post(this.$recommendation + "/recommendation", payload)
                .then((response) => {
                    let data = response.data;
                    this.recommendations = data.map((resource) => {
                        return {
                            resourceLink: resource.recommendationResource,
                            resourceLabel: resource.recommendationLabel,
                            resourceAbstract: resource.recommendationAbstract,
                        };
                    });
                });
        },
    },

    created() {
        $("body").attr(
            "style",
            "position: relative;\n" +
            "    margin: var(--header-height) 0 0 0;\n" +
            "    padding: 0 1rem;\n" +
            "    font-family: var(--body-font);\n" +
            "    font-size: var(--normal-font-size);\n" +
            "    transition: .5s"
        );
        this.getInitialData();
    },
};
</script>

<style scoped>
:root {
    --header-height: 3rem;
    --nav-width: 68px;
    --first-color: #4723d9;
    --first-color-light: #afa5d9;
    --white-color: #f7f6fb;
    --body-font: "Nunito", sans-serif;
    --normal-font-size: 1rem;
    --z-fixed: 100;
}

*,
::before,
::after {
    box-sizing: border-box;
}

body {
    position: relative;
    margin: var(--header-height) 0 0 0;
    padding: 0 1rem;
    font-family: var(--body-font);
    font-size: var(--normal-font-size);
    transition: 0.5s;
}

a {
    text-decoration: none;
}

.header {
    width: 100%;
    height: var(--header-height);
    position: fixed;
    top: 0;
    left: 0;
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 1rem;
    background-color: var(--white-color);
    z-index: var(--z-fixed);
    transition: 0.5s;
}

.header_toggle {
    color: var(--first-color);
    font-size: 1.5rem;
    cursor: pointer;
}

.header_img {
    width: 35px;
    height: 35px;
    display: flex;
    justify-content: center;
    border-radius: 50%;
    overflow: hidden;
}

.header_img img {
    width: 40px;
}

.l-navbar {
    position: fixed;
    top: 0;
    left: -30%;
    width: var(--nav-width);
    height: 100vh;
    background-color: var(--first-color);
    padding: 0.5rem 1rem 0 0;
    transition: 0.5s;
    z-index: var(--z-fixed);
}

.nav {
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    overflow: hidden;
}

.nav_logo,
.nav_link {
    display: grid;
    grid-template-columns: max-content max-content;
    align-items: center;
    column-gap: 1rem;
    padding: 0.5rem 0 0.5rem 1.5rem;
}

.nav_logo {
    margin-bottom: 2rem;
}

.nav_logo-icon {
    font-size: 1.25rem;
    color: var(--white-color);
}

.nav_logo-name {
    color: var(--white-color);
    font-weight: 700;
}

.nav_link {
    position: relative;
    color: var(--first-color-light);
    margin-bottom: 1.5rem;
    transition: 0.3s;
}

.nav_link:hover {
    color: var(--white-color);
}

.nav_icon {
    font-size: 1.25rem;
}

.show {
    left: 0;
}

.body-pd {
    padding-left: calc(var(--nav-width) + 1rem);
}

.active {
    color: var(--white-color);
}

.active::before {
    content: "";
    position: absolute;
    left: 0;
    width: 2px;
    height: 32px;
    background-color: var(--white-color);
}

.height-100 {
    height: 100vh;
}

@media screen and (min-width: 768px) {
    body {
        margin: calc(var(--header-height) + 1rem) 0 0 0;
        padding-left: calc(var(--nav-width) + 2rem);
    }

    .header {
        height: calc(var(--header-height) + 1rem);
        padding: 0 2rem 0 calc(var(--nav-width) + 2rem);
    }

    .header_img {
        width: 40px;
        height: 40px;
    }

    .header_img img {
        width: 45px;
    }

    .l-navbar {
        left: 0;
        padding: 1rem 1rem 0 0;
    }

    .show {
        width: calc(var(--nav-width) + 156px);
    }

    .body-pd {
        padding-left: calc(var(--nav-width) + 188px);
    }

    .bx-x {
        margin-left: 140px;
        transition: 0.5s;
    }
}
</style>
