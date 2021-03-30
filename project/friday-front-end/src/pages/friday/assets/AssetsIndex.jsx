import React from 'react';
import {
  Descriptions,
  Select,
  Row,
  Col,
  Button,
  Modal,
  Table,
  DatePicker,
  InputNumber,
  Input,
  Form,
  Popconfirm,
  message,
  Checkbox,
  Icon} from 'antd'
//引入echarts主模块
import * as echarts from 'echarts'// 引入柱状图
import 'echarts/lib/chart/bar';
// 引入标题和提示框
import 'echarts/lib/component/tooltip';
import 'echarts/lib/component/title';

import reqwest from 'reqwest';

import { connect , routerRedux} from 'dva';
const { Option } = Select;
const { RangePicker } = DatePicker;

const namespace = 'fundMSG';


let chartA ,chartB,chartC,groupSelectList = [], groupNameList = [],data = [];
// 初始化值
let param = {
  fundTime: '',
  fundSort: '',
};

// 选择分类
function changeType(value) {
  console.log(value.key); // { key: "lucy", label: "Lucy (101)" }
  param.fundSort = value.key;
}

const EditableContext = React.createContext();


@connect(({ fundMSG: fundMSG, loading }) => ({
  data: fundMSG.data, // 将data赋值给
  loading: loading
}))
class AssetsIndexHead extends React.Component{
  constructor(props){
    super(props)
    this.state = {
      editingKey: '',
      deleteKey:'',
      data: data,
      pagination: {},
      loading: false,
      visible: false
    }

    this.columns = [
      {
        title: '序号',
        dataIndex: 'fundId',
        align: 'center',
      },
      {
        title: '基金代码',
        dataIndex: 'fundCode',
        align: 'center',
        width:'10%'
      },
      {
        title: '基金名称',
        dataIndex: 'fundName',
        align: 'center',
      },
      {
        title: '基金类型',
        dataIndex: 'fundType',
        align: 'center',
        width:'10%'
      },
      {
        title: '当前基金单位净值',
        dataIndex: 'netWorth',
        align: 'center',
        width:'12%'
      },
      {
        title: '原始买入费率(%)',
        dataIndex: 'buySourceRate',
        align: 'center',
        width:'10%'

      },
      {
        title: '当前买入费率(%)',
        dataIndex: 'buyRate',
        align: 'center',
        width:'10%'
      },
      {
        title: '基金经理',
        dataIndex: 'manager',
        align: 'center',
        width:'10%'
      },
      {
        title: '每万分收益(货币基金)',
        dataIndex: 'millionCopiesIncome',
        align: 'center',
        width:'8%'
      },


      {
        title: '操作',
        dataIndex: 'operation',
        align: 'center',
        render: (text, record) => {
          return  (
            <div>
              <Popconfirm title="确定删除?" onConfirm={() => this.delete(record.fundId)}>
                <Button
                  type="danger"
                  shape="round"
                  icon="delete"
                  size={'default'}
                  style={{margin:'0 3px 0 3px'}}
                >
                  删除
                </Button>
              </Popconfirm>
            </div>
          );
        },
        width: '10%',
      },
    ];
  }


  // 变换条件发送请求
  handleTableChange = (pagination, filters) => {
    const pager = { ...this.state.pagination };
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
    this.fetch({
      limit: pagination.pageSize,
      offset: pagination.current,
      ...filters,
    });
  };

  // 发送请求
  fetch = (params = {}) => {
    console.log('params:', params);
    this.setState({ loading: true });
    reqwest({
      url: 'http://localhost:10010/friday/finance/userFund/selectAll',
      method: 'get',
      contentType: 'application/json',
      headers: {
        'Content-Type': 'application/json',
      },
      data: {
        limit: 10,
        ...params,
      },
      type: 'json',
    }).then(data => {
      const pagination = { ...this.state.pagination };
      // Read total count from server
      pagination.total = data.count;
      this.setState({
        loading: false,
        data: data.data,
        pagination,
      });
    });
  };

  // 跳转事件
  click(record,rowkey){

    console.log(record.fundCode);
    const {dispatch} = this.props;
    dispatch(routerRedux.push({
        pathname : '/fund/FundDetail' , query : { code : record.fundCode}
      })
    )
  }


  // 搜索
  searchFund(fundSort, fundTime) {
    param.fundSort = fundSort;
    param.fundTime = fundTime;
    this.fetch(param);
  }

