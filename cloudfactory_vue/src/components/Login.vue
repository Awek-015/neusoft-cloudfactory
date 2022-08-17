<template>
    <div class="loginContainer">
        <h3 class="loginTitle">Login</h3>


            <!--添加表单-->
            <el-form
                    ref="loginFormRef"
                    :model="loginForm"
                    :rules="loginRules"
                    class="login_form"
                    label-width="0px"
            >
                <el-form-item prop="user_account">
                    <el-input v-model="loginForm.user_account" autocomplete="off" prefix-icon="iconfont icondenglu" placeholder="account number"></el-input>
                </el-form-item>
                <el-form-item prop="user_password">
                    <el-input v-model="loginForm.user_password" autocomplete="off" prefix-icon="iconfont iconmima" type="password" placeholder="password"></el-input>
                </el-form-item>
                <el-link :underline="false" href="../Register" type="primary" style="padding: 0px 0px 20px 140px">Register</el-link>
                <el-form-item class="btns" style="text-align: center">
                    <el-button type="primary" @click="login" style="text-align: left">Login</el-button>
                    <el-button type="info" @click="resetLoginForm" style="text-align: right">Reset</el-button>
                </el-form-item>

            </el-form>

    </div>
</template>

<script>
    export default {
        data() {
            return {
                loginForm: {
                    user_account:'',
                    user_password:''
                },
                loginRules: {
                    user_account: [
                        { required: true, message: "Please enter your account number", trigger: "blur" },
                        { min: 1, max: 10, message: "Length from 1 to 10 characters", trigger: "blur" }
                    ],
                    user_password: [
                        { required: true, message: "Please enter your password", trigger: "blur" },
                        { min: 1, max: 10, message: "Length from 1 to 10 characters", trigger: "blur" }
                    ]
                }
            };
        },
        methods: {
            resetLoginForm() {
                this.$refs.loginFormRef.resetFields();
            },
            login() {
                //1、验证校验规则
                this.$refs.loginFormRef.validate(async valid => {
                    if (!valid) return;//验证失败
                    // 调用post请求
                    const {data :res} = await this.$http.post("login", this.loginForm);//访问后台
                    if (res.flag== "ok" ) {
                        window.sessionStorage.setItem('user',JSON.stringify(res.user)); // session 放置
                        console.log(window.sessionStorage.getItem('user')+".....");
                        this.$message.success("Success！");
                        if(res.user.user_role==1)await this.$router.push({path: "/admin"});
                        if(res.user.user_role==2)await this.$router.push({path: "/manager"});
                        if(res.user.user_role==3)await this.$router.push({path: "/dealer"});
                    }else{
                        this.$message.error("Failure");
                    }
                });
            }
        }
    };
</script>

<style lang="less" scoped>

    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 180px auto;
        width: 200px;
        padding: 15px 60px 15px 60px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        background:rgba(255,255,255,0.8);
    }

    .loginTitle {
        margin: 0 auto 40px auto;
        text-align: center;
        color: dodgerblue;
        font-size: 30px;
    }


</style>