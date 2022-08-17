<template>
        <div>
            <Header/>
            <div style="display: flex">
                <Aside/>
                <div style="padding: 10px ">
                    <div style="margin: 10px 0">
                        <el-input v-model="s" placeholder="Enter Account" style="width: 20%;min-width: 200px" clearable></el-input>
                        <el-button type="primary" style="margin-left: 5px" @click="getList" >Search</el-button>
                    </div>

                    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                            border stripe style="width: 1200px" max-height="690" >
                        <el-table-column prop="user_id" label="ID" sortable></el-table-column>
                        <el-table-column prop="user_account" label="Account" ></el-table-column>
                        <el-table-column prop="user_password" label="Password" ></el-table-column>
                        <el-table-column prop="user_name" label="Name" ></el-table-column>
                        <el-table-column prop="user_phone" label="Phone"></el-table-column>
                        <el-table-column prop="user_role" label="Type" ></el-table-column>

                        <el-table-column
                                fixed="right"
                                label="Operation"
                                width="100">
                            <template slot-scope="scope">
                                <el-button type="danger" size="small"  @click="deleteById(scope.row.user_id)">Delete</el-button>
                            </template>
                        </el-table-column>

                    </el-table>
                    <!-- 分页器 -->
                    <div class="block" style="margin-top:15px;">
                        <el-pagination align='center' @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="currentPage" :page-sizes="[1,5,10,20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="tableData.length">
                        </el-pagination>
                    </div>

                </div>
            </div>
        </div>
</template>

<script>
    import Header from "../../Header";
    import Aside from "./../A-Aside";
    export default {
        name: "UserManage",
        components:{
            Aside,
            Header
        },
        data(){
            return {
                s:'',
                tableData: [],
                currentPage: 1, // 当前页码
                total: 20, // 总条数
                pageSize: 10// 每页的数据条数

            }
        },
        created() {
            this.getList();
        },
        methods:{
            async getList(){
                this.tableData.length=0
                const {data:res} = await this.$http.get("au?s="+this.s);
                let userList = res.userList;
                let roleList = res.roleList;

                for(let j=0; j<userList.length; j++){
                    const u = userList[j];
                    let list = {
                        user_id:u.user_id,
                        user_account:u.user_account,
                        user_password:u.user_password,
                        user_name:u.user_name,
                        user_phone:u.user_phone,

                        user_role: roleList[u.user_role-1]["role_name"]
                            }
                        this.tableData.push(list)
                    }
            },async deleteById(user_id){
                const confirmResult = await this.$confirm("Sure to delete?？",'Attention',{
                    ConfirmButtonText:'Yes',
                    cancelButtonText:'No',
                    type:'warning'
                }).catch(err =>err)
                if(confirmResult!='confirm'){
                    return this.$message.info("Canceled")
                }
                const {data:res}=await this.$http.get("audelete?user_id="+user_id);
                if(res!="success"){
                    return this.$message.error("Failure！")
                }
                this.$message.success("Success");
                this.getList();
            }, handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
                this.currentPage = 1;
                this.pageSize = val;
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
                this.currentPage = val;
            },

        }
    }
</script>

<style scoped>

</style>