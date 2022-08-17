<template>
  <div >
    <Header/>
    <Aside/>
    <span class="welcome">NEU CloudFactory</span>

  </div>
</template>

<script>

import Header from "../Header";
import Aside from "./M-Aside";

export default {

  components:{
    Aside,
    Header
  }

}
</script>
<style scoped>
  .welcome{
    padding: 0px;
    text-align: center;
    color: dodgerblue;
    font-size: 80px;
    width: 60px; height: 40px;
    position: absolute; left: 50%; top: 50%;
    /* 高度的一半 */
    /* 宽度的一半 */
    margin: -200px 0px 0px -300px;
  }
</style>