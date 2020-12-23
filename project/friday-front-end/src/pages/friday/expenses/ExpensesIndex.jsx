import React from 'react';
import { PageHeader, Button, Descriptions,Statistic, Card,Popover,Tag} from 'antd';
import { ArrowUpOutlined, ArrowDownOutlined } from '@ant-design/icons';


export default class ExpensesIndex extends React.Component{

  state = {
    visible: false,
  };

  hide = () => {
    this.setState({
      visible: false,
    });
  };

  handleVisibleChange = visible => {
    this.setState({ visible });
  };

  render() {
    return(
      <div
        style={{
          backgroundColor: '#F5F5F5',
          padding: 0,
        }}
      >
        <PageHeader
          ghost={false}
          title="您好！ Ezer Wu"
          subTitle="下面是您的支出情况"
          extra={<Popover
            content={
              <span>
                <Tag color="red">{"总额：" + 1000+100000}</Tag>
              </span>

              }
            title="本年支出"
            trigger="click"
            visible={this.state.visible}
            onVisibleChange={this.handleVisibleChange}
          >
            <Button type="primary">查看本年</Button>
          </Popover>}
        >
          <Descriptions size="small" column={3}>
            <Card>
              <Descriptions.Item label="上升率">
              <Statistic
                value={11.28}
                precision={2}
                valueStyle={{ color: '#3f8600' }}
                prefix={<ArrowUpOutlined />}
                suffix="%"
              />
              </Descriptions.Item>
            </Card>
            <Descriptions.Item label="作日支出"><a>1000</a></Descriptions.Item>
            <Descriptions.Item label="本月支出">
              <a>421421</a>
            </Descriptions.Item>
            <Descriptions.Item label="统计时间">20201223</Descriptions.Item>
            <Descriptions.Item label="较大支出分类">
              Gonghu Road, Xihu District, Hangzhou, Zhejiang, China
            </Descriptions.Item>
          </Descriptions>
        </PageHeader>


      </div>

    )
  }
}
