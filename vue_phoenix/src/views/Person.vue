<template>
<el-card style="width: 1342px">
  <el-form :model="userData" label-width="80px" size="medium" :rule="rule" ref="studentForm">
    <div style="overflow: hidden">
      <div style="width:600px;float: left;">
        <el-upload
            class="avatar-uploader"
            action="http://127.0.0.1:9090/phoenix/file/upload"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
            :before-upload="beforeAvatarUpload">
          <img v-if="userData.avatarUrl" :src="userData.avatarUrl" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <el-form-item prop="studentNum">
          <el-input style="margin: 10px 0 0 0" placeholder="学号" prefix-icon="el-icon-s-management" v-model.number="userData.studentNum"></el-input>
        </el-form-item>
        <el-form-item prop="studentName">
          <el-input placeholder="姓名" prefix-icon="el-icon-s-custom" v-model="userData.studentName"></el-input>
        </el-form-item>
        <el-form-item prop="age">
          <el-input placeholder="年龄" prefix-icon="el-icon-female" v-model.number="userData.age"></el-input>
        </el-form-item>
        <el-form-item prop="idType" label="证件类型">
          <el-input placeholder="证件类型" prefix-icon="el-icon-suitcase" v-model="userData.idType"></el-input>
        </el-form-item>
        <el-form-item label="证件号码">
          <el-input placeholder="证件号码" prefix-icon="el-icon-lock" v-model.number="userData.idNum"></el-input>
        </el-form-item>
      </div>
      <div style="width:600px;float: left">
        <el-form-item prop="mobile">
          <el-input placeholder="手机号" disabled prefix-icon="el-icon-mobile-phone" v-model.number="userData.mobile"></el-input>
        </el-form-item>
        <el-form-item prop="birthday">
          <el-date-picker
              style=" width: 100%"
              v-model="userData.birthday"
              align="right"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="选择生日"
              :picker-options="pickerOptions">
          </el-date-picker>
        </el-form-item>
        <el-form-item prop="nativePlace" label="籍贯">
          <el-input placeholder="籍贯" readonly prefix-icon="el-icon-lock" v-model="userData.nativePlace"></el-input>
        </el-form-item>
        <div class="select" style="overflow: hidden;">
          <el-form-item prop="provinceId" class="ses">
            <el-select class="arrive" @change="choseCity" v-model="userData.provincesId" filterable placeholder="请选择省份">
              <el-option
                  v-for="item in provinces"
                  :key="item.id"
                  :label="item.province"
                  :value="item.provinceId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="城市" class="ses">
            <el-select class="arrive" @change="choseArea" v-model="userData.citiesId" :disabled="cityDisable" filterable placeholder="请选择城市">
              <el-option
                  v-for="item in cities"
                  :key="item.id"
                  :label="item.city"
                  :value="item.cityId">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item prop="区县" class="ses">
            <el-select class="arrive" v-model="userData.areasId" :disabled="areaDisable" filterable placeholder="请选择区县">
              <el-option
                  v-for="item in areas"
                  :key="item.id"
                  :label="item.area"
                  :value="item.areaId">
              </el-option>
            </el-select>
          </el-form-item>
        </div>
        <el-form-item prop="detailAddr" label="地址">
          <el-input type="textarea" placeholder="详细地址" prefix-icon="el-icon-lock" v-model="userData.detailAddr"></el-input>
        </el-form-item>
        <div slot="footer" class="dialog-footer" style="float: right">
          <el-button type="primary" @click="save">确 定</el-button>
        </div>
      </div>
    </div>
  </el-form>
</el-card>
</template>

