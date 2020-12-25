import React from 'react';
import { Select ,Row ,Col , Button, Tooltip, Table} from 'antd';
import reqwest from 'reqwest';

const columns = [
  {
    title: '股票代码',
    dataIndex: 'code',
    sorter: true,
  },
  {
    title: '股票名称',
    dataIndex: 'name',
    sorter: true,
  },
  {
    title: '当前价格',
    dataIndex: 'price',
    sorter: true,
  },{
    title: '涨幅',
    dataIndex: 'changePercent',
    sorter: true,
  },{
    title: '买入价格',
    dataIndex: 'buy',
    sorter: true,
  },{
    title: '卖出价格',
    dataIndex: 'shell',
    sorter: true,
  },{
    title: '最高价格',
    dataIndex: '最低价格',
    sorter: true,
  },{
    title: '成交量',
    dataIndex: 'volume',
    sorter: true,
  },{
    title: '成交额',
    dataIndex: 'turnover',
    sorter: true,
  },
];

const { Option } = Select;

function handleChange(value) {
  console.log(value); // { key: "lucy", label: "Lucy (101)" }
}

export default class ExpensesIndex extends React.Component{

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
      pageSize: pagination.pageSize,
      pageIndex: pagination.current,
      sort: sorter.field,
      ...filters,
    });
  };

  fetch = (params = {}) => {
    console.log('params:', params);
    this.setState({ loading: true });
    reqwest({
      url: 'https://api.doctorxiong.club/v1/stock/rank',
      method: 'post',
      contentType: 'application/json',
      headers:{
        'Content-Type':'application/json'
      },
      data: JSON.stringify(
        {
          pageSize: 10,
          sort: 'turnover',
          ...params,
        }
      ),
      type: 'json',
    }).then(data => {
      const pagination = { ...this.state.pagination };
      // Read total count from server
      // pagination.total = data.totalCount;
      pagination.total = data.data.allPages;
      this.setState({
        loading: false,
        data: data.data.rank,
        pagination,
      });
    });
  };


  click(record,rowkey){

    console.log(record);

    console.log(rowkey);
  }


  render() {
    return(
      <div>
        <Row style = {{fontSize:'14px'}}>
          <Col span={20}>
            <div style={{display : 'inline',whiteSpace:'nowrap'}}>
            <label>股票类型:</label>
            <Select
              labelInValue
              defaultValue={{ key: 'lucy' }}
              style={{ width: 180 ,marginRight: '2.5rem', marginBottom :'1rem', whiteSpace:'nowrap' }}
              onChange={handleChange}
            >
              <Option value="jack">Jack (100)</Option>
              <Option value="lucy">Lucy (101)</Option>
            </Select>
            </div>

            <div style={{display : 'inline',whiteSpace:'nowrap'}}>
              <label>排序方式:</label>
            <Select
              labelInValue
              defaultValue={{ key: 'lucy' }}
              style={{ width: 180 ,marginRight: '2.5rem', marginBottom :'1rem', whiteSpace:'nowrap'}}
              onChange={handleChange}
            >
              <Option value="jack">Jack (100)</Option>
              <Option value="lucy">Lucy (101)</Option>
            </Select>
            </div>

            <div style={{display : 'inline',whiteSpace:'nowrap'}}>
            <label>板块行业:</label>
            <Select
              labelInValue
              defaultValue={{ key: 'lucy' }}
              style={{ width: 180,marginRight: '2.5rem', marginBottom :'1rem', whiteSpace:'nowrap' }}
              onChange={handleChange}
            >
              <Option value="jack">Jack (100)</Option>
              <Option value="lucy">Lucy (101)</Option>
            </Select>
            </div>
            <Button icon="search" type="primary">Search</Button>
          </Col>

          <Col span = {4}>
          </Col>
        </Row>

        <Table
          columns={columns}
          rowKey="code"
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}
          onRow={(record,rowkey)=>{
            return{
              onClick : this.click.bind(this,record,rowkey)    //点击行 record 指的本行的数据内容，rowkey指的是本行的索引
            }
          }}
        />
      </div>
    )
  }
}
