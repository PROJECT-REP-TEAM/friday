import React from 'react';
import {Tabs, Divider } from 'antd'
//引入echarts主模块
import * as echarts from 'echarts'// 引入柱状图
import 'echarts/lib/chart/bar';
// 引入标题和提示框
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';

let chartA ,chartB,chartC;

export default class AssetsIndex extends React.Component{
  constructor(props){
    super(props)
    this.state = {
    }
  }

  componentDidMount = ()=>{
    this.showGraph();
  }
  showGraph = () =>{
    chartA = echarts.init(document.getElementById("fixed"));
    chartB = echarts.init(document.getElementById("balance"));
    chartC = echarts.init(document.getElementById("intangible"));
    chartA.setOption({
      title: {
        text: '固定资产占比情况',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      series: [
        {
          name: '访问来源',
          type: 'pie',
          radius: ['40%', '70%'],
          avoidLabelOverlap: false,
          itemStyle: {
            borderRadius: 10,
            borderColor: '#fff',
            borderWidth: 2
          },
          label: {
            show: false,
            position: 'center'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: '40',
              fontWeight: 'bold'
            }
          },
          labelLine: {
            show: false
          },
          data: [
            {value: 1048, name: '搜索引擎'},
            {value: 735, name: '直接访问'},
            {value: 580, name: '邮件营销'},
            {value: 484, name: '联盟广告'},
            {value: 300, name: '视频广告'}
          ]
        }
      ]
    });
    chartB.setOption({
      title: {
        text: '固定资产：无形资产',
        left: 'center'
      },
      series: [{
        name: 'Pressure',
        type: 'gauge',
        progress: {
          show: true
        },
        detail: {
          valueAnimation: true,
          formatter: '{value}'
        },
        data: [{
          value: 50,
          name: '比值%'
        }]
      }]
    });
    chartC.setOption({
      title: {
        text: '无形资产占比情况',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      series: [
        {
          type: 'pie',
          radius: '50%',
          data: [
            {value: 1048, name: '搜索引擎'},
            {value: 735, name: '直接访问'},
            {value: 580, name: '邮件营销'},
            {value: 484, name: '联盟广告'},
            {value: 300, name: '视频广告'}
          ]
        }
      ]
    });
  }

  render() {
    return(
      <div>
        <div style={{width:'100%'}}>
          <div id="fixed" style={{width:'33%',height : '30rem',marginTop : '1rem',float : 'left',minWidth:'300px'}}></div>
          <div id="balance" style={{width:'33%',height : '30rem',marginTop : '1rem', float : 'left',minWidth:'300px'}}></div>
          <div id="intangible" style={{width:'33%',height : '30rem',marginTop : '1rem', float : 'right',minWidth:'300px'}}></div>
        </div>
        <Divider />
      </div>
    )
  }
}
