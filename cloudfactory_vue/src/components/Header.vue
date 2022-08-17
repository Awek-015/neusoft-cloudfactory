<template>
    <div style="height: 50px;background-color: #545c64; line-height: 50px; border-bottom: 1px solid #ffffff; display: flex">
        <div style="width: 200px; padding-left: 30px; font-weight: bold; color: dodgerblue;font-size: 20px">Neusoft</div>
        <div style="flex: 1"></div>
        <div style="width: 100px">
            <el-dropdown >
        <span class="el-dropdown-link" style="color: dodgerblue">Hi,{{user.user_account}}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
                <template #dropdown>
                    <el-dropdown-menu>
                        <el-dropdown-item @click.native="exitPage">exit</el-dropdown-item>
                    </el-dropdown-menu>
                </template>
            </el-dropdown>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Header",
        data() {

            return {
                user:{}

            }
        },
        created(){
            let str = sessionStorage.getItem("user")
            this.user =JSON.parse(str)
        },
        methods:{
            exitPage(){
                console.log("Exit");
                sessionStorage.removeItem("user");
                this.$router.push({ path: "/Login"});
            }
        }

    }
</script>

<style scoped>

</style>
