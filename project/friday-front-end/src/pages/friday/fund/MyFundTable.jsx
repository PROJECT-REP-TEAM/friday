import React from 'react';
import {Tabs} from 'antd'

const TabPane = Tabs.TabPane;
const callback = (key) => {
  console.log(key);
}

export default class ExpensesIndex extends React.Component{
  render() {
    return(
      <Tabs defaultActiveKey="1" onChange={callback}>
        <TabPane tab="Tab 1" key="1">我的基金</TabPane>
        <TabPane tab="Tab 2" key="2">Content of Tab Pane 2</TabPane>
        <TabPane tab="Tab 3" key="3">Content of Tab Pane 3</TabPane>
      </Tabs>

    )
  }
}