  delete(key) {
    console.log(key);
    const { dispatch } = this.props;
    dispatch({
      type: `${namespace}/deleteFund`,
      payload: {
        id:key
      },
    });
    data = this.state.data;
    for (let i = 0; i <data.length; i++) {
      if (data[i].fundId === key) {
        data.splice(i,1)
      }
    }
    message.info('删除成功！');
  }

  componentDidMount = ()=>{
    this.showGraph();
    this.fetch();

    //分类
    this.props.dispatch({
      type: `${namespace}/findFundTypeName`,
      callback: (data)=>{
        console.log(data);
        groupSelectList = [];
        groupNameList = data;
        for (let i = 0; i < groupNameList.length; i++) {
          groupSelectList.push(<Select.Option value={groupNameList[i]} key={i}>{groupNameList[i]}</Select.Option>)
        }
      }
    })

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
        text: '资产负债率',
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
        text: '流动资产占比情况',
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
    const { getFieldDecorator } = this.props.form;

    const columns = this.columns.map(col => {
      if (!col.editable) {
        return col;
      }
      return {
        ...col,
      };
    });

    return(
      <div>
        <div style={{width:'100%',background:'#ffffff'}}>
          <div id="fixed" style={{width:'33%',height : '18rem',marginTop : '1rem',float : 'left',minWidth:'300px'}}></div>
          <div id="balance" style={{width:'34%',height : '18rem',marginTop : '1rem', float : 'left',minWidth:'300px'}}></div>
          <div id="intangible" style={{width:'33%',height : '18rem',marginTop : '1rem', float : 'right',minWidth:'300px'}}></div>
        </div>
        <Descriptions title="User Info" style={{background:'#ffffff',padding:'5%'}}>
          <Descriptions.Item label="UserName">Zhou Maomao</Descriptions.Item>
          <Descriptions.Item label="Telephone">1810000000</Descriptions.Item>
          <Descriptions.Item label="Live">Hangzhou, Zhejiang</Descriptions.Item>
          <Descriptions.Item label="Remark">empty</Descriptions.Item>
          <Descriptions.Item label="Address">
            No. 18, Wantang Road, Xihu District, Hangzhou, Zhejiang, China
          </Descriptions.Item>
        </Descriptions>

        <div style={{background:'#ffffff',padding:'5%'}}>
          <Row style={{ fontSize: '14px' }}>
            <Col span={20}>
              <div style={{ display: 'inline', whiteSpace: 'nowrap' }}>
                <label>支出类型:</label>
                <Select
                  // mode="multiple"
                  placeholder={'可选类型，默认全选'}
                  labelInValue
                  style={{
                    width: 180,
                    marginRight: '2.5rem',
                    marginBottom: '1rem',
                    whiteSpace: 'nowrap',
                  }}
                  onChange={changeType}
                >
                  <Option value="">全部</Option>
                  {groupSelectList}
                </Select>
              </div>

              <Button
                icon="search"
                type="primary"
                onClick={() => this.searchFund(param.fundSort, param.fundTime)}
              >
                Search
              </Button>
            </Col>

            <Col span={4}></Col>
          </Row>
          <Row style={{ marginTop: '1rem', marginBottom: '1rem' }}>
            <Col span={24}>
              <Button type="primary" icon="download" style={{ marginLeft: '0.3rem', float: 'right' }}
                      onClick={()=>{location.href = 'http://localhost:10010/friday/bills/userFund/downloadFund?fundTime='+param.fundTime}}
              >
                导出
              </Button>
            </Col>
          </Row>
          <EditableContext.Provider value={this.props.form}>
            <Table
              bordered
              dataSource={this.state.data}
              columns={columns}
              rowKey="fundId"
              rowClassName="editable-row"
              onChange={this.handleTableChange}
              pagination={this.state.pagination}
              loading={this.state.loading}
              onRow={(record,rowkey)=>{
                return{
                  onClick : this.click.bind(this,record,rowkey)    //点击行 record 指的本行的数据内容，rowkey指的是本行的索引
                }
              }}
            />
          </EditableContext.Provider>
        </div>
      </div>
    )
  }
}

const AssetsIndexHeader = Form.create()(AssetsIndexHead);

export default class AssetsIndex extends React.Component {
  render(){

    return(
      <div>
        <AssetsIndexHeader/>
      </div>
    )
  }
}

