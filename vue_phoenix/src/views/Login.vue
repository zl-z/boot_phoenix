<template>
  <div class="wrapper">
    <div style="margin: 200px auto; background: white; width: 600px; border-radius: 10px; overflow: hidden">
      <el-tabs type="card" v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="学生登录" name="first">
          <el-tabs :tab-position="tabPosition" @tab-click="handleClick" style="height: 260px;">
            <el-tab-pane label="账号密码登录">
              <div style="margin: 0 auto; background-color: #fff; width: 350px; height: 260px; padding: 20px; border-radius: 10px">
                <el-form :model="userData" :rules="stuRule" ref="stuForm">
                  <el-form-item prop="email">
                    <el-input size="medium" style="margin: 10px 0" placeholder="同学您的邮箱？" prefix-icon="el-icon-user" v-model="userData.email"></el-input>
                  </el-form-item>
                  <el-form-item prop="passWord">
                    <el-input size="medium" style="margin: 10px 0" placeholder="同学您的密码？" prefix-icon="el-icon-lock" show-password v-model="userData.passWord"></el-input>
                  </el-form-item>
                  <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="primary" size="medium"  autocomplete="off" @click="getLogin">登录</el-button>
                    <el-button type="warning" size="medium"  autocomplete="off" @click="getRegister">注册</el-button>
                  </el-form-item>
                  <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="text" size="medium"  autocomplete="off" @click="handPass">找回密码</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-tab-pane>
            <el-tab-pane label="邮箱登录">
              <div style="margin: 0 auto; background-color: #fff; width: 350px; height: 260px; padding: 20px; border-radius: 10px">
                <el-form :model="userData" :rules="stuRules" ref="stuEmailForm">
                  <el-form-item prop="email">
                    <el-input size="medium" style="margin: 10px 0" placeholder="同学您的邮箱？" prefix-icon="el-icon-message" v-model="userData.email"></el-input>
                  </el-form-item>
                  <el-form-item prop="code">
                    <el-input size="medium" :style="{width:sizeWidth + 'px',margin:' 10px 0'}" placeholder="同学您的验证码？" prefix-icon="el-icon-lock" v-model="userData.code"></el-input>
                    <el-button type="primary" size="medium" :loading="obtain" :disabled="obtain" class="ml-5" @click="getEmailCode(1)">获取验证码</el-button>
                  </el-form-item>
                  <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="primary" size="medium"  autocomplete="off" @click="getLoginEmail" @keyup.enter.native="enterLogin">登录</el-button>
                    <el-button type="warning" size="medium"  autocomplete="off" @click="getRegister">注册</el-button>
                  </el-form-item>
                  <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="text" size="mid"  autocomplete="off" @click="handPass">找回密码</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-tab-pane>
        <el-tab-pane label="教师登录" name="second">
          <el-tabs :tab-position="tabPosition" @tab-click="handleClick" style="height: 260px;">
            <el-tab-pane label="账号密码登录">
              <div style="margin: 0 auto; background-color: #fff; width: 350px; height: 260px; padding: 20px; border-radius: 10px">
                <el-form :model="userData" :rules="teaRule" ref="teaForm">
                  <el-form-item prop="email">
                    <el-input size="medium" style="margin: 10px 0" placeholder="老师您的邮箱？" prefix-icon="el-icon-user" v-model="userData.email"></el-input>
                  </el-form-item>
                  <el-form-item prop="passWord">
                    <el-input size="medium" style="margin: 10px 0" placeholder="老师您的密码？" prefix-icon="el-icon-lock" show-password v-model="userData.passWord"></el-input>
                  </el-form-item>
                  <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="primary" size="medium"  autocomplete="off" @click="getLogin" @keyup.enter.native="enterLogin">登录</el-button>
                    <el-button type="warning" size="medium"  autocomplete="off" @click="getRegister">注册</el-button>
                  </el-form-item>
                  <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="text" size="mid"  autocomplete="off" @click="handPass">找回密码</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-tab-pane>
            <el-tab-pane label="邮箱登录">
              <div style="margin: 0 auto; background-color: #fff; width: 350px; height: 260px; padding: 20px; border-radius: 10px">
                <el-form :model="userData" :rules="teaRules" ref="teaEmailForm">
                  <el-form-item prop="email">
                    <el-input size="medium" style="margin: 10px 0" placeholder="老师您的邮箱？" prefix-icon="el-icon-message" v-model="userData.email"></el-input>
                  </el-form-item>
                  <el-form-item prop="code">
                    <el-input size="medium" :style="{width:sizeWidth + 'px',margin:' 10px 0'}" placeholder="老师您的验证码？" prefix-icon="el-icon-lock" v-model="userData.code"></el-input>
                    <el-button type="primary" size="medium" :loading="obtain" :disabled="obtain" class="ml-5" @click="getEmailCode(1)">获取验证码</el-button>
                  </el-form-item>
                  <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="primary" size="medium"  autocomplete="off" @click="getLoginEmail" @keyup.enter.native="enterLogin">登录</el-button>
                    <el-button type="warning" size="medium"  autocomplete="off" @click="getRegister">注册</el-button>
                  </el-form-item>
                  <el-form-item style="margin: 10px 0; text-align: right">
                    <el-button type="text" size="mid"  autocomplete="off" @click="handPass">找回密码</el-button>
                  </el-form-item>
                </el-form>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-tab-pane>
      </el-tabs>
    </div>
    <el-dialog title="找回密码" :visible.sync="dialogFormVisible" style="margin-top: 8vh" width="30%">
      <el-form label-width="100px" size="small">
        <el-form-item label="邮箱">
          <el-input size="small" style="width: 288px" v-model="pass.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="验证码">
          <el-input size="small" :style="{width:sizeWidth + 'px'}" placeholder="同学您的验证码？" v-model="pass.code"></el-input>
          <el-button type="primary" size="small" :loading="obtain" :disabled="obtain" class="ml-5" @click="getEmailCode(2)">获取验证码</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="passwordBack">密码重置</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

