<template >
    <div>

    <div>
        <Header/>
        <div style="display: flex">
            <Aside/>
            <div style="padding: 10px ">


                <div style="margin: 10px 0">

                    <el-popover placement="right" width="1000" trigger="click">
                        <el-table :data="rentlist" height="400">
                            <el-table-column prop="id" label="ID"  sortable ></el-table-column>
                            <el-table-column prop="num" label="Equip Code" ></el-table-column>
                            <el-table-column prop="name" label="Equip Name" ></el-table-column>
                            <el-table-column prop="details" label="Equip Info" ></el-table-column>
                            <el-table-column prop="time" label="Time" width="400">
                                <template slot-scope="scope">
                                    <el-date-picker
                                            v-model="scope.row.time"
                                            type="daterange"
                                            range-separator="to"
                                            start-placeholder="Start"
                                            end-placeholder="End"
                                            value-format="yyyy-MM-dd">
                                    </el-date-picker>
                                </template>
                            </el-table-column>
                            <el-table-column
                                    fixed="right"
                                    label="Operation"
                                    width="100">
                                <template slot-scope="scope">
                                    <el-button type="primary" size="small" @click="rent(scope.row)" >Rent</el-button>
                                </template>
                            </el-table-column>
                        </el-table>
                        <el-button style="margin: 10px "type="primary" slot="reference" @click="getrentList">Rent</el-button>
                    </el-popover>
                    <el-input v-model="s" placeholder="Enter Name" style="width: 20%;min-width: 200px" clearable></el-input>
                    <el-button type="primary" style="margin-left: 5px" @click="getList" >Search</el-button>
                    <el-button style="margin: 10px "type="primary" @click="showaddeDialog">Add</el-button>
                </div>

                <el-table :data="tableData" border stripe max-height="690" style="width: 1200px">
                    <el-table-column prop="id" label="ID" sortable ></el-table-column>
                    <el-table-column prop="num" label="Equip Code" ></el-table-column>
                    <el-table-column prop="name" label="Equip Name" ></el-table-column>
                    <el-table-column prop="type" label="Equip Type" ></el-table-column>
                    <el-table-column prop="details" label="Equip Info" ></el-table-column>
                    <el-table-column prop="state" label="Current State" ></el-table-column>
                    <el-table-column prop="factory" label="Belong" ></el-table-column>
                    <el-table-column prop="leasestate" label="Rent State" ></el-table-column>

                    <el-table-column fixed="right" label="Operation" width="300" prop="switch" >
                        <template slot-scope="scope">
                            <el-tooltip :content="'current state:'+scope.row.switch" placement="top">
                                <el-switch
                                        v-model="scope.row.switch"
                                        active-value="开机"
                                        inactive-value="关机"
                                        :disabled="scope.row.leasestate=='已租用'||scope.row.state=='运行'"
                                        style="margin: 0 10px"
                                        @change="changeState($event,scope.row.id)"
                                ></el-switch>
                            </el-tooltip>
                           <el-button type="primary" size="small":disabled="scope.row.factory=='产能中心'||scope.row.leasestate=='已租用'||scope.row.state=='运行'"@click="open(scope.row)">Edit</el-button>
                            <el-button type="danger" size="small":disabled="scope.row.factory=='产能中心'||scope.row.leasestate=='已租用'||scope.row.state=='运行'"@click="deleteById(scope.row.id)" >Delete</el-button>
                            <el-button type="info" size="small" @click="showthroughput(scope.row)">Config</el-button>
                        </template>
                    </el-table-column>

                </el-table>
            </div>
        </div>
    </div>
    <!--添加设备对话框-->
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
                    <el-form-item label="Equip Info" :label-width="formLabelWidth" prop="e_details">
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

        <!--配置产能列表对话框-->

        <div>
            <el-dialog title="Config Productivity" :visible.sync="throughputDialog" style="width: 1550px">
                <el-form :model="throughputform" style="width: 720px">
                <el-row>
                    <el-col :span=120>
                            <el-form-item label="ID" prop="e_id" label-width="60px">
                                <el-input v-model="throughputform.e_id" autocomplete="off" disabled></el-input>
                            </el-form-item>
                    </el-col>
                    <el-col :span="60">
                        <el-form-item label="Equip Name" prop="e_name" label-width="240px">
                            <el-input v-model="throughputform.e_name" autocomplete="off" disabled></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                    <el-row>
                <el-button type="primary" @click="addtDialog=true">Add</el-button>
                    </el-row>

                    <el-row/>

                        <el-table :data="throughputform.list"  width="720" >
                            <el-table-column property="p_name" label="Product Name" ></el-table-column>
                            <el-table-column property="capacity" label="Productivity(pieces/hour)" ></el-table-column>
                            <el-table-column
                                    fixed="right"
                                    label="Operation">
                                <template slot-scope="scope">
                                    <el-button type="danger" size="small" >Delete</el-button>
                                </template>
                            </el-table-column>
                        </el-table>

                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button>Cancel</el-button>
                    <el-button type="primary" @click="throughputDialog=false">OK</el-button>
                </div>
            </el-dialog>
        </div>

        <!--添加产能-->
        <div>
            <el-dialog title="Add Product" :visible.sync="addtDialog">
                <el-form :model="addtform">
                    <el-form-item label="Product Name" :label-width="formLabelWidth" prop="p_id">
                        <el-select v-model="addtform.p_id" placeholder="choose">
                            <el-option
                                    v-for="pitem in plist"
                                    :key="pitem.p_id"
                                    :label="pitem.p_name"
                                    :value="pitem.p_id">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="Productivity" :label-width="formLabelWidth" prop="capacity">
                        <el-input-number v-model="addtform.capacity"  :min="0" :max="10000" ></el-input-number>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="addtDialog = false">Cancel</el-button>
                    <el-button type="primary" @click="addThroughput">OK</el-button>
                </div>
            </el-dialog>
        </div>

    </div>
