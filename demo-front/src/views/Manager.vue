<template>
  <el-container>
    <!--侧边栏-->
    <el-aside :width="asideWidth" style="min-height: 100vh; background-color: #304156;">
      <div
        style="height: 60px; line-height: 60px; color: white; display: flex; align-items: center; justify-content: center;">
        logo
      </div>

      <el-menu :collapse-transition="false" :collapse="isCollapse" router style="border: none;" text-color="white"
        background-color="#304156" active-text-color="#409EFF" :deffault-active="$router.path">

        <el-menu-item index="/">

          <i class="icon-Albbdashboard"></i>
          <span slot="title">首页</span>


        </el-menu-item>
        <el-menu-item index="/UserData" v-if="user.role === '管理员'">
          <i class="icon-Albbdocument_fill"></i>
          <span slot="title">用户信息</span>


        </el-menu-item>
        <el-menu-item v-if="user.role === '管理员'">
          <i class="icon-Albbguide"></i>
          <span slot="title">信息管理</span>
        </el-menu-item>


        <el-submenu>
          <template slot="title">
            <i class="el-icon-menu"></i>
            <span slot="title">Permission</span>
          </template>
          <el-menu-item index="about">Page Permission</el-menu-item>
          <el-menu-item>Directive Permission</el-menu-item>
          <el-menu-item>Role Permission</el-menu-item>
        </el-submenu>



        <el-menu-item>
          <i class="icon-Albbpermissions"></i>
          <span slot="title">用户管理</span>

        </el-menu-item>





      </el-menu>
    </el-aside>

    <el-container>
      <!--头部区域-->
      <el-header>
        <i :class="collapseIcon" style="font-size: 26px; " @click="handleCollapse"></i>



        <el-breadcrumb separator-class="el-icon-arrow-right" style="margin-left: 20px;">
          <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: $route.path }" >{{$route.meta.name}}</el-breadcrumb-item>
        </el-breadcrumb>

        <div style="flex: 1; width: 0; display: flex; align-content: center; justify-content: flex-end;">
          <div style="display: flex; align-items: center;">
            <i class="icon-Albbquanping" style="font-size: 23px;" @click="handleFull"></i>
            <el-dropdown placement="bottom">
              <div style="display: flex; align-items: center;cursor:default">

                <img src="@/assets/imges/dog.gif" alt="" style="width: 60px; height: 60px;">
                <span >{{ user.name }}</span>
              </div>
              <el-dropdown-menu>
                <el-dropdown-item @click.native="$router.push('/UserInfor')">个人信息</el-dropdown-item>
                <el-dropdown-item @click.native="$router.push('/UserPassword')">修改密码</el-dropdown-item>
                <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>

            </el-dropdown>
          </div>













        </div>











      </el-header>
      <!--主体区域-->
      <el-main>

        <router-view @update:user="updateUser" ></router-view>

      </el-main>
    </el-container>


  </el-container>
</template>
  
<script>
// import axios from 'axios';
import request from "@/utils/request";







export default {
  name: "HomeView",
  data() {
    return {
      isCollapse: false, //不受缩
      asideWidth: '200px',
      collapseIcon: 'el-icon-s-fold',
      user: JSON.parse(localStorage.getItem('honey-user') || '{}'),
      dbuser:[]
    }
  },
  mounted() { //页面加载完成之后触发
    // axios.get('http://127.0.0.1:9090/user/selectAll').then(res => {
    //   this.users = res.data.data
    //   console.log(res);
    // })
    request.get('/user/selectAll').then(res => {
      this.dbuser = res.data
      // console.log(this.dbuser);

    })

  },
  methods: {
    updateUser(user){       //获取子组件传过来的数据 更新当前页面的数据
      this.user = JSON.parse(JSON.stringify(user)) //让父级的对象跟子级的对象毫无关联
    },
    handleCollapse() {
      this.isCollapse = !this.isCollapse;
      this.asideWidth = this.isCollapse ? '64px' : '200px';
      this.collapseIcon = this.isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'
    },
    handleFull() {
      document.documentElement.requestFullscreen();
    },
    logout() {
      localStorage.removeItem('honey-user') //清除保存在浏览器的信息(当前的token和用户数据)
      this.$router.push('/login')
    }




  }

};

</script>
  
<style >
body {
  margin: 0;
  padding: 0;
  border: 0
}

.el-menu--inline .el-menu-item {
  background-color: #1F2D3D !important;
}

.el-menu-item i {
  font-size: 19px;
}

.el-menu-item {
  margin-left: 3px;
}

.el-menu-item span {
  margin-left: 8px;
}

.el-aside {
  transition: width .2s;
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
}

.el-header {
  box-shadow: 2px 0 6px rgba(0, 21, 41, .35);
  display: flex;
  align-items: center;
}
</style>
  