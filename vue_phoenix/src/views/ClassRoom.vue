<template>
  <div>
    <div style="margin: 10px 0; overflow: hidden">
      <div>
        <el-input style="width: 100px" placeholder="教室编号" suffix-icon="el-icon-search" v-model="classPageVO.classroomNumber"></el-input>
        <el-input style="width: 120px" placeholder="教室名称" suffix-icon="el-icon-mobile-phone" class="ml-5" v-model="classPageVO.classroomName"></el-input>
        <span style="font-size: 12px; margin-left: 15px; color: #606266">教室人数 </span>
        <el-input-number v-model.number="classPageVO.begin" :min="0" :max="500" label="教室人数"></el-input-number>
        <el-input-number class="ml-5" v-model.number="classPageVO.over" :min="0" :max="500" label="教室人数"></el-input-number>
      </div>
      <div style="margin-top: 10px">
        <el-select style="width: 140px" v-model="classPageVO.area" @change="getBuildingNo(classPageVO.area, 'check')" placeholder="区域">
          <el-option
              v-for="item in area"
              :key="item.pkId"
              :label="item.area"
              :value="item.area">
          </el-option>
        </el-select>
        <el-select style="width: 140px" class="ml-5" :disabled="noDisable" v-model="classPageVO.buildingNo" @change="getBuildingFloor(classPageVO.area, classPageVO.buildingNo, 'check')" placeholder="楼号">
          <el-option
              v-for="item in buildingNo"
              :key="item.pkId"
              :label="item.buildingNo"
              :value="item.buildingNo">
          </el-option>
        </el-select>
        <el-select style="width: 140px" class="ml-5" :disabled="floorDisable" v-model="classPageVO.buildingFloor" placeholder="楼层">
          <el-option
              v-for="item in buildingFloor"
              :key="item.pkId"
              :label="item.buildingFloor"
              :value="item.buildingFloor">
          </el-option>
        </el-select>
        <el-button class="ml-5" type="primary" @click="load()">搜索</el-button>
        <el-button type="warning" @click="reset">重置</el-button>
      </div>
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
      <el-upload action="http://127.0.0.1:9090/phoenix/classroom/excelImport" :show-file-list="false" :on-success="excelImport" :on-error="excelImportError" accept=".xlsx" style="display: inline-block">
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
      <el-table-column prop="classroomId" label="序号" width="150">
      </el-table-column>
      <el-table-column prop="area" label="区域" width="120">
      </el-table-column>
      <el-table-column prop="buildingNo" label="楼号" width="140">
      </el-table-column>
      <el-table-column prop="buildingFloor" label="楼层">
      </el-table-column>
      <el-table-column prop="classroomNumber" label="教室编号">
      </el-table-column>
      <el-table-column prop="classroomName" label="教室名称">
      </el-table-column>
      <el-table-column prop="galleryful" label="容纳人数">
      </el-table-column>
      <el-table-column prop="useStatus" label="使用状态">
        <template slot-scope="scope">
          <span v-if="scope.row.useStatus === '0'">使用</span>
          <span v-if="scope.row.useStatus === '1'">未使用</span>
          <span v-if="scope.row.useStatus === '2'">维修中</span>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="100">
      </el-table-column>
      <el-table-column prop="updateTime" label="修改时间" width="100">
      </el-table-column>
      <el-table-column label="操作" width="200" align="left" fixed="right">
        <template slot-scope="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              :title="delTitle(scope.row.classroomName)"
              @confirm="del(scope.row.classroomId)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <!--    :current-page="pageNum"当前页码 :page-size="10" 当前页条数     -->
      <!-- 分页 -->
      <el-pagination
          :current-page.sync="classPageVO.pageNum"
          :page-size="classPageVO.pageSize"
          :page-sizes="[3, 5, 8, 10]"
          :total="classPageVO.total"
          background
          layout="total, sizes ,prev, pager, next, jumper"
          style="padding: 30px 0; text-align: center"
          @current-change="load"
          @size-change="handleSizeChange"
      />
    </div>
    <el-dialog title="教室信息" :visible.sync="dialogFormVisible" :before-close="shutDown" width="38%" style="margin-top: -10vh;">
      <el-form :model="classRoomData" label-width="80px" size="small">
          <div style="width:600px;">
            <el-form-item prop="classroomNumber">
              <el-input size="medium" style="margin: 10px 0" placeholder="教室编号" prefix-icon="el-icon-s-management" v-model.number="classRoomData.classroomNumber"></el-input>
            </el-form-item>
            <el-form-item prop="classroomName">
              <el-input size="medium" style="margin: 10px 0" placeholder="教室名称" prefix-icon="el-icon-s-custom" v-model="classRoomData.classroomName"></el-input>
            </el-form-item>
            <div style="overflow: hidden">
              <el-form-item prop="galleryful" label="容纳人数" style="float: left; margin-left: 90px">
                <el-input-number v-model.number="classRoomData.galleryful" :min="0" :max="500" label="容纳人数"></el-input-number>
              </el-form-item>
              <el-form-item prop="useStatus" label="使用状态" style="float: left; margin-left: 40px">
                <el-select style="width: 120px" class="ml-5" v-model="classRoomData.useStatus" placeholder="使用状态">
                  <el-option
                      v-for="item in options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                  </el-option>
                </el-select>
              </el-form-item>
            </div>
            <div class="select" style="overflow: hidden; margin: 20px 0">
              <el-form-item prop="area" class="ses">
                <el-select class="arrive" v-model="classRoomData.area" allow-create
                           default-first-option @change="getBuildingNo(classRoomData.area, 'add')" filterable placeholder="区域">
                  <el-option
                      v-for="item in area"
                      :key="item.pkId"
                      :label="item.area"
                      :value="item.area">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="buildingNo" class="ses">
                <el-select class="arrive" v-model="classRoomData.buildingNo"  allow-create
                           default-first-option @change="getBuildingFloor(classRoomData.area, classRoomData.buildingNo, 'add')" :disabled="noDataDisable" filterable placeholder="楼号">
                  <el-option
                      v-for="item in buildingNo"
                      :key="item.pkId"
                      :label="item.buildingNo"
                      :value="item.buildingNo">
                  </el-option>
                </el-select>
              </el-form-item>
              <el-form-item prop="buildingFloor" class="ses">
                <el-select class="arrive" v-model="classRoomData.buildingFloor" allow-create
                           default-first-option  :disabled="floorDataDisable" filterable placeholder="楼层">
                  <el-option
                      v-for="item in buildingFloor"
                      :key="item.pkId"
                      :label="item.buildingFloor"
                      :value="item.buildingFloor">
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
  </div>
