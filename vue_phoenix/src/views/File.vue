<template>
  <div>
    <div style="margin: 10px 0">
      <el-input class="ml-5" style="width: 196px" placeholder="文件名" suffix-icon="el-icon-search" v-model="FileVo.name"></el-input>
      <el-button class="ml-5" type="primary" @click="load()">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-upload action="http://127.0.0.1:9090/phoenix/file/upload" :show-file-list="false" :on-success="fileUploadSuccess" style="display: inline-block">
        <el-button type="primary" class="ml-5">上传文件 <i class="el-icon-top"></i></el-button>
      </el-upload>
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
      <el-table-column prop="id" label="ID">
      </el-table-column>
      <el-table-column prop="name" label="文件名称">
      </el-table-column>
      <el-table-column prop="type" label="文件类型">
      </el-table-column>
      <el-table-column prop="size" label="文件大小(KB)">
      </el-table-column>
      <el-table-column prop="idType" label="下载">
        <template slot-scope="scope">
          <el-button type="primary" :disabled="!scope.row.enable" @click="download(scope.row.url)">下载</el-button>
        </template>
      </el-table-column>
      <el-table-column label="启用">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ff4949" @change="changeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="200" align="left">
        <template slot-scope="scope">
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              :title="delTitle(scope.row.name)"
              @confirm="del(scope.row.id, scope.row.name)"
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
          :current-page.sync="FileVo.pageNum"
          :page-size="FileVo.pageSize"
          :page-sizes="[3, 5, 8, 10]"
          :total="FileVo.total"
          background
          layout="total, sizes ,prev, pager, next, jumper"
          style="padding: 30px 0; text-align: center"
          @current-change="load"
          @size-change="handleSizeChange"
      />
    </div>
  </div>
</template>

<script>
export default {
  name: "File",
  data() {
    return{
      tableData: [], //文件数据
      multipleSelection: [],//勾选框
      FileVo:{ //模糊查数据-分页
        name: '',
        total: 0, //总条数
        pageNum: 1, //页码
        pageSize: 5, //条数
      },
    }
  },
  created() {
    this.load()
  },
  methods: {
    //模糊查-分页  {params:{xx:xx,xx:xx}}
    load(pageNum = 1){ //当前页码,如果undefined，赋值为1
      this.FileVo.pageNum = pageNum //改变页码有值，直接赋值
      this.request.post("/file/listFile", this.FileVo).then(
          res => {
            this.tableData = res.obj.records //更新数据
            this.FileVo.total = res.obj.total;//更新总条数
          })
    },
    //重置输入框
    reset(){
      this.FileVo.name = ''
      this.load()
    },
    //当前页条数改变
    handleSizeChange(pageSize){
      this.FileVo.pageSize = pageSize;
      this.load()
    },
    //删除提示
    delTitle(name){
      return `您确定删除文件：${name} ？`;
    },
    //删除弹窗
    del(id, name){
      this.request.delete("/file/deleteFile/" + id).then(res => {
        if(res.flag === true){
          this.$message.success(`删除文件：${name} 成功`)
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
      let ids = this.multipleSelection.map(file => file.id) //扁平化处理 [{}, {}, {}] => [1,2,3]
      this.request.post("/file/deleteListFile", ids).then(res => {
        if(res.flag === true){
          this.$message.success(`批量删除 ${this.multipleSelection.length} 个文件成功`)
          this.load();
        }else{
          this.$message.error(res.obj)
          this.load();
        }
      })
    },
    //下载文件
    download(url){
      window.open(url);
    },
    //文件禁用
    changeEnable(row){
      this.request.post("/file/enableSave", row).then(res => {
        if(res.code === '200'){
          this.$message.success(`操作成功`)
        }else{
          this.$message.error(`操作失败`)
        }
      })
    },
    //上传文件成功回调
    fileUploadSuccess(res){
      this.load();
    }
  }
}
</script>

<style scoped>

</style>