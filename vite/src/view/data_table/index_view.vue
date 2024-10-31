<template>
  <div class="echarts-box">
    <div class="top_menu_bar">
      <el-card style="height: 140px;margin: 10px 10px 20px 10px;box-shadow: none" ></el-card>
      <el-card style="height: 140px;margin: 10px 10px 20px 10px;box-shadow: none" ></el-card>
      <el-card style="height: 140px;margin: 10px 10px 20px 10px;box-shadow: none" ></el-card>
      <el-card style="height: 140px;margin: 10px 10px 20px 10px;box-shadow: none" ></el-card>
    </div>

    <div class="bottom_menu_bar">
      <el-card style="height: calc(100vh - 300px);margin: 0 10px 0 10px;box-shadow: none" >
        <v-chart class="chart"  :option="option" autoresize></v-chart>
      </el-card>
      <el-card style="height: calc(100vh - 300px);margin: 0 10px 0 10px;box-shadow: none" >
        <el-row>
          <el-col :span="6">
            <el-statistic  :value="268500">
              <template #title>
                注册用户
                <el-icon style="margin-left: 4px" :size="12">
                  <svg t="1730361885932" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1636" width="200" height="200"><path d="M926.26863 957.936375l-55.567573 0C859.981924 816.071344 768.64366 697.151907 642.9264 647.223774c-39.952944 19.377326-84.538395 30.569226-131.806955 30.569226-47.273676 0-91.879594-11.192923-131.830491-30.569226-125.716236 49.928133-217.054501 168.847569-227.756237 310.713624l-55.584969 0c9.907651-151.502529 99.44899-280.826943 226.671533-346.280678C251.660158 555.227524 205.943954 467.874013 205.943954 369.606942c0-170.193217 136.641053-308.165592 305.176514-308.165592 168.520111 0 305.160141 137.973398 305.160141 308.165592 0 98.267071-45.716204 185.620581-116.67328 242.049778C826.829873 677.110455 916.358933 806.434869 926.26863 957.936375zM760.786715 369.606942c0-139.254577-111.795179-252.137531-249.666246-252.137531-137.89665 0-249.693876 112.882953-249.693876 252.137531 0 139.248438 111.797225 252.144694 249.693876 252.144694C648.991536 621.751636 760.786715 508.85538 760.786715 369.606942z" fill="#272636" p-id="1637"></path></svg>
                </el-icon>
              </template>
            </el-statistic>
          </el-col>
          <el-col :span="6">
            <el-statistic :value="138">
              <template #title>
                <div style="display: inline-flex; align-items: center">
                  今日活跃
                  <el-icon style="margin-left: 4px" :size="12">
                    <svg t="1730362025621" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3463" width="200" height="200"><path d="M384.9216 877.1584c6.8096 24.9856 42.1888 25.1904 49.3056 0.256L613.8368 248.832l129.4848 474.7776a25.6 25.6 0 0 0 47.5648 4.7104l95.5392-190.6688h86.528a25.6 25.6 0 1 0 0-51.2h-102.2976a25.6 25.6 0 0 0-22.8864 14.1312l-72.704 144.9984-135.9872-498.688c-6.8096-24.9856-42.1888-25.1904-49.3056-0.256L410.1632 775.168 280.6784 300.4416a25.6 25.6 0 0 0-47.616-4.7104L137.6768 486.4H51.2a25.6 25.6 0 0 0 0 51.2h102.2464a25.6 25.6 0 0 0 22.8864-14.1312l72.6016-144.9472 135.9872 498.6368z" fill="#666666" p-id="3464"></path></svg>
                  </el-icon>
                </div>
              </template>
            </el-statistic>
          </el-col>
          <el-col :span="6">
            <el-statistic :value="outputValue">
              <template #title>
                剩余制卡
                <el-icon style="vertical-align: -0.125em">
                  <svg t="1730362058040" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="5441" width="200" height="200"><path d="M0 0h1024v1024H0z" fill="#FFFFFF" p-id="5442"></path><path d="M801.216 404.48a32 32 0 0 0 45.568-44.96l-45.568 44.96z m-60.48-152.512a32 32 0 0 0-45.568 44.96l45.568-44.96z m194.464 18.496a32 32 0 0 0 45.568-44.928l-45.568 44.928z m-60.48-152.48a32 32 0 0 0-45.568 44.928l45.568-44.928z m43.936 0.64a32 32 0 1 0-45.312-45.216l45.312 45.184zM494.08 453.44a32 32 0 0 0 45.312 45.216l-45.312-45.216z m352.672-93.952l-106.048-107.52-45.568 44.928 106.048 107.52 45.568-44.928z m134.016-133.984l-106.048-107.552-45.6 44.928 106.048 107.552 45.568-44.928zM576 672a224 224 0 0 1-224 224v64a288 288 0 0 0 288-288h-64z m-224 224a224 224 0 0 1-224-224H64a288 288 0 0 0 288 288v-64z m-224-224a224 224 0 0 1 224-224v-64a288 288 0 0 0-288 288h64z m224-224a224 224 0 0 1 224 224h64a288 288 0 0 0-288-288v64zM873.344 73.408L494.144 453.44l45.28 45.216 379.2-380.096-45.28-45.184z" fill="#000000" p-id="5443"></path></svg>
                </el-icon>
              </template>
            </el-statistic>
          </el-col>
          <el-col :span="6">
            <el-statistic title="剩余项目" :value="100">
              <template #suffix>
                <el-icon style="vertical-align: -0.125em">
                  <svg t="1730362349496" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="14571" width="200" height="200"><path d="M751.4 957.2c-113.5 0-205.8-92.3-205.8-205.8V545.7h205.8c113.5 0 205.8 92.3 205.8 205.8 0 113.4-92.3 205.7-205.8 205.7zM617 751.4c0 74.1 60.3 134.4 134.4 134.4 74.1 0 134.4-60.3 134.4-134.4 0-74.1-60.3-134.4-134.4-134.4H617v134.4zM272.6 957.2c-113.5 0-205.8-92.3-205.8-205.8 0-113.5 92.3-205.8 205.7-205.8h205.8v205.8c0.1 113.5-92.2 205.8-205.7 205.8z m0-340.2c-74.1 0-134.4 60.3-134.4 134.4 0 74.1 60.3 134.4 134.4 134.4 74.1 0 134.4-60.3 134.4-134.4V617H272.6z m273-138.7V272.6c0-113.5 92.3-205.8 205.8-205.8 113.5 0 205.8 92.3 205.8 205.8 0 55-21.4 106.6-60.3 145.5-38.9 38.9-90.5 60.3-145.5 60.3H545.6z m205.8-340.1c-74.1 0-134.4 60.3-134.4 134.4V407h134.4c74.1 0 134.4-60.3 134.4-134.4 0-74.1-60.3-134.4-134.4-134.4zM272.6 478.3c-54.9 0-106.6-21.4-145.5-60.3-38.9-38.9-60.3-90.5-60.3-145.5 0-113.5 92.3-205.8 205.8-205.8 113.5 0 205.8 92.3 205.8 205.8v205.8H272.6z m0-340.1c-74.1 0-134.4 60.3-134.4 134.4 0 74.1 60.3 134.4 134.4 134.4H407V272.6c0-74.1-60.3-134.4-134.4-134.4z" fill="#333333" p-id="14572"></path></svg>
                </el-icon>
              </template>
            </el-statistic>
          </el-col>
        </el-row>
        <div class="demo-collapse">
          <el-text size="default" type="info" style="font-weight: bold;" >
            系统公告
          </el-text>
          <el-collapse v-model="activeName" accordion style="margin-top: 10px">

            <el-collapse-item title="2024年12月2日 这是一条普通的系统公告" name="1">
              <div>
                Operation feedback: enable the users to clearly perceive their
                operations by style updates and interactive effects;
              </div>
              <div>
                Visual feedback: reflect current state by updating or rearranging
                elements of the page.
              </div>
            </el-collapse-item>
            <el-collapse-item title="2024年12月2日 元宵节活动即将开始" name="2">
              <div>
                Operation feedback: enable the users to clearly perceive their
                operations by style updates and interactive effects;
              </div>
              <div>
                Visual feedback: reflect current state by updating or rearranging
                elements of the page.
              </div>
            </el-collapse-item>
            <el-collapse-item title="2024年12月2日 了解易验的工作流?" name="3">
              <el-text type="info">

              </el-text>
            </el-collapse-item>
            <el-collapse-item title="2024年12月2日 给新伙伴的一封信" name="4">
              <div>
                Decision making: giving advices about operations is acceptable, but do
                not make decisions for the users;
              </div>
              <div>
                Controlled consequences: users should be granted the freedom to
                operate, including canceling, aborting or terminating current
                operation.
              </div>
            </el-collapse-item>
          </el-collapse>
        </div>
      </el-card>
    </div>
  </div>
