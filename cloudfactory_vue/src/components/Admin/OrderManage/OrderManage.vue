<template>
        <div>
            <Header/>
            <div style="display: flex">
                <Aside/>
                <div style="padding: 10px ">
                    <div style="margin: 10px 0">
                        <el-input v-model="s" placeholder="Enter ID" style="width: 20%;min-width: 200px" clearable></el-input>
                        <el-button type="primary" style="margin-left: 5px" @click="getList" >Search</el-button>
                    </div>

                    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" border stripe style="width: 1200px" max-height="690">
                        <el-table-column prop="o_id" label="ID" sortable></el-table-column>
                        <el-table-column prop="o_num" label="ID Code" ></el-table-column>
                        <el-table-column prop="o_pname" label="Product Name" ></el-table-column>
                        <el-table-column prop="o_amount" label="Order Num" ></el-table-column>
                        <el-table-column prop="o_delivery" label="Delivery Date" ></el-table-column>
                        <el-table-column prop="o_ddl" label="DeadLine" ></el-table-column>
                        <el-table-column prop="u_name" label="Consignee" ></el-table-column>
                        <el-table-column prop="u_phone" label="Phone"></el-table-column>
                        <el-table-column prop="u_address" label="Address" ></el-table-column>
                        <el-table-column prop="o_state" label="State" ></el-table-column>

<!--                        <el-table-column-->
<!--                                fixed="right"-->
<!--                                label="Operation"-->
<!--                                width="100">-->
<!--                            &lt;!&ndash;<template slot-scope="scope">-->
<!--                                <el-button type="primary" size="small"  @click="deleteById(scope.row.user_id)">Delete</el-button>-->
<!--                            </template>&ndash;&gt;-->
<!--                        </el-table-column>-->

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
        name: "OrderManage",
        components:{
            Aside,
            Header
        },
        data(){
            return{
              currentPage: 1, // 当前页码
              total: 20, // 总条数
              pageSize: 5,// 每页的数据条数
                s:'',
                tableData:[],
                plist:[],

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
                const {data:res} = await this.$http.get("ao?s="+this.s);
                let olist = res.olist;
                let plist = res.plist;

                this.plist=plist;
                for(let i=0; i<olist.length; i++){
                    let o = olist[i];

                    for(let j=0; j<plist.length; j++){
                        let p = plist[j];
                        if(o.o_pid==p.p_id&&o.o_state!="未保存"){
                            let list = {
                                o_id:o.o_id,
                                o_num:o.o_num,
                                o_pname:p.p_name,
                                o_amount:o.o_amount,
                                o_delivery:o.o_delivery,
                                o_ddl:o.o_ddl,
                                u_name:o.u_name,
                                u_phone:o.u_phone,
                                u_address:o.u_address,
                                o_state:o.o_state
                            }
                            console.log(o.o_state)
                            this.tableData.push(list)
                            break
                        }
                    }
                }
            },
           async changeState(state,f_id){
                let f_state=state?"开启" :"关停"
               await this.$http.get("afswitch?f_state="+f_state+"&&f_id="+f_id);
                this.getList()

    }

        }
    }
</script>

<style scoped>

</style>