<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-6 login-section-wrapper">
        <div class="brand-wrapper">
          <h2>Bidar</h2>
        </div>
        <div class="login-wrapper my-auto">
          <h1 class="login-title">Log in</h1>
          <form>
            <div class="form-group">
              <label for="email">Username</label>
              <input type="email" name="email" id="email" class="form-control" placeholder="username" v-model="username">
            </div>
            <div class="form-group mb-4">
              <label for="password">Password</label>
              <input type="password" name="password" id="password" class="form-control" placeholder="enter your passsword" v-model="password">
            </div>
            <div class="form-group mb-4" v-if="loginError">
              <div class="alert alert-danger">Credentials invalid</div>
            </div>
            <button id="login" class="btn btn-block login-btn" type="button" @click="login">Login</button>
          </form>
          <p class="login-wrapper-footer-text">Don't have an account? <a style="cursor: pointer;" class="text-reset" @click="goToSignUp">Register here</a></p>
        </div>
      </div>
      <div class="col-sm-6 px-0 d-none d-sm-block">
        <img src="../assets/login.jpeg" alt="login image" class="login-img">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      username: '',
      password: '',
      loginError: false
    }
  },
  methods: {
    login() {
      this.$http.post(this.$hostname + '/auth', {username: this.username, password: this.password}).then((response) => {
        if(response.data !== 'Couldn\'t generate jwt token') {
            localStorage.setItem('auth_token', response.data);
            localStorage.setItem('username', this.username);
            this.$router.push({"name": "Profile"});
        } else {
          this.loginError = true;
        }

      })
    },
    goToSignUp() {
      this.$router.push({name: 'Register'})
    }
  },
  created() {
    $("#app").attr('style', 'font-family: \'Avenir\', Helvetica, Arial, sans-serif !important;\n' +
      '  -webkit-font-smoothing: antialiased !important;\n' +
      '  -moz-osx-font-smoothing: grayscale !important;\n' +
      '  text-align: center !important;\n' +
      '  color: #2c3e50 !important;\n' +
      '  margin-top: 0 !important;')
  }
}
</script>

<!--<style scoped>
html,body{
  background-image: url('http://getwallpapers.com/wallpaper/full/a/5/d/544750.jpg');
  background-size: cover;
  background-repeat: no-repeat;
  height: 100%;
  font-family: 'Numans', sans-serif;
}

.container{
  height: 100%;
  align-content: center;
}

.card{
  height: 370px;
  margin-top: auto;
  margin-bottom: auto;
  width: 400px;
  background-color: rgba(0,0,0,0.5) !important;
}

.social_icon span{
  font-size: 60px;
  margin-left: 10px;
  color: #FFC312;
}

.social_icon span:hover{
  color: white;
  cursor: pointer;
}

.card-header h3{
  color: white;
}

.social_icon{
  position: absolute;
  right: 20px;
  top: -45px;
}

.input-group-prepend span{
  width: 50px;
  background-color: #FFC312;
  color: black;
  border:0 !important;
  height: 36px;
}

input:focus{
  outline: 0 0 0 0  !important;
  box-shadow: 0 0 0 0 !important;

}

.remember{
  color: white;
}

.remember input
{
  width: 20px;
  height: 20px;
  margin-left: 15px;
  margin-right: 5px;
}

.login_btn{
  color: black;
  background-color: #FFC312;
  width: 100px;
}

.login_btn:hover{
  color: black;
  background-color: white;
}

.links{
  color: white;
}

.links a{
  margin-left: 4px;
}
</style>-->
<style scoped>
body {
  font-family: "Karla", sans-serif;
  background-color: #fff;
  min-height: 100vh; }

.brand-wrapper {
  padding-top: 7px;
  padding-bottom: 8px; }
.brand-wrapper .logo {
  height: 25px; }

.login-section-wrapper {
  display: -webkit-box;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  flex-direction: column;
  padding: 68px 100px;
  background-color: #fff; }
@media (max-width: 991px) {
  .login-section-wrapper {
    padding-left: 50px;
    padding-right: 50px; } }
@media (max-width: 575px) {
  .login-section-wrapper {
    padding-top: 20px;
    padding-bottom: 20px;
    min-height: 100vh; } }

.login-wrapper {
  width: 300px;
  max-width: 100%;
  padding-top: 24px;
  padding-bottom: 24px; }
@media (max-width: 575px) {
  .login-wrapper {
    width: 100%; } }
.login-wrapper label {
  font-size: 14px;
  font-weight: bold;
  color: #b0adad; }
.login-wrapper .form-control {
  border: none;
  border-bottom: 1px solid #e7e7e7;
  border-radius: 0;
  padding: 9px 5px;
  min-height: 40px;
  font-size: 18px;
  font-weight: normal; }
.login-wrapper .form-control::-webkit-input-placeholder {
  color: #b0adad; }
.login-wrapper .form-control::-moz-placeholder {
  color: #b0adad; }
.login-wrapper .form-control:-ms-input-placeholder {
  color: #b0adad; }
.login-wrapper .form-control::-ms-input-placeholder {
  color: #b0adad; }
.login-wrapper .form-control::placeholder {
  color: #b0adad; }
.login-wrapper .login-btn {
  padding: 13px 20px;
  background-color: #fdbb28;
  border-radius: 0;
  font-size: 20px;
  font-weight: bold;
  color: #fff;
  margin-bottom: 14px; }
.login-wrapper .login-btn:hover {
  border: 1px solid #fdbb28;
  background-color: #fff;
  color: #fdbb28; }
.login-wrapper a.forgot-password-link {
  color: #080808;
  font-size: 14px;
  text-decoration: underline;
  display: inline-block;
  margin-bottom: 54px; }
@media (max-width: 575px) {
  .login-wrapper a.forgot-password-link {
    margin-bottom: 16px; } }
.login-wrapper-footer-text {
  font-size: 16px;
  color: #000;
  margin-bottom: 0; }

.login-title {
  font-size: 30px;
  color: #000;
  font-weight: bold;
  margin-bottom: 25px; }

.login-img {
  width: 100%;
  height: 100vh;
  -o-object-fit: cover;
  object-fit: cover;
  -o-object-position: left;
  object-position: left; }

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif !important;
  -webkit-font-smoothing: antialiased !important;
  -moz-osx-font-smoothing: grayscale !important;
  text-align: center !important;
  color: #2c3e50 !important;
  margin-top: 0 !important;
}
/*# sourceMappingURL=login.css.map */

</style>
