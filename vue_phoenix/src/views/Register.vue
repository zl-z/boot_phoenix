<template>
  <div class="wrapper">
    <div style="margin: 76px auto; background-color: #fff; width: 1050px; height: 630px; padding: 20px; border-radius: 10px">
      <div style="margin: 0 auto; width: 860px;">
        <div style="margin: 20px 0; text-align: center;"><b style="font-size: 24px;">{{userName}}</b></div>
        <el-form :model="studentData" :rules="rules" ref="studentForm">
          <div>
            <div style="width: 400px; height: 480px; float: left;">
            <el-form-item prop="studentNum">
              <el-input size="medium" style="margin: 10px 0" placeholder="学号" prefix-icon="el-icon-s-management" v-model.number="studentData.studentNum"></el-input>
            </el-form-item>
            <el-form-item prop="studentName">
              <el-input size="medium" style="margin: 10px 0" placeholder="姓名" prefix-icon="el-icon-s-custom" v-model="studentData.studentName"></el-input>
            </el-form-item>
            <el-form-item prop="email">
              <el-input size="medium" style="margin: 10px 0" placeholder="邮箱" prefix-icon="el-icon-message" v-model="studentData.email"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input size="medium" style="margin: 10px 0" placeholder="密码" prefix-icon="el-icon-lock" show-password v-model="studentData.password"></el-input>
            </el-form-item>
            <el-form-item prop="mobile">
              <el-input size="medium" style="margin: 10px 0" placeholder="手机号" prefix-icon="el-icon-mobile-phone" v-model.number="studentData.mobile"></el-input>
            </el-form-item>
            <el-form-item prop="sex" class="sex">
              <div style="width: 210px; height: 36px; margin: 0 auto; text-align: center;">
                <el-radio v-model="studentData.sex" label="M" border size="medium">男</el-radio>
                <el-radio v-model="studentData.sex" label="F" border size="medium">女</el-radio>
              </div>
            </el-form-item>
            <el-form-item prop="age">
              <el-input size="medium" style="margin: 10px 0" placeholder="年龄" prefix-icon="el-icon-female" v-model.number="studentData.age"></el-input>
            </el-form-item>
            </div>
            <div style="width: 400px; height: 480px; float: right">
              <el-form-item prop="idType">
                <el-input size="medium" style="margin: 10px 0;" placeholder="证件类型" prefix-icon="el-icon-suitcase" v-model="studentData.idType"></el-input>
              </el-form-item>
              <el-form-item prop="idNum">
                <el-input size="medium" style="margin: 10px 0" placeholder="证件号码" prefix-icon="el-icon-lock" v-model.number="studentData.idNum"></el-input>
              </el-form-item>
              <el-form-item prop="birthday">
                <el-date-picker
                    size="medium"
                    style="margin: 10px 0; width: 100%"
                    v-model="studentData.birthday"
                    align="right"
                    type="date"
                    value-format="yyyy-MM-dd"
                    placeholder="选择生日"
                    :picker-options="pickerOptions">
                </el-date-picker>
              </el-form-item>
              <el-form-item prop="nativePlace">
                <el-input size="medium" style="margin: 10px 0" placeholder="籍贯" prefix-icon="el-icon-lock" v-model="studentData.nativePlace"></el-input>
              </el-form-item>
              <div class="select" style="margin: 10px 0">
                <el-form-item prop="provinceId" class="ses">
                  <el-select class="arrive" @change="choseCity" v-model="studentData.provincesId" filterable placeholder="请选择省份">
                    <el-option
                        v-for="item in provinces"
                        :key="item.id"
                        :label="item.province"
                        :value="item.provinceId">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item prop="城市" class="ses">
                  <el-select class="arrive" @change="choseArea" v-model="studentData.citiesId" :disabled="cityDisable" filterable placeholder="请选择城市">
                    <el-option
                        v-for="item in cities"
                        :key="item.id"
                        :label="item.city"
                        :value="item.cityId">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item prop="区县" class="ses">
                  <el-select class="arrive" v-model="studentData.areasId" :disabled="areaDisable" filterable placeholder="请选择区县">
                    <el-option
                        v-for="item in areas"
                        :key="item.id"
                        :label="item.area"
                        :value="item.areaId">
                    </el-option>
                  </el-select>
                </el-form-item>
              </div>
              <el-form-item prop="detailAddr">
                <el-input size="medium" style="margin: 10px 0" placeholder="详细地址" prefix-icon="el-icon-lock" v-model="studentData.detailAddr"></el-input>
              </el-form-item>
              <el-form-item style="margin: 28px 0; text-align: right; overflow: hidden">
                <el-button type="primary" size="small"  autocomplete="off" @click="getRegister">注册</el-button>
                <el-button type="warning" size="small"  autocomplete="off" @click="$router.push('/login')">返回登陆</el-button>
              </el-form-item>
            </div>
          </div>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>

