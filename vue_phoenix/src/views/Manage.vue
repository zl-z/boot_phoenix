<template>
  <el-container style="min-height: 100vh;">
    <!--  侧边栏    -->
    <el-aside :width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%);">
      <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
    </el-aside>

    <el-container>
      <!--    头部    -->
      <el-header style="border-bottom: 1px solid #ccc;">
        <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" :user="user"/>
      </el-header>
      <!--    主体内容    -->
      <el-main>
        <!--    表示当前页面的子路由会在 <router-view /> 里面展示    -->
        <router-view @refreshUser="getUserInFo"/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'Manage',
  data(){
    return {
      collapseBtnClass:'el-icon-s-fold', //按钮图标切换
      isCollapse:false,    //控制侧边收缩
      sideWidth:200,  //侧边宽度
      logoTextShow:true,  //后台管理文字隐藏
      //本地存储取值  三目判断
      user: {},
      userInfoId: {  //查询用户信息
        studentId:'',
        userCode:''
      },
    }
  },
  components: {
    Aside,
    Header,
  },
  created() {
    this.getUserInFo() //获取最新个人信息
  },
  methods:{
    //侧边收缩
    collapse(){ //点击收缩按钮触发
      this.isCollapse = !this.isCollapse;
      if(this.isCollapse){ //收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      }else{  //展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    //查询最新学生个人信息
    getUserInFo(){
      if(localStorage.getItem("user")){
        this.user.name = '昵称'
        if(null != JSON.parse(localStorage.getItem("user")).student){
          this.userInfoId = {
            studentId: JSON.parse(localStorage.getItem("user")).student.studentId,
            userCode:0
          }
        }else{
          this.userInfoId = {
            studentId: JSON.parse(localStorage.getItem("user")).teacher.userId,
            userCode:1
          }
        }
      }else {
        this.user.name = '昵称'
        this.userInfoId = {}
      }
      this.request.post("/register/getUserInfo", this.userInfoId).then(res => {
        if(res.code === '200'){
          if(res.obj.student != null){
            if(null != res.obj){
              //更新浏览器存储的用户信息
              localStorage.setItem("user", JSON.stringify(res.obj))
              if(null != res.obj.student){
                this.user =  res.obj.student
                this.user.name =  res.obj.student.studentName
                this.user.image =  res.obj.student.avatarUrl
                // res.student = JSON.parse(localStorage.getItem("user")).token
              }else{
                this.user =  res.obj.teacher
                this.user.name =  res.obj.student.employeeName
                this.user.image =  res.obj.student.avatarUrl
              }
            }
          }
        }
      })
    }
  },
}
</script>
