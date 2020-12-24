import React from 'react';
import {Tabs, Input, Row, Col, Card } from 'antd';

const { TabPane } = Tabs;
const { Search } = Input;

const gridStyle = {
  width: '100%',
  textAlign: 'center',
  marginBottom: '1.5rem'
};

function callback(key) {
  console.log(key);
}

export default class ExpensesIndex extends React.Component{
  render() {
    return(
      <div>
        <Search
          placeholder="input search text"
          enterButton="Search"
          size="large"
          onSearch={value => console.log(value)}
        />

        <Row style={{marginTop:'1.2rem'}}>
          <Col span={6}>
            <Card title="Card Title" hoverable={true}>
              <Card.Grid style={gridStyle}>Content</Card.Grid>
              <Card.Grid style={gridStyle}>Content</Card.Grid>
              <Card.Grid style={gridStyle}>Content</Card.Grid>
              <Card.Grid style={gridStyle}>Content</Card.Grid>
              <Card.Grid style={gridStyle}>Content</Card.Grid>
              <Card.Grid style={gridStyle}>Content</Card.Grid>
            </Card>
          </Col>

          <Col span = {18}>
            <Tabs defaultActiveKey="1" onChange={callback} style={{textAlign:'center'}}>
              <TabPane tab="今日分时数据" key="1">
                Content of Tab Pane 1
              </TabPane>
              <TabPane tab="历史股价信息" key="2">
                Content of Tab Pane 2
              </TabPane>
            </Tabs>
          </Col>
        </Row>
      </div>
    )
  }
}