<script>
export default {
  name: "Person",
  data(){
    return {
      userData:{//注册信息
      },
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
      userInfoId: {  //查询用户信息
        studentId:'',
        userCode:''
      },
      rule: {//限制规则
        studentNum: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { type: 'number', message: '请输入正确的学号(数字)', trigger: 'blur' },
        ],
        studentName: [
          { required: true, message: '姓名不能为空', trigger: 'blur' },
          {min: 2, max: 6, message: '长度在 2 到 6 个字符', trigger: 'blur'}
        ],
        mobile: [
          { required: true, message: '手机号不能为空', trigger: 'blur' },
          { type: 'number', message: '手机号为11位数字  例：136xxxxx631', trigger: 'blur'}
        ],
        sex: [
          { required: true, message: '请选择您的性别', trigger: 'blur' },
        ],
        age: [
          { required: true, message: '年龄不能为空', trigger: 'blur' },
          { type: 'number', message: '年龄必须为数字值', trigger: 'blur'},
        ],
        idType: [
          { required: true, message: '证件类型不能为空', trigger: 'blur' },
          {min: 2, max: 6, message: '证件类型在 2 到 6 个字符', trigger: 'blur'}
        ],
        idNum: [
          { required: true, message: '证件号码不能为空', trigger: 'blur' },
          { type: 'number', message: '证件号码必须为数字值', trigger: 'blur'},
        ],
        birthday: [
          { required: true, message: '生日不能为空', trigger: 'blur' },
        ],
        nativePlace: [
          { required: true, message: '籍贯不能为空', trigger: 'blur' },
        ],
        provincesId: [
          { required: true, message: '省份不能为空', trigger: 'blur' },
        ],
        detailAddr: [
          { required: true, message: '详细地址不能为空', trigger: 'blur' },
        ],
      },
    }
  },
  created() {
    this.getUserInFo(); //查询用户信息
    this.choseProvince(); //初始化省份
  },
  methods:{
    //查询学生个人信息
    getUserInFo(){
      if(localStorage.getItem("user")){
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
        this.userInfoId = {}
      }
      this.request.post("/register/getUserInfo", this.userInfoId).then(res => {
        if(res.code === '200'){
          if(res.obj.student != null){
            this.userData =  res.obj.student
            this.choseCitys();
            this.choseAreas();
          }
          if(res.obj.teacher != null){
            this.userData =  res.obj.teacher
          }
        }
      })
    },
    //修改个人信息
    save(){
      this.$refs['studentForm'].validate((valid) => {
        if (valid) {  //表单校验合法
          this.request.post("/register/updateStudentInfo", this.userData).then(res => {
          if(res.flag === true){
            this.$message.success(`修改学生：${this.userData.studentName} 成功`)
            //触发父级更新用户(头部)个人信息方法
            this.$emit("refreshUser")
          }else {
            this.$message.error(`修改学生：${this.userData.studentName} 失败`)
          }
        })
        }
      });
    },
    //省份
    choseProvince() {
      this.request.post("/provinces/getProvinces").then(
          res => {
            this.provinces = res;
          })
    },
    //城市
    choseCity() {
      this.request.post("/provinces/getCities", this.userData.provincesId).then(
          res => {
            this.cities = res;
            this.cityDisable = false;
            this.areaDisable = true;
            this.userData.citiesId = '';
            this.userData.areasId = '';
          })
    },
    //区县
    choseArea() {
      this.request.post("/provinces/getAreas", this.userData.citiesId,).then(
          res => {
            this.areas = res;
            this.areaDisable = false;
            this.userData.areasId = '';
          })
    },
    //修改初始化城市
    choseCitys() {
      this.request.post("/provinces/getCities", this.userData.provincesId).then(
          res => {
            this.cities = res;
            this.cityDisable = false;
          })
    },
    //修改初始化区县
    choseAreas() {
      this.request.post("/provinces/getAreas", this.userData.citiesId,).then(
          res => {
            this.areas = res;
            this.areaDisable = false;
          })
    },
    //头像上传
    handleAvatarSuccess(res){
      this.userData.avatarUrl = res
    },
    //上传头像限制
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  }

}
</script>

<style>
.avatar-uploader{
  text-align: center;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 118px;
  height: 118px;
  line-height: 118px;
  text-align: center;
}
.avatar {
  width: 118px;
  height: 118px;
  display: block;
}
</style>