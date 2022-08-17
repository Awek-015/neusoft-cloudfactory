<template>
        <div>
            <Header/>
            <div style="display: flex">
                <Aside/>
                <div style="padding: 10px ">
                    <div style="margin: 10px 0">
                        <el-input v-model="s" placeholder="Enter Code" style="width: 20%;min-width: 200px" clearable></el-input>
                        <el-button type="primary" style="margin-left: 5px" @click="getList" >Search</el-button>
                    </div>

                    <el-table :data="tableData" border stripe style="width: 1200px" max-height="690">
                        <el-table-column prop="o_id" label="ID" sortable></el-table-column>
                        <el-table-column prop="o_num" label="Order ID" ></el-table-column>
                        <el-table-column prop="o_pname" label="Product" ></el-table-column>
                        <el-table-column prop="o_amount" label="Quantity" ></el-table-column>
                        <el-table-column prop="o_delivery" label="Delivery Date" ></el-table-column>
                        <el-table-column prop="o_ddl" label="Deadline" ></el-table-column>
                        <el-table-column prop="u_name" label="Consignee" ></el-table-column>
                        <el-table-column prop="u_phone" label="Phone"></el-table-column>
                        <el-table-column prop="u_address" label="Address" ></el-table-column>
                        <el-table-column prop="o_state" label="State" ></el-table-column>

                        <el-table-column
                                fixed="right"
                                label="Operation"
                                width="150">
                            <template slot-scope="scope">
                                <el-dropdown split-button type="primary">
                                    Choose
                                    <el-dropdown-menu slot="dropdown">
                                        <el-dropdown-item :disabled="scope.row.o_state!='已接单'&&scope.row.o_state!='生产中'" @click.native="open(scope.row)">Schedule</el-dropdown-item>
                                        <el-dropdown-item :disabled="scope.row.o_state!='生产中'" @click.native="changeState(scope.row)">Complete</el-dropdown-item>
                                        <el-dropdown-item :disabled="scope.row.o_state!='已完工'" @click.native="changeState(scope.row)">Dispatch</el-dropdown-item>

                                    </el-dropdown-menu>
                                </el-dropdown>
                            </template>
                        </el-table-column>

                    </el-table>

                </div>
            </div>
            <!--展示订单排产信息-->
            <div>
                <el-dialog title="Schedule" :visible.sync="scheduleDialog" style="width: 1550px">
                    <el-form :model="scheduleform" style="width: 720px">
                        <el-row>
                            <el-col :span=120>
                                <el-form-item label="ID" prop="o_id" label-width="60px">
                                    <el-input v-model="scheduleform.o_id" autocomplete="off" disabled></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="60">
                                <el-form-item label="Order Code" prop="o_num" label-width="240px">
                                    <el-input v-model="scheduleform.o_num" autocomplete="off" disabled></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-button type="primary" @click="openadd(scheduleform.o_id)">Add Equip</el-button>
                        </el-row>

                        <el-row/>

                        <el-table :data="scheduleform.list"  width="720" >
                            <el-table-column property="e_name" label="Equip Name" ></el-table-column>
                            <el-table-column property="start" label="Start" ></el-table-column>
                            <el-table-column property="end" label="End" ></el-table-column>
                            <!--<el-table-column
                                    fixed="right"
                                    label="Operation">
                                <template slot-scope="scope">
                                    <el-button type="primary" size="small" >Delete</el-button>
                                </template>
                            </el-table-column>-->
                        </el-table>

                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="scheduleDialog=false">Cancel</el-button>
                        <el-button type="primary" @click="">OK</el-button>
                    </div>
                </el-dialog>
            </div>

            <!--添加排产-->
            <div>
                <el-dialog title="Add Schedule Info" :visible.sync="addDialog">
                    <el-form :model="addform">
                        <el-form-item label="Equip Name" :label-width="formLabelWidth" prop="e_id">
                            <el-select v-model="addform.e_id" placeholder="choose">
                                <el-option
                                        v-for="eitem in elist"
                                        :key="eitem.e_id"
                                        :label="eitem.e_name"
                                        :value="eitem.e_id">
                                </el-option>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="Schedule Time" :label-width="formLabelWidth" prop="p_id">
                            <template slot-scope="scope">
                                <el-date-picker
                                        v-model="addform.time"
                                        type="daterange"
                                        range-separator="to"
                                        start-placeholder="Start"
                                        end-placeholder="End"
                                        value-format="yyyy-MM-dd">
                                </el-date-picker>
                            </template>
                        </el-form-item>

                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="addDialog = false">Cancel</el-button>
                        <el-button type="primary" @click="addSchedule">OK</el-button>
                    </div>
                </el-dialog>
            </div>
        </div>
