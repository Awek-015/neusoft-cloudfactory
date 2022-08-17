<template>
    <div>
        <div>
            <Header/>
            <div style="display: flex">
                <Aside/>
                <div style="padding: 10px ">


                    <div style="margin: 10px 0">
                        <el-input v-model="s" placeholder="Enter Code" style="width: 20%;min-width: 200px" clearable></el-input>
                        <el-button type="primary" style="margin-left: 5px" @click="getList" >Search</el-button>
                        <el-button style="margin: 10px "type="primary" @click="showaddpDialog">Add</el-button>
                    </div>

                    <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" border stripe style="width: 1200px" max-height="690">
                        <el-table-column prop="p_id" label="ID" sortable ></el-table-column>
                        <el-table-column prop="p_num" label="Product Code" ></el-table-column>
                        <el-table-column prop="p_name" label="Product Name" ></el-table-column>
                        <el-table-column prop="p_type" label="Product Type" ></el-table-column>
                        <el-table-column prop="p_details" label="Product Info" ></el-table-column>

                        <el-table-column
                                fixed="right"
                                label="Operation"
                                width="100">
                            <template slot-scope="scope">
                                <el-button type="text" size="small"  @click="deleteById(scope.row.p_id)">Delete</el-button>
                                <el-button type="text" size="small" @click="open(scope.row)">Edit</el-button>
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
        <!--添加对话框-->

        <div>
            <el-dialog title="Add Product" :visible.sync="addpDialog">
                <el-form :model="addform">
                    <el-form-item label="Product Code" :label-width="formLabelWidth" prop="p_num">
                        <el-input v-model="addform.p_num" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="Product Name" :label-width="formLabelWidth" prop="p_name">
                        <el-input v-model="addform.p_name" autocomplete="off"></el-input>
                    </el-form-item>

                    <el-form-item label="Product Type" :label-width="formLabelWidth" prop="p_type">
                    <el-select v-model="addform.p_type" placeholder="Choose">
                        <el-option
                                v-for="item in itemList"
                                :key="item.pt_id"
                                :label="item.pt_name"
                                :value="item.pt_id">
                        </el-option>
                    </el-select>
                    </el-form-item>
                    <el-form-item label="Product Info" :label-width="formLabelWidth" prop="p_details">
                        <el-input v-model="addform.p_details" autocomplete="off"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="addpDialog = false">Cancel</el-button>
                    <el-button type="primary" @click="addp">OK</el-button>
                </div>
            </el-dialog>
        </div>



        <!--修改对话框-->
        <div>
            <el-dialog title="Edit Product" :visible.sync="updatepDialog">
                <el-form :model="updateform">
                    <el-form-item label="ID" :label-width="formLabelWidth" prop="p_id">
                        <el-input v-model="updateform.p_id" autocomplete="off" disabled></el-input>
                    </el-form-item>
                    <el-form-item label="Product Code" :label-width="formLabelWidth" prop="p_num">
                        <el-input v-model="updateform.p_num" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="Product Name" :label-width="formLabelWidth" prop="p_name">
                        <el-input v-model="updateform.p_name" autocomplete="off" ></el-input>
                    </el-form-item>
                    <el-form-item label="Product Info" :label-width="formLabelWidth" prop="p_details">
                        <el-input v-model="updateform.p_details" autocomplete="off" ></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="updatepDialog = false">Cancel</el-button>
                    <el-button type="primary" @click="updatep">OK</el-button>
                </div>
            </el-dialog>
        </div>

    </div>
</template>

<script>
    import Header from "../../Header";
    import Aside from "./../A-Aside";

    export default {
        name: "ProductManage",
        components:{
            Aside,
            Header
        },data(){
            return{
              currentPage: 1, // 当前页码
              total: 20, // 总条数
              pageSize: 10,// 每页的数据条数

              s:'',
                tableData:[],
                updatepDialog:false,
                addpDialog:false,
                itemList:[],
                addform:{
                    p_num:'' ,
                    p_name: '',
                    p_type:'',
                    p_details: ''
                },
                updateform:{
                  p_id:'',
                  p_num:'',
                  p_name:'',
                  p_details:''
                },
                formLabelWidth: '120px'
            }
        },created() {
            this.getList()
        },methods:{
            async getList(){
                this.tableData.length=0
                const {data:res} = await this.$http.get("ap?s="+this.s);
                let productList = res.productList;
                let productTypeList = res.productTypeList;

                for(let j=0; j<productList.length; j++){
                    let i=0
                    let p = productList[j];
                    for(i=0;i<productTypeList.length;i++){
                        if(productTypeList[i]["pt_id"]==p.p_type) break
                    }

                    let list = {
                        p_id:p.p_id,
                        p_num:p.p_num,
                        p_name:p.p_name,
                        p_type:productTypeList[i]["pt_name"],
                        p_details:p.p_details
                    }
                    this.tableData.push(list)
                }
            },async deleteById(p_id){
                const confirmResult = await this.$confirm("Sure to delete?",'Attention',{
                    ConfirmButtonText:'OK',
                    cancelButtonText:'Cancel',
                    type:'warning'
                }).catch(err =>err)
                if(confirmResult!='confirm'){
                    return this.$message.info("Canceled")
                }
                const {data:res}=await this.$http.get("apdelete?p_id="+p_id);
                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.getList();
            },
            async showaddpDialog(){

                this.addpDialog=true;
                const {data:res} = await this.$http.get("apt?s="+this.s);
                this.itemList=res.list
                console.log(this.option)
            },
            async addp(){
                const {data:res} =await this.$http.post("apadd",this.addform)

                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.addpDialog = false;
                this.getList();
            },open(row){
                this.updatepDialog = true;
                this.updateform.p_id=row.p_id
                this.updateform.p_num=row.p_num
                this.updateform.p_name=row.p_name
                this.updateform.p_details=row.p_details
            },async updatep(){
                const {data:res} = await this.$http.post("apupdate",this.updateform)
                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.updatepDialog = false;
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