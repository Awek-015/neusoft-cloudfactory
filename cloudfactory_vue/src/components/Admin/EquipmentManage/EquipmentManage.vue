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
            <el-button style="margin: 10px "type="primary" @click="showaddeDialog">Add</el-button>
          </div>

          <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)"border stripe max-height="690" style="width: 1200px">
            <el-table-column prop="id" label="ID" sortable ></el-table-column>
            <el-table-column prop="num" label="Equip Code" ></el-table-column>
            <el-table-column prop="name" label="Equip Name" ></el-table-column>
            <el-table-column prop="type" label="Equip Type" ></el-table-column>
            <el-table-column prop="details" label="Equip Info" ></el-table-column>
            <el-table-column prop="state" label="Equip State" ></el-table-column>
            <el-table-column prop="factory" label="Belong" ></el-table-column>
            <el-table-column prop="leasestate" label="Rent State" ></el-table-column>

            <el-table-column fixed="right" label="Operation" width="300" prop="switch" >
              <template slot-scope="scope">
                <el-tooltip :content="'Current State:'+scope.row.switch" placement="top">
                  <el-switch
                      v-model="scope.row.switch"
                      active-value="开机"
                      inactive-value="关机"
                      :disabled="scope.row.leasestate=='已租用'||scope.row.state=='运行'"
                      style="margin: 0 10px"
                      @change="changeState($event,scope.row.id)"
                  ></el-switch>
                </el-tooltip>
                <el-button type="primary" size="small":disabled="scope.row.factory!='产能中心'||scope.row.leasestate=='已租用'||scope.row.state=='运行'"@click="open(scope.row)">Edit</el-button>
                <el-button type="danger" size="small":disabled="scope.row.factory!='产能中心'||scope.row.leasestate=='已租用'||scope.row.state=='运行'"@click="deleteById(scope.row.id)" >Delete</el-button>

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
      <el-dialog title="Add Equip" :visible.sync="addeDialog">
        <el-form :model="addform">
          <el-form-item label="Equip Code" :label-width="formLabelWidth" prop="e_num">
            <el-input v-model="addform.e_num" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="Equip Name" :label-width="formLabelWidth" prop="e_name">
            <el-input v-model="addform.e_name" autocomplete="off"></el-input>
          </el-form-item>

          <el-form-item label="Equip Type" :label-width="formLabelWidth" prop="e_type">
            <el-select v-model="addform.e_type" placeholder="choose">
              <el-option
                  v-for="item in itemList"
                  :key="item.et_id"
                  :label="item.et_name"
                  :value="item.et_id">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="Equip Intro" :label-width="formLabelWidth" prop="e_details">
            <el-input v-model="addform.e_details" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addeDialog = false">Cancel</el-button>
          <el-button type="primary" @click="adde">OK</el-button>
        </div>
      </el-dialog>
    </div>



    <!--修改设备类别对话框-->
    <div>
      <el-dialog title="Edit Equip" :visible.sync="updateeDialog">
        <el-form :model="updateform">
          <el-form-item label="Equip ID" :label-width="formLabelWidth" prop="e_id">
            <el-input v-model="updateform.e_id" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="Equip Code" :label-width="formLabelWidth" prop="e_id">
            <el-input v-model="updateform.e_num" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="Equip Name" :label-width="formLabelWidth" prop="e_id">
            <el-input v-model="updateform.e_name" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="Equip Info" :label-width="formLabelWidth" prop="e_id">
            <el-input v-model="updateform.e_details" autocomplete="off" ></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="updateeDialog = false">Cancel</el-button>
          <el-button type="primary" @click="updatee">OK</el-button>
        </div>
      </el-dialog>
    </div>

  </div>
</template>

<script>
import Header from "../../Header";
import Aside from "./../A-Aside";
export default {
  name: "EquipmentManage",
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
      switch:true,
      updateeDialog:false,
      addeDialog:false,
      itemList:[],
      addform:{
        e_num:'' ,
        e_name: '',
        e_type:'',
        e_details: '',
        e_factory:0
      },updateform:{
        e_id:'',
        e_num:'',
        e_name:'',
        e_details:''
      },
      formLabelWidth: '120px',
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
      this.tableData.length=0
      const {data:res} = await this.$http.get("ae?s="+this.s);
      let equipmentList = res.equipmentList;
      let equipment_typeList = res.equipment_typeList;
      let factoryList = res.factoryList;
      let ei=0;
      let eti=0;
      let fi=0;
      let typename;
      let factoryname;
      for(ei=0;ei<equipmentList.length;ei++){
        for(eti=0;eti<equipment_typeList.length;eti++){
          if(equipment_typeList[eti]["et_id"]==equipmentList[ei]["e_type"]){
            typename=equipment_typeList[eti]["et_name"];
            break;
          }
        }
        for(fi=0;fi<factoryList.length;fi++){
          if(equipmentList[ei]["e_factory"]===0){
            factoryname="产能中心";
          }else if(factoryList[fi]["f_id"]==equipmentList[ei]["e_factory"]){
            factoryname=factoryList[fi]["f_name"];
            break;
          }
        }
        let list ={
          id:equipmentList[ei]["e_id"],
          num:equipmentList[ei]["e_num"],
          name:equipmentList[ei]["e_name"],
          type:typename,
          details:equipmentList[ei]["e_details"],
          state:equipmentList[ei]["e_state"],
          factory:factoryname,
          leasestate:equipmentList[ei]["e_leasestate"],
          switch:equipmentList[ei]["e_state"]!="关机"?"开机":"关机"
        }
        this.tableData.push(list)
      }
    },async changeState(state,id){
      let e_state= state=="关机"?"关机" :"空闲"
      await this.$http.get("aeswitch?e_state="+e_state+"&&e_id="+id);
      this.getList()

    },async deleteById(e_id){
      const confirmResult = await this.$confirm("Sure to delete?",'Attention',{
        ConfirmButtonText:'OK',
        cancelButtonText:'Cancel',
        type:'warning'
      }).catch(err =>err)
      if(confirmResult!='confirm'){
        return this.$message.info("Canceled")
      }
      const {data:res}=await this.$http.get("aedelete?e_id="+e_id);
      if(res!="success"){
        return this.$message.error("Failure")
      }
      this.$message.success("Success");
      this.getList();
    },async showaddeDialog(){

      this.addeDialog=true;
      const {data:res} = await this.$http.get("aet?s="+this.s);
      this.itemList=res.list

    },async adde(){
      const {data:res} =await this.$http.post("aeadd",this.addform)

      if(res!="success"){
        return this.$message.error("Failure")
      }
      this.$message.success("Success");
      this.addeDialog = false;
      this.getList();
    },open(row){
      this.updateeDialog = true;
      this.updateform.e_id=row.id
      this.updateform.e_num=row.num
      this.updateform.e_name=row.name
      this.updateform.e_details=row.details

    },async updatee(){
      const {data:res} = await this.$http.post("aeupdate",this.updateform)
      if(res!="success"){
        return this.$message.error("Failure！")
      }
      this.$message.success("Success");
      this.updateeDialog = false;
      this.getList();
    },


  }
}
</script>

<style scoped>

</style>