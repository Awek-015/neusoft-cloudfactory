<template>
    <div>
    <div>
        <Header/>
        <div style="display: flex">
            <Aside/>
            <div style="padding: 10px ">


                <div style="margin: 10px 0">
                    <el-input v-model="s" placeholder="Enter Name" style="width: 20%;min-width: 200px" clearable></el-input>
                    <el-button type="primary" style="margin-left: 5px" @click="getList" >Search</el-button>
                    <el-button style="margin: 10px "type="primary" @click="addptDialog = true">Add</el-button>
                </div>

                <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" border stripe style="width: 1200px" max-height="690">
                    <el-table-column prop="pt_id" label="ID" sortable ></el-table-column>
                    <el-table-column prop="pt_name" label="Product Type" ></el-table-column>

                    <el-table-column
                            fixed="right"
                            label="Operation"
                            width="100">
                        <template slot-scope="scope">
                            <el-button type="text" size="small"  @click="deleteById(scope.row.pt_id)">Delete</el-button>
                            <el-button type="text" size="small" @click="open(scope.row.pt_id,scope.row.pt_name)">Edit</el-button>
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
    <!--添加类别对话框-->

    <div>
        <el-dialog title="Add Product Type" :visible.sync="addptDialog">
            <el-form :model="addform">
                <el-form-item label="Name" :label-width="formLabelWidth" prop="pt_name">
                    <el-input v-model="addform.pt_name" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addptDialog = false">Cancel</el-button>
                <el-button type="primary" @click="addpt">OK</el-button>
            </div>
        </el-dialog>
    </div>

        <!--修改产品类别对话框-->
        <div>
            <el-dialog title="Edit Product Type" :visible.sync="updateptDialog">
                <el-form :model="updateform">
                    <el-form-item label="Type ID" :label-width="formLabelWidth" prop="pt_id">
                        <el-input v-model="updateform.pt_id" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="Type Name" :label-width="formLabelWidth" prop="pt_name">
                        <el-input v-model="updateform.pt_name" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="updateptDialog = false">Cancel</el-button>
                    <el-button type="primary" @click="updatept">OK</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import Header from "../../Header";
    import Aside from "./../A-Aside";
    export default {
        name: "ProductTypeManage",
        components:{
            Aside,
            Header
        },
        data(){
            return{
                addptDialog:false,
                updateptDialog:false,
                s:'',
                tableData:[],
                addform:{
                    pt_name:''
                },
                updateform:{
                    pt_id:''/*scope.row.pt_id*/,
                    pt_name:''/*scope.row.pt_name*/
                },
              currentPage: 1, // 当前页码
              total: 20, // 总条数
              pageSize: 10,// 每页的数据条数
            formLabelWidth: '120px'
            }
        },created(){
            this.getList();
        }, methods:{
            async getList(){
               const {data:res} = await this.$http.get("apt?s="+this.s);
               this.tableData=res.list;
            },
            async deleteById(pt_id){
                const confirmResult = await this.$confirm("Sure to delete？",'Attention',{
                    ConfirmButtonText:'OK',
                    cancelButtonText:'Cancel',
                    type:'warning'
                }).catch(err =>err)
                if(confirmResult!='confirm'){
                    return this.$message.info("Canceled")
                }
                const {data:res}=await this.$http.get("aptdelete?pt_id="+pt_id);
                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.getList();
            },
            async addpt(){
                const {data:res} =await this.$http.post("aptadd",this.addform)

                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.addptDialog = false;
                this.getList();
            },open(pt_id,pt_name){
                this.updateptDialog = true;
                this.updateform.pt_id=pt_id
                this.updateform.pt_name=pt_name
            },
            async updatept(){
                const {data:res} = await this.$http.post("aptupdate",this.updateform)
            if(res!="success"){
                return this.$message.error("Failure")
            }
            this.$message.success("Success");
            this.updateptDialog = false;
            this.getList();
    },
        handleSizeChange(val) {
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