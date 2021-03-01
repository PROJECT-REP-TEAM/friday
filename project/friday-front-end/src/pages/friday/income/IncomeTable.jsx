import React from 'react';
import { Table, Input, InputNumber, Popconfirm, Form, Col, Row, DatePicker } from 'antd';
import Button from "antd/es/button";
import { connect } from 'dva';

const namespace = 'selectAll';
const data = {
  incomeId:'',

  incomeTime:'',

  incomeNum:'',

  incomeSort:'',

  incomeRemark:'',

  incomeUserId:'',

  incomeUser:'',

  offset:'',

  limit:'',
};
// for (let i = 0; i < 100; i++) {
//   data.push({
//     key: i.toString(),
//     name: `Edrward ${i}`,
//     age: 32,
//     address: `London Park no. ${i}`,
//   });
// }

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

@connect(({ Income }) => ({
  ...Income,
}))
class EditableTable extends React.Component {
  constructor(props) {
    super(props);
    this.state = { data, editingKey: '' };
    this.columns = [
      {
        title: 'incomeId',
        dataIndex: 'incomeId',
        editable: true,
      },
      {
        title: 'incomeTime',
        dataIndex: 'incomeTime',
        editable: true,
      },
      {
        title: 'incomeNum',
        dataIndex: 'incomeNum',
        editable: true,
      },
      {
        title: 'incomeSort',
        dataIndex: 'incomeSort',
        editable: true,
      },
      {
        title: 'incomeRemark',
        dataIndex: 'incomeRemark',
        editable: true,
      },
      {
        title: 'incomeUserId',
        dataIndex: 'incomeUserId',
        editable: true,
      },
      {
        title: 'incomeUser',
        dataIndex: 'incomeUser',
        editable: true,
      },

      {
        title: 'operation',
        dataIndex: 'operation',
        render: (text, record) => {
          const { editingKey } = this.state;
          const editable = this.isEditing(record);
          return editable ? (
            <span>
              <EditableContext.Consumer>
                {form => (
                  <a
                    onClick={() => this.save(form, record.key)}
                    style={{ marginRight: 8 }}
                  >
                    保存
                  </a>
                )}
              </EditableContext.Consumer>
              <Popconfirm title="Sure to cancel?" onConfirm={() => this.cancel(record.key)}>
                <a>取消</a>
              </Popconfirm>
            </span>
          ) : (
            <div>
              <Button type="primary" shape="round" icon="download" size={'default'} onClick={() => this.edit(record.key)}>
                编辑
              </Button>

              <Button type="danger" shape="round" icon="download" size={'default'} onClick={() => this.delete(record.key)}>
                删除
              </Button>

            </div>


          );
        },
      },
    ];
  }

  //传后端值
  // componentDidMount() {
  //   this.props.dispatch({
  //     type: `${namespace}/selectAll`,
  //     payload: {
  //       offset: '1',
  //       limit: '10',
  //     },
  //     callback: (data) => {
  //       if(data.state){
  //         if(data.data.length > 0) {
  //           this.setState({data  : data.data});
  //         }
  //       }
  //     }
  //   })
  // }
  // 连接models层
  componentDidMount() {
    const { dispatch } = this.props;
    dispatch({
      type: 'Income/selectAll',
      payload: {
        offset: '1',
        limit: '10',
      },
    });
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
    const { data } = this.props;
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
          dataSource={data}
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



export default class IncomeTable extends React.Component{

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
            <Button type="primary" icon="add" style={{marginLeft :'0.3rem',float:'right'}}>
              添加
            </Button>
            <Button type="primary" icon="download" style={{marginLeft :'0.3rem',float:'right'}}>
              下载
            </Button>
          </Col>

        </Row>
        <EditableFormTable />
      </div>
    )
  }
}
