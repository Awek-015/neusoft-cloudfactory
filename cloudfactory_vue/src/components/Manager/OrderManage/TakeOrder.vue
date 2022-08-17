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
                                width="100">
                            <template slot-scope="scope">
                                <el-button type="primary" size="small" @click="open(scope.row)">Bid</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>



    <!--投标Operation-->



    <div>
        <el-dialog title="Bid Info" :visible.sync="biddingDialog">
            <el-form :model="biddingform">
                <el-form-item label="Weights" :label-width="formLabelWidth" prop="price">
                    <el-input-number v-model="biddingform.price"  :min="0" :max="10000" ></el-input-number>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="biddingDialog = false">Cancel</el-button>
                <el-button type="primary" @click="setprice">OK</el-button>
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
                s:'',
                tableData:[],
                plist:[],
                userId:'',
                biddingDialog:false,
                biddingform:{
                    o_id:'1',
                    f_id:'1',
                    price:'200'
                },
                formLabelWidth: '120px',

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
                        if(o.o_pid==p.p_id&&(o.o_state=="已发布"||o.o_state=="待选标")){
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
            async open(row){
                this.userId=JSON.parse(window.sessionStorage.getItem("user")).user_factory
                console.log(this.userId+"++++++++++++++")
                this.biddingform.o_id=row.o_id
                this.biddingform.f_id=this.userId
                const {data:res} = await this.$http.get("getbidding?o_id="+row.o_id+"&&f_id="+this.userId)
                console.log(res)
                if(res==null) this.biddingform.price=0;
                else this.biddingform.price=res.price
                this.biddingDialog=true
            },
            async setprice(){
                await this.$http.post("setbidding",this.biddingform)
                this.biddingDialog=false;
                this.getList()

            },

    }


    }
</script>

<style scoped>

</style>