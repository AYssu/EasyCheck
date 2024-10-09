<template>
  <div class="echarts-box">
    <v-chart class="chart" :option="option" autoresize></v-chart>
  </div>
</template>
<script setup lang="ts">
import {ref} from "vue";
import * as echarts from 'echarts/core';
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  VisualMapComponent
} from 'echarts/components';
import { LineChart } from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';

echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  VisualMapComponent,
  LineChart,
  CanvasRenderer,
  UniversalTransition
]);
const days = 30;

const data1 = [];
const data2 = [];

function getRandomInt(min:number, max:number) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}

// 使用正弦函数生成周期性趋势
const amplitude = 20; // 振幅
const period = days /2; // 周期

for (let i = days; i > 1; i--) {
  const date = new Date();
  date.setDate(date.getDate() - i);
  // 计算趋势值
  const trendValue = amplitude * Math.sin(2 * Math.PI * (i - 1) / period);
  const baseValue = 40 + trendValue;
  const randomValue = getRandomInt(baseValue, baseValue + 260);
  data1.push([date.toISOString().split('T')[0], randomValue]);
}

for (let i = days; i > 1; i--) {
  const date = new Date();
  date.setDate(date.getDate() - i);
  // 计算趋势值
  const trendValue = amplitude * Math.sin(2 * Math.PI * (i - 1) / period);
  const baseValue = 40 + trendValue;
  const randomValue = getRandomInt(baseValue, baseValue + 260);
  data2.push([date.toISOString().split('T')[0], randomValue]);
}

const dateList = data1.map(function (item) {
  return item[0];
});

const valueList1 = data1.map(function (item) {
  return item[1];
});


const valueList2 = data2.map(function (item) {
  return item[1];
});

const option = ref({
    // Make gradient line here
    visualMap: [
      {
        show: false,
        type: 'continuous',
        seriesIndex: 0,
        min: 0,
        max: 400
      },
      {
        show: false,
        type: 'continuous',
        seriesIndex: 1,
        dimension: 0,
        min: 0,
        max: dateList.length - 1
      }
    ],
    title: [
      {
        left: 'center',
        text: '用户注册活跃报表'
      },
      {
        top: '55%',
        left: 'center',
        text: '用户验证/登录报表'
      }
    ],
    tooltip: {
      trigger: 'axis'
    },
    xAxis: [
      {
        data: dateList
      },
      {
        data: dateList,
        gridIndex: 1
      }
    ],
    yAxis: [
      {},
      {
        gridIndex: 1
      }
    ],
    grid: [
      {
        bottom: '60%'
      },
      {
        top: '60%'
      }
    ],
    series: [
      {
        type: 'line',
        showSymbol: false,
        data: valueList1
      },
      {
        type: 'line',
        showSymbol: false,
        data: valueList2,
        xAxisIndex: 1,
        yAxisIndex: 1
      }
    ]
})



</script>
<style lang="scss" scoped>
.echarts-box {
  height: calc(100vh - 100px);
}

.chart {
  width: 100%;
}

</style>