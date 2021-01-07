import React from 'react';
import { PageHeader, Button, Descriptions,Statistic, Card,Popover,Tag} from 'antd';
import { ArrowUpOutlined, ArrowDownOutlined } from '@ant-design/icons';

//引入echarts主模块
import * as echarts from 'echarts'// 引入柱状图
import 'echarts/lib/chart/bar';
// 引入标题和提示框
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';

export default class ExpensesIndex extends React.Component{
  constructor(props) {
    super(props);
  }

  componentDidMount=()=>{
    // 图表初始化
    let myChart=echarts.init(document.getElementById("spendingCurve"));
    myChart.setOption({
      title: {
        text: '本月支出曲线图',
        left: 'center'
      },
      xAxis: {
        type: 'category',
        data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
      },
      yAxis: {
        type: 'value'
      },
      series: [{
        data: [820, 932, 901, 934, 1290, 1330, 1320],
        type: 'line',
        smooth: true
      }]
    })

    let myRChart = echarts.init(document.getElementById("spendingGroup"));
    myRChart.setOption({
        title: {
          text: '本月支出分类',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          orient: 'vertical',
          left: 'left',
          data: ['生活', '交通', '科技', '烟酒', '人情']
        },
        series: [
          {
            name: '本月支出分类',
            type: 'pie',
            radius: '55%',
            center: ['50%', '60%'],
            data: [
              {value: 335, name: '生活'},
              {value: 310, name: '交通'},
              {value: 234, name: '科技'},
              {value: 135, name: '烟酒'},
              {value: 158, name: '人情'}
            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]

    })
  }

  state = {
    visible: false,
  };

  hide = () => {
    this.setState({
      visible: false,
    });
  };

  handleVisibleChange = visible => {
    this.setState({ visible });
  };




  render() {
    return(
      <div
        style={{
          backgroundColor: '#F5F5F5',
          padding: 0,
        }}
      >
        <PageHeader
          ghost={false}
          title="您好！ Ezer Wu"
          subTitle="下面是您的支出情况"
          extra={<Popover
            content={
              <span>
                <Tag color="red">{"总额：" + 1000+100000}</Tag>
              </span>

              }
            title="本年支出"
            trigger="click"
            visible={this.state.visible}
            onVisibleChange={this.handleVisibleChange}
          >
            <Button type="primary">查看本年</Button>
          </Popover>}
        >
          <Descriptions size="small" column={3}>
            <Card>
              <Descriptions.Item label="上升率">
              <Statistic
                value={11.28}
                precision={2}
                valueStyle={{ color: '#3f8600' }}
                prefix={<ArrowUpOutlined />}
                suffix="%"
              />
              </Descriptions.Item>
            </Card>
            <Descriptions.Item label="昨日支出"><a>1000</a></Descriptions.Item>
            <Descriptions.Item label="本月支出">
              <a>421421</a>
            </Descriptions.Item>
            <Descriptions.Item label="统计时间">20201223</Descriptions.Item>
            <Descriptions.Item label="较大支出分类">
              Gonghu Road, Xihu District, Hangzhou, Zhejiang, China
            </Descriptions.Item>
          </Descriptions>
        </PageHeader>
        <div style={{width:'100%'}}>
          <div id="spendingCurve" style={{width:'45%',height : '30rem',marginTop : '1rem',float : 'left',minWidth:'300px'}}></div>
          <div id="spendingGroup" style={{width:'45%',height : '30rem',marginTop : '1rem', float : 'right',minWidth:'300px'}}></div>
        </div>

      </div>

    )
  }
}
