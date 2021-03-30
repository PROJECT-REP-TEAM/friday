import React from 'react';
import {Tabs,Descriptions,Divider,Row,Col  } from 'antd'
import * as echarts from 'echarts'// 引入柱状图
import 'echarts/lib/chart/bar';
// 引入标题和提示框
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';

let chartOne,chartTwo;

export default class ExpensesIndex extends React.Component{
  constructor(props){
    super(props)
    this.state = {
      cadId:'',
      cadType:'',
      creditor:'',
      obligor:'',
      cadNum:'',
      cadTime:'',
      cadRepay:'',
      cadPlan:'',
      cadRemark:'',
      cadStatus:''
    }
  }

  componentDidMount(){
    this.showGraph();
  }

  showGraph = () =>{
    chartOne = echarts.init(document.getElementById("debt"));
    chartTwo = echarts.init(document.getElementById("claims"));
    chartOne.setOption({
      legend: {
        top: 'bottom'
      },
      title:{
        text: '今年债务占比图',
        left: 'center'
      },
      series: [
        {
          name: '面积模式',
          type: 'pie',
          radius: [30, 120],
          center: ['50%', '50%'],
          roseType: 'area',
          itemStyle: {
            borderRadius: 5
          },
          label: {
            show: 'false'
          },
          emphasis: {
            label: {
              show: 'true'
            }
          },
          data: [
            {value: 40, name: 'rose 1'},
            {value: 38, name: 'rose 2'},
            {value: 32, name: 'rose 3'},
            {value: 30, name: 'rose 4'},
            {value: 28, name: 'rose 5'},
            {value: 26, name: 'rose 6'},
            {value: 22, name: 'rose 7'},
            {value: 18, name: 'rose 8'}
          ]
        }
      ]
    });
    chartTwo.setOption({
      legend: {
        top: 'bottom'
      },
      title:{
        text: '今年债权占比图',
        left: 'center'
      },
      series: [
        {
          name: '面积模式',
          type: 'pie',
          radius: [30, 120],
          center: ['50%', '50%'],
          roseType: 'area',
          itemStyle: {
            borderRadius: 5
          },
          label: {
            show: 'false'
          },
          emphasis: {
            label: {
              show: 'true'
            }
          },
          data: [
            {value: 40, name: 'rose 1'},
            {value: 38, name: 'rose 2'},
            {value: 32, name: 'rose 3'},
            {value: 30, name: 'rose 4'},
            {value: 28, name: 'rose 5'},
            {value: 26, name: 'rose 6'},
            {value: 22, name: 'rose 7'},
            {value: 18, name: 'rose 8'}
          ]
        }
      ]
    });
  }

  render() {
    return(
      <div style={{background:'#ffffff',padding:'5%'}}>
        <div>
          <div id="debt" style={{width:'50%',height : '22rem',marginTop : '1rem',float : 'left',minWidth:'260px'}}></div>
          <div id="claims" style={{width:'50%',height : '22rem',marginTop : '1rem', float : 'left',minWidth:'260px'}}></div>
        </div>
        <Divider plain>Text</Divider>
        <Descriptions title="User Info" style={{marginTop:'1.5rem'}}>
          <Descriptions.Item label="债务总额">Zhou Maomao</Descriptions.Item>
          <Descriptions.Item label="债权总额">1810000000</Descriptions.Item>
          <Descriptions.Item label="债务债权差额">Hangzhou, Zhejiang</Descriptions.Item>
          <Descriptions.Item label="最高债务额">empty</Descriptions.Item>
          <Descriptions.Item label="最高债权额">No. 18, Wantang Road</Descriptions.Item>
          <Descriptions.Item label="当前财务状况">良好</Descriptions.Item>
        </Descriptions>

      </div>
    )
  }
}
