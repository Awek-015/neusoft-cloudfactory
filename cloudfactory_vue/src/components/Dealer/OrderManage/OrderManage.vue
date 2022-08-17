<template>
        <div>
            <Header/>
            <div style="display: flex">
                <Aside/>
                <div style="padding: 10px ">
                    <div style="margin: 10px 0">
                        <el-input v-model="s" placeholder="Enter Code" style="width: 20%;min-width: 200px" clearable></el-input>
                        <el-button type="primary" style="margin-left: 5px" @click="getList" >Search</el-button>
                        <el-button type="primary" style="margin-left: 5px" @click="addoDialog = true" >Order</el-button>
                    </div>

                    <el-table :data="tableData" border stripe style="width: 1200px" max-height="690">
                        <el-table-column prop="o_id" label="ID" sortable></el-table-column>
                        <el-table-column prop="o_num" label="Order Code" ></el-table-column>
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
                                        <el-dropdown-item :disabled="scope.row.o_state!='未保存'" @click.native="changeState(scope.row)">Save</el-dropdown-item>
                                        <el-dropdown-item :disabled="scope.row.o_state!='未保存'&&scope.row.o_state!='已保存'" @click.native="deleteOrder(scope.row)">Delete</el-dropdown-item>
                                        <el-dropdown-item :disabled="scope.row.o_state!='未保存'&&scope.row.o_state!='已保存'" @click.native="open(scope.row)">Edit</el-dropdown-item>
                                        <el-dropdown-item :disabled="scope.row.o_state!='已保存'" @click.native="changeState(scope.row)">Publish</el-dropdown-item>
                                        <el-dropdown-item :disabled="scope.row.o_state!='待选标'" @click.native="openselect(scope.row)">Select</el-dropdown-item>
                                        <el-dropdown-item :disabled="scope.row.o_state!='已发货'"  @click.native="changeState(scope.row)">Confirm</el-dropdown-item>
                                    </el-dropdown-menu>
                                </el-dropdown>
                            </template>
                        </el-table-column>

                    </el-table>

                </div>
            </div>

  <!--  添加订单对话框-->
    <div>
        <el-dialog title="Order" :visible.sync="addoDialog">
            <el-form :model="addform">
                <el-form-item label="Order Code" :label-width="formLabelWidth" prop="o_num">
                    <el-input v-model="addform.o_num" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="Product" :label-width="formLabelWidth" prop="o_pid">
                    <el-select v-model="addform.o_pid" placeholder="choose">
                        <el-option
                                v-for="item in plist"
                                :key="item.p_id"
                                :label="item.p_name"
                                :value="item.p_id">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item label="Quantity" :label-width="formLabelWidth" prop="o_amount">
                    <el-input-number v-model="addform.o_amount"  :min="1" :max="10000" ></el-input-number>
                </el-form-item>

                <el-form-item label="Delivery Date" :label-width="formLabelWidth" prop="o_delivery">
                    <el-date-picker
                            v-model="addform.o_delivery"
                            type="date"
                            placeholder="choose"
                            value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="Deadline" :label-width="formLabelWidth" prop="o_ddl">
                    <el-date-picker
                            v-model="addform.o_ddl"
                            type="date"
                            placeholder="choose"
                            value-format="yyyy-MM-dd">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="收货人" :label-width="formLabelWidth" prop="u_name">
                    <el-input v-model="addform.u_name" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="联系方式" :label-width="formLabelWidth" prop="u_phone">
                    <el-input v-model="addform.u_phone" autocomplete="off"></el-input>
                </el-form-item>

                <el-form-item label="收货地址" :label-width="formLabelWidth" prop="u_address">
                    <el-input v-model="addform.u_address" autocomplete="off"></el-input>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="addoDialog = false">Cancel</el-button>
                <el-button type="primary" @click="addOrder">OK</el-button>
            </div>
        </el-dialog>
    </div>
            <!--修改订单对话框-->
            <div>
                <el-dialog title="Edit Order" :visible.sync="updateoDialog">
                    <el-form :model="updateform">
                        <el-form-item label="Product" :label-width="formLabelWidth" prop="o_pid">
                            <el-select v-model="updateform.o_pid" placeholder="choose">
                                <el-option
                                        v-for="item in plist"
                                        :key="item.p_id"
                                        :label="item.p_name"
                                        :value="item.p_id">
                                </el-option>
                            </el-select>
                        </el-form-item>

                        <el-form-item label="Quantity" :label-width="formLabelWidth" prop="o_amount">
                            <el-input-number v-model="updateform.o_amount"  :min="1" :max="10000" ></el-input-number>
                        </el-form-item>

                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="updateoDialog = false">Cancel</el-button>
                        <el-button type="primary" @click="updatept">OK</el-button>
                    </div>
                </el-dialog>
            </div>

            <!--展示选标列表-->
            <div>
                <el-dialog title="选标" :visible.sync="selectDialog" style="width: 1550px">
                    <el-form :model="selectform" style="width: 720px">
                        <el-row>
                            <el-col :span=120>
                                <el-form-item label="ID" prop="o_id" label-width="60px">
                                    <el-input v-model="selectform.o_id" autocomplete="off" disabled></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="60">
                                <el-form-item label="Order Code" prop="o_num" label-width="240px">
                                    <el-input v-model="selectform.o_num" autocomplete="off" disabled></el-input>
                                </el-form-item>
                            </el-col>
                        </el-row>


                        <el-row/>

                        <el-table :data="selectform.list"  width="720" >
                            <el-table-column property="id" label="Bid ID" ></el-table-column>
                            <el-table-column property="f_id" label="Factory ID" ></el-table-column>
                            <el-table-column property="f_name" label="Factory Name" ></el-table-column>
                            <el-table-column property="price" label="Weights" ></el-table-column>
                            <el-table-column
                                    fixed="right"
                                    label="Operation">
                                <template slot-scope="scope">
                                    <el-button type="primary" size="small" @click="select(scope.row)">选择代工厂</el-button>
                                </template>
                            </el-table-column>
                        </el-table>

                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button>Cancel</el-button>
                        <el-button type="primary" @click="closeDialog">OK</el-button>
                    </div>
                </el-dialog>
            </div>
        </div>
