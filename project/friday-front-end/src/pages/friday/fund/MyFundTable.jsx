import React from 'react';
import { Table, Input, InputNumber, Popconfirm, Form, Col, Row, DatePicker } from 'antd';
import Button from "antd/es/button";


const data = [];
for (let i = 0; i < 100; i++) {
  data.push({
    key: i.toString(),
    name: `Edrward ${i}`,
    age: 32,
    address: `London Park no. ${i}`,
  });
}

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

class EditableTable extends React.Component {
  constructor(props) {
    super(props);
    let params = new URLSearchParams(document.location.search.substring(1));
    let code = params.get("code") != null ? params.get("code") : null;
    let visit = params.get("visit") != null ? params.get("visit") : null;
    let type = params.get("type") != null ? params.get("type") : null;
    let netWorth = params.get("netWorth") != null ? params.get("netWorth") : null;
    let dayGrowth = params.get("dayGrowth") != null ? params.get("dayGrowth") : null;
    console.log(code);
    console.log(visit);
    console.log(type);
    console.log(netWorth);
    console.log(dayGrowth);

    this.state = {
      data,
      editingKey: '' ,
      code: code,
      visit: visit,
      type: type,
      netWorth: netWorth,
      dayGrowth: dayGrowth
    };
    this.columns = [
      {
        title: '序号',
        dataIndex: 'fundId',
      },
      {
        title: '基金代码',
        dataIndex: 'fundCode',
      },
      {
        title: '基金名称',
        dataIndex: 'fundName',
      },
      {
        title: '基金类型',
        dataIndex: 'fundType',
      },
      {
        title: '单位净值',
        dataIndex: 'netWorth',
      },
      {
        title: '原始买入费率',
        dataIndex: 'buySourceRate',
      },
      {
        title: '当前买入费率',
        dataIndex: 'buyRate',
      },
      {
        title: '基金经理',
        dataIndex: 'manager',
      },{
        title: '每万分收益(货币基金)',
        dataIndex: 'millionCopiesIncome',
      },
      {
        title: '操作',
        dataIndex: 'operation',
        render: (text, record) => {
          const { editingKey } = this.state;
          return (
            <div>
              <Button type="danger" shape="round" icon="delete" size={'default'} onClick={() => this.delete(record.key)}>
                删除
              </Button>
            </div>
          );
        },
      },
    ];
  }

  isEditing = record => record.key === this.state.editingKey;

  cancel = () => {
    this.setState({ editingKey: '' });
  };

  save(form, key) {
    form.validateFields((error, row) => {
      if (error) {
        return;
      }
      const newData = [...this.state.data];
      const index = newData.findIndex(item => key === item.key);
      if (index > -1) {
        const item = newData[index];
        newData.splice(index, 1, {
          ...item,
          ...row,
        });
        this.setState({ data: newData, editingKey: '' });
      } else {
        newData.push(row);
        this.setState({ data: newData, editingKey: '' });
      }
    });
  }

  edit(key) {
    this.setState({ editingKey: key });
  }

  delete(key) {
    this.setState({deleteKey : key});
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
          rowClassName="editable-row"
          pagination={{
            onChange: this.cancel,
          }}
        />
      </EditableContext.Provider>
    );
  }
}


const EditableFormTable = Form.create()(EditableTable);
const InputGroup = Input.Group;
const {  RangePicker } = DatePicker;
function onChange(date, dateString) {
  console.log(date, dateString);
}
export default class MyFundTable extends React.Component{

  render() {
    return(
      <div>
        <Row style={{marginTop : '1rem',marginBottom:'1rem'}}>
          <Col span={5}>
          </Col>

          <Col span={12}>
            <InputGroup compact>
              <Input style={{ width: '50%' }}  />
              <RangePicker onChange={onChange} style={{ width: '50%' }} />
            </InputGroup>
          </Col>
          <Col span={7}>
            <Button type="primary" shape="circle" icon="search" />
          </Col>
        </Row>


        <Row style={{marginTop : '1rem',marginBottom:'1rem'}}>
          <Col span={24}>
            <Button type="primary" icon="download" style={{marginLeft :'0.3rem',float:'right'}}>
              导出当前收藏
            </Button>
          </Col>

        </Row>
        <EditableFormTable />
      </div>
    )
  }
}
