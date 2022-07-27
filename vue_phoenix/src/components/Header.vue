<template>
  <div style="line-height: 60px; display: flex;">
    <div style="flex: 1;">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>
        <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
          <el-breadcrumb-item :to="'/manage'">首页</el-breadcrumb-item>
          <el-breadcrumb-item>{{currentPathName}}</el-breadcrumb-item>
        </el-breadcrumb>
    </div>
    <el-dropdown style="width: 112px; cursor: pointer; margin-right: 40px">
      <div>
        <img :src="user.image" alt=""
             style="width: 32px; position: relative; top: 10px; right: 5px; margin-left: 10px; border-radius: 50%;">
        <span>{{user.name | ellipsis}}</span>
        <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <router-link to="/manage/person">个人信息</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="loginOut">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,    //按钮图标切换
    collapse: {},  //控制侧边收缩  父传子函数，子组件用{}接收，也就是函数类型
    user: Object, //更新后数据
  },
  data(){
    return {
      //本地存储取值  三目判断
      // user : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      firstPlayFlag: true, //登陆成功提示
    }
  },
  methods: {
    //登录成功提示
    success(res){
      this.firstPlayFlag = false,
      this.$notify({
        title: `登录成功`,
        dangerouslyUseHTMLString: true,
        message: `欢迎您回来<b style="color: #42b983"> ${this.name} </b>！`,
        type: 'success'
      });
    },
    //退出登录
    loginOut(){
      this.$router.push('/login')
      this.$message.success("退出成功")
      localStorage.removeItem("user")
      localStorage.removeItem("currentPathName")
    }
  },
  filters: {
    //文字数超出时，超出部分使用...
    ellipsis(value) {
      if (!value) return ''
      if (value.length > 3) {
        return value.slice(0, 3) + '...'
      }
      return value
    }
  },
  computed: { //计算属性
    currentPathName(){
      return this.$store.state.currentPathName; //需要监听的数据
    }
  },
}
</script>

<style scoped>

</style>