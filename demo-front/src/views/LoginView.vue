<template>
    <div class="backgroud">

        <div id="logo" style="left: 45%;">Login Form</div>
        <div id="box">
            
            <el-form :model="user" :rules="rules" ref="loginRef">
                <el-form-item prop="username">
                    <el-input style=" width: 450px;height: 50px; " v-model="user.username" placeholder="Username"
                        prefix-icon="iconfont icon-Albbmail">
                    </el-input>
                </el-form-item>
                <el-form-item prop="password">
                    <el-input v-model="user.password" placeholder="Password" prefix-icon="iconfont icon-Albbpermissions"
                        style="width: 450px;height: 50px;" show-password></el-input>

                </el-form-item>

                <el-button class="button-login" type="primary" @click="login">Login</el-button>



            </el-form>

        </div>





    </div>
</template>
    
<script>



export default {
    name: "LoginView",
    data() {
        return {
            user: {
                username: '',
                password: ''
            },
            rules: {
                username: [
                    { required: true, message: '请输入账号', trigger: 'blur' },

                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },

                ],
            }
        }


    },
    methods: {
        login() {
            this.$refs['loginRef'].validate((valid) => {
                if (valid) {
                    //验证通过
                    this.$request.post('/login', this.user).then(res => {
                        if (res.code === '200') {
                            this.$router.push('/')
                            this.$message.success('登录成功')
                            localStorage.setItem("honey-user",JSON.stringify(res.data)) //存储用户数据
                        } else {
                            this.$message.error(res.msg);
                        }
                    })

                }

            })

        }



    }

};

</script>
    
<style scoped>
.backgroud {
    background-color: #2D3A4B;
    width: 100%;
    height: 100%;
    position: fixed;
    background-size: 100% 100%;
}

#logo {
    position: relative;
    color: white;
    font-size: 26px;
    font-weight: 800;
    top: 23%;

}

#box {
    position: relative;
    top: 43%;
    left: 43%;
    transform: translate(-50%, -50%);
    width: 250px;
    padding: 40px;

}

.el-input__inner {
    color: white;
    background-color: #283443;
    border: 1px solid #3E4957;
    margin-top: 3px;

}

.el-input__icon {
    font-size: 19px;


}

.button-login {
    width: 250px;
}

.el-button {
    color: white;
    width: 450px;
    height: 35px;
}
</style>
    