<template>

  <div >
    <body id="poster">
    <Header/>
    <Aside/>
    <span class="Cloud-Factory">Cloud-Factory</span>
    </body>
  </div>
</template>

<script>

import Header from "../Header";
import Aside from "./D-Aside";

export default {
  name: "Layout",
  components: {
    Aside,
    Header
  },
}
</script>
<style scoped>
#poster {
  background: url("/public/cloud.png") no-repeat center;
  height: 100%;
  width: 100%;
  background-size: cover;
  position: fixed;
}
body{
  margin: 0px;
}
  .welcome{
    padding: 0px;
    text-align: center;
    color: dodgerblue;
    font-size: 100px;
    width: 600px; height: 400px;
    position: absolute; left: 60%; top: 60%;

    margin: -200px 0px 0px -300px;
  }
</style>