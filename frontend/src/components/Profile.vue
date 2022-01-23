<template>
<div>
    <dashboard-structure></dashboard-structure>
    <!--Container Main start-->
    <div class="height-100 bg-light">
        <div class="container text-center" style="margin-top: 64px">
            <h4>Profile</h4>
            <div class="row mt-4">
                <div class="col-md-4 text-left">
                    <form>
                        <div class="form-group">
                            <h4>Profile Details</h4>
                            <label for="first_name">First Name</label>
                            <input type="text" class="form-control" name="first_name" id="first_name" placeholder="First name" v-model="firstName" />
                            <label for="last_name">Last Name</label>
                            <input type="text" class="form-control" name="last_name" id="last_name" placeholder="Last name" v-model="lastName" />
                            <label for="country">Country</label>
                            <select class="form-select" v-model="country">
                                <option value="Germany">Germany</option>
                                <option value="Switzerland">Switzerland</option>
                                <option value="Romania">Romania</option>
                            </select>
                            <label for="age">Age</label>
                            <input type="number" class="form-control" name="age" id="age" placeholder="Age" v-model="age" />
                            <button id="login" class="btn btn-block btn-sm btn-primary mt-2" type="button" @click="submit">
                                Submit
                            </button>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <form>
                            <div class="form-group">
                                <h4>Skills</h4>
                                <div class="row" v-if="skills.length > 0">
                                    <ul class="list-group">
                                        <li class="list-group-item" v-for="(skill, index) in skills">
                                            {{ skill.skillData }}
                                            <button type="submit" class="btn btn-primary" @click="deleteSkill(index, skill.skillId)">Delete</button>
                                        </li>
                                    </ul>
                                </div>
                                <p class="text-warning" v-if="skills.length == 0">No skills</p>
                                <input id="custom_skill" type="text" class="form-control mt-3" placeholder="Add your skill" v-model="custom_skill">
                                <button id="submit-skills" class="btn btn-block btn-sm btn-primary mt-2" type="button" @click="submitSkills">
                                    Submit
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-4">
                        <form>
                            <div class="form-group">
                                <h4>Interests</h4>
                                <div class="row" v-if="interests.length > 0">
                                    <ul class="list-group">
                                        <li class="list-group-item" v-for="(interest, index) in interests">
                                            {{ interest.interestData }}
                                            <button type="submit" class="btn btn-primary" @click="deleteInterest(index, interest.interestId)">Delete</button>
                                        </li>
                                    </ul>
                                </div>
                                <p class="text-warning" v-if="interests.length == 0">No interests</p>
                                <input id="custom_interest" type="text" class="form-control mt-3" placeholder="Add your interest" v-model="custom_interest">
                                <button id="submit-interest" class="btn btn-block btn-sm btn-primary mt-2" type="button" @click="submitInterests">
                                    Submit
                                </button>
                            </div>
                        </form>
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
    name: "Profile",
    components: {
        DashboardStructure
    },
    data() {
        return {
            firstName: "",
            lastName: "",
            country: "",
            age: "",
            skills: [{
                skillData: "Test"
            }, {
                skillData: "Another test"
            }],
            custom_skill: "",
            interests: [],
            custom_interest: ""
        };
    },
    methods: {
        getUser() {
            let userId = localStorage.getItem("userId");
            console.log(userId);
            this.$http
                .get(this.$profile + "/profile/" + userId)
                .then((response) => {
                    let payload = response.data;
                    this.firstName = payload.firstName;
                    this.lastName = payload.lastName;
                    this.age = payload.age;
                    switch (payload.country) {
                        case "de":
                            this.country = "Germany";
                            break;
                        case "ro":
                            this.country = "Romania";
                            break;
                        case "sw":
                            this.country = "Switzerland";
                    }

                });

            this.$http.get(this.$profile + "/profile/skill/" + userId)
                .then((response) => {
                    let data = response.data;
                    console.log(`skills ${data}`);
                    this.skills = data;
                });

            this.$http.get(this.$profile + "/profile/interest/" + userId)
                .then((response) => {
                    let data = response.data;
                    console.log(`interests ${data}`);
                    this.interests = data;
                });
        },

        submit() {
            // TODO: Add more countries!!!!!
            let countryCodes = {
                "UK": "en",
                "Germany": "de",
                "Romania": "ro",
                "Switzerland": "de"
            };

            let payload = {
                userId: localStorage.getItem("userId"),
                firstName: this.firstName,
                lastName: this.lastName,
                age: this.age,
                country: countryCodes[this.country]
            }

            this.$http
                .post(this.$profile + "/profile", payload)
                .then((response) => {
                    console.log(response);
                });
        },

        submitSkills() {
            if (this.custom_skill) {
                let userId = localStorage.getItem("userId");
                this.$http.post(this.$profile + "/profile/skill/" + userId, {
                    skillData: this.custom_skill
                }).then(response => {
                    console.log(response);
                    this.skills.push(response.data);
                });
            }
        },

        deleteSkill(arrayIndex, skillId) {
            console.log(`Delete skill ${arrayIndex} ${skillId}`);
            let userId = localStorage.getItem("userId");
            this.$http.delete(this.$profile + "/profile/skill/" + userId + "/" + skillId).then(response => {
                console.log(response.data);
                this.skills = this.removeByIndex(this.skills, arrayIndex);
            });
        },

        submitInterests() {
            if (this.custom_interest) {
                let userId = localStorage.getItem("userId");
                this.$http.post(this.$profile + "/profile/interest/" + userId, {
                    interestData: this.custom_interest
                }).then(response => {
                    this.interests.push(response.data);
                });
            }
        },

        deleteInterest(arrayIndex, interestId) {
            console.log(`Delete interest ${arrayIndex} ${interestId}`);
            let userId = localStorage.getItem("userId");
            this.$http.delete(this.$profile + "/profile/interest/" + userId + "/" + interestId).then(response => {
                console.log(response.data);
                this.interests = this.removeByIndex(this.interests, arrayIndex);
            });
        },

        removeByIndex(array, index) {
            let newArray = [];
            for (let i = 0; i < array.length; i++) {
                if (i != index) {
                    newArray.push(array[i]);
                }
            }
            return newArray;
        }
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
        this.getUser();
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

.form-check-label {
    white-space: nowrap;
}
</style>
