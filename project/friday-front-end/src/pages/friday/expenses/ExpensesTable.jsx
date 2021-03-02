import React from 'react';
import { Select ,Row ,Col , Button, Tooltip, Table} from 'antd';
import reqwest from 'reqwest';
import {hashHistory} from 'react-router';

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
  }
];

const { Option } = Select;

let param = {
  fundType: [],
  sort: "r",
  fundCompany: []
}

function changeType(value) {
  param.fundType = [];
  for (var i = 0; i < value.length; i++) {
    param.fundType.push(value[i].key)
  }
}

function changeSort(value) {
  console.log(value.key); // { key: "lucy", label: "Lucy (101)" }
  param.sort = value.key;

}

function handleChange(value) {
  console.log(value); // { key: "lucy", label: "Lucy (101)" }
  param.fundCompany = [];
  for (let i = 0; i < value.length; i++) {
    param.fundCompany.push(value[i].key);
  }
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
    console.log(pager.current);
    console.log("???????????????????????");
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
      console.log(data);
      // Read total count from server
      pagination.total = data.count;
      console.log("--------------------");
      console.log(data.count);
      console.log(data.data);
      this.setState({
        loading: false,
        data: data.data,
        pagination,
      });
    });
  };


  searchFund(sort, fundType, fundCompany) {
    param.sort = sort;
    param.fundType = fundType;
    param.fundCompany = fundCompany;
    this.fetch(param)
  }

  render() {
    return(
      <div>
        <Row style = {{fontSize:'14px'}}>
          <Col span={20}>
            <div style={{display : 'inline',whiteSpace:'nowrap'}}>
              <label>基金类型:</label>
              <Select
                mode="multiple"
                placeholder={"可选多个，默认全选"}
                labelInValue
                style={{ width: 180 ,marginRight: '2.5rem', marginBottom :'1rem', whiteSpace:'nowrap' }}
                onChange={changeType}
              >
                <Option value="gp">股票型</Option>
                <Option value="hh">混合型</Option>
                <Option value="zq">债券型</Option>
                <Option value="zs">指数型</Option>
                <Option value="qdii">QHII</Option>
                <Option value="fof">FOF</Option>
              </Select>
            </div>

            <div style={{display : 'inline',whiteSpace:'nowrap'}}>
              <label>排序方式:</label>
              <Select
                labelInValue
                placeholder={"日涨幅"}
                style={{ width: 180 ,marginRight: '2.5rem', marginBottom :'1rem', whiteSpace:'nowrap'}}
                onChange={changeSort}
              >
                <Option value="r">日涨幅</Option>
                <Option value="z">周涨幅</Option>
                <Option value="1y">近一个月</Option>
                <Option value="3y">近三个月</Option>
                <Option value="6y">近半年</Option>
                <Option value="jn">今年涨幅</Option>
                <Option value="1n">近一年</Option>

              </Select>
            </div>

            <div style={{display : 'inline',whiteSpace:'nowrap'}}>
              <label>基金公司:</label>
              <Select
                mode="multiple"
                placeholder={"可选多个，默认全选"}
                labelInValue
                style={{ width: 180,marginRight: '2.5rem', marginBottom :'1rem', whiteSpace:'nowrap' }}
                onChange={handleChange}
              >
                <Option value="80000222">华夏</Option>
                <Option value="80000223">嘉实</Option>
                <Option value="80000229">易方达</Option>
                <Option value="80000220">南方</Option>
                <Option value="80048752">中银</Option>
                <Option value="80000248">广发</Option>
                <Option value="80064225">工银</Option>
                <Option value="80000226">博时</Option>
                <Option value="80000228">华安</Option>
                <Option value="80053708">汇添富</Option>

              </Select>
            </div>
            <Button icon="search" type="primary" onClick={() => this.searchFund(param.sort, param.fundType,param.fundCompany)}>Search</Button>
          </Col>

          <Col span = {4}>
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
