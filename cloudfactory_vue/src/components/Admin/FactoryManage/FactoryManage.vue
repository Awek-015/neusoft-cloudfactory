<template>
        <div>
            <Header/>
            <div style="display: flex">
                <Aside/>
                <div style="padding: 10px ">
                    <div style="margin: 10px 0">
                        <el-input v-model="s" placeholder="Enter Name" style="width: 20%;min-width: 200px" clearable></el-input>
                        <el-button type="primary" style="margin-left: 5px" @click="getList" >Search</el-button>
                    </div>

                    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" border stripe style="width: 1200px" max-height="690">
                        <el-table-column prop="f_id" label="ID" sortable></el-table-column>
                        <el-table-column prop="f_name" label="Factory Name" ></el-table-column>
                        <el-table-column prop="f_detail" label="Factory Info" ></el-table-column>
                        <el-table-column prop="user_name" label="Manager" ></el-table-column>
                        <el-table-column prop="user_phone" label="Phone"></el-table-column>
                        <el-table-column prop="user_account" label="Account" ></el-table-column>

                        <el-table-column prop="f_state" fixed="right" label="State">
                            <template slot-scope="scope">
                                    <el-switch v-model="scope.row.f_state"  active-text="Open" inactive-text="Close" @change="changeState($event,scope.row.f_id)"></el-switch>
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
        name: "FactoryManage",
        components:{
            Aside,
            Header
        },
        data(){
            return{
              currentPage: 1, // 当前页码
              total: 20, // 总条数
              pageSize: 10,// 每页的数据条数
                s:'',
                tableData:[],
                f_state:true

            }
        },
        created() {
            this.getList();
        },
        methods:{
          handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.currentPage = 1;
            this.pageSize = val;
          },
          handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.currentPage = val;
          },
            async getList(){
                this.tableData.length=0
                const {data:res} = await this.$http.get("af?s="+this.s);
                let userList = res.userList;
                let factoryList = res.factoryList;
                for(let i=0; i<factoryList.length; i++){
                    const f = factoryList[i];

                    for(let j=1; j<userList.length; j++){
                        const u = userList[j];
                        if(u.user_factory==f.f_id){
                            let list = {
                                f_id:f.f_id,
                                f_name:f.f_name,
                                f_detail:f.f_detail,
                                user_name:u.user_name,
                                user_phone:u.user_phone,
                                user_account:u.user_account,
                                f_state:f.f_state=="open"
                            }
                            console.log(list.f_state)
                            this.tableData.push(list)
                            break
                        }
                    }
                }
                console.log(this.tableData)
            },
           async changeState(state,f_id){
                let f_state=state?"open" :"close"
               await this.$http.get("afswitch?f_state="+f_state+"&&f_id="+f_id);
                this.getList()

    }

        }
    }
</script>

<style scoped>

</style>