import React from 'react';
import {
  Select,
  Row,
  Col,
  Button,
  Tooltip,
  Table,
  DatePicker,
  InputNumber,
  Input,
  Form,
  Popconfirm,
} from 'antd';
import reqwest from 'reqwest';

const { Option } = Select;
const { RangePicker } = DatePicker;

const EditableContext = React.createContext();

class EditableCell extends React.Component {
  getInput = () => {
    if (this.props.inputType === 'number') {
      return <InputNumber />;
    }
    return <Input />;
  };

  renderCell = ({ getFieldDecorator }) => {
    const {
      editing,
      dataIndex,
      title,
      inputType,
      record,
      index,
      children,
      ...restProps
    } = this.props;
    return (
      <td {...restProps}>
        {editing ? (
          <Form.Item style={{ margin: 0 }}>
            {getFieldDecorator(dataIndex, {
              rules: [
                {
                  required: true,
                  message: `Please Input ${title}!`,
                },
              ],
              initialValue: record[dataIndex],
            })(this.getInput())}
          </Form.Item>
        ) : (
          children
        )}
      </td>
    );
  };

  render() {
    return <EditableContext.Consumer>{this.renderCell}</EditableContext.Consumer>;
  }
}

// 初始化值
let param = {
  expensesTime: '',
  expensesSort: '',
};

let data = [];
// 选择分类
function changeType(value) {
  console.log(value.key); // { key: "lucy", label: "Lucy (101)" }
  param.expensesSort = value.key;
}

// 选择时间段
function onChangeTime(date, dateString) {
  console.log(dateString.toString());
  param.expensesTime = dateString.toString();
}

class EditableTableExpenses extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      editingKey: '',
      data: data,
      pagination: {},
      loading: false,
    };
    this.columns = [
      {
        title: '消费id',
        dataIndex: 'expensesId',
        editable: true,
      },
      {
        title: '消费时间',
        dataIndex: 'expensesTime',
        editable: true,
      },
      {
        title: '消费金额',
        dataIndex: 'expensesNum',
        editable: true,
      },
      {
        title: '消费类型',
        dataIndex: 'expensesSort',
        editable: true,
      },
      {
        title: '备注',
        dataIndex: 'expensesRemark',
        editable: true,
      },
      {
        title: '消费人账号',
        dataIndex: 'expensesUserId',
        editable: true,
      },
      {
        title: '消费人姓名',
        dataIndex: 'expensesUser',
        editable: true,
      },

      {
        title: '操作',
        dataIndex: 'operation',
        render: (text, record) => {
          const { editingKey } = this.state;
          const editable = this.isEditing(record);
          return editable ? (
            <span>
              <EditableContext.Consumer>
                {form => (
                  <a onClick={() => this.save(form, record.expensesId)} style={{ marginRight: 8 }}>
                    保存
                  </a>
                )}
              </EditableContext.Consumer>
              <Popconfirm title="Sure to cancel?" onConfirm={() => this.cancel(record.expensesId)}>
                <a>取消</a>
              </Popconfirm>
            </span>
          ) : (
            <div>
              <Button
                type="primary"
                shape="round"
                icon="download"
                size={'default'}
                onClick={() => this.edit(record.expensesId)}
              >
                编辑
              </Button>

              <Button
                type="danger"
                shape="round"
                icon="download"
                size={'default'}
                onClick={() => this.delete(record.expensesId)}
              >
                删除
              </Button>
            </div>
          );
        },
        width: '20%',
      },
    ];
  }
  // 初始化表格数据
  componentDidMount() {
    this.fetch();
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
      url: 'http://localhost:10010/friday/bills/userExpenses/selectAll',
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

  // 搜索
  searchFund(expensesSort, expensesTime) {
    param.expensesSort = expensesSort;
    param.expensesTime = expensesTime;
    this.fetch(param);
  }

  isEditing = record => record.expensesId === this.state.editingKey;

  cancel = () => {
    this.setState({ editingKey: '' });
  };

  save(form, key) {
    location.reload();
    // form.validateFields((error, row) => {
    //   if (error) {
    //     return;
    //   }
    //   const newData = [...this.state.data];
    //   const index = newData.findIndex(item => key === item.key);
    //   if (index > -1) {
    //     const item = newData[index];
    //     newData.splice(index, 1, {
    //       ...item,
    //       ...row,
    //     });
    //     this.setState({ data: newData, editingKey: '' });
    //   } else {
    //     newData.push(row);
    //     this.setState({ data: newData, editingKey: '' });
    //   }
    // });
  }

  edit(key) {
    this.setState({ editingKey: key });
  }

  delete(key) {
    this.setState({ deleteKey: key });
  }

  render() {
    const components = {
      body: {
        cell: EditableCell,
      },
    };

    const columns = this.columns.map(col => {
      if (!col.editable) {
        return col;
      }
      return {
        ...col,
        onCell: record => ({
          record,
          inputType: col.dataIndex === 'age' ? 'number' : 'text',
          dataIndex: col.dataIndex,
          title: col.title,
          editing: this.isEditing(record),
        }),
      };
    });

    return (
      <EditableContext.Provider value={this.props.form}>
        <Table
          components={components}
          bordered
          dataSource={this.state.data}
          columns={columns}
          rowKey="expensesId"
          rowClassName="editable-row"
          onChange={this.handleTableChange}
          pagination={this.state.pagination}
          loading={this.state.loading}
        />
      </EditableContext.Provider>
    );
  }
}

const EditableCellForm = Form.create()(EditableTableExpenses);

export default class ExpensesTable extends React.Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
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

            <div style={{ display: 'inline', whiteSpace: 'nowrap' }}>
              <label>查找时间：</label>
              <RangePicker
                onChange={onChangeTime}
                style={{
                  width: 180,
                  marginRight: '2.5rem',
                  marginBottom: '1rem',
                  whiteSpace: 'nowrap',
                }}
              />
            </div>
            <Button
              icon="search"
              type="primary"
              onClick={() => this.searchFund(param.expensesSort, param.expensesTime)}
            >
              Search
            </Button>
          </Col>

          <Col span={4}></Col>
        </Row>
        <Row style={{ marginTop: '1rem', marginBottom: '1rem' }}>
          <Col span={24}>
            <Button type="primary" icon="add" style={{ marginLeft: '0.3rem', float: 'right' }}>
              添加
            </Button>
            <Button type="primary" icon="download" style={{ marginLeft: '0.3rem', float: 'right' }}>
              下载
            </Button>
          </Col>
        </Row>
        <EditableCellForm />
      </div>
    );
  }
}
