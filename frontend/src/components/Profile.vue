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
                <input
                  type="text"
                  class="form-control"
                  name="first_name"
                  id="first_name"
                  placeholder="First name"
                  v-model="firstName"
                />
                <label for="last_name">Last Name</label>
                <input
                  type="text"
                  class="form-control"
                  name="last_name"
                  id="last_name"
                  placeholder="Last name"
                  v-model="lastName"
                />
                <label for="country">Country</label>
                <select class="form-select" v-model="country">
                  <option value="Germany">Germany</option>
                  <option value="Switzerland">Switzerland</option>
                  <option value="Romania">Romania</option>
                </select>
                <label for="age">Age</label>
                <input
                  type="number"
                  class="form-control"
                  name="age"
                  id="age"
                  placeholder="Age"
                  v-model="age"
                />
                <button
                  id="login"
                  class="btn btn-block btn-sm btn-primary mt-2"
                  type="button"
                  @click="submit"
                >
                  Submit
                </button>
              </div>
            </form>
          </div>
          <div class="col-md-4">
            <form>
              <div class="form-group">
                <h4>Skills</h4>
                <div class="row">

                  <div class="col-md-4">
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Adaptability" id="Adaptability" v-model="skills">
                      <label class="form-check-label" for="Adaptability">
                        Adaptability
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Creativity" id="Creativity" v-model="skills">
                      <label class="form-check-label" for="Creativity">
                        Creativity
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Self-motivation" id="Self-motivation" v-model="skills">
                      <label class="form-check-label" for="Self-motivation">
                        Self-motivation
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Empathy" id="Empathy" v-model="skills">
                      <label class="form-check-label" for="Empathy">
                        Empathy
                      </label>
                    </div>
                  </div>

                  <div class="col-md-4">
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Collaboration" id="Collaboration" v-model="skills">
                      <label class="form-check-label" for="Collaboration">
                        Collaboration
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Positivity" id="Positivity" v-model="skills">
                      <label class="form-check-label" for="Positivity">
                        Positivity
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Leadership" id="Leadership" v-model="skills">
                      <label class="form-check-label" for="Leadership">
                        Leadership
                      </label>
                    </div>
                    <div class="col-md-3">
                    <input class="form-check-input" type="checkbox" value="Time management" id="Time management" v-model="skills">
                    <label class="form-check-label" for="Time management">
                      Time management
                    </label>
                  </div>
                  </div>

                  <div class="col-md-4">
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Communication" id="Communication" v-model="skills">
                      <label class="form-check-label" for="Communication">
                        Communication
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Work ethic" id="Work ethic" v-model="skills">
                      <label class="form-check-label" for="Work ethic">
                        Work ethic
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Multitasking" id="Multitasking" v-model="skills">
                      <label class="form-check-label" for="Multitasking">
                        Multitasking
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Problem solving" id="Problem solving" v-model="skills">
                      <label class="form-check-label" for="Problem solving">
                        Problem solving
                      </label>
                    </div>
                  </div>
                  <input id="custom_skill" type="text" class="form-control mt-3" placeholder="custom skill" v-model="custom_skill">
                </div>
                <button
                  id="submit-skills"
                  class="btn btn-block btn-sm btn-primary mt-2"
                  type="button"
                  @click="submitSkills"
                >
                  Submit
                </button>
              </div>
            </form>
          </div>
          <div class="col-md-4">
            <form>
              <div class="form-group">
                <h4>Interests</h4>
                <div class="row">
                  <div class="col-md-4">
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Music" id="Music" v-model="interests">
                      <label class="form-check-label" for="Music">
                        Music
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Art" id="Art" v-model="interests">
                      <label class="form-check-label" for="Art">
                        Art
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Foreign languages" id="Foreign languages" v-model="interests">
                      <label class="form-check-label" for="Foreign languages">
                        Foreign languages
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Soccer" id="Soccer" v-model="interests">
                      <label class="form-check-label" for="Soccer">
                        Soccer
                      </label>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Gaming" id="Gaming" v-model="interests">
                      <label class="form-check-label" for="Gaming">
                        Gaming
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Nature" id="Nature" v-model="interests">
                      <label class="form-check-label" for="Nature">
                        Nature
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Research" id="Research" v-model="interests">
                      <label class="form-check-label" for="Research">
                        Research
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Volleyball" id="Volleyball" v-model="interests">
                      <label class="form-check-label" for="Volleyball">
                        Volleyball
                      </label>
                    </div>
                  </div>
                  <div class="col-md-4">
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Travel" id="Travel" v-model="interests">
                      <label class="form-check-label" for="Travel">
                        Travel
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Social causes" id="Social causes" v-model="interests">
                      <label class="form-check-label" for="Social causes">
                        Social causes
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="History" id="History" v-model="interests">
                      <label class="form-check-label" for="History">
                        History
                      </label>
                    </div>
                    <div class="col-md-3">
                      <input class="form-check-input" type="checkbox" value="Badminton" id="Badminton" v-model="interests">
                      <label class="form-check-label" for="Badminton">
                        Badminton
                      </label>
                    </div>
                  </div>
                  <input id="custom_interest" type="text" class="form-control mt-3" placeholder="custom interest" style="margin-left: 10px;" v-model="custom_interest">
                </div>
                <button
                  id="submit-interests"
                  class="btn btn-block btn-sm btn-primary mt-2"
                  type="button"
                  @click="submitInterests"
                >
                  Submit
                </button>
              </div>
            </form>
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
  components: { DashboardStructure },
  data() {
    return {
      firstName: "",
      lastName: "",
      country: "",
      age: "",
      skills: [],
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
          console.log(payload);
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
      if(this.custom_skill) {
        this.skills.push(this.custom_skill);
      }
    },
    submitInterests() {
      if(this.custom_interest) {
        this.interests.push(this.custom_interest);
      }
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
