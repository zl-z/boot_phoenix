<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 100px" placeholder="姓名" suffix-icon="el-icon-search" v-model="studentVO.studentName"></el-input>
      <el-input style="width: 120px" placeholder="手机号" suffix-icon="el-icon-mobile-phone" class="ml-5" v-model="studentVO.mobile"></el-input>
      <el-select style="width: 80px" class="ml-5" v-model="studentVO.sex" placeholder="性别">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-input style="width: 80px" placeholder="年龄" suffix-icon="el-icon-position" class="ml-5" v-model.number="studentVO.age"></el-input>
      <el-input style="width: 200px" placeholder="证件号码" suffix-icon="el-icon-position" class="ml-5" v-model="studentVO.idNum"></el-input>
      <el-button class="ml-5" type="primary" @click="load()">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量对这些数据，进行删除吗？"
          @confirm="delBatch">
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <el-upload action="http://127.0.0.1:9090/phoenix/register/excelImport" :show-file-list="false" :on-success="excelImport" :on-error="excelImportError" accept=".xlsx" style="display: inline-block">
      <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
      </el-upload>
      <el-button class="ml-5" type="primary" @click="excelExport">导出 <i class="el-icon-top"></i></el-button>
    </div>
    <el-table :data="tableData"  border stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55"
          align="left">
      </el-table-column>
      <el-table-column prop="studentNum" label="序号" width="150">
      </el-table-column>
      <el-table-column prop="studentName" label="班级ID" width="120">
      </el-table-column>
      <el-table-column prop="mobile" label="班级姓名" width="140">
      </el-table-column>
      <el-table-column prop="sex" label="学生数量">
        <template slot-scope="scope">
          {{ scope.row.sex === "M" ? "男" : "女" }}
        </template>
      </el-table-column>
      <el-table-column prop="age" label="班主任">
      </el-table-column>
      <el-table-column prop="idType" label="讲师">
      </el-table-column>
      <el-table-column prop="idNum" label="所在班级">
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="100">
      </el-table-column>
      <el-table-column prop="updateTime" label="修改时间" width="100">
      </el-table-column>
      <el-table-column label="操作" width="280" align="left" fixed="right">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              :title="delTitle(scope.row.studentName)"
              @confirm="del(scope.row.studentId)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
          <el-button type="success" class="ml-5" @click="handleEdit(scope.row)">查看 <i class="el-icon-edit"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <!--    :current-page="pageNum"当前页码 :page-size="10" 当前页条数     -->
      <!-- 分页 -->
      <el-pagination
          :current-page.sync="studentVO.pageNum"
          :page-size="studentVO.pageSize"
          :page-sizes="[3, 5, 8, 10]"
          :total="studentVO.total"
          background
          layout="total, sizes ,prev, pager, next, jumper"
          style="padding: 30px 0; text-align: center"
          @current-change="load"
          @size-change="handleSizeChange"
      />
    </div>
    <el-dialog title="学生信息" :visible.sync="dialogFormVisible" width="70%" style="margin-top: -10vh;">
      <el-form :model="studentData" label-width="80px" size="small" :rules="rules" ref="studentForm">
        <div style="overflow: hidden">
          <div style="width:600px;float: left;">
            <el-form-item prop="studentNum">
              <el-input size="medium" style="margin: 10px 0" placeholder="学号" prefix-icon="el-icon-s-management" v-model.number="studentData.studentNum"></el-input>
            </el-form-item>
            <el-form-item prop="studentName">
              <el-input size="medium" style="margin: 10px 0" placeholder="姓名" prefix-icon="el-icon-s-custom" v-model="studentData.studentName"></el-input>
            </el-form-item>
            <el-form-item prop="email">
              <el-input size="medium" style="margin: 10px 0" placeholder="邮箱" :disabled="diasabledInput" prefix-icon="el-icon-message" v-model="studentData.email"></el-input>
            </el-form-item>
            <el-form-item prop="password">
              <el-input size="medium" style="margin: 10px 0" placeholder="密码" :disabled="diasabledInput" prefix-icon="el-icon-lock" show-password v-model="studentData.password"></el-input>
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
          <div style="float: left">
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
            <el-form-item>
              <el-input size="medium" style="margin: 10px 0"  readonly placeholder="籍贯" prefix-icon="el-icon-lock" v-model="studentData.nativePlace"></el-input>
            </el-form-item>
            <div class="select" style="overflow: hidden; margin: 42px 0">
              <el-form-item prop="provinceId" class="ses">
                <el-select class="arrive" @change="choseP" v-model="studentData.provincesId" filterable placeholder="请选择省份">
                  <el-option
                      v-for="item in provinces"
                      :key="item.id"
                      :label="item.province"
                      :value="item.provinceId">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="城市" class="ses">
                <el-select class="arrive" @change="choseC" v-model="studentData.citiesId" :disabled="cityDisable" filterable placeholder="请选择城市">
                  <el-option
                      v-for="item in cities"
                      :key="item.id"
                      :label="item.city"
                      :value="item.cityId">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="区县" class="ses">
                <el-select class="arrive" @change="choseA" v-model="studentData.areasId" :disabled="areaDisable" filterable placeholder="请选择区县">
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
              <el-input size="medium" placeholder="详细地址" prefix-icon="el-icon-lock" v-model="studentData.detailAddr"></el-input>
            </el-form-item>
          </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="shutDown">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Staff",
  data(){
    return {
      tableData: [], //学生数据
      studentVO:{ //模糊查数据-分页
        studentName: '',
        mobile: '',
        sex: '',
        age: '',
        idNum: '',
        total: 0, //总条数
        pageNum: 1, //页码
        pageSize: 5, //条数
      },
      //模糊-性别
      options: [ {
        value: null,
        label: '全部'
      }, {
        value: 'M',
        label: '男'
      }, {
        value: 'F',
        label: '女'
      }],
      dialogFormVisible: false, //添加/修改弹窗
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
      province: '', // 省 赋值籍贯
      citie: '',    // 市 赋值籍贯
      cityDisable: true, //市县下拉框禁用
      areaDisable: true,
      rules: {//限制规则
        studentNum: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { type: 'number', message: '请输入正确的学号(数字)', trigger: 'blur' },
        ],
        studentName: [
          { required: true, message: '姓名不能为空', trigger: 'blur' },
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
        provincesId: [
          { required: true, message: '省份不能为空', trigger: 'blur' },
        ],
        detailAddr: [
          { required: true, message: '详细地址不能为空', trigger: 'blur' },
        ],
      },
      diasabledInput: false, //修改页面隐藏邮箱/密码
      readonly: true, //修改页面 籍贯只读
      multipleSelection: [], //勾选框
    }
  },
  created() {//渲染前，模板编译前，通常初始化某些属性值，
    //请求分页数据
    this.load();
    //省份
    this.choseProvince();
  },
  methods: {
    //模糊查-分页  {params:{xx:xx,xx:xx}}
    load(pageNum = 1){ //当前页码,如果undefined，赋值为1
      this.studentVO.pageNum = pageNum //改变页码有值，直接赋值
      this.request.post("/basic-info/listStudent", this.studentVO).then(
          res => {
            this.tableData = res.obj.records //更新数据
            this.studentVO.total = res.obj.total;//更新总条数
            /*this.tableData = res.data.records //更新数据
            this.studentVO.total = res.data.total;//更新总条数*/
          })
    },
    //重置输入框
    reset(){
      this.studentVO.studentName = ''
      this.studentVO.mobile = ''
      this.studentVO.sex = ''
      this.studentVO.age = ''
      this.studentVO.idNum = ''
      this.load()
    },
    //当前页条数改变
    handleSizeChange(pageSize){
      this.studentVO.pageSize = pageSize;
      this.load()
    },
    //省份
    choseProvince() {
      this.request.post("/provinces/getProvinces").then(
          res => {
            this.provinces = res;
          })
    },
    //城市
    choseP(val) {
      let obj = {}
      obj = this.provinces.find((item) => { // 这里的provincelist就是上面省的数据
        return item.provinceId === val // 筛选出匹配数据，这里的id是接口数据中的每一项的id
      })
      this.studentData.nativePlace = this.province = obj.province
      this.request.post("/provinces/getCities", this.studentData.provincesId).then(
          res => {
            this.cities = res;
            this.cityDisable = false;
            this.areaDisable = true;
            this.studentData.citiesId = '';
            this.studentData.areasId = '';
          })
    },
    //省份
    choseC(val) {
      let obj = {}
      obj = this.cities.find((item) => { // 这里的provincelist就是上面省的数据
        return item.cityId === val // 筛选出匹配数据，这里的id是接口数据中的每一项的id
      })
      // console.log(obj.city) // 这个obj里面就是你想要的select中对应的数据
      this.studentData.nativePlace = `${this.province} / ${(this.citie = obj.city)}`
      this.request.post("/provinces/getAreas", this.studentData.citiesId,).then(
          res => {
            this.areas = res;
            this.areaDisable = false;
            this.studentData.areasId = '';
          })
    },
    //区县
    choseA(val) {
      let obj = {}
      obj = this.areas.find((item) => { // 这里的provincelist就是上面省的数据
        return item.areaId === val // 筛选出匹配数据，这里的id是接口数据中的每一项的id
      })
      // console.log(obj.area) // 这个obj里面就是你想要的select中对应的数据
      this.studentData.nativePlace = `${this.province} / ${this.citie} / ${obj.area}`
    },
    //修改初始化城市
    chosePs() {
      this.request.post("/provinces/getCities", this.studentData.provincesId).then(
          res => {
            this.cities = res;
            this.cityDisable = false;
            let obj = {}
            obj = this.provinces.find((item) => { // 这里的provincelist就是上面省的数据
              return item.provinceId === this.studentData.provincesId // 筛选出匹配数据，这里的id是接口数据中的每一项的id
            })
            this.studentData.nativePlace = this.province = obj.province
            this.choseCs();
          })
    },
    //修改初始化省份
    choseCs() {
      this.request.post("/provinces/getAreas", this.studentData.citiesId).then(
          res => {
            this.areas = res;
            this.areaDisable = false;
            let obj = {}
            obj = this.cities.find((item) => { // 这里的provincelist就是上面省的数据
              return item.cityId === this.studentData.citiesId // 筛选出匹配数据，这里的id是接口数据中的每一项的id
            })
            this.studentData.nativePlace = `${this.province} / ${(this.citie = obj.city)}`
            this.choseAs();
          })
    },
    //修改初始化区县
    choseAs() {
      if(0 != this.areas){
        let obj = {}
        obj = this.areas.find((item) => { // 这里的provincelist就是上面省的数据
          return item.areaId === this.studentData.areasId // 筛选出匹配数据，这里的id是接口数据中的每一项的id
        })
        this.studentData.nativePlace = `${this.province} / ${this.citie} / ${obj.area}`
      }
    },
    //添加弹窗
    handleAdd(){
      if(this.cityDisable === false && this.areaDisable === false){
        this.cityDisable = !this.cityDisable
        this.areaDisable = !this.areaDisable
      }
      this.studentData = this.$options.data().studentData
      this.dialogFormVisible = true
      this.diasabledInput = false
    },
    //添加/修改信息
    save(){
      if(this.diasabledInput === true){
        this.request.post("/register/updateStudentInfo", this.studentData).then(res => {
          if(res.flag === true){
            this.dialogFormVisible = false
            this.$message.success(`修改学生：${this.studentData.studentName}成功`)
            this.load();
          }else{
            this.dialogFormVisible = false
            this.$message.error(res.obj)
            this.load();
          }
        })
      }else{
        this.$refs['studentForm'].validate((valid) => {
          if (valid) {
            this.request.post("/register/insertStudentInfo", this.studentData).then(res => {
              if(res.code === "200"){
                this.dialogFormVisible = false
                this.$message.success(`添加学生：${this.studentData.studentName}成功`)
                this.load();
              }else if(res.code === "203"){
                this.dialogFormVisible = true
                this.$message.error(res.obj)
              }else if(res.code === "500"){
                this.dialogFormVisible = false
                this.$message.error(res.obj)
                this.load();
              }else {
                this.dialogFormVisible = true
                this.$message.error("添加失败，请确认信息完整！")
              }
            })
          }
        });
      }
    },
    //修改弹窗
    handleEdit(row){
      this.studentData = row //把一行内容赋值到弹窗内
      this.dialogFormVisible = true
      this.diasabledInput = true
      this.chosePs(); //修改初始化三级联动
    },
    //关闭弹窗
    shutDown(){
      this.dialogFormVisible = false
      this.load();
    },
    //删除提示
    delTitle(name){
      return `您确定删除学生：${name} ？`;
    },
    //删除弹窗
    del(studentId){
      this.request.post("/register/deleteStudentInfo", studentId).then(res => {
        if(res.flag === true){
          this.$message.success(`删除学生：${this.studentData.studentName}成功`)
          this.load();
        }else{
          this.$message.error(res.obj)
          this.load();
        }
      })
    },
    //勾选框
    handleSelectionChange(val){
      this.multipleSelection = val //把勾选的对象存入数组
    },
    //批量删除
    delBatch(){
      let studentIds = this.multipleSelection.map(stu => stu.studentId) //扁平化处理 [{}, {}, {}] => [1,2,3]
      this.request.post("/register/listDelStudentInfo", studentIds).then(res => {
        if(res.flag === true){
          this.$message.success(`批量删除 ${this.multipleSelection.length} 位学生信息成功`)
          this.load();
        }else{
          this.$message.error(res.obj)
          this.load();
        }
      })
    },
    //Excel 导入
    excelImport(){
      this.$message.success("导入Excel文件成功");
      this.load();
    },
    excelImportError(){
      this.$message.error("导入Excel文件失败，请检查表格内容是否完整！！！");
    },
    //Excel 导出
    excelExport(){
      window.open("http://localhost:9090/phoenix/register/excelExport") //导出路径
    },
  },
}
</script>

<style scoped>
.headerBg{
  background-color: #eee !important;
}
/*表头*/
.el-table__header div{
  margin: 0 auto;
  text-align: center;
}
.el-table--border .el-table__cell:first-child .cell{
  padding-left: 14px;
}
/*表内容*/
.el-table__body div{
  margin: 0 auto;
  text-align: center;
}
/*三级联动*/
.select{
  overflow: hidden;
}
.select .arrive{
  width: 120px;
  overflow: hidden;
}
.select .ses{
  float: left;
  margin: 10px 0;
}
/*性别错误提示*/
.sex .el-form-item__error{
  margin-top: 6px;
  width: 510px;
  text-align: center;
}
</style>