</template>
<script setup lang="ts">
import {ref} from "vue";
import * as echarts from 'echarts/core';
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  VisualMapComponent,
  LegendComponent
} from 'echarts/components';
import { LineChart } from 'echarts/charts';
import { UniversalTransition } from 'echarts/features';
import { CanvasRenderer } from 'echarts/renderers';
import { useTransition } from '@vueuse/core'

const source = ref(0)
const outputValue = useTransition(source, {
  duration: 1500,
})


source.value = 100000
echarts.use([
  TitleComponent,
  TooltipComponent,
  GridComponent,
  VisualMapComponent,
  LineChart,
  CanvasRenderer,
  UniversalTransition,
    LegendComponent,
]);

const activeName = ref('1')

const colorList =  ["#9E87FF", '#73DDFF', '#fe9a8b', '#F56948', '#9E87FF']
const option = ref({
  backgroundColor: '#fff',
  title: {
    text: '数据预览',
    textStyle: {
      fontSize: 12,
      fontWeight: 400
    },
    left: '0',
    top: '5%'
  },
  legend: {
    icon: 'circle',
    top: '5%',
    right: '5%',
    itemWidth: 6,
    itemGap: 20,
    textStyle: {
      color: '#556677'
    }
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      label: {
        show: true,
        backgroundColor: '#fff',
        color: '#556677',
        borderColor: 'rgba(0,0,0,0)',
        shadowColor: 'rgba(0,0,0,0)',
        shadowOffsetY: 0
      },
      lineStyle: {
        width: 0
      }
    },
    backgroundColor: '#fff',
    textStyle: {
      color: '#5c6c7c'
    },
    padding: [10, 10],
    extraCssText: 'box-shadow: 1px 0 2px 0 rgba(163,163,163,0.5)'
  },
  grid: {
    top: '15%'
  },
  xAxis: [{
    type: 'category',
    data: ['星期一', '星期二', '星期三', '星期四', '星期五', '星期六', '星期天'],
    axisLine: {
      lineStyle: {
        color: '#DCE2E8'
      }
    },
    axisTick: {
      show: false
    },
    axisLabel: {
      interval: 0,
      textStyle: {
        color: '#556677'
      },
      // 默认x轴字体大小
      fontSize: 12,
      // margin:文字到x轴的距离
      margin: 15
    },
    axisPointer: {
      label: {
        // padding: [11, 5, 7],
        padding: [0, 0, 10, 0],

        // 这里的margin和axisLabel的margin要一致!
        margin: 15,
        // 移入时的字体大小
        fontSize: 12,
        backgroundColor: {
          type: 'linear',
          x: 0,
          y: 0,
          x2: 0,
          y2: 1,
          colorStops: [{
            offset: 0,
            color: '#fff' // 0% 处的颜色
          }, {
            // offset: 0.9,
            offset: 0.86,
            /*
0.86 = （文字 + 文字距下边线的距离）/（文字 + 文字距下边线的距离 + 下边线的宽度）

            */
            color: '#fff' // 0% 处的颜色
          }, {
            offset: 0.86,
            color: '#33c0cd' // 0% 处的颜色
          }, {
            offset: 1,
            color: '#33c0cd' // 100% 处的颜色
          }],
          global: false // 缺省为 false
        }
      }
    },
    boundaryGap: false
  }],
  yAxis: [{
    type: 'value',
    axisTick: {
      show: false
    },
    axisLine: {
      show: true,
      lineStyle: {
        color: '#DCE2E8'
      }
    },
    axisLabel: {
      textStyle: {
        color: '#556677'
      }
    },
    splitLine: {
      show: false
    }
  }, {
    type: 'value',
    position: 'right',
    axisTick: {
      show: false
    },
    axisLabel: {
      textStyle: {
        color: '#556677'
      },
      formatter: '{value}'
    },
    axisLine: {
      show: true,
      lineStyle: {
        color: '#DCE2E8'
      }
    },
    splitLine: {
      show: false
    }
  }],
  series: [{
    name: '卡密制作',
    type: 'line',
    data: [10, 10, 30, 12, 15, 3, 7],
    symbolSize: 1,
    symbol: 'circle',
    smooth: true,
    yAxisIndex: 0,
    showSymbol: false,
    lineStyle: {
      width: 5,
      color: new echarts.graphic.LinearGradient(0, 1, 0, 0, [{
        offset: 0,
        color: '#9effff'
      },
        {
          offset: 1,
          color: '#9E87FF'
        }
      ]),
      shadowColor: 'rgba(158,135,255, 0.3)',
      shadowBlur: 10,
      shadowOffsetY: 20
    },
    itemStyle: {
      normal: {
        color: colorList[0],
        borderColor: colorList[0]
      }
    }
  }, {
    name: '用户注册',
    type: 'line',
    data: [5, 12, 11, 14, 25, 16, 10],
    symbolSize: 1,
    symbol: 'circle',
    smooth: true,
    yAxisIndex: 0,
    showSymbol: false,
    lineStyle: {
      width: 5,
      color: new echarts.graphic.LinearGradient(1, 1, 0, 0, [{
        offset: 0,
        color: '#73DD39'
      },
        {
          offset: 1,
          color: '#73DDFF'
        }
      ]),
      shadowColor: 'rgba(115,221,255, 0.3)',
      shadowBlur: 10,
      shadowOffsetY: 20
    },
    itemStyle: {
      normal: {
        color: colorList[1],
        borderColor: colorList[1]
      }
    }
  },
    {
      name: '卡密/用户登录',
      type: 'line',
      data: [150, 120, 170, 140, 500, 160, 110],
      symbolSize: 1,
      yAxisIndex: 1,
      symbol: 'circle',
      smooth: true,
      showSymbol: false,
      lineStyle: {
        width: 5,
        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
          offset: 0,
          color: '#fe9a'
        },
          {
            offset: 1,
            color: '#fe9a8b'
          }
        ]),
        shadowColor: 'rgba(254,154,139, 0.3)',
        shadowBlur: 10,
        shadowOffsetY: 20
      },
      itemStyle: {
        normal: {
          color: colorList[2],
          borderColor: colorList[2]
        }
      }
    }
  ]
});

</script>
<style lang="scss" scoped>
.echarts-box {
  height: calc(100vh - 100px);
}

.chart {
  width: 100%;
  height: 50vh;
}

.top_menu_bar {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
}

.bottom_menu_bar {
  display: grid;
  grid-template-columns: 1fr 1fr;
}

.demo-collapse {
  margin-top: 16px;
}

</style>