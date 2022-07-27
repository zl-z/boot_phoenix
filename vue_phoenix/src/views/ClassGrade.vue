<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 100px" placeholder="班级名称" suffix-icon="el-icon-search" v-model="GradeVO.gradeName"></el-input>
      <el-select style="width: 120px" class="ml-5" v-model="GradeVO.classTeacherName" placeholder="班主任">
        <el-option
            v-for="item in classTeacherName"
            :key="item.pkId"
            :label="item.classTeacherName"
            :value="item.classTeacherName">
        </el-option>
      </el-select>
      <el-select style="width: 120px" class="ml-5" v-model="GradeVO.lecturerName" placeholder="讲师">
        <el-option
            v-for="item in lecturerName"
            :key="item.pkId"
            :label="item.lecturerName"
            :value="item.lecturerName">
        </el-option>
      </el-select>
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
    </div>
    <el-table :data="tableData"  border stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55"
          align="left">
      </el-table-column>
      <el-table-column prop="pkId" label="序号" width="150">
      </el-table-column>
      <el-table-column prop="gradeId" label="班级ID" width="120">
      </el-table-column>
      <el-table-column prop="gradeName" label="班级姓名" width="140">
      </el-table-column>
      <el-table-column prop="" label="学生数量">
        <template slot-scope="scope">
          {{ `${scope.row.already}(已有) / ${scope.row.studentsNumber}(总数)` }}
        </template>
      </el-table-column>
      <el-table-column prop="classTeacherName" label="班主任">
      </el-table-column>
      <el-table-column prop="lecturerName" label="讲师">
      </el-table-column>
      <el-table-column prop="classroomClassroomNumber" label="所在教室">
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
              :title="delTitle(scope.row.gradeName, 'grade')"
              @confirm="del(scope.row, 'grade')"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
          <el-button type="primary" class="ml-5" @click="handleView(scope.row)">查看 <i class="el-icon-view"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <!--    :current-page="pageNum"当前页码 :page-size="10" 当前页条数     -->
      <!-- 分页 -->
      <el-pagination
          :current-page.sync="GradeVO.pageNum"
          :page-size="GradeVO.pageSize"
          :page-sizes="[3, 5, 8, 10]"
          :total="GradeVO.total"
          background
          layout="total, sizes ,prev, pager, next, jumper"
          style="padding: 30px 0; text-align: center"
          @current-change="load"
          @size-change="handleSizeChange"
      />
    </div>
    <el-dialog title="班级信息" :visible.sync="dialogFormVisible" :before-close="shutDown" width="38%" style="margin-top: -10vh;">
      <el-form :model="gradeData" label-width="80px" size="small">
        <div style="width:600px;">
          <el-form-item prop="gradeId">
            <el-input size="medium" style="margin: 10px 0" placeholder="班级编号" prefix-icon="el-icon-s-management" v-model.number="gradeData.gradeId"></el-input>
          </el-form-item>
          <el-form-item prop="gradeName">
            <el-input size="medium" style="margin: 10px 0" placeholder="班级名称" prefix-icon="el-icon-s-custom" v-model="gradeData.gradeName"></el-input>
          </el-form-item>
          <div style="overflow: hidden">
            <el-form-item prop="studentsNumber" label="班级人数" style="float: left;">
              <el-input-number v-model.number="gradeData.studentsNumber" :min="0" :max="500" label="容纳人数"></el-input-number>
            </el-form-item>
          </div>
          <div class="select" style="overflow: hidden; margin: 20px 0">
            <el-form-item prop="classTeacherId" class="ses">
              <el-select class="arrive" v-model="gradeData.classTeacherId" @change="choseTea"
                          filterable placeholder="班主任">
                <el-option
                    v-for="item in classTeacherName"
                    :key="item.pkId"
                    :label="item.classTeacherName"
                    :value="item.classTeacherId">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item prop="lecturerId" class="ses">
              <el-select class="arrive" v-model="gradeData.lecturerId" @change="choseLec"
                          filterable placeholder="讲师">
                <el-option
                    v-for="item in lecturerName"
                    :key="item.pkId"
                    :label="item.lecturerName"
                    :value="item.lecturerId">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item prop="classroomClassroomId" class="ses">
              <el-select class="arrive" v-model="gradeData.classroomClassroomId" @change="choseCla"
                          filterable placeholder="所在教室">
                <el-option
                    v-for="item in classroomClassroomNumber"
                    :key="item.pkId"
                    :label="item.classroomClassroomNumber"
                    :value="item.classroomClassroomId">
                </el-option>
              </el-select>
            </el-form-item>
          </div>
        </div>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="shutDown">取 消</el-button>
        <el-button type="primary" @click="save(saveStatus)">确 定</el-button>
      </div>
    </el-dialog>
    <!--  查看外  -->
    <el-dialog :visible.sync="outerVisible" width="88%" @close="closeStu">
      <el-descriptions class="margin-top" title="班级详情" :column="3" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-wallet"></i>
            <span style="font-weight: bold"> 班级名称</span>
          </template>
          <el-tag size="small">{{classGrade.gradeName}}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            <span style="font-weight: bold"> 学生数量</span>
          </template>
          <el-tag size="small">{{ `${classGrade.already}(已有) / ${classGrade.studentsNumber}(总数)` }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-office-building"></i>
            <span style="font-weight: bold"> 所在教室</span>
          </template>
          <el-tag size="small">{{classGrade.classroomClassroomNumber}}</el-tag>
        </el-descriptions-item>
      </el-descriptions>
      <div style="margin: 10px 0">
        <el-input style="width: 100px" placeholder="学生姓名" suffix-icon="el-icon-search" v-model="stuViewVO.studentName"></el-input>
        <el-input style="width: 100px" placeholder="手机号" class="ml-5" suffix-icon="el-icon-search" v-model="stuViewVO.mobile"></el-input>
        <el-select style="width: 120px" class="ml-5" v-model="stuViewVO.sex" placeholder="性别">
          <el-option
              v-for="item in options"
              :key="item.pkId"
              :label="item.label"
              :value="item.value">
          </el-option>
        </el-select>
        <el-button class="ml-5" type="primary" @click="getStuList()">搜索</el-button>
        <el-button type="warning" @click="stuReset">重置</el-button>
        <el-button type="primary" @click="viewStu">添加学生</el-button>
      </div>
      <el-table :data="classStuData"  border stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
        <el-table-column
            type="selection"
            width="55"
            align="left">
        </el-table-column>
        <el-table-column prop="studentNum" label="学号" width="150">
        </el-table-column>
        <el-table-column prop="studentName" label="学生姓名" width="120">
        </el-table-column>
        <el-table-column prop="mobile" label="手机号" width="140">
        </el-table-column>
        <el-table-column prop="sex" label="性别">
          <template slot-scope="scope">
            {{ scope.row.sex === "M" ? "男" : "女" }}
          </template>
        </el-table-column>
        <el-table-column prop="idType" label="证件类型">
        </el-table-column>
        <el-table-column prop="idNum" label="证件号">
        </el-table-column>
        <el-table-column prop="nativePlace" label="籍贯">
        </el-table-column>
        <el-table-column label="操作" align="left" fixed="right">
          <template slot-scope="scope">
            <el-popconfirm
                class="ml-5"
                confirm-button-text='确定'
                cancel-button-text='我再想想'
                icon="el-icon-info"
                icon-color="red"
                :title="delTitle(scope.row.studentName, 'stu')"
                @confirm="del(scope.row, 'stu')"
            >
              <el-button type="danger" slot="reference">删除 <i class="el-icon-delete"></i></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <div style="padding: 10px 0">
        <!--    :current-page="pageNum"当前页码 :page-size="10" 当前页条数     -->
        <!-- 分页 -->
        <el-pagination
            :current-page.sync="stuViewVO.pageNum"
            :page-size="stuViewVO.pageSize"
            :page-sizes="[3, 5, 8, 10]"
            :total="stuViewVO.total"
            background
            layout="total, sizes ,prev, pager, next, jumper"
            style="padding: 30px 0; text-align: center"
            @current-change="getStuList"
            @size-change="handleSizeStuChange"
        />
      </div>
      <!--  查看内  -->
      <el-dialog
          width="80%"
          :visible.sync="innerVisible"
          title="学生信息汇总"
          append-to-body>
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
          <el-button class="ml-5" type="primary" @click="getStudent()">搜索</el-button>
          <el-button type="warning" @click="resetStudent">重置</el-button>
        </div>
        <el-table :data="studentDate"  border stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
          <el-table-column
              type="selection"
              width="55"
              align="left">
          </el-table-column>
          <el-table-column prop="studentNum" label="学号" width="150">
          </el-table-column>
          <el-table-column prop="studentName" label="学生姓名" width="120">
          </el-table-column>
          <el-table-column prop="mobile" label="手机号" width="140">
          </el-table-column>
          <el-table-column prop="" label="性别">
            <template slot-scope="scope">
              {{ scope.row.sex === "M" ? "男" : "女" }}
            </template>
          </el-table-column>
          <el-table-column prop="idType" label="证件类型">
          </el-table-column>
          <el-table-column prop="idNum" label="证件号码" width="200">
          </el-table-column>
          <el-table-column prop="nativePlace" label="籍贯" width="300">
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间" width="100">
          </el-table-column>
          <el-table-column prop="updateTime" label="修改时间" width="100">
          </el-table-column>
          <el-table-column label="操作" width="280" align="left" fixed="right">
            <template slot-scope="scope">
              <el-button type="success" @click="handleEditAddClass(classGrade, scope.row)">添加 <i class="el-icon-edit-outline"></i></el-button>
              <el-button type="warning" class="ml-5" @click="handleEditStu(scope.row)">详细信息 <i class="el-icon-s-management"></i></el-button>
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
              @current-change="getStudent"
              @size-change="handleStudentChange"
          />
        </div>
        <el-dialog
            width="80%"
            :visible.sync="innerVisibles"
            append-to-body>
          <el-descriptions :title='StuName' :column="3" border>
          <el-descriptions-item label="学号" label-class-name="my-label" content-class-name="my-content">{{studentDates.studentNum}}</el-descriptions-item>
          <el-descriptions-item label="姓名">{{studentDates.studentName}}</el-descriptions-item>
          <el-descriptions-item label="性别">{{studentDates.sex === "M" ? "男" : "女"}}</el-descriptions-item>
          <el-descriptions-item label="年龄">{{studentDates.age}}</el-descriptions-item>
          <el-descriptions-item label="手机号">
            <el-tag size="small">{{studentDates.mobile}}</el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="证件类型">{{studentDates.idType}}</el-descriptions-item>
          <el-descriptions-item label="证件号码">{{studentDates.idNum}}</el-descriptions-item>
          <el-descriptions-item label="生日">{{studentDates.birthday}}</el-descriptions-item>
          <el-descriptions-item label="籍贯">{{studentDates.nativePlace}}</el-descriptions-item>
          <el-descriptions-item label="详细地址">{{studentDates.detailAddr}}</el-descriptions-item>
        </el-descriptions>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="innerVisibles = false">返 回</el-button>
          </div>
        </el-dialog>
      </el-dialog>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "ClassGrade",
  data(){
    return {
      tableData: [], //班级数据
      GradeVO:{ //模糊查数据-分页
        gradeName: '',
        classTeacherName: '',
        lecturerName: '',
        total: 0, //总条数
        pageNum: 1, //页码
        pageSize: 5, //条数
      },
      dialogFormVisible: false, //添加/修改弹窗
      outerVisible: false, //查看外层弹窗
      innerVisible: false, //查看内层弹窗
      innerVisibles: false, //内内层弹窗
      gradeData:{//注册信息
        gradeId: '',   //班级编号
        gradeName: '',  //班级名称
        studentsNumber: 0, //学生总数量
        classTeacherId: '',  //班主任ID
        classTeacherName: '',  //班主任
        lecturerId: '',      //讲师ID
        lecturerName: '',      //讲师
        classroomClassroomId: '',  //教室ID
        classroomClassroomNumber: '',  //教室
      },
      classTeacherName: [], //班主任
      lecturerName: [], //教师
      classroomClassroomNumber: [], //教室
      saveStatus: false, //添加 / 修改 弹窗状态
      multipleSelection: [], //勾选框
      classStuData: [], //班级内学生信息
      classGrade: {}, //查看班级信息
      stuViewVO: { //查看班级学生模糊查
        gradeName: '',
        studentName: '',
        mobile: '',
        sex: '',
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
      studentDate: [], //内弹窗学生信息
      StuName: '', //学生信息标头
      studentVO: { //模糊查数据-分页
        studentName: '',
        mobile: '',
        sex: '',
        age: '',
        idNum: '',
        total: 0, //总条数
        pageNum: 1, //页码
        pageSize: 5, //条数
        theClass: false, //没入班学生
      },
      studentDates: [], //详细学生信息
    }
  },
  created() {//渲染前，模板编译前，通常初始化某些属性值，
    //请求分页数据
    this.load();
    //班主任
    this.getTeacher();
  },
  methods: {
    //模糊查-分页  {params:{xx:xx,xx:xx}}
    load(pageNum = 1){ //当前页码,如果undefined，赋值为1
      this.GradeVO.pageNum = pageNum //改变页码有值，直接赋值
      this.request.post("/grade/listGradeInfo", this.GradeVO).then(
          res => {
            if(res.code === '200'){
              this.tableData = res.obj.data //更新数据
              this.GradeVO.total = res.obj.total;//更新总条数
            }else {
              this.$message.error(res.obj)
            }
          })
    },
    //重置输入框
    reset(){
      this.GradeVO.gradeName = ''
      this.GradeVO.classTeacherName = ''
      this.GradeVO.lecturerName = ''
      this.load()
    },
    //当前页条数改变
    handleSizeChange(pageSize){
        this.GradeVO.pageSize = pageSize;
        this.load()
    },
    //班级学生页条数改变
    handleSizeStuChange(pageSize){
      this.stuViewVO.pageSize = pageSize;
      this.getStuList()
    },
    //学生信息页条数改变
    handleStudentChange(pageSize){
      this.studentVO.pageSize = pageSize;
      this.getStudent()
    },
    //班主任
    getTeacher() {
      this.request.get("/grade/getTeacher").then(
          res => {
            this.classTeacherName = res.obj;
            this.getLecturer();
          })
    },
    //讲师
    getLecturer() {
      this.request.get(`/grade/getLecturerName`).then(
          res => {
            this.lecturerName = res.obj;
          })
    },
    //教室
    getClassRoom() {
      this.request.get(`/grade/getClassRoom`).then(
          res => {
            this.classroomClassroomNumber = res.obj;
          })
    },
    //班主任 ID / 名称
    choseTea(){
      let obj = {}
      obj = this.classTeacherName.find((item) => { // 这里的teacher就是上面班主任的数据
        return item.classTeacherId === this.gradeData.classTeacherId // 筛选出匹配数据，这里的id是接口数据中的每一项的id
      })
      this.gradeData.classTeacherName = obj.classTeacherName
    },
    //讲师 ID / 名称
    choseLec(){
      let obj = {}
      obj = this.lecturerName.find((item) => { // 这里的teacher就是上面班主任的数据
        return item.lecturerId === this.gradeData.lecturerId // 筛选出匹配数据，这里的id是接口数据中的每一项的id
      })
      this.gradeData.lecturerName = obj.lecturerName
    },
    //教室 ID / 名称
    choseCla(){
      let obj = {}
      obj = this.classroomClassroomNumber.find((item) => { // 这里的teacher就是上面班主任的数据
        return item.classroomClassroomId === this.gradeData.classroomClassroomId // 筛选出匹配数据，这里的id是接口数据中的每一项的id
      })
      this.gradeData.classroomClassroomNumber = obj.classroomClassroomNumber
    },
    //添加弹窗
    handleAdd(){
      this.gradeData = this.$options.data().gradeData
      this.dialogFormVisible = true
      this.saveStatus = false
      this.getClassRoom()
    },
    //添加/修改信息
    save(val){
      if(val){
        this.request.post("/grade/updateGrade", this.gradeData).then(res => {
          if(res.code === "200"){
            this.dialogFormVisible = false
            this.$message.success(`修改班级：${this.gradeData.gradeName}成功`)
            this.load();
          }else{
            this.dialogFormVisible = true
            this.$message.error(res.obj)
          }
        })
      }else {
         this.request.post("/grade/insertGrade", this.gradeData).then(res => {
           if(res.code === "200"){
             this.dialogFormVisible = false
             this.$message.success(`添加班级：${this.gradeData.gradeName}成功`)
             this.load();
           }else {
             this.dialogFormVisible = true
             this.$message.error(res.obj);
           }
         })
      }
    },
    //修改弹窗
    handleEdit(row){
      this.gradeData = row //把一行内容赋值到弹窗内
      this.dialogFormVisible = true
      this.saveStatus = true
      this.getClassRoom(); //修改初始化教室
    },
    //关闭弹窗
    shutDown(){
      this.dialogFormVisible = false
      this.load();
    },
    //删除提示
    delTitle(name, type){
      if(type === 'grade'){
        return `您确定删除班级：${name} ？`;
      }else {
        return `您确定删除学生：${name} ？`;
      }
    },
    //删除弹窗
    del(row, type){
      if(type === 'grade'){
        if(row.already){
          this.$message.warning("请清空班级内学生信息，再进行删除班级操作！")
        }else {
          this.request.post("/grade/delGradeInfo", [row.gradeId]).then(res => {
            if(res.code === "200"){
              this.$message.success(`删除班级：${row.gradeName} 成功`)
              this.load();
            }else{
              this.$message.error(res.obj)
              this.load();
            }
          })
        }
      }else {
        this.request.post(`/grade/delClassStu`, [row.studentId]).then(res => {
          if(res.code === "200"){
            this.$message.success(`删除学生：${row.studentName} 成功`)
            this.getStuList();
          }else{
            this.$message.error(res.obj)
            this.getStuList();
          }
        })
      }
    },
    //勾选框
    handleSelectionChange(val){
      this.multipleSelection = val //把勾选的对象存入数组
    },
    //批量删除
    delBatch(){
      let gradeIds = this.multipleSelection.map(gra => gra.gradeId) //扁平化处理 [{}, {}, {}] => [1,2,3]
      let gradeAlreadys = this.multipleSelection.map(gra => gra.already) //扁平化处理 [{}, {}, {}] => [1,2,3]
      let exert = gradeAlreadys.find((item) => {
        return item === true ? false : true; // 筛选出匹配数据，这里的id是接口数据中的每一项的id
      }) //判断选中班级是否存在学生
      if(!exert){
        this.request.post("/grade/delGradeInfo", gradeIds).then(res => {
          if(res.code === "200"){
            this.$message.success(`批量删除 ${this.multipleSelection.length} 个班级信息成功`)
            this.load();
          }else{
            this.$message.error(res.obj)
            this.load();
          }
        })
      }else {
        this.$message.warning("请清空班级内学生信息，再进行删除班级操作！")
      }
    },
    //打开弹窗查看班级学生信息
    handleView(stuInfo){
      this.classGrade = stuInfo
      this.outerVisible = true
      this.stuViewVO.gradeName = stuInfo.gradeName
      this.stuReset()
      this.getStuNum(stuInfo.gradeName)
    },
    //更新学生数量
    getStuNum(gradeName){
      this.request.get(`/grade/getStuNum/${gradeName}`).then(
          res => {
            if(res.code === '200'){
              console.log(res.obj.already)
              this.classGrade.already = res.obj.already
              this.classGrade.studentsNumber = res.obj.studentsNumber
            }else {
              this.$message.error(res.obj)
            }
          })
    },
    //班级学生模糊查
    getStuList(pageNum = 1){
      this.stuViewVO.pageNum = pageNum //改变页码有值，直接赋值
      this.request.post("/grade/getStuInfo", this.stuViewVO).then(res => {
        if(res.code === "200"){
          this.classStuData = res.obj.data //更新数据
          this.stuViewVO.total = res.obj.total;//更新总条数
        }else{
          this.$message.error(res.obj)
        }
      })
    },
    //班级学生重置
    stuReset(){
      this.stuViewVO.studentName = ''
      this.stuViewVO.mobile = ''
      this.stuViewVO.sex = ''
      this.getStuList()
    },
    //班级学生外弹窗关闭时
    closeStu(){
      //全查班级
      this.load()
    },
    //内弹窗学生信息
    viewStu(){
      this.innerVisible = true
      this.getStudent();
    },
    //弹窗内学生信息
    getStudent(pageNum = 1){
      this.studentVO.pageNum = pageNum //改变页码有值，直接赋值
      this.request.post("/basic-info/listStudent", this.studentVO).then(
          res => {
            this.studentDate = res.obj.records //更新数据
            this.studentVO.total = res.obj.total;//更新总条数
          })
    },
    //学生信息重置
    resetStudent(){
      this.studentVO.studentName = ''
      this.studentVO.mobile = ''
      this.studentVO.sex = ''
      this.studentVO.age = ''
      this.studentVO.idNum = ''
      this.getStudent()
    },
    //学生添加到班级
    handleEditAddClass(classGrade, student){
      console.log(student.studentId) //学生ID
      console.log(classGrade.gradeId) //班级ID
      console.log(classGrade.gradeName) //班级名称
      console.log(classGrade.already) //已有学生
      console.log(classGrade.studentsNumber) //现有学生
      let gradeStu = {
        studentId: student.studentId,
        gradeId: classGrade.gradeId,
        gradeName: classGrade.gradeName
      }
      if(classGrade.already === classGrade.studentsNumber){
        this.$message.warning("该班级学生人数已达标，请选择其他班级添加！")
        this.innerVisible = false
      }else {
        this.request.post("/grade/insertGradeStudent", gradeStu).then(
            res => {
              if(res.code === '200'){
                this.getStuNum(classGrade.gradeName)
                this.$message.success(`添加学生：${student.studentName} 到${classGrade.gradeName} 班级成功！`)
                this.innerVisible = false
                this.stuReset()
              }else {
                this.$message.error(res.obj)
              }
            })
      }
    },
    //学生详细信息
    handleEditStu(row){
      this.innerVisibles = true
      this.studentDates = row //把一行内容赋值到弹窗内
      this.StuName = `学生：${this.studentDates.studentName} 详细信息`
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
/*详细信息背景色*/
.my-label {
  background: #E1F3D8;
}
.my-content {
  background: #FDE2E2;
}
</style>