<template>
  <div>
    <el-row :gutter="10" style="margin-bottom: 60px">
<!--   :span 最大24   -->
      <el-col :span="6">
        <el-card>
          <div style="color: #409EFF"><i class="el-icon-user-solid"/> 用户总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold;">
            <el-tag type="primary">100</el-tag>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div style="color: #F56C6C"><i class="el-icon-money"/> 销售总量</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold;">￥ 1000000</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div style="color: #67c23a"><i class="el-icon-bank-card"/> 收益总额</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold;">￥ 300000</div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card>
          <div style="color: #E6A23C"><i class="el-icon-s-shop"/> 门店总数</div>
          <div style="padding: 10px 0; text-align: center; font-weight: bold;">100</div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="12">
        <div id="main" style="width: 500px; height: 450px;"></div>
      </el-col>
      <el-col :span="12">
        <div id="pie" style="width: 600px; height: 450px;"></div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "Home",
  data() {
    return {

    }
  },
  mounted(){ //挂载完触发，页面渲染完毕
    var option = {
      title: {//标题
        text: '各季度用户数量统计',
        subtext: '趋势图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      xAxis: { //横轴
        type: 'category',
        // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        data: ['第一季度', '第二季度', '第三季度', '第四季度']
      },
      yAxis: {
        type: 'value'
      },
      series: [ //纵轴
        {//柱状图
          name: "星巴克",
          // data: [150, 230, 224, 218, 135, 147, 260],
          data: [],
          type: 'bar'
        },
        {//折线图
          name: "星巴克",
          // data: [150, 230, 224, 218, 135, 147, 260],
          data: [],
          type: 'line'
        },
        {//柱状图
          name: "瑞幸咖啡",
          // data: [150, 230, 224, 218, 135, 147, 260],
          data: [],
          type: 'bar'
        },
        {//折线图
          name: "瑞幸咖啡",
          // data: [150, 230, 224, 218, 135, 147, 260],
          data: [],
          type: 'line'
        },
      ]
    };
    //饼图
    let pieOption = {
      title: {//标题
        text: '各季度用户数量统计',
        subtext: '比例图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item',
        formatter: '{a} <br/>{b} : {c} ({d}%)'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: '星巴克',
          type: 'pie',
          radius: '50%',
          center: ['25%', '50%'], //控制左右 上下 距离
          label: { //饼图上的文本标签
            normal: {
              show: true,
              position: 'inner', //标签的位置
              textStyle: {
                fontWeight: 300,
                fontSize: 14, //文字的大小
                color: '#fff',
              },
              formatter: '{d}%'
            },
          },
          data: [], //赋值
          // data: [
          //   { value: 1048, name: 'Search Engine' },
          //   { value: 735, name: 'Direct' },
          //   { value: 580, name: 'Email' },
          //   { value: 484, name: 'Union Ads' },
          //   { value: 300, name: 'Video Ads' }
          // ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        },
        {
          name: '瑞星咖啡',
          type: 'pie',
          radius: '60%',
          center: ['75%', '50%'],
          label: { //饼图上的文本标签
            normal: {
              show: true,
              position: 'inner', //标签的位置
              textStyle: {
                fontWeight: 300,
                fontSize: 14, //文字的大小
                color: '#fff',
              },
              formatter: '{c} / ({d}%)'
            },
          },
          // data: [], //赋值
          data: [
            { value: 1048, name: '第一季度' },
            { value: 735, name: '第二季度' },
            { value: 580, name: '第三季度' },
            { value: 484, name: '第四季度' }
          ],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        },
      ]
    };
    //折线/柱状图
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    //饼图
    var pieChartDom = document.getElementById('pie'); //获取元素Id
    var pieChart = echarts.init(pieChartDom); //初始化元素

    //查询四季度用户情况
    this.request.get("/echarts/members").then(res => {
      //赋值 折现/柱状图
      // option.xAxis.data = res.obj.x
      option.series[0].data = res.obj
      option.series[1].data = res.obj
      //瑞幸咖啡
      option.series[2].data = [5,6,7,20]
      option.series[3].data = [5,6,7,20]
      //数据准备完毕后，再set
      myChart.setOption(option)
      //饼图
      pieOption.series[0].data = [
        {name: '第一季度', value: res.obj[0]},
        {name: '第二季度', value: res.obj[1]},
        {name: '第三季度', value: res.obj[2]},
        {name: '第四季度', value: res.obj[3]}
      ]
      pieChart.setOption(pieOption) //给元素配置
    })
  }
}
</script>

<style scoped>

</style>