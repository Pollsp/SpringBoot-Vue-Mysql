<script>


export default {
  name: "UserPassword",
  data(){
    const validatePassword =(rule,value,callback)=>{
      if (value === ''){
        callback(new Error('请确认密码'))
      }else if (value !== this.user.newPassword){
        callback(new Error('两次密码不一样'))
      }else {
        callback()
      }
    }
    return{

      user:JSON.parse(localStorage.getItem('honey-user')),
      rules:{
        password:[{required:true, trigger: 'blur', message:'请输入旧密码'}],
        newPassword:[{required:true,  trigger: 'blur' , message:'请输入新密码'}],
        confirmPassword:[{validator:validatePassword , required:true , trigger: 'blur'}]

      }
    }


  },





  methods:{

    updata(){
      this.$refs['formRef'].validate((valid)=>{
        if (valid){
          //保存当前用户信息到数据库
          this.user.password = this.user.newPassword
          this.$request.put('/user/updata',this.user).then(res =>{
            if(res.code ==='200'){
              //更新成功
              this.$message.success('修改成功')
              this.$router.push('/login')


            }else {
              this.$message.error(res.msg)
            }
          })
        }
      })

    }
  }
}
</script>

<template>
  <el-card style="width: 50%" >
    <el-form :model="user" :rules="rules" label-width="80px" ref="formRef" style="padding-right: 80px">
      <el-form-item prop="password" label="原始密码">
        <el-input v-model="user.password" placeholder="原始密码" show-password></el-input>
      </el-form-item>
      <el-form-item prop="newPassword" label="新密码">
        <el-input v-model="user.newPassword" placeholder="新密码" show-password></el-input>
      </el-form-item>
      <el-form-item prop="confirmPassword" label="确认密码">
        <el-input v-model="user.confirmPassword" placeholder="确认密码" show-password></el-input>
      </el-form-item>
      <div>
        <el-button type="primary" @click="updata" style="margin-left: 45%">确认修改</el-button>
      </div>

    </el-form>

  </el-card>

</template>

<style scoped>
/deep/.el-form-item__label{
  font-weight: bold;
}
</style>