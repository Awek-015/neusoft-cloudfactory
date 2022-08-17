<template>
    <div class="RegisterContainer">
        <h3 class="registerTitle">Register</h3>

        <el-form ref="registerFormRef"
                 :model="registerForm"
                 class="register_form"
                 label-width="0px">
            <el-form-item prop="user_account">
                <el-input v-model="registerForm.user_account" autocomplete="off"  placeholder="Please enter your account number"></el-input>
            </el-form-item>
            <el-form-item prop="user_password">
                <el-input v-model="registerForm.user_password" autocomplete="off"  placeholder="Please enter your password"></el-input>
            </el-form-item>
            <el-form-item prop="user_name">
                <el-input v-model="registerForm.user_name" autocomplete="off"  placeholder="Please enter your real name"></el-input>
            </el-form-item>
            <el-form-item prop="user_phone">
                <el-input v-model.number="registerForm.user_phone" autocomplete="off"  placeholder="Please enter your phone number"></el-input>
            </el-form-item>


            <el-form-item prop="user_role">
                <el-radio-group v-model="registerForm.user_role" size="small" style="display: inline-block">
                    <el-radio label="2" id="manager" @change="show = true">FactoryManager</el-radio>
                    <el-radio label="3" id="customer" @change="show = false">Dealer</el-radio>
                </el-radio-group>
            </el-form-item>


            <div v-if="show" >
                <el-form-item prop="f_name" >
                    <el-input v-model="registerForm.f_name" autocomplete="off"  placeholder="Please enter the factory name"></el-input>
                </el-form-item>
                <el-form-item prop="f_detail" >
                    <el-input v-model="registerForm.f_detail" autocomplete="off"  placeholder="Please enter a description"></el-input>
                </el-form-item>
            </div>


            <el-form-item class="btns" style="text-align: center">
                <el-button type="primary" @click="register" style="text-align: left">Register</el-button>
                <el-button type="info" @click="exit" style="text-align: right">Exit</el-button>
            </el-form-item>

        </el-form>
    </div>
</template>

<script>
    export default {
        name: "Register",
        data(){
            return {
                show:false,
                registerForm:{
                    user_account:'',
                    user_password:'',
                    user_name:'',
                    user_phone:'',
                    user_role:'',
                    factory:false,
                    f_name:'',
                    f_detail:''
                },


            }
        },
        methods:{
            change:function(val){
                console.log(val)
                 if (val == 2) this.isShow = true;
              if(val==3)  this.isShow=false;
             },
            exit(){
                this.$router.push({ path: "/Login"});
            },
            async register(){

                //1、验证校验规则

                    // 调用post请求
                    console.log(this.registerForm)

                    const {data :res} = await this.$http.post("register", this.registerForm);//访问后台

                if(res!="success"){
                    return this.$message.error("Failure")
                }
                this.$message.success("Success");



            }
        }
    }
</script>

<style scoped>

    .RegisterContainer{
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 250px;
        padding: 15px 75px 15px 75px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        background:rgba(255,255,255,0.8);
    }
    .registerTitle{
        margin: 0 auto 40px auto;
        text-align: center;
        color: dodgerblue;
        font-size: 30px;
    }
    .register_form{
        margin: 0 auto 40px auto;
        text-align: center;
        padding: 0px 0px 0px 0px;
        width: 250px;
    }
</style>