export default {
    name:'Login',
    data() {
        return {
          activeName: 'first', //顶部栏默认
          tabPosition: 'left', //侧边栏
          userData:{//登录信息
                email:'',
                passWord:'',
                code:'',
                userCode:'0'
            },
          //学生验证
          stuRule: {//学生账号登录限制规则
            email: [
              { required: true, message: '请输入邮箱地址', trigger: 'blur' },
              { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
            ],
            passWord: [
              {required: true, message: '请输入密码', trigger: 'blur'},
              {min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur'}
            ],
          },
          //邮箱
          stuRules: {//学生邮箱登录限制规则
            email: [
              { required: true, message: '请输入邮箱地址', trigger: 'blur' },
              { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
            ],
            code: [
              {required: true, message: '请输入验证码', trigger: 'blur'},
              {min: 4, max: 4, message: '长度为 4 个字符', trigger: ['blur', 'change']}
            ],
          },
          //教师验证
          teaRule: {//教师账号登录限制规则
            email: [
              { required: true, message: '请输入邮箱地址', trigger: 'blur' },
              { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
            ],
            passWord: [
              {required: true, message: '请输入密码', trigger: 'blur'},
              {min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur'}
            ],
          },
          //邮箱
          teaRules: {//教师邮箱登录限制规则
            email: [
              { required: true, message: '请输入邮箱地址', trigger: 'blur' },
              { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
            ],
            code: [
              {required: true, message: '请输入验证码', trigger: 'blur'},
              {min: 4, max: 4, message: '长度为 4 个字符', trigger: ['blur', 'change']}
            ],
          },
          obtain: false, // 获取验证码图标 / 禁用
          sizeWidth: 192, //获取验证码默认宽度
          dialogFormVisible: false, //找回密码弹窗
          pass:{},
        }
    },
  created() {
      //初始化回车事件
      this.enterLogin()
  },
  methods:{
    //这是定义的触发回车的事件 此函数必须初始化的时候执行一次此函数 否则回车事件不生效
    enterLogin(){
      document.onkeydown =(e) => {
        e = window.event || e;
        if(this.$route.path=='/login'&&(e.code=='Enter'||e.code=='enter')){
          //调用登录事件方法
          this.getLogin();
        }
      }
    },
    //Tabs标签页
    handleClick(){
      this.userData = {}
      if(this.activeName === 'first'){
        this.userData.userCode = '0'
      }else {
        this.userData.userCode = '1'
      }
    },
    //登录方法
    getLogin(){
      if(this.userData.userCode === '0'){
        this.$refs['stuForm'].validate((valid) => {
          if (valid) {  //表单校验合法
            this.login();
          }
        });
      }else{
        this.$refs['teaForm'].validate((valid) => {
          if (valid) {  //表单校验合法
            this.login();
          }
        });
      }
    },
    //登录
    login(){
      this.request.post("/register/getStudentLogin", this.userData).then(
          res => {
            if(res.code === '200'){
              localStorage.setItem("user", JSON.stringify(res.obj)) //存储用户信息到本地浏览器
              this.$router.push("/manage")
              this.$message.success("登录成功")
            }else if(res.code === '404'){
              this.$message.error(res.obj)
            }else{
              this.$message.error(res.obj)
            }
          })
    },
    //邮箱验证码登录
    getLoginEmail(){
      if(this.userData.userCode === '0') {
        this.$refs['stuEmailForm'].validate((valid) => {
          if (valid) {  //表单校验合法
            this.loginEmail();
          }
        });
      }else {
        this.$refs['teaEmailForm'].validate((valid) => {
          if (valid) {  //表单校验合法
            this.loginEmail();
          }
        });
      }
    },
    //邮箱验证登录
    loginEmail(){
      this.request.post("/register/getLoginEmail", this.userData).then(
          res => {
            if(res.code === '200'){
              localStorage.setItem("user", JSON.stringify(res.obj)) //存储用户信息到本地浏览器
              this.$router.push("/manage")
              this.$message.success("登录成功")
            }else if(res.code === '404'){
              this.$message.error(res.obj)
            }else{
              this.$message.error(res.obj)
            }
          })
    },
    //发送邮箱验证码
    getEmailCode(type){
      this.userData.type = type
      if(type === 2){
        this.userData.email = this.pass.email;
      }
      if(!this.userData.email){
        this.$message.warning("请输入邮箱账号")
        return
      }
      //邮箱正则
      if(!/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/.test(this.userData.email)){
        this.$message.warning("请输入正确的邮箱账号")
        return
      }
      this.obtain = true;   //验证码加载图标
      this.sizeWidth = 174; //验证码框
      // 发送邮箱验证码
      this.request.post("/register/sendEmailCode", this.userData).then(res => {
        this.obtain = false;
        this.sizeWidth = 192;
        if(res.code === '200'){
          this.$message.success("发送成功")
        }else{
          this.$message.error(res.obj)
        }
      })
    },
    //找回密码
    handPass(){
      this.dialogFormVisible = true;
      this.pass = {}
    },
    //密码重置
    passwordBack(){
      if(!this.pass.email){
        this.$message.warning("请输入邮箱账号")
        return
      }
      if(!this.pass.code){
        this.$message.warning("请输入验证码")
        return
      }
      if(!this.pass.code.length === 4){
        this.$message.warning("验证码的长度为四位字符")
        return
      }
      //邮箱正则
      if(!/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/.test(this.pass.email)){
        this.$message.warning("请输入正确的邮箱账号")
        return
      }
      this.pass.userCode = this.userData.userCode;
      this.request.post("/register/resetPass", this.pass).then(res => {
        if(res.code === '200'){
          this.prompt(res.obj);
          this.dialogFormVisible = false
        }else{
          this.$message.error(res.obj)
        }
      })
    },
    //重置密码成功提示
    prompt(obj){
      this.$notify({
        title: '您的密码重置成功',
        dangerouslyUseHTMLString: true,
        message: `新密码为：<b style="color: red"> ${obj.P} </b> ，请尽快修改您的密码，并妥善保管！`,
        position: 'bottom-right'
      });
    },
    //注册按钮
    getRegister(){
      this.$router.push('/register');
    },
  }
}
</script>

<style>
.wrapper {
  /*视图高度100%*/
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B , #3F5EFB);
  overflow: hidden;
}
</style>