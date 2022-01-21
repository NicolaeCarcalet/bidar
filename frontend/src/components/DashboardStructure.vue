<template>
  <div>
    <header class="header" id="header">
      <div class="header_toggle">
        <i class="bx bx-menu" id="header-toggle" @click="openNavbar()"></i>
      </div>
      <div class="header_img">
        <img src="https://i.imgur.com/hczKIze.jpg" alt="" />
      </div>
    </header>
    <div class="l-navbar" id="nav-bar">
      <nav class="nav">
        <div>
          <a href="#" class="nav_logo">
            <i class="bx bx-layer nav_logo-icon"></i>
            <span class="nav_logo-name">Bidar</span>
          </a>
          <div class="nav_list">
            <a href="#" :class="explorerPage ? 'nav_link active' : 'nav_link'">
              <i class="bx bx-user nav_icon" @click="goToExplorer"></i>
              <span class="nav_name">Explorer</span>
            </a>
          </div>
          <div class="nav_list"> 
            <a href="#" :class="profilePage ? 'nav_link active' : 'nav_link'">
              <i class="bx bx-user nav_icon" @click="goToProfile"></i>
              <span class="nav_name">Profile</span>
            </a>
          </div>
        </div>
        <a href="#" class="nav_link" @click="logOut()">
          <i class="bx bx-log-out nav_icon"></i>
          <span class="nav_name">Sign Out</span>
        </a>
      </nav>
    </div>
  </div>
</template>

<script>
export default {
  name: "DashboardStructure",
  data() {
    return {
      profilePage: false,
      explorerPage: false
    };
  },
  methods: {
    openNavbar() {
      const toggle = document.getElementById("header-toggle"),
        nav = document.getElementById("nav-bar"),
        bodypd = document.getElementById("body-pd"),
        headerpd = document.getElementById("body-pd");

      nav.classList.toggle("show");
      toggle.classList.toggle("bx-x");
      bodypd.classList.toggle("body-pd");
      headerpd.classList.toggle("body-pd");

      const linkColor = document.querySelectorAll(".nav_link");

      function colorLink() {
        if (linkColor) {
          linkColor.forEach((l) => l.classList.remove("active"));
          this.classList.add("active");
        }
      }
      linkColor.forEach((l) => l.addEventListener("click", colorLink));
    },
    logOut() {
      localStorage.removeItem("auth_token");
      this.$router.push({ name: "Login" });
    },
    goToProfile() {
      this.$router.push({ name: "Profile" });
    },
    goToExplorer() {
      this.$router.push({ name: "Explorer" });
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

    this.profilePage = window.location.href.indexOf("profile") > -1;
    this.explorerPage = window.location.href.indexOf("explorer") > -1;
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
