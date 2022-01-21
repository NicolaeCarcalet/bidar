<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
:root {
  --header-height: 3rem;
  --nav-width: 68px;
  --first-color: #4723D9;
  --first-color-light: #AFA5D9;
  --white-color: #F7F6FB;
  --body-font: 'Nunito', sans-serif;
  --normal-font-size: 1rem;
  --z-fixed: 100
}
*,
::before,
::after {
  box-sizing: border-box
}
</style>
