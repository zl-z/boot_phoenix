import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "@/store";

Vue.use(VueRouter)

const routes = [
  {
    path: '/manage', //主页
    name: 'Manage',
    component: () => import('../views/Manage'),
    redirect: "/manage/home",
    children: [
      { path: 'home', name: 'Home', meta: {title: '首页'}, component: () => import('../views/Home') },
      { path: 'student', name: 'Student', meta: {title: '系统管理 / 学生管理'}, component: () => import('../views/Student') },
      { path: 'classroom', name: 'ClassRoom', meta: {title: '系统管理 / 教室管理'}, component: () => import('../views/ClassRoom') },
      { path: 'class', name: 'ClassGrade', meta: {title: '系统管理 / 班级管理'}, component: () => import('../views/ClassGrade') },
      { path: 'dorm', name: 'Dorm', meta: {title: '系统管理 / 宿舍管理'}, component: () => import('../views/Dorm') },
      { path: 'file', name: 'File', meta: {title: '系统管理 / 文件管理'}, component: () => import('../views/File') },
      { path: 'staff', name: 'Staff', meta: {title: '系统管理 / 员工管理'}, component: () => import('../views/Staff') },
      { path: 'person', name: 'Person', meta: {title: '系统管理 / 个人信息'}, component: () => import('../views/Person') },
    ]
  },
  {
    path: '/login', //登录
    name: 'Login',
    component: () => import('../views/Login'),
  },
  {
    path: '/register', //路径名   注册
    name: 'Register', //组件名
    component: () => import('../views/Register'),
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  //重定向，访问/，定向登录
  {
    path: '/',
    // name: 'LoginView',
    redirect: "/login"
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
//全局前置路由守卫-初始化/切换调用
router.beforeEach((to, from, next)=>{
  // console.log('前置路由守卫', to, from);
  // console.log('前置路由守卫', from);
  // console.log('前置路由守卫1', to.meta.title);
  localStorage.setItem("currentPathName", to.meta.title) //设置当前的路由名称,为了在Header组件中使用
  store.commit("setPath") //触发store的数据更新
  next()  //放行路由
})

export default router
