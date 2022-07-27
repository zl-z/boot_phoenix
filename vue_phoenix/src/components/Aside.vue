<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center; ">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; margin-right: 5px">
      <b style="color: #fff" v-show="logoTextShow">智慧校园</b>
    </div>
    <el-menu-item index="/manage">
        <i class="el-icon-house"></i>
        <span slot="title">主页</span>
    </el-menu-item>
    <el-submenu index="1-1">
      <template slot="title">
        <i class="el-icon-setting"></i>
        <span slot="title">系统管理</span>
      </template>
        <el-menu-item index="/manage/student">
          <i class="el-icon-s-custom"></i>
          <span slot="title">学生管理</span>
        </el-menu-item>
      <el-menu-item index="/manage/classroom">
        <i class="el-icon-school"></i>
        <span slot="title">教室管理</span>
      </el-menu-item>
        <el-menu-item index="/manage/class">
          <i class="el-icon-menu"></i>
          <span slot="title">班级管理</span>
        </el-menu-item>
      <el-menu-item index="/manage/dorm">
        <i class="el-icon-office-building"></i>
        <span slot="title">宿舍管理</span>
      </el-menu-item>
        <el-menu-item index="/manage/file">
          <i class="el-icon-document"></i>
          <span slot="title">文件管理</span>
        </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,    //控制侧边收缩
    logoTextShow: Boolean,  //后台管理文字隐藏
  },
}
</script>

<style scoped>

</style>