</template>

<script>
    import Header from "../../Header";
    import Aside from "./../M-Aside";
    export default {
        name: "EquipmentManage",
        components:{
            Aside,
            Header
        },
        data(){


            return{
                plist:[],
                time:'',
                productList:[],
                rentlist:[],
                s:'',
                throughputDialog:false,
                throughputform:{
                    e_id:'',
                    list:[]

                },
                tableData:[],
                switch:true,
                updateeDialog:false,
                addeDialog:false,
                addtDialog:false,
                itemList:[],
                addtform:{
                    p_id:'',
                    capacity:''
                },
                addform:{
                    e_num:'' ,
                    e_name: '',
                    e_type:'',
                    e_details: '',
                    e_factory:JSON.parse(window.sessionStorage.getItem("user")).user_factory
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
            async getList(){
               this.tableData.length=0
                let userFactory=JSON.parse(window.sessionStorage.getItem("user")).user_factory;
               const {data:res} = await this.$http.get("me?s="+this.s+"&&factory_id="+userFactory);
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
                           factoryname="center";
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
                       leasestate:equipmentList[ei]["e_leasestate"]=='未租用'?'自有设备':'租用设备',
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
                return this.$message.error("Failure")
            }
            this.$message.success("Success");
            this.updateeDialog = false;
            this.getList();
        },async getrentList(){
                this.rentlist.length=0
                const {data:res} = await this.$http.get("merent");
                let equipmentList = res.equipmentList;
                for(let i=0;i<equipmentList.length;i++){
                    let list={
                        id:equipmentList[i]["e_id"],
                        num:equipmentList[i]["e_num"],
                        name:equipmentList[i]["e_name"],
                        details:equipmentList[i]["e_details"],
                        time:''
                    }
                    this.rentlist.push(list)
                }


            },
            async rent(row){
                this.rentlist.length=0
                let userFactory=JSON.parse(window.sessionStorage.getItem("user")).user_factory;
                const {data:res} = await this.$http.get("merentequipment?f_id="+userFactory+"&&e_id="+row.id+"&&start="+row.time[0]+"&&end="+row.time[1]);
                this.getList()
                if(res!="Success"){

                    //return this.$message.error("Failure")
                  return this.$message.success("Success")
                }
                this.$message.success(res);
                this.addtDialog = false;
                this.throughputDialog = false;

            },
            async showthroughput(row){
                this.throughputform.list.length=0
                this.throughputform.e_id= row.id
                this.throughputform.e_name=row.name
                const {data:res} = await this.$http.get("megetcapacity?e_id="+row.id);
                let plist = res.plist
                this.plist= plist
                let tlist = res.tlist
                console.log(this.plist)
                console.log(tlist)
                for(let i=0;i<tlist.length;i++){
                    let t = tlist[i];
                    for(let j=0;j<plist.length;j++){
                        if(t["p_id"]==plist[j]["p_id"]&&t["e_id"]==row.id){
                            let list={
                                p_name:plist[j]["p_name"],
                                capacity: t["capacity"]
                            }
                            this.throughputform.list.push(list)
                        }
                    }
                }
                this.throughputDialog=true
            },
            async addThroughput(){
                let e_id = this.throughputform.e_id;
                let p_id = this.addtform.p_id;
                let capacity = this.addtform.capacity;
                let list={
                    e_id:e_id,
                    p_id:p_id,
                    capacity:capacity
                }
                const {data :res} = await this.$http.post("meset", list)
                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.addtDialog = false;
                this.throughputDialog = false;
            }


        }
    }
</script>

<style scoped>

</style>