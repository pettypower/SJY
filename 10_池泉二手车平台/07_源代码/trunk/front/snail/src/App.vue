<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import CommonHeader from './components/commonHead'
import CommonFooter from './components/commonFoot'
export default {
  name: 'App',
  components: {
    CommonHeader,
    CommonFooter
  }
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
