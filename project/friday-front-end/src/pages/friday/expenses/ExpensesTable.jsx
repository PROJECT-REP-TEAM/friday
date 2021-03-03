import React from 'react';
import { Select ,Row ,Col , Button, Tooltip, Table,DatePicker } from 'antd';
import reqwest from 'reqwest';
import {hashHistory} from 'react-router';

const { Option } = Select;
const { MonthPicker, RangePicker, WeekPicker } = DatePicker;




const columns = [
  {
    title: '消费id',
    dataIndex: 'expensesId',
    sorter: true,
  },
  {
    title: '消费时间',
    dataIndex: 'expensesTime',
    sorter: true,
  },
  {
    title: '消费金额',
    dataIndex: 'expensesNum',
    sorter: true,
  },
  {
    title: '消费类型',
    dataIndex: 'expensesSort',
    sorter: true,
  },
  {
    title: '备注',
    dataIndex: 'expensesRemark',
    sorter: true,
  },
  {
    title: '消费人账号',
    dataIndex: 'expensesUserId',
    sorter: true,
  },
  {
    title: '消费人姓名',
    dataIndex: 'expensesUser',
    sorter: true,
  },

];

let param = {
  expensesTime: "",
  expensesSort: "",
}

function changeType(value) {
  console.log(value.key); // { key: "lucy", label: "Lucy (101)" }
  param.expensesSort = value.key;

}


function onChangeTime(date, dateString) {
  console.log(dateString.toString());
  param.expensesTime = dateString.toString()
}


export default class FundRank extends React.Component{

  state = {
    data: [],
    pagination: {},
    loading: false,
  };

  componentDidMount() {
    this.fetch();
  }

  handleTableChange = (pagination, filters, sorter) => {
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

  fetch = (params = {}) => {
    console.log('params:', params);
    this.setState({ loading: true });
    reqwest({
      url: 'http://localhost:10010/friday/bills/userExpenses/selectAll',
      method: 'get',
      contentType: 'application/json',
      headers:{
        'Content-Type':'application/json'
      },
      data: {
        limit: 10,
        ...params
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


  searchFund(expensesSort, expensesTime) {
    param.expensesSort = expensesSort;
    param.expensesTime = expensesTime;
    this.fetch(param)
  }

  render() {
    return(
      <div>
        <Row style = {{fontSize:'14px'}}>
          <Col span={20}>
            <div style={{display : 'inline',whiteSpace:'nowrap'}}>
              <label>支出类型:</label>
              <Select
                // mode="multiple"
                placeholder={"可选类型，默认全选"}
                labelInValue
                style={{ width: 180 ,marginRight: '2.5rem', marginBottom :'1rem', whiteSpace:'nowrap' }}
                onChange={changeType}
              >
                <Option value="">全部</Option>
                <Option value="交通">交通</Option>
                <Option value="饮食">饮食</Option>
                <Option value="烟酒">烟酒</Option>
                <Option value="人情">人情</Option>
                <Option value="学习">学习</Option>
                <Option value="设备">设备</Option>
                <Option value="家居">家居</Option>
                <Option value="其他">其他</Option>
              </Select>
            </div>

            <div style={{display : 'inline',whiteSpace:'nowrap'}}>
              <label>查找时间：</label>
              <RangePicker
                onChange={onChangeTime}
                style={{ width: 180,marginRight: '2.5rem', marginBottom :'1rem', whiteSpace:'nowrap' }}
              />
            </div>

            {/*<div style={{display : 'inline',whiteSpace:'nowrap'}}>*/}
            {/*  <label>基金公司:</label>*/}
            {/*  <Select*/}
            {/*    mode="multiple"*/}
            {/*    placeholder={"可选多个，默认全选"}*/}
            {/*    labelInValue*/}
            {/*    style={{ width: 180,marginRight: '2.5rem', marginBottom :'1rem', whiteSpace:'nowrap' }}*/}
            {/*    onChange={handleChange}*/}
            {/*  >*/}
            {/*    <Option value="80000222">华夏</Option>*/}
            {/*    <Option value="80000223">嘉实</Option>*/}
            {/*    <Option value="80000229">易方达</Option>*/}
            {/*    <Option value="80000220">南方</Option>*/}
            {/*    <Option value="80048752">中银</Option>*/}
            {/*    <Option value="80000248">广发</Option>*/}
            {/*    <Option value="80064225">工银</Option>*/}
            {/*    <Option value="80000226">博时</Option>*/}
            {/*    <Option value="80000228">华安</Option>*/}
            {/*    <Option value="80053708">汇添富</Option>*/}

            {/*  </Select>*/}
            {/*</div>*/}
            <Button icon="search" type="primary" onClick={() => this.searchFund(param.expensesSort, param.expensesTime)}>Search</Button>
          </Col>

          <Col span = {4}>

          </Col>
        </Row>
        <Row style={{marginTop : '1rem',marginBottom:'1rem'}}>
          <Col span={24}>
            <Button type="primary" icon="add" style={{marginLeft :'0.3rem',float:'right'}}>
              添加
            </Button>
            <Button type="primary" icon="download" style={{marginLeft :'0.3rem',float:'right'}}>
              下载
            </Button>
          </Col>

        </Row>
        <Table
          columns={columns}
          rowKey="expensesId"
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}
        />
      </div>
    )
  }


}
