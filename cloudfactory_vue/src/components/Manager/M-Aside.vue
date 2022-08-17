<template>
    <div>
        <el-menu default-active="2" class="el-menu-vertical-demo" router unique-opened
                 background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#ffd04b">
            <el-menu-item index="manager">Home</el-menu-item>
            <el-submenu index="1">
                <span slot="title">My Factory</span>
                <el-menu-item index="me">My Equipment</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <span slot="title">Order Management</span>
                <el-menu-item index="mtk">Order Taking</el-menu-item>
                <el-menu-item index="ms">Order Scheduling</el-menu-item>
            </el-submenu>
        </el-menu>
    </div>
</template>

<script>
    export default {
        name: "M-Aside"
    }
</script>

<style scoped >
    .el-menu-vertical-demo{
        min-height: calc(100vh - 50px);
        width: 200px;
    }

</style>