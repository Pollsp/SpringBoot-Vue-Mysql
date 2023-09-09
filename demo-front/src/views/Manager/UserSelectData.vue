<script>
import request from "@/utils/request";

export default {
  name: "UserSelectData",
  data(){
    return{
      user:'',
      id:' '
    }
  },created() {
    this.load()
  },
  methods:{
    load(){
      request.get('/user/selectAll').then(res =>{
        this.user= res.data
        console.log(this.user)
      })
    }
  }
}
</script>

<template>
  <div>
    <el-card>
      <div slot="header" label-width="80px">
     <el-input v-model="id"> </el-input>    <el-button type="primary"> </el-button>
    </div>

    <el-table :data="user" stripe>
      <el-table-column label="序号" prop="id" ></el-table-column>
      <el-table-column label="用户名" prop="username"></el-table-column>
      <el-table-column label="姓名" prop="name"></el-table-column>
      <el-table-column label="手机号" prop="phone"></el-table-column>
      <el-table-column label="邮箱" prop="email"></el-table-column>
      <el-table-column label="地址" prop="address"></el-table-column>
      <el-table-column label="角色" prop="role"></el-table-column>
      <el-table-column label="操作" >
        <div>
          <el-button type="primary" plain size="mini">编辑</el-button>
        </div>
        <div style="display: flex;margin-top: 10px">
          <el-button type="danger" plain size="mini">删除</el-button>
        </div>
      </el-table-column>
    </el-table>
    </el-card>

  </div>
</template>

<style scoped>

</style>