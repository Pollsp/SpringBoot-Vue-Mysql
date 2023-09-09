import Vue from 'vue'
import VueRouter from 'vue-router'
import Manager from '../views/Manager.vue'


const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location){
  return originalPush.call(this,location).catch(err => err)
}


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manager',
    redirect:'/UserHome', //第一次展示的子页面
    children:[          //子页面
        {path:'UserHome',name:'UserHome', meta:{name:'系统首页'}, component: () => import(/* webpackChunkName: "UserHome" */ '@/views/Manager/UserHome.vue')},
        {path:'UserData',meta:{name:'用户信息'}, component:() => import(/* webpackChunkName: "UserData" */ '@/views/Manager/UserData.vue')},
        {path:'/403',name:'Auth',meta:{name:'无权限'},component:() => import(/* webpackChunkName: "Auth" */ '../views/ErrorView/Auth.vue')},
        {path: '/UserInfor', name: 'UserInfor',meta:{name:'个人信息'}, component:() => import(/* webpackChunkName: "Auth" */ '../views/Manager/UserInfor.vue')},
        {path: '/UserPassword',name:'UserPassword',meta: {name: '修改密码'},component:() => import(/* webpackChunkName: "Auth" */ '../views/Manager/UserPassword.vue')},
        {path: '/UserSelectData',name:'UserSelectData',meta: {name:'数据查询'}, component:() => import(/* webpackChunkName: "UserData" */ '@/views/Manager/UserSelectData.vue')}
    ],
    component: Manager
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path:'/UserHome',
    name:'UserHome',
    // redirect:'/UserData', //第一次展示的子页面
    // children:[{            //子页面
    //           path:'/UserData',
    //           component: () => import(/* webpackChunkName: "UserData" */ '../views/UserData.vue'),
              
    // },{
    //            path:'/login',
    //            component:() => import(/* webpackChunkName: "Login" */ '../views/LoginView.vue'),
    // }],
    component:() => import(/* webpackChunkName: "His" */ '@/views/Manager/UserHome.vue')
  },
  {
    path:'/login',
    name:'Login',meta:{name:'登录'},
    component:() => import(/* webpackChunkName: "Login" */ '../views/LoginView.vue')
  },
  // {
  //   path:'/UserData',
  //   name:'UserData',
  //   component:() => import(/* webpackChunkName: "UserData" */ '@/views/Manager/UserData.vue')
  // },
  {
    path:'*',
    name:'404',meta:{name:'无法访问'},
    component:() => import(/* webpackChunkName: "BaoCuo" */ '../views/ErrorView/404.vue')
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next)=>{
  //to 是到达的路由信息
  //from 是来源的路由信息
  //next 是帮助我们跳转路由的函数
    let adminPaths = ['/UserData']
  let user = JSON.parse(localStorage.getItem('honey-user')||'{}')
  if(user.role !== '管理员' && adminPaths.includes(to.path)){ //如果当前登录用户不是管理员，然后当前达到的路径是管理员才有权限访问的路径，
                                                            // 那这个时侯我就让用户去跳转到403页面
    next('/403')
  }else{
    next()
  }
})





export default router
