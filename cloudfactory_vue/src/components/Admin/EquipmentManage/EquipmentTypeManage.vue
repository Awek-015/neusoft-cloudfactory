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
                    <el-button style="margin: 10px "type="primary" @click="addetDialog = true">Add</el-button>
                </div>

                <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"border stripe style="width: 1200px" max-height="690">
                    <el-table-column prop="et_id" label="ID" sortable ></el-table-column>
                    <el-table-column prop="et_name" label="Equip Type" ></el-table-column>

                    <el-table-column
                            fixed="right"
                            label="Operation"
                            width="100">
                        <template slot-scope="scope">
                            <el-button type="text" size="small"  @click="deleteById(scope.row.et_id)">Delete</el-button>
                            <el-button type="text" size="small" @click="open(scope.row.et_id,scope.row.et_name)">Edit</el-button>
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
        <el-dialog title="Add Equip Type" :visible.sync="addetDialog">
            <el-form :model="addform">
                <el-form-item label="Type Name" :label-width="formLabelWidth" prop="et_name">
                    <el-input v-model="addform.et_name" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addetDialog = false">Cancel</el-button>
                <el-button type="primary" @click="addet">OK</el-button>
            </div>
        </el-dialog>
    </div>

        <!--修改对话框-->
        <div>
            <el-dialog title="Edit Equip Type" :visible.sync="updateetDialog">
                <el-form :model="updateform">
                    <el-form-item label="Type ID" :label-width="formLabelWidth" prop="et_id">
                        <el-input v-model="updateform.et_id" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="Type Name" :label-width="formLabelWidth" prop="et_name">
                        <el-input v-model="updateform.et_name" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="updateetDialog = false">Cancel</el-button>
                    <el-button type="primary" @click="updateet">OK</el-button>
                </div>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import Header from "../../Header";
    import Aside from "./../A-Aside";
    export default {
        name: "EquipmentTypeManage",
        components:{
            Aside,
            Header
        },
        data(){
            return{
              currentPage: 1, // 当前页码
              total: 20, // 总条数
              pageSize: 5,// 每页的数据条数
                addetDialog:false,
                updateetDialog:false,
                s:'',
                tableData:[],
                addform:{
                    et_name:''
                },
                updateform:{
                    et_id:''/*scope.row.pt_id*/,
                    et_name:''/*scope.row.pt_name*/
                },
            formLabelWidth: '120px'
            }
        },created(){
            this.getList();
        }, methods:{
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
               const {data:res} = await this.$http.get("aet?s="+this.s);
               this.tableData=res.list;
            },
            async deleteById(et_id){
                const confirmResult = await this.$confirm("Sure to delete?",'Attention',{
                    ConfirmButtonText:'OK',
                    cancelButtonText:'Cancel',
                    type:'warning'
                }).catch(err =>err)
                if(confirmResult!='confirm'){
                    return this.$message.info("已Cancel")
                }
                const {data:res}=await this.$http.delete("aetdelete?et_id="+et_id);
                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.getList();
            },
            async addet(){
                const {data:res} =await this.$http.post("aetadd",this.addform)

                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.addetDialog = false;
                this.getList();
            },open(et_id,et_name){
                this.updateetDialog = true;
                this.updateform.et_id=et_id
                this.updateform.et_name=et_name
            },
            async updateet(){
                const {data:res} = await this.$http.post("aetupdate",this.updateform)
            if(res!="success"){
                return this.$message.error("Failure")
            }
            this.$message.success("Success");
            this.updateetDialog = false;
            this.getList();
    }

        }
    }
</script>

<style scoped>

</style>