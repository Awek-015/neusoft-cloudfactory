<template>
    <div>
        <el-menu  class="el-menu-vertical-demo" router unique-opened
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b">
            <el-menu-item index="dealer">Home</el-menu-item>
            <el-submenu index="1">
                <span slot="title">Order Management</span>
                <el-menu-item index="do">My Order</el-menu-item>
            </el-submenu>


        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "D-Aside"
    }
</script>

<style scoped >
    .el-menu-vertical-demo{
        min-height: calc(100vh - 50px);
        width: 200px;
    }

</style>