</template>

<script>
    import Header from "../../Header";
    import Aside from "./../D-Aside";
    export default {
        name: "OrderManage",
        components:{
            Aside,
            Header
        },
        data(){
            return{
                s:'',
                tableData:[],
                plist:[],
                flist:[],
                userId:'',
                itemList:'',
                addoDialog:false,
                addform:{
                    o_id:'',
                    o_num:'',
                    o_pname:'',
                    o_amount:'',
                    o_delivery:'',
                    o_ddl:'',
                    o_user:this.userId,
                    u_name:'',
                    u_phone:'',
                    u_address:'',
                    o_state:''
                },
                formLabelWidth: '120px',
                updateoDialog:false,
                selectDialog:false,
                updateform:{
                    o_id:'',
                    o_pid:'',
                    o_amount:''
                },
                selectform:{
                    o_id:'',
                    o_num:'',
                    list:[]
                },

            }
        },
        created() {
            this.getList();
        },
        methods:{
            async getList(){
                this.userId=JSON.parse(window.sessionStorage.getItem("user")).user_id
                this.tableData.length=0
                const {data:res} = await this.$http.get("ao?s="+this.s);
                let olist = res.olist;
                let plist = res.plist;

                this.plist=plist;
                for(let i=0; i<olist.length; i++){
                    let o = olist[i];

                    for(let j=0; j<plist.length; j++){
                        let p = plist[j];
                        if(o.o_pid==p.p_id&&o.o_user==this.userId){
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

            },
            async addOrder(){
                this.addform.o_user=this.userId
                const {data:res} =await this.$http.post("addorder",this.addform)

                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.addoDialog = false;
                this.getList();
            },
            async changeState(row){
                const confirmResult = await this.$confirm("Sure to change?",'Attention',{
                    ConfirmButtonText:'OK',
                    cancelButtonText:'Cancel',
                    type:'warning'
                }).catch(err =>err)
                if(confirmResult!='confirm'){
                    return this.$message.info("Canceled")
                }
                await this.$http.get("dcs?id="+row.o_id);
                this.getList();
            },
            async deleteOrder(row){
                const confirmResult = await this.$confirm("Sure to delete?",'Attention',{
                    ConfirmButtonText:'OK',
                    cancelButtonText:'Cancel',
                    type:'warning'
                }).catch(err =>err)
                if(confirmResult!='confirm'){
                    return this.$message.info("已Cancel")
                }
                const {data:res}=await this.$http.get("orderdelete?o_id="+row.o_id);
                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.getList();

            },
            open(row){
                this.updateform.o_id= row.o_id
                this.updateform.o_pid = row.o_pid
                this.updateform.o_amount=row.o_amount
                this.updateoDialog = true;
            },
            async updatept(){
                const {data:res} = await this.$http.post("orderupdate",this.updateform)
                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");
                this.updateoDialog = false;
                this.getList();
            },
            async openselect(row){
                this.selectform.o_id=row.o_id
                this.selectform.o_num=row.o_num
                const {data:res} = await this.$http.get("showbidding?oid="+row.o_id)
                let blist=res.blist
                let flist = res.flist
                this.flist=flist
                for(let i = 0;i<blist.length;i++){
                    for(let j=0;j<flist.length;j++){
                        if(blist[i]["f_id"]==flist[j]["f_id"]){
                            let list={
                                id:blist[i]["id"],
                                f_id:blist[i]["f_id"],
                                f_name:flist[j]["f_name"],
                                price:blist[i]["price"]
                            }
                            this.selectform.list.push(list)
                        }
                    }
                }
                this.selectDialog=true
            },
            closeDialog(){
                this.selectDialog=false
                this.getList()
            },
            async select(row){
                const confirmResult = await this.$confirm("Sure to select?",'Attention',{
                    ConfirmButtonText:'OK',
                    cancelButtonText:'Cancel',
                    type:'warning'
                }).catch(err =>err)
                if(confirmResult!='confirm'){
                    return this.$message.info("已Cancel")
                }
                await this.$http.get("select?id="+row.id+"&&oid="+this.selectform.o_id);
                this.selectDialog=false
                this.getList()
            },

        }
    }
</script>

<style scoped>

</style>