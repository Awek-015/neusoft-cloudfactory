<template>
    <div>
        <el-menu  class="el-menu-vertical-demo"  router unique-opened
                  background-color="#545c64"
                  text-color="#fff"
                  active-text-color="#ffd04b">
            <el-menu-item index="admin">Home</el-menu-item>

            <el-submenu index="1">
                <span slot="title">System Setup</span>
                <el-menu-item index="au">User Management</el-menu-item>
            </el-submenu>

            <el-submenu index="2">
                <span slot="title">CloudFactory</span>
                <el-menu-item index="af">CloudFactory Info</el-menu-item>
            </el-submenu>

            <el-submenu index="3">
                <span slot="title">Product Management</span>
                <el-menu-item index="apt" >Type Management</el-menu-item>
                <el-menu-item index="ap">Info Management</el-menu-item>
            </el-submenu>

            <el-submenu index="4">
                <span slot="title">Productivity Center</span>
                <el-menu-item index="aet">Type Management</el-menu-item>
                <el-menu-item index="ae">Info Management</el-menu-item>
            </el-submenu>

            <el-submenu index="5">
                <span slot="title">Order Management</span>
                <el-menu-item index="ao">Order Info</el-menu-item>
            </el-submenu>

        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "A-Aside"
    }
</script>

<style scoped >
    .el-menu-vertical-demo{
        min-height: calc(100vh - 50px);
        width: 200px;
    }

</style>