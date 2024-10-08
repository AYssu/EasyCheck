<template>
  <div class="echarts-box">
    <v-chart class="chart" :option="option" autoresize></v-chart>
  </div>
</template>
<script setup lang="ts">
import {ref} from "vue";

const option = ref({
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }

  },
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: [
    {
      type: 'category',
      data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
      axisTick: {
        alignWithLabel: true
      }
    }
  ],
  yAxis: [
    {
      type: 'value'
    }
  ],
  series: [
    {
      name: '活跃用户',
      type: 'bar',
      barWidth: '70%',
      data: [
        10, 52, 200, 334, 390, 330, 200
        // 注意：这里不需要内嵌对象，直接使用数值数组即可
      ],
      itemStyle: {
        color: (params: any) => {
          // 高亮今天的数据点
          if (params.dataIndex === getTodayIndex()) {
            return '#ffa755'; // 今天的数据点颜色
          }
          return 'skyblue'; // 其他数据点颜色
        }
      }
    }]
})
const getTodayIndex = () => {
  const days = ['周一', '周二', '周三', '周四', '周五', '周六', '周日'];
  const date = new Date();
  return days.indexOf(getDayName(date.getDay()));
}

const getDayName = (dayIndex: any) => {
  // 根据数字索引返回中文星期几
  const days = ['周日', '周一', '周二', '周三', '周四', '周五', '周六'];
  return days[dayIndex];
}
</script>
<style lang="scss" scoped>
.echarts-box {
  height: calc(100vh - 325px);
}



.chart {
  width: 100%;
  height: calc(100vh - 330px);
}

</style>