</template>

<script>
    import Header from "../../Header";
    import Aside from "./../M-Aside";
    export default {
        name: "TakeOrder",
        components:{
            Aside,
            Header
        },
        data(){
            return{
                oid:'',
                elist:[],
                s:'',
                tableData:[],
                plist:[],
                factoryId:'',
                scheduleDialog:false,
                scheduleform:{
                    e_id:'',
                    e_num:'',
                    list:[]
                },
                formLabelWidth: '120px',
                addform:{
                    o_id:'',
                    e_id:'',
                    time:[]
                },
                addDialog:false,

            }
        },
        created() {
            this.getList();
        },
        methods:{
            async getList(){
                this.factoryId=JSON.parse(window.sessionStorage.getItem("user")).user_factory
                this.tableData.length=0
                const {data:res} = await this.$http.get("aos?s="+this.s+"&&id="+this.factoryId);
                let olist = res.olist;
                let plist = res.plist;

                this.plist=plist;
                for(let i=0; i<olist.length; i++){
                    let o = olist[i];

                    for(let j=0; j<plist.length; j++){
                        let p = plist[j];
                        if(o.o_pid==p.p_id  ){
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
                            this.tableData.push(list)
                            break
                        }
                    }
                }
                console.log(this.tableData)
            },
           async changeState(state,f_id){
                let f_state=state?"开启" :"关停"
               await this.$http.get("afswitch?f_state="+f_state+"&&f_id="+f_id);
                this.getList()

    },

            async changeState(row){
                const confirmResult = await this.$confirm("是否修改状态？",'Attention',{
                    ConfirmButtonText:'OK',
                    cancelButtonText:'取消',
                    type:'warning'
                }).catch(err =>err)
                if(confirmResult!='confirm'){
                    return this.$message.info("已取消")
                }
                await this.$http.get("dcs?id="+row.o_id);
                this.getList();
            },
            async open(row){
                this.oid=row.o_id
                this.scheduleform.list.length=0
                this.scheduleform.o_id=row.o_id
                this.scheduleform.o_num=row.o_num
                const {data:res} = await this.$http.get("ss?oid="+row.o_id+"&&fid="+this.factoryId);
                this.elist=res.elist;
                console.log(this.elist)
                let eList=res.elist;
                let sList=res.slist;
                for(let i=0;i<sList.length;i++){
                    for(let j=0;j<eList.length;j++){
                        if(sList[i]["e_id"]==eList[j]["e_id"]){
                            let list={
                                e_name:eList[j]["e_name"],
                                start:sList[i]["start"],
                                end:sList[i]["end"],
                            }
                            this.scheduleform.list.push(list)
                            break
                        }
                    }

                }

              this.scheduleDialog=true
            },
            async addSchedule(){
                let e_id = this.addform.e_id
                let start=this.addform.time[0]
                let end =this.addform.time[1]
                let o_id = this.addform.o_id
                let list={
                    e_id:e_id,
                    start:start,
                    end:end,
                    o_id:o_id
                }
                const {data :res} = await this.$http.post("schedule", list)
                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.addDialog = false;
                this.scheduleDialog = false;
                this.getList()
            },
            openadd(o_id){
                this.addform.o_id=o_id
                this.addDialog=true
            }

        }
    }
</script>

<style scoped>

</style>