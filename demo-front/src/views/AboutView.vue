
<template>
  <div>
    <el-card style="width: 50%;">
      <el-table :data="info" stripe>
      <el-table-column label="序号" prop="id" ></el-table-column>
      <el-table-column label="用户名" prop="name"></el-table-column>
      <el-table-column label="信息" prop="content"></el-table-column>
      <el-table-column label="hits" prop="hits"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "AboutView",
  data() {
    return {
      info:[]




    }
  },
  mounted() { //页面加载完成之后触发
    axios.get('http://127.0.0.1:8080/user/get').then(res => {
      this.info = res.data.data
      console.log(this.info);
    })

  },
  methods: {
    // login() {
    //   this.$refs['loginRef'].validate((valid) => {
    //     if (valid) {
    //       axios.post('http://127.0.0.1:9090/login', this.user).then(res => {
    //         if (res.data.code === '200') {
    //           this.$router.push('/')
    //           this.$message.success('登录成功')
    //         } else {
    //           this.$message.error(res.data.msg);
    //         }

    //       })
    //     }
    //   })
    // }



  }

};


</script>




<style>
.Bj {
  background: url("../assets/imges/a.png");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
}

</style>