</template>

<script>
export default {
  name: "ClassRoom",
  data(){
    return {
      tableData: [], //学生数据
      classPageVO:{ //模糊查数据-分页
        classroomNumber: '',
        classroomName: '',
        begin: 0,
        over: 500,
        area: '',
        buildingNo: '',
        buildingFloor: '',
        total: 0, //总条数
        pageNum: 1, //页码
        pageSize: 5, //条数
      },
      dialogFormVisible: false, //添加/修改弹窗
      classRoomData:{//注册信息
        area: '', //区域
        buildingNo: '', //楼号
        buildingFloor: '', //楼层
        classroomNumber: '', //教室编号
        classroomName: '', //教室名称
        galleryful: 0, //容纳人数
        useStatus: '', //教室状态
      },
      //单表联动 / 动态sql
      area: [],          // 区域
      buildingNo: [],    // 楼号
      buildingFloor: [], // 楼层
      province: '', // 省 赋值籍贯
      citie: '',    // 市 赋值籍贯
      noDisable: true, //楼号/楼层 下拉框禁用 模糊查
      floorDisable: true,
      noDataDisable: true, //楼号/楼层 下拉框禁用 添加
      floorDataDisable: true,
      readonly: true, //修改页面 籍贯只读
      saveStatus: false, //弹窗状态
      multipleSelection: [], //勾选框
      //添加-教室状态
      options: [ {
        value: '0',
        label: '使用'
      }, {
        value: '1',
        label: '未使用'
      }, {
        value: '2',
        label: '维修中'
      }],
    }
  },
  created() {//渲染前，模板编译前，通常初始化某些属性值，
    //请求分页数据
    this.load();
  },
  methods: {
    //模糊查-分页  {params:{xx:xx,xx:xx}}
    load(pageNum = 1){ //当前页码,如果undefined，赋值为1
      this.classPageVO.pageNum = pageNum //改变页码有值，直接赋值
      this.request.post("/classroom/listClassRoom", this.classPageVO).then(
          res => {
            //区域
            this.getArea();
            if(res.code === '200'){
              this.tableData = res.obj.records //更新数据
              this.classPageVO.total = res.obj.total;//更新总条数
            }else{
              this.$message.error(res.obj)
            }
          })
    },
    //重置输入框
    reset(){
      this.classPageVO.classroomNumber = ''
      this.classPageVO.classroomName = ''
      this.classPageVO.begin = 0
      this.classPageVO.over = 500
      this.classPageVO.area = ''
      this.classPageVO.buildingNo = ''
      this.classPageVO.buildingFloor = ''
      this.noDisable = true;
      this.floorDisable = true;
      this.load()
    },
    //当前页条数改变
    handleSizeChange(pageSize){
      this.classPageVO.pageSize = pageSize;
      this.load()
    },
    //区域
    getArea() {
      this.request.get("/classroom/getArea").then(
          res => {
            this.area = res.obj;
          })
    },
    //楼号
    getBuildingNo(val, type) {
      this.request.get(`/classroom/getBuildingNo/${val}`).then(
          res => {
            this.buildingNo = res.obj;
            if(type === 'check'){
              this.noDisable = false;
              this.floorDisable = true;
              this.classPageVO.buildingNo = '';
              this.classPageVO.buildingFloor = '';
            }else {
              this.noDataDisable = false;
              this.floorDataDisable = true;
              this.classRoomData.buildingNo = '';
              this.classRoomData.buildingFloor = '';
            }
          })
    },
    //楼层
    getBuildingFloor(area, buildingNo, type) {
      this.request.get(`/classroom/getBuildingFloor`, {params: {area: area, buildingNo: buildingNo}}).then(
          res => {
            this.buildingFloor = res.obj;
            if(type === 'check'){
              this.floorDisable = false;
              this.classPageVO.buildingFloor = '';
            }else {
              this.floorDataDisable = false;
              this.classRoomData.buildingFloor = '';
            }
          })
    },
    //修改初始化楼号
    choseNo() {
      this.request.get(`/classroom/getBuildingNo/${this.classRoomData.area}`).then(
          res => {
            this.buildingNo = res.obj;
            this.noDataDisable = false;
            this.choseFleer();
          })
    },
    //修改初始化楼层
    choseFleer() {
      this.request.get(`/classroom/getBuildingFloor`, {params: {area: this.classRoomData.area, buildingNo: this.classRoomData.buildingNo}}).then(
          res => {
            this.buildingFloor = res.obj;
            this.floorDataDisable = false;
          })
    },
    //添加弹窗
    handleAdd(){
      if(this.noDataDisable === false && this.floorDataDisable === false){
        this.noDataDisable = !this.noDataDisable
        this.floorDataDisable = !this.floorDataDisable
      }
      this.classRoomData = this.$options.data().classRoomData
      this.saveStatus = false
      this.dialogFormVisible = true
    },
    //添加/修改信息
    save(val){
      if(val){
        this.request.post("/classroom/updateClassRoom", this.classRoomData).then(res => {
          if(res.code === "200"){
            this.dialogFormVisible = false
            this.$message.success(`修改教室：${this.classRoomData.classroomName}成功`)
            this.load();
          }else{
            this.dialogFormVisible = true
            this.$message.error(res.obj)
          }
        })
      }else{
        this.request.post("/classroom/saveClassRoom", this.classRoomData).then(res => {
          if(res.code === "200"){
            this.dialogFormVisible = false
            this.$message.success(`添加教室：${this.classRoomData.classroomName}成功`)
            this.load();
          }else {
            this.dialogFormVisible = true
            this.$message.error(res.obj)
          }
        })
      }
    },
    //修改弹窗
    handleEdit(row){
      this.classRoomData = row //把一行内容赋值到弹窗内
      this.saveStatus = true
      this.dialogFormVisible = true
      this.choseNo(); //修改初始化联动
    },
    //关闭弹窗
    shutDown(){
      this.dialogFormVisible = false
      this.load(); //全查
    },
    //删除提示
    delTitle(name){
      return `您确定删除：${name} ？`;
    },
    //删除弹窗
    del(classroomId){
      this.request.get(`/classroom/deleteClassRoom/${classroomId}`).then(res => {
        if(res.code === '200'){
          this.$message.success(`删除教室：${this.classRoomData.classroomName}成功`)
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
      let classroomIds = this.multipleSelection.map(cla => cla.classroomId) //扁平化处理 [{}, {}, {}] => [1,2,3]
      this.request.post(`/classroom/listDelClassRoom`, classroomIds).then(res => {
        if(res.flag === true){
          this.$message.success(`批量删除 ${this.multipleSelection.length} 个教室信息成功`)
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
      window.open("http://localhost:9090/phoenix/classroom/excelExport") //导出路径
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
/*input*/
.input-prepend::before {
  display: block;
  content: attr(data-content);

position: absolute;
  top: 1px;
  left: 1px;
  padding: 4px;
}
</style>