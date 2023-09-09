<script>




export default {
  name: "UserInfor",
  data(){
    return{
      user:JSON.parse(localStorage.getItem('honey-user')||'{}')
    }
  },
  methods:{
    updata(){

      //保存当前用户信息到数据库
      this.$request.put('/user/updata',this.user).then(res =>{
        if(res.code ==='200'){
          //更新成功
          this.$message.success('保存成功')
          //更新浏览器缓存信息
          localStorage.setItem('honey-user',JSON.stringify(this.user))
          //触发父级的数据
          this.$emit('update:user',this.user)

        }else {
          this.$message.error(res.msg)
        }
      })

    }

  }
}
</script>

<template>
<div>
  <el-card style="width: 50%" >
    <el-form :model="user" label-width="80px" style=" padding-right: 80px">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="user.username" placeholder="用户名" disabled></el-input>
      </el-form-item>
      <el-form-item label="姓名" prop="name">
        <el-input v-model="user.name" placeholder="姓名" ></el-input>
      </el-form-item>
      <el-form-item label="电话" prop="phone">
        <el-input v-model="user.phone" placeholder="电话" ></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="user.email" placeholder="邮箱" ></el-input>
      </el-form-item>
      <el-form-item label="地址" prop="address">
        <el-input type="textarea" v-model="user.address" placeholder="地址" ></el-input>
      </el-form-item>
      <div>
        <el-button type="primary" @click="updata" style="margin-left: 45%">保存</el-button>
      </div>
    </el-form>
  </el-card>
</div>
</template>

<style scoped>
/deep/.el-form-item__label{
  font-weight: bold;
}
</style>