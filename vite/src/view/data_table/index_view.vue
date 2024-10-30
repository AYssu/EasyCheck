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
            <el-statistic title="Daily active users" :value="268500" />
          </el-col>
          <el-col :span="6">
            <el-statistic :value="138">
              <template #title>
                <div style="display: inline-flex; align-items: center">
                  Ratio of men to women
                  <el-icon style="margin-left: 4px" :size="12">
                    <Male />
                  </el-icon>
                </div>
              </template>
              <template #suffix>/100</template>
            </el-statistic>
          </el-col>
          <el-col :span="6">
            <el-statistic title="Total Transactions" :value="outputValue" />
          </el-col>
          <el-col :span="6">
            <el-statistic title="Feedback number" :value="562">
              <template #suffix>
                <el-icon style="vertical-align: -0.125em">
                  <ChatLineRound />
                </el-icon>
              </template>
            </el-statistic>
          </el-col>
        </el-row>
        <div class="demo-collapse">
          <el-text size="large" >
            Collapse
          </el-text>
          <el-collapse v-model="activeName" accordion>

            <el-collapse-item title="Consistency" name="1">
              <div>
                Consistent with real life: in line with the process and logic of real
                life, and comply with languages and habits that the users are used to;
              </div>
              <div>
                Consistent within interface: all elements should be consistent, such
                as: design style, icons and texts, position of elements, etc.
              </div>
            </el-collapse-item>
            <el-collapse-item title="Feedback" name="2">
              <div>
                Operation feedback: enable the users to clearly perceive their
                operations by style updates and interactive effects;
              </div>
              <div>
                Visual feedback: reflect current state by updating or rearranging
                elements of the page.
              </div>
            </el-collapse-item>
            <el-collapse-item title="Efficiency" name="3">
              <div>
                Simplify the process: keep operating process simple and intuitive;
              </div>
              <div>
                Definite and clear: enunciate your intentions clearly so that the
                users can quickly understand and make decisions;
              </div>
              <div>
                Easy to identify: the interface should be straightforward, which helps
                the users to identify and frees them from memorizing and recalling.
              </div>
            </el-collapse-item>
            <el-collapse-item title="Controllability" name="4">
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
import { ChatLineRound, Male } from '@element-plus/icons-vue'

const source = ref(0)
const outputValue = useTransition(source, {
  duration: 1500,
})
source.value = 172000
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
    text: '程序数据预览',
    textStyle: {
      fontSize: 12,
      fontWeight: 400
    },
    left: 'center',
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