export default {
    name:'Register',
    data() {
        return {
          studentData:{//注册信息
                studentNum: '',   //学号
                studentName: '',  //姓名
                email:'',         //邮箱
                password:'',      //密码
                mobile: '',       //手机号
                sex: '',          //性别
                age: '',          //年龄
                idType: '',       //证件类型
                idNum: '',        //证件号码
                birthday: '',     //出生日期
                nativePlace: '',  //籍贯
                provincesId: '',  //省
                citiesId: '',     //市
                areasId: '',      //县
                detailAddr: '',   //详细地址
            },
          rules: {//限制规则
            studentNum: [
              { required: true, message: '请输入学号', trigger: 'blur' },
              { type: 'number', message: '请输入正确的学号', trigger: ['blur', 'change'] },
            ],
            studentName: [
              { required: true, message: '姓名不能为空'},
              {min: 2, max: 6, message: '长度在 2 到 6 个字符', trigger: 'blur'}
            ],
            email: [
              { required: true, message: '请输入邮箱地址', trigger: 'blur' },
              { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
            ],
            password: [
              {required: true, message: '请输入密码', trigger: 'blur'},
              {min: 6, max: 16, message: '长度在 6 到 16 个字符', trigger: 'blur'}
            ],
            mobile: [
              { required: true, message: '手机号不能为空'},
              { type: 'number', message: '手机号为11位数字  例：136xxxxx631'}
            ],
            sex: [
              { required: true, message: '请选择您的性别'},
            ],
            age: [
              { required: true, message: '年龄不能为空'},
              { type: 'number', message: '年龄必须为数字值'},
            ],
            idType: [
              { required: true, message: '证件类型不能为空'},
              {min: 2, max: 6, message: '证件类型在 2 到 6 个字符', trigger: 'blur'}
            ],
            idNum: [
              { required: true, message: '证件号码不能为空'},
              { type: 'number', message: '证件号码必须为数字值'},
            ],
            birthday: [
              { required: true, message: '生日不能为空'},
            ],
            nativePlace: [
              { required: true, message: '籍贯不能为空'},
            ],
            provincesId: [
              { required: true, message: '省份不能为空'},
            ],
            detailAddr: [
              { required: true, message: '详细地址不能为空'},
            ],
          },
          userName: '学生注册',
          // 生日
          pickerOptions: {
            disabledDate(time) {
              return time.getTime() > Date.now();
            },
          },
          //三级联动
          provinces: [], // 省
          cities: [],    // 市
          areas: [],     // 县
          cityDisable: true, //市县下拉框禁用
          areaDisable: true,
        }
    },
    methods: {
      getRegister() {
        this.$refs['studentForm'].validate((valid) => {
          if (valid) {  //表单校验合法
            this.request.post("/register/insertStudentInfo", this.studentData).then(
                res => {
                  if (res.code === "200") {
                    this.$router.push("/login")
                    this.$message.success('注册成功！'+this.studentData.studentName)
                  } else if(res.code === "203"){
                    this.$message.error(res.obj)
                  }else if(res.code === "500"){
                    this.$router.push("/register")
                    this.$message.error(res.obj)
                  }else {
                    this.$router.push("/register")
                    this.$message.error("注册失败，请确认信息完整！")
                  }
                })
          }
        });
      },
      //省份
      choseProvince() {
        this.request.post("/provinces/getProvinces").then(
            res => {
              // alert(JSON.stringify(res.data))
              this.provinces = res;
            })
      },
      //城市
      choseCity() {
        this.request.post("/provinces/getCities", this.studentData.provincesId).then(
            res => {
              this.cities = res;
              this.cityDisable = false;
              this.areaDisable = true;
              this.studentData.citiesId = '';
              this.studentData.areasId = '';
            })
      },
      //区县
      choseArea() {
        this.request.post("/provinces/getAreas", this.studentData.citiesId,).then(
            res => {
              this.areas = res;
              this.areaDisable = false;
              this.studentData.areasId = '';
            })
      },
    },
    created() {//挂载钩子
      //省份
      this.choseProvince();
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
/*三级联动*/
.select{
  overflow: hidden;
}
.select .arrive{
  width: 110px;
  margin-left: 18px;
  overflow: hidden;
}
.select .ses{
  float: left;
  margin: 10px 0 0 0;
}
/*性别错误提示*/
.sex .el-form-item__error{
  margin-top: 6px;
  width: 400px;
  text-align: center;
}
</style>