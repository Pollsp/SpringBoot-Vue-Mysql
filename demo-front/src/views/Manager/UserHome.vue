<template>
  <div>
    <div style="box-shadow: 0 0 10px rgba(0,0,0,.1); padding: 10px 20px;border-radius: 5px; margin-bottom: 10px;">
      <div style="font-weight: lighter"> ({{user.role}}-{{user.name}})--------早上好</div>
        </div>
        <div style="display: flex;">
          <el-card style="width: 100%;">
            <div slot="header" class="clearfix">
              <span>欢迎进入管理系统</span>
            </div>
            <div style="margin-top: 20px;">
              2023/8/26
            </div>
            <div style="margin:10px 0"><strong>主题色</strong></div>
            <el-button type="primary">按钮</el-button>
            <el-button type="primary">按钮</el-button>
            <el-button type="primary">按钮</el-button>
            <el-button type="primary">按钮</el-button>

          </el-card>


        </div>
  </div>
</template>
  
<script>
// import axios from 'axios';
// import request from "@/utils/request";





export default {
  name: "UserHome",
  data() {
    return{
      user: JSON.parse(localStorage.getItem('honey-user') || '{}')
    }

  },
  mounted() { //页面加载完成之后触发
    // axios.get('http://127.0.0.1:9090/user/selectAll').then(res => {
    //   this.users = res.data.data
    //   console.log(res);
    // })
    // request.get('/user/selectAll').then(res => {
    //   this.users = res.data


    // })

  },
  methods: {



  }

};

</script>
  
<style >

</style>
  