import React from 'react';
import { Select ,Row ,Col , Button, Tooltip, Table} from 'antd';
import reqwest from 'reqwest';

const columns = [
  {
    title: 'Name',
    dataIndex: 'name',
    sorter: true,
    render: name => `${name.first} ${name.last}`,
    width: '20%',
  },
  {
    title: 'Gender',
    dataIndex: 'gender',
    filters: [{ text: 'Male', value: 'male' }, { text: 'Female', value: 'female' }],
    width: '20%',
  },
  {
    title: 'Email',
    dataIndex: 'email',
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
      results: pagination.pageSize,
      page: pagination.current,
      sortField: sorter.field,
      sortOrder: sorter.order,
      ...filters,
    });
  };

  fetch = (params = {}) => {
    console.log('params:', params);
    this.setState({ loading: true });
    reqwest({
      url: 'https://randomuser.me/api',
      method: 'get',
      data: {
        results: 10,
        ...params,
      },
      type: 'json',
    }).then(data => {
      const pagination = { ...this.state.pagination };
      // Read total count from server
      // pagination.total = data.totalCount;
      pagination.total = 200;
      this.setState({
        loading: false,
        data: data.results,
        pagination,
      });
    });
  };


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
          rowKey={record => record.login.uuid}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}
        />
      </div>
    )
  }
}
